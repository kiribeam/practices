
import org.bouncycastle.util.encoders.*;

public class BCHexTest {
	public static void main(String[] args) throws Exception{
		String str = "Hex エンコード";
		System.out.println("str :" + str);
		byte[] input = str.getBytes();
		byte[] data = Hex.encode(input);
		System.out.println("after encode" + new String(data));
		byte[] output = Hex.decode(data);
		System.out.println("after decode" + new String(output));
	}

}
