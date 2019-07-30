import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.kiri.BinaryTools;

public class DesedeCoder {
	
	public static final String KEY_ALG = "DESede";
	public static final String CIPHER_ALG = "DESede/CFB/PKCS7Padding";
	public static byte[] ivs = new byte[8];
	
	private static Key toKey(byte[] key ) throws Exception{
		DESedeKeySpec dks = new DESedeKeySpec(key);
		SecretKeyFactory fac = SecretKeyFactory.getInstance(KEY_ALG);
		return fac.generateSecret(dks);
	}
	
	public static byte[] decrypt(byte[] data, byte[] key)throws Exception{
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALG, "BC");
		IvParameterSpec iv = new IvParameterSpec(ivs);
		cipher.init(Cipher.DECRYPT_MODE, k, iv );
		return cipher.doFinal(data);
	}
	
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception{
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALG, "BC");
		IvParameterSpec iv = new IvParameterSpec(ivs);
		cipher.init(Cipher.ENCRYPT_MODE, k, iv);
		return cipher.doFinal(data);
	}
	
	public static byte[] initKey() throws Exception{
		KeyGenerator gen = KeyGenerator.getInstance(KEY_ALG, "BC");
		gen.init(192);
		SecretKey sk = gen.generateKey();
		return sk.getEncoded();
	}
	
	public static void main(String[] args) throws Exception{
		String input = "DESede";
		byte[] data = input.getBytes();
		System.out.println("Origin: " + input);
		
		byte[] key = initKey();
		System.out.println("Key : " + BinaryTools.bytesToHex(key));

		byte[] enData = encrypt(data, key);
		
		System.out.println("Encrypt: " + BinaryTools.bytesToHex(enData));
		
		byte[] deData = decrypt(enData, key);
		
		System.out.println("Decrypt: " + BinaryTools.bytesToHex(deData));
		
		System.out.println("Equals: " + input.equals(new String(deData)));
		
		
	}
}
