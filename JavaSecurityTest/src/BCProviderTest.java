import java.security.Provider;
import java.security.Security;
import java.util.Map;

public class BCProviderTest {
	public static void main(String[] args) {
		Provider provider=null;
		try{
			provider = Security.getProvider("BC");
		}catch (Exception e){
			e.printStackTrace();
		}
		System.err.println(provider);
		for(Map.Entry<Object,Object> entry : provider.entrySet()){
			System.err.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}
