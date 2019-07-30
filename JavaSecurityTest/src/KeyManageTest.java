import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;

public class KeyManageTest {
	public static void main(String [] args) throws Exception{
		KeyManagerFactory fac = KeyManagerFactory.getInstance("SunX509");
		FileInputStream is = new FileInputStream("d:\\x.keystore");
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(is, "Password".toCharArray());
		is.close();
		fac.init(ks, "Password".toCharArray());
	}

}
