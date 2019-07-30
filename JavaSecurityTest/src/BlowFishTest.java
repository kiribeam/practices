import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.kiri.BinaryTools;

public class BlowFishTest {
	public static void main(String[] args) throws Exception{
		testBC();
	}

	public static void testBC()throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance("Blowfish");
		kg.init(128);
		SecretKey key = kg.generateKey();
		Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] txt = cipher.doFinal("123".getBytes());
		System.out.println(BinaryTools.bytesToHex(txt));
	}
}
