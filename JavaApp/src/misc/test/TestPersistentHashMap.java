package misc.test;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import junit.framework.Assert;
import misc.PersistentHashMap;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestPersistentHashMap {

	static PersistentHashMap<Long, Long> persistentHashMap;
	static HashMap<Long, Long> map;

	@BeforeClass
	public static void oneTimeSetUp() {
		long mapCapacity = Math.round((Math.random() * 1000));
		long entries = Math.round((Math.random() * 1000));
		System.out.println("Map Capacity:" + mapCapacity);
		System.out.println("Entries:" + entries);
		try {
			persistentHashMap = new PersistentHashMap<Long, Long>(new File(
					"C:\\mapBackup"), mapCapacity);
		} catch (Exception ex) {
			throw new RuntimeException();
		}
		map = new HashMap<Long, Long>();
		try {
			for (int i = 0; i < entries; i++) {
				Long key = Math.round((Math.random() * 10000));
				Long value = Math.round((Math.random() * 10000));
				persistentHashMap.put(key, value);
				map.put(key, value);
			}
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}

	@Test
	public void testToHashMap() throws Exception {
		junit.framework.Assert.assertEquals(map, persistentHashMap.toHashMap());
	}

	@Test
	public void testGet() throws Exception {
		Set<Entry<Long, Long>> entrySet = map.entrySet();
		for (Entry<Long, Long> entry : entrySet) {
			junit.framework.Assert.assertEquals(entry.getValue(),
					persistentHashMap.get(entry.getKey()));
		}
	}

	@Test
	public void testClear() throws Exception {
		persistentHashMap.clear();
		map.clear();
		Assert.assertEquals(map.toString(), persistentHashMap.toString());
		Assert.assertEquals(map, persistentHashMap.toHashMap());
	}

	@Test
	public void testContainsKey() throws Exception {
		Set<Entry<Long, Long>> entrySet = map.entrySet();
		for (Entry<Long, Long> entry : entrySet) {
			Assert.assertEquals(true, persistentHashMap.containsKey(entry
					.getKey()));
		}
	}
}
