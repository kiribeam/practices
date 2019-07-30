import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

import com.kiri.BinaryTools;

public class SignTest {
	
	public static void main(String[] args)throws Exception{
		byte[] data = "Data Signature".getBytes();
		KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
		gen.initialize(1024);
		KeyPair pair  = gen.generateKeyPair();
		Signature sig = Signature.getInstance(gen.getAlgorithm());
		sig.initSign(pair.getPrivate());
		sig.update(data);
		byte[] sign = sig.sign();
		System.out.println(BinaryTools.bytesToHex(sign));

		System.out.println("------");
		sig.initVerify(pair.getPublic());
		sig.update(data);
		System.out.println(sig.verify(sign));
		
	}

}
