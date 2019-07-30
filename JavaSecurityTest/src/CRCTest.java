import java.util.zip.CRC32;

public class CRCTest {
	public static void main(String [] args)throws Exception{
		String s = "test crc";
		CRC32 crc = new CRC32();
		crc.update(s.getBytes());
		String hex = Long.toHexString(crc.getValue());
		System.out.println(hex);
	}

}
