import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;

public class KeystoreTest {
	public static void main(String[] args)throws Exception{
		FileInputStream is = new FileInputStream("e:\\.keystore");
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(is, "password".toCharArray());
		is.close();
		PrivateKey key = (PrivateKey) ks.getKey("alias", "password".toCharArray());
	}

}
