package PGPTEST;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.bcpg.PublicKeyAlgorithmTags;
import org.bouncycastle.bcpg.sig.KeyFlags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
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
import org.bouncycastle.openpgp.PGPSignatureSubpacketVector;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.bc.BcKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyDataDecryptorFactory;
 
public class PGPUtilss {
 
    private static final int BUFFER_SIZE = 1 << 16; // should always be power of 2
    private static final int KEY_FLAGS = 27;
    private static final int[] MASTER_KEY_CERTIFICATION_TYPES = new int[]{
            PGPSignature.POSITIVE_CERTIFICATION,
            PGPSignature.CASUAL_CERTIFICATION,
            PGPSignature.NO_CERTIFICATION,
            PGPSignature.DEFAULT_CERTIFICATION
    };
 
    @SuppressWarnings("unchecked")
    public static PGPPublicKey readPublicKey(InputStream in)
            throws IOException, PGPException {
        PGPPublicKeyRingCollection keyRingCollection = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(in), null);
        //
        // we just loop through the collection till we find a key suitable for encryption, in the real
        // world you would probably want to be a bit smarter about this.
        //
        PGPPublicKey publicKey = null;
 
        // iterate through the key rings.
        Iterator<PGPPublicKeyRing> rIt = keyRingCollection.getKeyRings();
 
        while (publicKey == null && rIt.hasNext()) {
            PGPPublicKeyRing kRing = rIt.next();
            Iterator<PGPPublicKey> kIt = kRing.getPublicKeys();
            while (publicKey == null && kIt.hasNext()) {
                PGPPublicKey key = kIt.next();
                if (key.isEncryptionKey()) {
                    publicKey = key;
                }
            }
        }
        if (publicKey == null) {
            throw new IllegalArgumentException("Can't find public key in the key ring.");
        }
        if (!isForEncryption(publicKey)) {
            throw new IllegalArgumentException("KeyID " + publicKey.getKeyID() + " not flagged for encryption.");
        }
 
