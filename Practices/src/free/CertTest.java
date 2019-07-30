import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import java.security.cert.X509Certificate;

public class CertTest {
	public static void main(String[] args) throws Exception{
	  CertificateFactory certfac = CertificateFactory.getInstance("X.509");
	  FileInputStream is = new FileInputStream("D:\\x.keystore");
	  Certificate cert = certfac.generateCertificate(is);
	  is.close();
	  is = new FileInputStream("D:\\x.keystore");
	  KeyStore ks = KeyStore.getInstance("JKS");
	  ks.load(is, "password".toCharArray());
	  is.close();
	  X509Certificate x509 = (X509Certificate) ks.getCertificate("alias");
	  Signature sig = Signature.getInstance(x509.getSigAlgName());
	}
}

