package PGPTEST;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Iterator;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.bc.BcPGPObjectFactory;
import org.bouncycastle.openpgp.bc.BcPGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.bc.BcPGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.PublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.PublicKeyKeyEncryptionMethodGenerator;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.bc.BcPGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyKeyEncryptionMethodGenerator;


public class PGPTools {
	private PGPTools(){}
	
	public static PGPPublicKey readPublicKey(String filename) throws IOException, PGPException{
		return readPublicKey(new FileInputStream(new File(filename)));
	}
	
	public static PGPPublicKey readPublicKey(InputStream in) throws IOException, PGPException{
		InputStream is = PGPUtil.getDecoderStream(in);
		PGPPublicKeyRingCollection pgpPub = new BcPGPPublicKeyRingCollection(is);
		
		PGPPublicKey pubkey = null;
		
		Iterator<PGPPublicKeyRing> rIt = pgpPub.getKeyRings();
		while(pubkey == null && rIt.hasNext()){
			PGPPublicKeyRing kRing = rIt.next();
			Iterator<PGPPublicKey> kIt = kRing.getPublicKeys();
			while(pubkey == null && kIt.hasNext()){
				PGPPublicKey k = kIt.next();
				if(k.isEncryptionKey()){
					pubkey = k;
				}
			}
		}
		if(pubkey == null) throw new IllegalArgumentException("Can't find encryption key");
		return pubkey;
	}
	
	public static PGPPrivateKey findPrivateKey(String filename, long keyID, char[] pass) throws IOException, PGPException, NoSuchProviderException{
		return findPrivateKey(new FileInputStream(filename), keyID, pass);
	}
	
