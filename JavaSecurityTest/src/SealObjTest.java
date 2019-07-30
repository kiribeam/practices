import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class SealObjTest {
	public static void main(String[] args) throws Exception{
		String input = "SealedObject";
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		SecretKey key = kg.generateKey();
		Cipher cp1 = Cipher.getInstance(key.getAlgorithm());
		cp1.init(Cipher.ENCRYPT_MODE, key);
		SealedObject sobj = new SealedObject(input, cp1);
		Cipher cp2 = Cipher.getInstance(key.getAlgorithm());
		cp2.init(Cipher.DECRYPT_MODE, key);
		String output = (String) sobj.getObject(cp2);
		System.out.println(output);
	}

}
