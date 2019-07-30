package uaes;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

import com.kiri.BinaryTools;

public class SignTest4 {
	public static void main(String[] args) throws Exception{
		String file = "e:\\code\\uaes\\u\\target2.bin";
		String keyf = "e:\\code\\uaes\\u\\base_userkey.der";
		FileInputStream ks = new FileInputStream(keyf);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		int ch;
		while((ch = ks.read())!=-1)
			bout.write(ch);
		byte[] key = bout.toByteArray();
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(key);
		ks.close();
		//System.out.println(spec.getFormat());
		//System.out.println(BinaryTools.bytesToHex(spec.getEncoded()));
		KeyFactory fac = KeyFactory.getInstance("RSA");
		PrivateKey pk = fac.generatePrivate(spec);
		FileInputStream fs = new FileInputStream(file);
		int i = 0;
		while(i<74){
			fs.read();
			i++;
		}
		byte[] data = new byte[282];
		byte[] sign = new byte[256];
		fs.read(data, 0, 282);
		System.out.println(BinaryTools.bytesToHex(data));
		fs.read(sign, 0, 256);
		System.out.println(BinaryTools.bytesToHex(sign));
		fs.close();
		System.out.println("-------------------");
		Signature sig = Signature.getInstance("SHA256withRSA");
		sig.initSign(pk);;
		sig.update(data);
		System.out.println(BinaryTools.bytesToHex(sig.sign()));
	}
}