        return publicKey;
    }
 
    @SuppressWarnings("unchecked")
    public static PGPSecretKey readSecretKey(InputStream in)
            throws IOException, PGPException {
 
        PGPSecretKeyRingCollection keyRingCollection = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(in),null);
        PGPSecretKey secretKey = null;
        Iterator<PGPSecretKeyRing> rIt = keyRingCollection.getKeyRings();
        while (secretKey == null && rIt.hasNext()) {
            PGPSecretKeyRing keyRing = rIt.next();
            Iterator<PGPSecretKey> kIt = keyRing.getSecretKeys();
            while (secretKey == null && kIt.hasNext()) {
                PGPSecretKey key = kIt.next();
                if (key.isSigningKey()) {
                    secretKey = key;
                }
            }
        }
        // Validate secret key
        if (secretKey == null) {
            throw new IllegalArgumentException("Can't find private key in the key ring.");
        }
        if (!secretKey.isSigningKey()) {
            throw new IllegalArgumentException("Private key does not allow signing.");
        }
 
        if (secretKey.getPublicKey().isRevoked()) {
            throw new IllegalArgumentException("Private key has been revoked.");
        }
 
        if (!hasKeyFlags(secretKey.getPublicKey(), KeyFlags.SIGN_DATA)) {
            throw new IllegalArgumentException("Key cannot be used for signing.");
        }
        return secretKey;
 
    }
 
    /**
     * Load a secret key ring collection from keyIn and find the private key corresponding to
     * keyID if it exists.
     * <p/>
     *
     * @param keyIn input stream representing a key ring collection.
     * @param keyID keyID we want.
     * @param pass  passphrase to decrypt secret key with.
     * @return 156
     * @throws IOException             157
     * @throws PGPException            158
     * @throws NoSuchProviderException 159
     */
 
    public static PGPPrivateKey findPrivateKey(InputStream keyIn, long keyID, char[] pass)
            throws IOException, PGPException, NoSuchProviderException
    {
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn),new BcKeyFingerprintCalculator());
        return findPrivateKey(pgpSec.getSecretKey(keyID), pass);
 
    }
 
    /**
     * 169
     * Load a secret key and find the private key in it
     * 170
     *
     * @param pgpSecKey The secret key
     * @param pass      passphrase to decrypt secret key with
     * @throws PGPException
     */
    public static PGPPrivateKey findPrivateKey(PGPSecretKey pgpSecKey, char[] pass)
            throws PGPException {
        if (pgpSecKey == null) return null;
        PBESecretKeyDecryptor decryptor = new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(pass);
        return pgpSecKey.extractPrivateKey(decryptor);
    }
 
    @SuppressWarnings("unchecked")
    public static void decryptFile(InputStream in, OutputStream out, InputStream keyIn, char[] passwd)
            throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        in = org.bouncycastle.openpgp.PGPUtil.getDecoderStream(in);
 
        PGPObjectFactory pgpF = new PGPObjectFactory(in,null);
        PGPEncryptedDataList enc;
        Object o = pgpF.nextObject();
        // the first object might be a PGP marker packet.
        if (o instanceof PGPEncryptedDataList) {
            enc = (PGPEncryptedDataList) o;
        } else {
            enc = (PGPEncryptedDataList) pgpF.nextObject();
        }
        Iterator<PGPPublicKeyEncryptedData> it = enc.getEncryptedDataObjects();
        PGPPrivateKey sKey = null;
        PGPPublicKeyEncryptedData pbe = null;
 
        while (sKey == null && it.hasNext()) {
            pbe = it.next();
            sKey = findPrivateKey(keyIn, pbe.getKeyID(), passwd);
        }
 
        if (sKey == null) {
            throw new IllegalArgumentException("Secret key for message not found.");
        }
 
        InputStream clear = pbe.getDataStream(new BcPublicKeyDataDecryptorFactory(sKey));
        PGPObjectFactory plainFact = new PGPObjectFactory(clear,null);
        Object message = plainFact.nextObject();
 
        if (message instanceof PGPCompressedData) {
            PGPCompressedData cData = (PGPCompressedData) message;
            PGPObjectFactory pgpFact = new PGPObjectFactory(cData.getDataStream(),null);
            message = pgpFact.nextObject();
            //message = pgpFact.nextObject();

        }
 
        if (message instanceof PGPLiteralData) {
            PGPLiteralData ld = (PGPLiteralData) message;
            InputStream unc = ld.getInputStream();
            int ch;
            while ((ch = unc.read()) >= 0) {
                out.write(ch);
            }
        } else if (message instanceof PGPOnePassSignatureList) {

            throw new PGPException("Encrypted message contains a signed message - not literal data.");
        } else {
            throw new PGPException("Message is not a simple encrypted file - type unknown.");
        }
 
        if (pbe.isIntegrityProtected()) {
            if (!pbe.verify()) {
                throw new PGPException("Message failed integrity check");
            }
        }
    }
 
    /**
     * From LockBox Lobs PGP Encryption tools.
     * http://www.lockboxlabs.org/content/downloads
     * <p/>
     * I didn't think it was worth having to import a 4meg lib for three methods
     */
 
    public static boolean isForEncryption(PGPPublicKey key) {
        if (key.getAlgorithm() == PublicKeyAlgorithmTags.RSA_SIGN
                || key.getAlgorithm() == PublicKeyAlgorithmTags.DSA
                || key.getAlgorithm() == PublicKeyAlgorithmTags.EC
                || key.getAlgorithm() == PublicKeyAlgorithmTags.ECDSA) {
            return false;
        }
        return hasKeyFlags(key, KeyFlags.ENCRYPT_COMMS | KeyFlags.ENCRYPT_STORAGE);
    }
 
 
    /**
     * From LockBox Lobs PGP Encryption tools.
     * http://www.lockboxlabs.org/content/downloads
     * <p/>
     * I didn't think it was worth having to import a 4meg lib for three methods
     */
    @SuppressWarnings("unchecked")
    private static boolean hasKeyFlags(PGPPublicKey encKey, int keyUsage) {
        if (encKey.isMasterKey()) {
            for (int i = 0; i != PGPUtilss.MASTER_KEY_CERTIFICATION_TYPES.length; i++) {
                for (Iterator<PGPSignature> eIt = encKey.getSignaturesOfType(PGPUtilss.MASTER_KEY_CERTIFICATION_TYPES[i]); eIt.hasNext(); ) {
                    PGPSignature sig = eIt.next();
                    if (!isMatchingUsage(sig, keyUsage)) {
                        return false;
                    }
                }
            }
        } else {
            for (Iterator<PGPSignature> eIt = encKey.getSignaturesOfType(PGPSignature.SUBKEY_BINDING); eIt.hasNext(); ) {
                PGPSignature sig = eIt.next();
                if (!isMatchingUsage(sig, keyUsage)) {
                    return false;
                }
            }
        }
        return true;
    }
 
    /**
     * From LockBox Lobs PGP Encryption tools.
     * http://www.lockboxlabs.org/content/downloads
     * <p/>
     * I didn't think it was worth having to import a 4meg lib for three methods
     */
    private static boolean isMatchingUsage(PGPSignature sig, int keyUsage) {
        if (sig.hasSubpackets()) {
            PGPSignatureSubpacketVector sv = sig.getHashedSubPackets();
            if (sv.hasSubpacket(PGPUtilss.KEY_FLAGS)) {
                // code fix suggested by kzt (see comments)
                if ((sv.getKeyFlags() == 0 && keyUsage == 0)) {
                    return false;
                }
            }
        }
        return true;
    }
 
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("e:\\code\\keys\\1234.txt.gpg");
        File keyInFile = new File("e:\\code\\keys\\sec.key");
        FileInputStream keyIn = new FileInputStream(keyInFile);
        File outputFile = File.createTempFile("CITIC_DECRYPTED", ".csv");
        FileOutputStream out = new FileOutputStream(outputFile);
        PGPUtilss.decryptFile(in, out, keyIn, "1234".toCharArray());
        System.out.println(FileUtils.readFileToString(outputFile));
    }
}
 