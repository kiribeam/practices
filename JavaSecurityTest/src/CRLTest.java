import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;

public class CRLTest {
	public static void main(String[ ] args) throws Exception{
		CertificateFactory fac = CertificateFactory.getInstance("X.509");
		FileInputStream in = new FileInputStream("d:\\x.keystore");
		X509Certificate cert = (X509Certificate) fac.generateCertificate(in);
		X509CRL crl = (X509CRL) fac.generateCRL(in);
		X509CRLEntry x509 = crl.getRevokedCertificate(cert);
	}

}
