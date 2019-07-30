import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.kiri.BinaryTools;

public class PolicyTest {
	public static void main(String[]args)throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(256);
		SecretKey sk = kg.generateKey();
		byte[] key = sk.getEncoded();
		System.out.println(BinaryTools.bytesToHex(key));
	}

}
