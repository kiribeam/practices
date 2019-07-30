import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.PKCS8EncodedKeySpec;

import com.kiri.BinaryTools;

public class KeyGenerateTest {

	public static void main(String[] args)throws Exception{
		KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
		gen.initialize(2048);
		KeyPair pair = gen.generateKeyPair();
		byte[] keyBytes = pair.getPrivate().getEncoded();
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key privateKey = keyFactory.generatePrivate(spec);
		System.out.println(pair.getPublic().getEncoded().length);
		System.out.println(BinaryTools.bytesToHex(pair.getPublic().getEncoded()));
		FileOutputStream os = new FileOutputStream("e://code//uaes//sample.der");
		os.write(pair.getPublic().getEncoded());
		os.close();
	}
}
