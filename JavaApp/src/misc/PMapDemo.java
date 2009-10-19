package misc;

import java.io.File;
import com.sdm.product.PersistentHashMap;

public class PMapDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		File mapBackup = new File("/media/DATAPART1/sdm/PersistentHashMap/bkp");
		if (!mapBackup.exists())
			mapBackup.mkdir();
		com.sdm.product.PersistentHashMap<Integer, Integer> map = new com.sdm.product.PersistentHashMap<Integer, Integer>(
				mapBackup, 10);
		for (int i = 0; i < 100; i++)
			map.put(i, i);
		for (int i = 0; i < 100; i++)
			System.out.println(map.get(i));

	}

}
