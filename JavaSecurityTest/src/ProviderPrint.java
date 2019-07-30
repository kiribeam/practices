import java.util.*;
import java.security.*;

public class ProviderPrint {
	public static void main(String[] args){
		for(Provider p : Security.getProviders()){
			System.out.println(p);
			for(Map.Entry<Object, Object> entry : p.entrySet()){
				System.out.println("\t" + entry.getKey());
			}
		}
	}

}
