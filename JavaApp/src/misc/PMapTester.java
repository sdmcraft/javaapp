package misc;

import java.io.File;
import com.sdm.product.PersistentHashMap;

public class PMapTester {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		PersistentHashMap<String, String> map = 
			new PersistentHashMap<String, String>(new File("/home/satyam/Desktop"), 10);
		map.put("a", "b");
		System.out.println(map.get("a"));
	}

}
