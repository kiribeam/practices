package uaes;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;

import com.kiri.BinaryTools;

public class MDTest {
	public static void main(String args[])throws Exception{

		String s = "e:\\code\\uaes\\u\\target.bin";
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(s));
		int length = reader.available();
		int head = 0x35a;
		System.out.println(length);
		byte[] input = new byte[length-head];
		int i=0;
		while(i<head){
			reader.read();
			i++;
		}
		reader.read(input, 0, length-head);
		reader.close();
		System.out.println(BinaryTools.bytesToHex(input));
		MessageDigest sha = MessageDigest.getInstance("SHA256");
		sha.update(input);
		byte[] output = sha.digest();
		System.out.println(BinaryTools.bytesToHex(output));
		System.out.println("------------");
	}

}
