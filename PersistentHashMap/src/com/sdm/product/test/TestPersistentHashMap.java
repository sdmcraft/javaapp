package com.sdm.product.test;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sdm.product.PersistentHashMap;

public class TestPersistentHashMap {

	static PersistentHashMap<Long, Long> persistentHashMap;
	static HashMap<Long, Long> sameMap;
	static HashMap<Long, Long> otherMap;

	@BeforeClass
	public static void oneTimeSetUp() {

		try {
			sameMap = generateRandomMap();
			otherMap = generateRandomMap();
			long mapCapacity = Math.round((Math.random() * 100));
			System.out.println("Map Capacity:" + mapCapacity);
			persistentHashMap = new PersistentHashMap<Long, Long>(sameMap,
					new File("temp" + File.separator + "mapBackup"), mapCapacity);

		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}

	private static HashMap<Long, Long> generateRandomMap() {
		long entries = Math.round((Math.random() * 100));
		System.out.println("Entries:" + entries);
		HashMap<Long, Long> map = new HashMap<Long, Long>();
		for (int i = 0; i < entries; i++) {
			Long key = Math.round((Math.random() * 10000));
			Long value = Math.round((Math.random() * 10000));
			map.put(key, value);
		}
		return map;
	}

	@Test
	public void testPersistentHashMap() throws Exception {
		PersistentHashMap<Long, Long> pmap = new PersistentHashMap<Long, Long>(
				sameMap, new File("temp" + File.separator + "mapBackup2"), Math
						.round((Math.random() * 100)));
		Assert.assertEquals(sameMap, pmap.toHashMap());

		pmap = new PersistentHashMap<Long, Long>(otherMap, new File(
				"temp" + File.separator + "mapBackup2"), Math.round((Math.random() * 100)));
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

	// @Test
	// public void testEntrySet() throws Exception {
	// Assert.assertEquals(sameMap.entrySet(), persistentHashMap.entrySet());
	// Assert.assertEquals(false, otherMap.entrySet().equals(
	// persistentHashMap.entrySet()));
	// }

	@Test
	public void testIsEmpty() throws Exception {
		Assert.assertEquals(sameMap.isEmpty(), persistentHashMap.isEmpty());
	}

	@Test
	public void testPutAll() throws Exception {
		for (int i = 0; i < 10; i++) {
			HashMap<Long, Long> randomMap = generateRandomMap();
			sameMap.putAll(randomMap);
			otherMap.putAll(randomMap);
			persistentHashMap.putAll(randomMap);
			Assert.assertEquals(sameMap, persistentHashMap.toHashMap());
			Assert.assertEquals(false, otherMap.equals(persistentHashMap
					.toHashMap()));
		}
	}

	@Test
	public void testSize() throws Exception {
		Assert.assertEquals(sameMap.size(), persistentHashMap.size());
		Assert.assertEquals(false, otherMap.size() == persistentHashMap.size());
	}

	@Test
	public void testRemove() throws Exception {

		for (int i = 0; i < 10000; i++) {
			Long key = Math.round((Math.random() * 10000));
			Assert.assertEquals(sameMap.remove(key), persistentHashMap
					.remove(key));
			Assert.assertEquals(sameMap, persistentHashMap.toHashMap());
		}

		Set<Entry<Long, Long>> entrySet = sameMap.entrySet();
		Set<Entry<Long, Long>> lclEntrySet = new HashSet<Entry<Long, Long>>(
				entrySet);
		for (Entry<Long, Long> entry : lclEntrySet) {
			Assert.assertEquals(sameMap.remove(entry.getKey()),
					persistentHashMap.remove(entry.getKey()));
			Assert.assertEquals(sameMap, persistentHashMap.toHashMap());
		}

	}

	@Test
	public void testClear() throws Exception {
		persistentHashMap.clear();
		sameMap.clear();
		Assert.assertEquals(sameMap.toString(), persistentHashMap.toString());
		Assert.assertEquals(sameMap, persistentHashMap.toHashMap());
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
