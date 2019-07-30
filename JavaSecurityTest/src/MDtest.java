import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import com.kiri.BinaryTools;

public class MDtest {
	public static void main(String[] args)throws Exception{
		byte[] input = "saaa".getBytes();
		MessageDigest sha = MessageDigest.getInstance("SHA");
		sha.update(input);
		byte[] output = sha.digest();
		String s = BinaryTools.bytesToHex(output);
		System.out.println(s);
		System.out.println("------------");
		sha.reset();
		input = "md5a".getBytes();
		DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(input), sha);
		dis.on(true);
		dis.read(input,  0, input.length);
		dis.close();
		System.out.println(BinaryTools.bytesToHex(sha.digest()));
		System.out.println("-------------");
		input="ssss".getBytes();
		sha.reset();
		DigestOutputStream dos = new DigestOutputStream(new ByteArrayOutputStream(), sha);
		dos.write(input, 0, input.length);
		output = dos.getMessageDigest().digest();
		dos.flush();
		dos.close();
		System.out.println(BinaryTools.bytesToHex(output));
	}
}
