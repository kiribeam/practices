import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CipherInputTest {
	public static void main(String [] args) throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		SecretKey sk = kg.generateKey();
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, sk);
		CipherInputStream is = new CipherInputStream(new FileInputStream(new File("secret")), cipher);
		DataInputStream dis = new DataInputStream(is);
		String output=dis.readUTF();
		dis.close();
	}

}
