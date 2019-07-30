import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.TrustManagerFactory;

public class TrustMangTest {
	public static void main(String[] args) throws Exception{
		TrustManagerFactory fac = TrustManagerFactory.getInstance("SunX509");
		FileInputStream is = new FileInputStream("e:\\x.keystore");
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(is, "Password".toCharArray());
		is.close();
		fac.init(ks);
	}

}
