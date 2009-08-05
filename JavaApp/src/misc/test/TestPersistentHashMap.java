package misc.test;

import java.io.File;

import junit.framework.TestCase;
import misc.PersistentHashMap;

import org.junit.Test;

public class TestPersistentHashMap extends TestCase {

	@Test
	public void testGeneric() throws Exception {
		PersistentHashMap<String, String> persistentHashMap = new PersistentHashMap<String, String>(
				new File("C:\\mapBackup"), 10);
		try {
			for (int i = 0; i < 100; i++)
				persistentHashMap.put(Integer.toString(i), Integer.toString(i));
			System.out.println(persistentHashMap.toString());
		} catch (Exception ex) {
			System.out.println(ex);
			//fail();
			throw ex;
		}

	}
}
