package uaes;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import com.kiri.BinaryTools;

public class RsaDecryptTest {
	public static void main(String[] args)throws Exception{
		String file = "e:\\code\\uaes\\FanyaNew.bin";
		String keyf = "e:\\code\\uaes\\u\\pubkey.der";
		FileInputStream ks = new FileInputStream(keyf);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		int ch;
		while((ch = ks.read())!=-1)
			bout.write(ch);
		byte[] key = bout.toByteArray();
		byte[] keyMo= new byte[257];
		byte[] keyE = new byte[4];
		System.arraycopy(key, 9, keyMo, 1, 256);
		System.out.println(BinaryTools.bytesToHex(keyMo)+"\n------------");
		System.arraycopy(key, 267, keyE, 1, 3);
		System.out.println(BinaryTools.bytesToHex(keyE)+"\n------------");
		ks.close();
		RSAPublicKeySpec spec = new RSAPublicKeySpec(new BigInteger(keyMo), new BigInteger(keyE));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey pubkey =  keyFactory.generatePublic(spec);
		FileInputStream fis = new FileInputStream(file);
		int length = fis.available();
		byte[] content = new byte[256];
		for(int i=0; i<length-256; i++){
			fis.read();
		}
		fis.read(content, 0, 256);
		fis.close();
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, pubkey);
		byte[] txt = cipher.doFinal(content);
		System.out.println(BinaryTools.bytesToHex(txt));
	}
}
