import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import com.kiri.BinaryTools;

public class MacTest {
	public static void main(String[] args)throws Exception{
		byte[] input = "MAC".getBytes();
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
		SecretKey secretKey = keyGenerator.generateKey();
		String s = secretKey.getAlgorithm();
		Mac mac = Mac.getInstance(s);
		System.out.println(s);
		mac.init(secretKey);
		byte[] output = mac.doFinal(input);
		System.out.println(BinaryTools.bytesToHex(output));
	}

}