	public static PGPPrivateKey findPrivateKey(InputStream keyIn, long keyID, char[] pass) throws IOException, PGPException,NoSuchProviderException{
		PGPSecretKeyRingCollection pgpSec = new BcPGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn));
		PGPSecretKey secKey = pgpSec.getSecretKey(keyID);
		if(secKey == null) throw new IllegalArgumentException("Can't find secret key");
		
		PBESecretKeyDecryptor pkd = new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(pass);
		return secKey.extractPrivateKey(pkd);
	}	
	public static PGPSecretKey readSecretKey(String filename) throws Exception{
		InputStream keyIn = new BufferedInputStream(new FileInputStream(filename));
		PGPSecretKey secKey = readSecretKey(keyIn);
	    keyIn.close();
	    return secKey;
	}
	public static PGPSecretKey readSecretKey(InputStream input)throws Exception{
        PGPSecretKeyRingCollection pgpSec = new BcPGPSecretKeyRingCollection(
                PGPUtil.getDecoderStream(input));
        Iterator<PGPSecretKeyRing> keyRingIter = pgpSec.getKeyRings();
        while (keyRingIter.hasNext()){
            PGPSecretKeyRing keyRing = (PGPSecretKeyRing)keyRingIter.next();
            Iterator<PGPSecretKey> keyIter = keyRing.getSecretKeys();
            while (keyIter.hasNext()){
                PGPSecretKey key = (PGPSecretKey)keyIter.next();
                if (key.isSigningKey())
                    return key;
            }
        }
        throw new IllegalArgumentException("Can't find signing key in key ring.");
	}

	/*public static OutputStream getEncryptOutputStream(OutputStream out, PGPPublicKey pubKey,
			boolean armor, boolean withIntegrityCheck)throws Exception{

		if(armor) out=new ArmoredOutputStream(out);
		PGPDataEncryptorBuilder builder = new BcPGPDataEncryptorBuilder(PGPEncryptedData.AES_256)
				.setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom());
		PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(builder);
		PublicKeyKeyEncryptionMethodGenerator gen = new BcPublicKeyKeyEncryptionMethodGenerator(pubKey)
				.setSecureRandom(new SecureRandom());
		cPk.addMethod(gen);
		OutputStream cOut = cPk.open(out, new byte[1<<16]);
		return cOut;
	}
	
	public static OutputStream getCompressOutputStream(OutputStream out, int algorithm)throws Exception{
		PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(algorithm);
		return comData.open(out);
	}
	
	public static void encryptTest(String outputFilename, String filename, String pubkeyPath,
			boolean armor, boolean withIntegrityCheck)throws Exception{
		OutputStream out = new FileOutputStream(outputFilename);
		PGPPublicKey pubKey = readPublicKey(pubkeyPath);
		OutputStream o = getEncryptOutputStream(out, pubKey, armor, withIntegrityCheck);
		PGPUtil.writeFileToLiteralData(o, PGPLiteralData.BINARY, new File(filename));
		o.flush();
		out.flush();
		o.close();
		out.close();
	}*/
	
	public static boolean encryptFile(String outputFilename, String filename, String pubkeyPath,
			boolean armor, boolean withIntegrityCheck)throws Exception{
		return encryptFile(new FileOutputStream(outputFilename), filename, readPublicKey(pubkeyPath), armor, withIntegrityCheck);
	}
	
	
	public static boolean encryptFile(OutputStream out, String filename, PGPPublicKey pubKey, 
			boolean armor, boolean withIntegrityCheck)throws Exception{
		if(armor) out=new ArmoredOutputStream(out);
		
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(PGPCompressedData.ZIP);
		PGPUtil.writeFileToLiteralData(comData.open(bOut), PGPLiteralData.BINARY, new File(filename));
		comData.close();
		PGPDataEncryptorBuilder builder = new BcPGPDataEncryptorBuilder(PGPEncryptedData.AES_256)
				.setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom());
		PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(builder);
		PublicKeyKeyEncryptionMethodGenerator gen = new BcPublicKeyKeyEncryptionMethodGenerator(pubKey)
				.setSecureRandom(new SecureRandom());
		cPk.addMethod(gen);
		byte[] bytes = bOut.toByteArray();
		bOut.close();
		OutputStream cOut = cPk.open(out, bytes.length);
		cOut.write(bytes);
		cOut.flush();
		cOut.close();
		out.close();
		return true;
	}

	
	private static class DecryptData{
		public InputStream is;
		public PGPPublicKeyEncryptedData pbe;
		private DecryptData(InputStream is, PGPPublicKeyEncryptedData pbe){
			this.is = is;
			this.pbe = pbe;
		}
	}
	public static DecryptData getDecryptedStream(InputStream is, InputStream keyIn, char[] passwd)throws Exception{
		InputStream in = PGPUtil.getDecoderStream(is);
		PGPObjectFactory pgpF = new BcPGPObjectFactory(in);
		PGPEncryptedDataList enc = null;
		Object o;
		while((o=pgpF.nextObject()) != null){
			if(o instanceof PGPEncryptedDataList){
				enc = (PGPEncryptedDataList) o;
				break;
			}
		}
		if(enc == null) throw new IllegalArgumentException("There's no encrypted content in ");
		Iterator<PGPPublicKeyEncryptedData> it = enc.getEncryptedDataObjects();
		PGPPrivateKey priKey = null; 
		PGPPublicKeyEncryptedData pbe = null;
		while(priKey==null && it.hasNext()){
			pbe = it.next();
			priKey = findPrivateKey(keyIn, pbe.getKeyID(), passwd);
		}
		if(priKey == null){
			throw new IllegalArgumentException("Not a Encrypt file or Private key not match");
		}
		
		PublicKeyDataDecryptorFactory pdf = new BcPublicKeyDataDecryptorFactory(priKey);
		InputStream clear = pbe.getDataStream(pdf);
		return new DecryptData(clear, pbe);
	}
	
	public static boolean decryptFile(String inputFilePath, String outputFilePath, String priKeyFilePath, char[] passwd)throws Exception{
		return decryptFile(new FileInputStream(inputFilePath), new FileOutputStream(outputFilePath), new FileInputStream(priKeyFilePath), passwd);
	}

	public static boolean decryptFile(InputStream is, OutputStream os, InputStream keyIn, char[] passwd) throws Exception{
		/*InputStream in = PGPUtil.getDecoderStream(is);
		PGPObjectFactory pgpF = new BcPGPObjectFactory(in);
		PGPEncryptedDataList enc = null;
		Object o;
		while((o=pgpF.nextObject()) != null){
			if(o instanceof PGPEncryptedDataList){
				enc = (PGPEncryptedDataList) o;
				break;
			}
		}
		if(enc == null) throw new IllegalArgumentException("There's no encrypted content in ");
		Iterator<PGPPublicKeyEncryptedData> it = enc.getEncryptedDataObjects();
		PGPPrivateKey priKey = null; 
		PGPPublicKeyEncryptedData pbe = null;
		//In this point, it shows a chaos data form in encrypted data storage.
		while(priKey==null && it.hasNext()){
			pbe = it.next();
			priKey = findPrivateKey(keyIn, pbe.getKeyID(), passwd);
		}
		if(priKey == null){
			throw new IllegalArgumentException("Not a Encrypt file or Private key not match");
		}
		
		PublicKeyDataDecryptorFactory pdf = new BcPublicKeyDataDecryptorFactory(priKey);
		InputStream clear = pbe.getDataStream(pdf);*/

		DecryptData dd = getDecryptedStream(is, keyIn, passwd);
		InputStream clear = dd.is;
		PGPPublicKeyEncryptedData pbe = dd.pbe;
		PGPObjectFactory plainFact = new BcPGPObjectFactory(clear);
		System.out.println("before getData");
		PGPLiteralData message = getLiteralData(plainFact);
		InputStream unc = message.getInputStream();
		int ch;
		while ((ch = unc.read()) >= 0) {
			os.write(ch);
		}
		os.flush();
		os.close();
		if (pbe.isIntegrityProtected()) {
            if (!pbe.verify()) {
                throw new PGPException("Message failed integrity check");
            }
        }
		unc.close();
		is.close();
        return true;
	}
	
	private static PGPLiteralData getLiteralData(PGPObjectFactory fac) throws Exception{
		Object message = null;
        if((message = fac.nextObject()) instanceof PGPCompressedData) {
            PGPCompressedData cData = (PGPCompressedData) message;
            fac = new BcPGPObjectFactory(cData.getDataStream());
        }
        System.out.println("before where" + message.getClass());
        while(!(message instanceof PGPLiteralData)){
        	message = fac.nextObject();
        }
        System.out.println("before return");
        if(message != null){
        	return (PGPLiteralData) message;
        }
        throw new Exception("Can't find Encrypted content!");
	}
	
	
	public static void signFile(String filename, InputStream keyIn, OutputStream out, char[] passwd, boolean armor)throws Exception{
		if(armor) out = new ArmoredOutputStream(out);
		PGPSecretKey  secKey = readSecretKey(keyIn);
		PGPPrivateKey priKey = secKey.extractPrivateKey(new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(passwd));
		PGPSignatureGenerator sGen = new PGPSignatureGenerator(new BcPGPContentSignerBuilder(secKey.getPublicKey().getAlgorithm(),PGPUtil.SHA256));
		sGen.init(PGPSignature.BINARY_DOCUMENT,priKey);
		Iterator<String> it = secKey.getPublicKey().getUserIDs(); 
	    if (it.hasNext()){
	    	PGPSignatureSubpacketGenerator  spGen = new PGPSignatureSubpacketGenerator();
	        spGen.setSignerUserID(false, it.next());
	        sGen.setHashedSubpackets(spGen.generate());
	    }	
	    BCPGOutputStream bOut = new BCPGOutputStream(out);
	    sGen.generateOnePassVersion(false).encode(bOut);
	    
	    File file = new File(filename);
        PGPLiteralDataGenerator lGen = new PGPLiteralDataGenerator();
        OutputStream lOut = lGen.open(bOut, PGPLiteralData.BINARY, file);
        FileInputStream fIn = new FileInputStream(file);
        int ch;
        while((ch=fIn.read())>=0){
        	lOut.write(ch);
        	sGen.update((byte)ch);
        }
        lGen.close();
        sGen.generate().encode(bOut);
        if(armor)
        	out.close();
        fIn.close();

	}


	
	public static boolean verifyFile(String filename, String outputPath, String pubKeyPath)throws Exception{
		return verifyFile(new FileInputStream(filename), new FileOutputStream(outputPath),  new FileInputStream(pubKeyPath));
	}
	
	public static boolean verifyFile(String filename, String pubKeyPath)throws Exception{
		InputStream is = new FileInputStream(filename + ".sig");
		InputStream fin = new FileInputStream(filename);
		InputStream in = PGPUtil.getDecoderStream(is);
		PGPObjectFactory fac = new BcPGPObjectFactory(in);
		Object message = fac.nextObject();

		if(message instanceof PGPSignatureList){
			PGPSignature sig = ((PGPSignatureList) message).get(0);
			PGPPublicKeyRingCollection pgpRing = new BcPGPPublicKeyRingCollection(PGPUtil.getDecoderStream(new FileInputStream(pubKeyPath)));
			PGPPublicKey pubKey = pgpRing.getPublicKey(sig.getKeyID());
			sig.init(new BcPGPContentVerifierBuilderProvider(), pubKey);
			int size = fin.available();
			byte[] b = new byte[size];
			fin.read(b, 0, size);
			fin.close();
			sig.update(b);
			System.out.println("ok");
			return sig.verify();
		}
		throw new Exception("not good file");
	}
	public static boolean verifyFile(InputStream is, OutputStream os, InputStream keyIn) throws Exception{
		InputStream in = PGPUtil.getDecoderStream(is);
		PGPObjectFactory fac = new BcPGPObjectFactory(in);
		Object message = fac.nextObject();
		//System.out.println(message.getClass());
		//System.out.println(fac.nextObject().getClass());
		if(message instanceof PGPCompressedData){
			fac = new BcPGPObjectFactory(((PGPCompressedData) message).getDataStream());
			message = fac.nextObject();
		}else if(message instanceof PGPSignatureList){
			throw new Exception("Can't verigy it without origin file!");
		}else if(!(message instanceof PGPOnePassSignatureList)){
			throw new Exception("Not a signature file");
		}
		PGPOnePassSignatureList p1 = (PGPOnePassSignatureList) message;
		PGPOnePassSignature ops = p1.get(0);
		PGPLiteralData p2 = (PGPLiteralData) fac.nextObject();
		InputStream dIn = p2.getInputStream();
		int ch;
		PGPPublicKeyRingCollection pgpRing = new BcPGPPublicKeyRingCollection(PGPUtil.getDecoderStream(keyIn));
		
		PGPPublicKey pubKey = pgpRing.getPublicKey(ops.getKeyID());
		
		ops.init(new BcPGPContentVerifierBuilderProvider(), pubKey);
		while((ch=dIn.read()) >= 0){
			ops.update((byte)ch);
			os.write(ch);
		}
		os.flush();
		os.close();
		is.close();
		PGPSignatureList p3 = (PGPSignatureList) fac.nextObject();
		return ops.verify(p3.get(0));
	}
	
	
	
	
	public static boolean decryptAndVerify(String inputFilePath, String outputFilePath, String priKeyFilePath, String pubKeyPath, char[] passwd)throws Exception{
		return decryptAndVerify(new FileInputStream(inputFilePath), new FileOutputStream(outputFilePath),
					new FileInputStream(priKeyFilePath), new FileInputStream(pubKeyPath), passwd);
	}
	public static boolean decryptAndVerify(InputStream is, OutputStream os, InputStream priKeyIn, InputStream pubKeyIn, char[] passwd) throws Exception{
		/*InputStream in = PGPUtil.getDecoderStream(is);
		PGPObjectFactory pgpF = new BcPGPObjectFactory(in);
		PGPEncryptedDataList enc = null;
		Object o;
		while((o=pgpF.nextObject()) != null){
			if(o instanceof PGPEncryptedDataList){
				enc = (PGPEncryptedDataList) o;
				break;
			}
		}	
		if(enc == null) throw new IllegalArgumentException("There's no encrypted content in ");
		Iterator<PGPPublicKeyEncryptedData> it = enc.getEncryptedDataObjects();
		PGPPrivateKey priKey = null; 
		PGPPublicKeyEncryptedData pbe = null;
		//In this point, it shows a chaos data form in encrypted data storage.
		while(priKey==null && it.hasNext()){
			pbe = it.next();
			priKey = findPrivateKey(priKeyIn, pbe.getKeyID(), passwd);
		}
		if(priKey == null){
			throw new IllegalArgumentException("Not a Encrypt file or Private key not match");
		}
		
		PublicKeyDataDecryptorFactory pdf = new BcPublicKeyDataDecryptorFactory(priKey);
		InputStream clear = pbe.getDataStream(pdf);*/
		DecryptData dd = getDecryptedStream(is, priKeyIn, passwd);
		InputStream clear = dd.is;
	    /*PGPObjectFactory plainFact = new BcPGPObjectFactory(clear);	
		Object message = null;
        if((message = plainFact.nextObject()) instanceof PGPCompressedData) {
            PGPCompressedData cData = (PGPCompressedData) message;
            plainFact = new BcPGPObjectFactory(cData.getDataStream());
        }
        if((message = plainFact.nextObject()) instanceof PGPOnePassSignatureList){
        	PGPOnePassSignatureList p1 = (PGPOnePassSignatureList) message;
    		PGPOnePassSignature ops = p1.get(0);
    		PGPLiteralData p2 = (PGPLiteralData) plainFact.nextObject();
    		InputStream dIn = p2.getInputStream();
    		int ch;
    		PGPPublicKeyRingCollection pgpRing = new BcPGPPublicKeyRingCollection(PGPUtil.getDecoderStream(pubKeyIn));
    		
    		PGPPublicKey pubKey = pgpRing.getPublicKey(ops.getKeyID());
    		
    		ops.init(new BcPGPContentVerifierBuilderProvider(), pubKey);
    		while((ch=dIn.read()) >= 0){
    			ops.update((byte)ch);
    			os.write(ch);
    		}
    		os.close();
    		PGPSignatureList p3 = (PGPSignatureList) plainFact.nextObject();
    		return ops.verify(p3.get(0));
    	}*/
		boolean result = verifyFile(clear, os, pubKeyIn);
		clear.close();
		os.close();
		is.close();
		return result;
	}
	public static void signAndEncrypt(OutputStream os,  String filename, PGPPublicKey pubKey, 
			PGPPrivateKey priKey, boolean armor, boolean withIntegrityCheck)throws Exception{

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		if(armor) os = new ArmoredOutputStream(os);
		PGPSignatureGenerator sGen = new PGPSignatureGenerator(new BcPGPContentSignerBuilder(pubKey.getAlgorithm(),PGPUtil.SHA256));
		sGen.init(PGPSignature.BINARY_DOCUMENT,priKey);
		Iterator<String> it = pubKey.getUserIDs(); 
	    if (it.hasNext()){
	    	PGPSignatureSubpacketGenerator  spGen = new PGPSignatureSubpacketGenerator();
	        spGen.setSignerUserID(false, it.next());
	        sGen.setHashedSubpackets(spGen.generate());
	    }	
	    PGPCompressedDataGenerator cGen = new PGPCompressedDataGenerator(PGPCompressedData.ZLIB);
	    BCPGOutputStream bOut = new BCPGOutputStream(cGen.open(out));
	    sGen.generateOnePassVersion(false).encode(bOut);
	    
	    File file = new File(filename);
        PGPLiteralDataGenerator lGen = new PGPLiteralDataGenerator();
        OutputStream lOut = lGen.open(bOut, PGPLiteralData.BINARY, file);
        FileInputStream fIn = new FileInputStream(file);
        int ch;
        while((ch=fIn.read())>=0){
        	lOut.write(ch);
        	sGen.update((byte)ch);
        }
        lGen.close();
        sGen.generate().encode(bOut);
		bOut.close();
        fIn.close();
        byte[] bytes = out.toByteArray();
		PGPDataEncryptorBuilder builder = new BcPGPDataEncryptorBuilder(PGPEncryptedData.AES_256)
				.setWithIntegrityPacket(withIntegrityCheck).setSecureRandom(new SecureRandom());
		PGPEncryptedDataGenerator cPk = new PGPEncryptedDataGenerator(builder);
		PublicKeyKeyEncryptionMethodGenerator gen = new BcPublicKeyKeyEncryptionMethodGenerator(pubKey)
				.setSecureRandom(new SecureRandom());
		cPk.addMethod(gen);
		OutputStream cOut = cPk.open(os, bytes.length);
		cOut.write(bytes);
		cOut.flush();
		cOut.close();
		os.close();
	}
	
	public static void getFileFormat(String filename)throws Exception{
		InputStream is = new FileInputStream(filename);
		InputStream in = PGPUtil.getDecoderStream(is);
		PGPObjectFactory pgpF = new BcPGPObjectFactory(in);
		PGPEncryptedDataList enc = null;
		Object o = pgpF.nextObject();	
		while(o!=null){
			System.out.println(o.getClass());
			o=pgpF.nextObject();
		}
		in.close();
		is.close();
	}
	
	public static void main(String[] args) throws Exception{
		//encryptFile("e:\\code\\keys\\1234.en.txt", "e:\\code\\keys\\1234.txt", "e:\\code\\keys\\pub.asc", true, true);
    	//decryptFile(new FileInputStream("e:\\code\\keys\\1234.en.txt"), new FileOutputStream("e:\\code\\keys\\output"), new FileInputStream("e:\\code\\keys\\sec.key"), "1234".toCharArray());
    	//System.out.println(verifyFile("e:\\code\\keys\\1234.txt.sig", "e:\\code\\keys\\pub.asc"));
    	System.out.println(decryptAndVerify("e:\\code\\keys\\1234.txt.gpg", "e:\\code\\keys\\output.txt",
    			"e:\\code\\keys\\sec.key","e:\\code\\keys\\pub.asc", "1234".toCharArray()));
		//getFileFormat("e:\\code\\keys\\1234.txt.sig");
		//System.out.println(verifyFile("e:\\code\\keys\\1234.txt", "e:\\code\\keys\\pub.asc"));
		//signFile("e:\\code\\keys\\1234.txt", new FileInputStream("e:\\code\\keys\\sec.key"), 
		//		new FileOutputStream("e:\\code\\keys\\1234.txt.sig"),"1234".toCharArray(), true);
		//encryptFile("e:\\code\\keys\\1234.txt.gpg", "e:\\code\\keys\\1234.txt.sig", "e:\\code\\keys\\pub.asc", true, true);
		//System.out.println(verifyFile("e:\\code\\keys\\1234.txt.sig", "e:\\code\\keys\\output.txt", "e:\\code\\keys\\pub.asc"));
		//encryptTest("e:\\code\\keys\\1234.txt.enc", "e:\\code\\keys\\1234.txt", "e:\\code\\keys\\pub.asc",true,true);
		signAndEncrypt(new FileOutputStream("e:\\code\\keys\\1234.txt.gpg"), "e:\\code\\keys\\1234.txt", 
				readPublicKey("e:\\code\\keys\\pub.asc"), 
				findPrivateKey("e:\\code\\keys\\sec.key",readPublicKey("e:\\code\\keys\\pub.asc").getKeyID(), "1234".toCharArray()),true, true);

	}

}
