import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.kiri.BinaryTools;

public class SecretKeyFacTest {
	public static void main(String[] args)throws Exception{
		KeyGenerator gen = KeyGenerator.getInstance("DES");
		SecretKey sk = gen.generateKey();
		byte[] key = sk.getEncoded();
		System.out.println(new String(key));
		System.out.println(BinaryTools.bytesToHex(key));
		SecretKeyFactory fac = SecretKeyFactory.getInstance("DES");
		DESKeySpec spec = new DESKeySpec(key);
		SecretKey kk = fac.generateSecret(spec);
		
	}

}
