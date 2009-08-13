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
	static HashMap<Long, Long> sameMap;
	static HashMap<Long, Long> otherMap;

	@BeforeClass
	public static void oneTimeSetUp() {
		long mapCapacity = Math.round((Math.random() * 100));
		long entries = Math.round((Math.random() * 100));
		System.out.println("Map Capacity:" + mapCapacity);
		System.out.println("Entries:" + entries);
		try {
			persistentHashMap = new PersistentHashMap<Long, Long>(new File(
					"C:\\mapBackup"), mapCapacity);
		} catch (Exception ex) {
			throw new RuntimeException();
		}
		sameMap = new HashMap<Long, Long>();
		otherMap = new HashMap<Long, Long>();
		try {
			for (int i = 0; i < entries; i++) {
				Long key = Math.round((Math.random() * 10000));
				Long value = Math.round((Math.random() * 10000));
				persistentHashMap.put(key, value);
				sameMap.put(key, value);
				otherMap.put(new Long(i), new Long(i));
			}
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}

	@Test
	public void testPersistentHashMap() throws Exception {
		PersistentHashMap<Long, Long> pmap = new PersistentHashMap<Long, Long>(
				sameMap, new File("C:\\mapBackup2"), Math.round((Math.random() * 100)));
		Assert.assertEquals(sameMap, pmap.toHashMap());
		
		pmap = new PersistentHashMap<Long, Long>(
				otherMap, new File("C:\\mapBackup2"), Math.round((Math.random() * 100)));
		Assert.assertEquals(otherMap, pmap.toHashMap());
	}

	@Test
	public void testToHashMap() throws Exception {
		Assert.assertEquals(sameMap, persistentHashMap.toHashMap());
		Assert.assertEquals(false, persistentHashMap.toHashMap().equals(
				otherMap));
	}

	@Test
	public void testGet() throws Exception {
		Set<Entry<Long, Long>> entrySet = sameMap.entrySet();
		for (Entry<Long, Long> entry : entrySet) {
			Assert.assertEquals(entry.getValue(), persistentHashMap.get(entry
					.getKey()));
		}
		for (int i = 0; i < 10000; i++) {
			Long key = Math.round((Math.random() * 10000));
			Assert.assertEquals(sameMap.get(key), persistentHashMap.get(key));
		}

		entrySet = otherMap.entrySet();
		for (Entry<Long, Long> entry : entrySet) {
			if (!sameMap.containsKey(entry.getKey()))
				Assert.assertEquals(false, entry.getValue().equals(
						persistentHashMap.get(entry.getKey())));

		}
	}

	@Test
	public void testContainsKey() throws Exception {
		Set<Entry<Long, Long>> entrySet = sameMap.entrySet();
		for (Entry<Long, Long> entry : entrySet) {
			Assert.assertEquals(true, persistentHashMap.containsKey(entry
					.getKey()));
		}
		for (int i = 0; i < 10000; i++) {
			Long key = Math.round((Math.random() * 10000));
			Assert.assertEquals(sameMap.containsKey(key), persistentHashMap
					.containsKey(key));
		}

		entrySet = otherMap.entrySet();
		for (Entry<Long, Long> entry : entrySet) {
			if (!sameMap.containsKey(entry.getKey()))
				Assert.assertEquals(false, persistentHashMap.containsKey(entry
						.getKey()));
		}
	}

	@Test
	public void testContainsValue() throws Exception {
		Set<Entry<Long, Long>> entrySet = sameMap.entrySet();
		for (Entry<Long, Long> entry : entrySet) {
			Assert.assertEquals(true, persistentHashMap.containsValue(entry
					.getValue()));
		}
		for (int i = 0; i < 10000; i++) {
			Long value = Math.round((Math.random() * 10000));
			Assert.assertEquals(sameMap.containsValue(value), persistentHashMap
					.containsValue(value));
		}

		entrySet = otherMap.entrySet();
		for (Entry<Long, Long> entry : entrySet) {
			if (!sameMap.containsValue(entry.getValue()))
				Assert.assertEquals(false, persistentHashMap
						.containsValue(entry.getValue()));
		}

	}

	@Test
	public void testEntrySet() throws Exception {
		Assert.assertEquals(sameMap.entrySet(), persistentHashMap.entrySet());
		Assert.assertEquals(false, otherMap.entrySet().equals(
				persistentHashMap.entrySet()));
	}

	@Test
	public void testIsEmpty() throws Exception {
		Assert.assertEquals(sameMap.isEmpty(), persistentHashMap.isEmpty());
	}

	@Test
	public void testClear() throws Exception {
		persistentHashMap.clear();
		sameMap.clear();
		Assert.assertEquals(sameMap.toString(), persistentHashMap.toString());
		Assert.assertEquals(sameMap, persistentHashMap.toHashMap());
		Assert.assertEquals(sameMap.entrySet(), persistentHashMap.entrySet());
		for (int i = 0; i < 10; i++) {
			Long value = Math.round((Math.random() * 10000));
			Long key = Math.round((Math.random() * 10000));
			Assert.assertEquals(sameMap.containsValue(value), persistentHashMap
					.containsValue(value));
			Assert.assertEquals(sameMap.containsKey(key), persistentHashMap
					.containsKey(key));
			Assert.assertEquals(sameMap.get(key), persistentHashMap.get(key));
		}

	}

}
