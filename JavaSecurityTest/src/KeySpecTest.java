import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class KeySpecTest {
	public static void main(String[] args)throws Exception{
		KeyPairGenerator gen = KeyPairGenerator.getInstance("DSA");
		gen.initialize(1024);
		KeyPair keys = gen.genKeyPair();
		byte [] publicKeyBytes = keys.getPublic().getEncoded();
		X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
		KeyFactory fac = KeyFactory.getInstance("DSA");
		PublicKey pk = fac.generatePublic(spec);
		
	}

}
