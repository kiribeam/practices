package uaes;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.kiri.BinaryTools;

public class SignTest2 {

	public static void main(String[] args) throws Exception{
		String file = "e:\\code\\uaes\\u\\target2.bin";
		String keyf = "e:\\code\\uaes\\u\\base_pubkey.der";
		FileInputStream ks = new FileInputStream(keyf);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		int ch;
		while((ch = ks.read())!=-1)
			bout.write(ch);
		byte[] key = bout.toByteArray();
		byte[] keyMo= new byte[256];
		byte[] keyE = new byte[3];
		System.arraycopy(key, 33, keyMo, 0, 256);
		System.out.println(BinaryTools.bytesToHex(keyMo)+"\n------------");
		System.arraycopy(key, 0x123, keyE, 0, 3);
		System.out.println(BinaryTools.bytesToHex(keyE)+"\n------------");
		ks.close();
		KeySpec spec = new X509EncodedKeySpec(key);
		//System.out.println(spec.getFormat());
		//System.out.println(BinaryTools.bytesToHex(spec.getEncoded()));
		KeyFactory fac = KeyFactory.getInstance("RSA");
		PublicKey pk = fac.generatePublic(spec);
		System.out.println(((RSAPublicKey)pk).getPublicExponent());
		FileOutputStream fout = new FileOutputStream("e:\\code\\uaes\\2.der");
		fout.write(pk.getEncoded());
		fout.close();
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
		Signature sig = Signature.getInstance("SHA256withRSA");
		sig.initVerify(pk);
		sig.update(data);
		System.out.println(sig.verify(sign));
	}
}
