import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;

public class KeyAgreementTest {
	public static void main(String [] args)throws Exception{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
		KeyPair kp1 = kpg.generateKeyPair();
		KeyPair kp2 = kpg.generateKeyPair();
		KeyAgreement keyAgree = KeyAgreement.getInstance(kpg.getAlgorithm());
		keyAgree.init(kp2.getPrivate());
		keyAgree.doPhase(kp1.getPublic(), true);
		SecretKey secretKey = keyAgree.generateSecret("DES");
	}

}
