package com.sdm.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

//import org.apache.log4j.Logger;

import com.sdm.support.Tools;

/**
 * A file system backed HashMap. The PersistentHashMap, is an extension for
 * HashMap, which can spill over to the file system. It is particularly suitable
 * for large sized HashMaps which cannot be completely retained in memory. Since
 * PersistentHashMap is file system backed, any excess entries, over the
 * specified number of maximum entries allowed in map, are written to the
 * backing files.
 * <p>
 * Internally, the PersistentHashMap maintains list of backing files, each one
 * of those is a serialized HashMap written onto a backing file. So in a way, a
 * PersistentHashMap is a container for multiple HashMaps.
 * </p>
 * 
 * @author Satya Deep Maheshwari
 * 
 * @param <K>
 *            the type of keys maintained by this map
 * @param <V>
 *            the type of mapped values
 * @see <a
 *      href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/HashMap.html">HashMap</a>
 */
public class PersistentHashMap<K, V> implements Serializable {

	public static final long serialVersionUID = 1L;
//	private static final Logger logger = Logger
//			.getLogger(com.sdm.product.PersistentHashMap.class);

	private long maxEntries = 1000000L;
	private File backupDir = null;
	private Map<File, Long> backupFilesMap = new HashMap<File, Long>();

	private long currentEntries = 0L;
	private Map<K, V> currentMap = new HashMap<K, V>();
	private File currentMapFile = null;
	private boolean currentMapChanged = false;

	/**
	 * Constructs an emptry PersistentHashMap with the specified backup
	 * directory and maximum entries
	 * 
	 * @param backupDir
	 *            The directory to place backing files
	 * @param maxEntries
	 *            Maximum number of entries retained in memory before being
	 *            written to backing files
	 * @throws Exception
	 *             If an internal error occurred
	 */
	public PersistentHashMap(File backupDir, long maxEntries) throws Exception {
		//logger.debug("(+)PersistentHashMap(+)");
		//logger.debug("Args:backupDir->" + backupDir);
		//logger.debug("Args:maxEntries->" + maxEntries);
		this.backupDir = backupDir;
		this.maxEntries = maxEntries;
		if (backupDir.exists())
			Tools.deleteFolder(backupDir);
		backupDir.mkdir();
		//logger.debug("(-)PersistentHashMap(-)");
	}

	/**
	 * Constructs an PersistentHashMap from the specified hashmap with the
	 * specified backup directory and maximum entries
	 * 
	 * @param map
	 *            The hashmap whose entries will be put in this
	 *            PersistentHashMap
	 * @param backupDir
	 *            The directory to place backing files
	 * @param maxEntries
	 *            Maximum number of entries retained in memory before being
	 *            written to backing files
	 * @throws Exception
	 *             If an internal error occurred
	 */
	public PersistentHashMap(HashMap<K, V> map, File backupDir, long maxEntries)
			throws Exception {
		//logger.debug("(+)PersistentHashMap(+)");
		//logger.debug("Args:backupDir->" + backupDir);
		//logger.debug("Args:maxEntries->" + maxEntries);
		//logger.debug("Args:map->" + map);
		this.backupDir = backupDir;
		this.maxEntries = maxEntries;
		if (backupDir.exists())
			Tools.deleteFolder(backupDir);
		backupDir.mkdir();
		Set<Entry<K, V>> entrySet = map.entrySet();
		for (Entry<K, V> entry : entrySet)
			this.put(entry.getKey(), entry.getValue());
		//logger.debug("(-)PersistentHashMap(-)");
	}

	/**
	 * Removes all of the mappings from this map. The map will be empty after
	 * this call returns. This results in the deletion of underlying map files
	 * as well.
	 * 
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public void clear() throws Exception {
		//logger.debug("(+)clear(+)");
		Tools.deleteFolder(backupDir);
		backupFilesMap = new HashMap<File, Long>();
		currentEntries = 0L;
		currentMap = new HashMap<K, V>();
		currentMapFile = null;
		currentMapChanged = false;
		//logger.debug("(-)clear(-)");
	}

	private void createCurrentMap() {
		//logger.debug("(+)createCurrentMap(+)");
		currentEntries = 0L;
		currentMap = new HashMap<K, V>();
		currentMapFile = null;
		currentMapChanged = false;
		//logger.debug("(-)createCurrentMap(-)");
	}

	/**
	 * Returns the value to which the specified key is mapped in this identity
	 * hash map, or null if the map contains no mapping for this key. A return
	 * value of null does not necessarily indicate that the map contains no
	 * mapping for the key; it is also possible that the map explicitly maps the
	 * key to null. The containsKey method may be used to distinguish these two
	 * cases.
	 * <p>
	 * As a side effect, this method causes the entries in memory to be written
	 * to the backing files and be replaced by another set on entries.
	 * </p>
	 * 
	 * @param key
	 *            the key whose associated value is to be returned.
	 * @return the value to which this map maps the specified key, or null if
	 *         the map contains no mapping for this key.
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public V get(Object key) throws Exception {
		//logger.debug("(+)get(+)");
		//logger.debug("Args:key->" + key);
		writeCurrentMap();
		V returnValue = null;
		if (currentMap.get(key) != null) {
			returnValue = currentMap.get(key);
		} else {
			Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
			for (Entry<File, Long> entry : entrySet) {
				switchCurrentMap(entry.getKey());
				if (currentMap.get(key) != null) {
					returnValue = currentMap.get(key);
					break;
				}
			}
		}
		//logger.debug("(-)get(-)");
		return returnValue;
	}

	private void switchCurrentMap(File mapFile) throws Exception {
		//logger.debug("(+)switchCurrentMap(+)");
		//logger.debug("Args:mapFile->" + mapFile);
		writeCurrentMap();
		readCurrentMap(mapFile);
		//logger.debug("(-)switchCurrentMap(-)");
	}

	private void readCurrentMap(File mapFile) throws Exception {
		//logger.debug("(+)readCurrentMap(+)");
		//logger.debug("Args:mapFile->" + mapFile);
		currentMapFile = mapFile;
		currentEntries = backupFilesMap.get(currentMapFile);
		currentMap = readMap(mapFile);
		currentMapChanged = false;
		//logger.debug("(-)readCurrentMap(-)");
	}

	private Map<K, V> readMap(File mapFile) throws Exception {
		//logger.debug("(+)readMap(+)");
		//logger.debug("Args:mapFile->" + mapFile);
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(mapFile));
			//logger.debug("(-)readMap(-)");
			return (Map<K, V>) in.readObject();
		} finally {
			if (in != null)
				in.close();
		}
	}

	private void writeMap(Map<K, V> map, File mapFile) throws Exception {
		//logger.debug("(+)writeMap(+)");
		//logger.debug("Args:map->" + map);
		//logger.debug("Args:mapFile->" + mapFile);
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(mapFile));
			out.writeObject(map);

		} finally {
			if (out != null)
				out.close();
		}
		//logger.debug("(-)writeMap(-)");
	}

	private void writeCurrentMap() throws Exception {
		//logger.debug("(+)writeCurrentMap(+)");
		if (currentMapChanged) {
			if (currentMapFile == null || !currentMapFile.exists()) {
				currentMapFile = new File(backupDir, Calendar.getInstance()
						.getTimeInMillis()
						+ ".ser");
			}
			writeMap(currentMap, currentMapFile);
			backupFilesMap.put(currentMapFile, currentEntries);
			currentMapChanged = false;
		}
		//logger.debug("(-)writeCurrentMap(-)");
	}

	private boolean switchToVacantMap() throws Exception {
		//logger.debug("(+)switchToVacantMap(+)");
		Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
		boolean result = false;
		for (Entry<File, Long> entry : entrySet) {
			if (entry.getValue() < maxEntries) {
				result = true;
				switchCurrentMap(entry.getKey());
				break;
			}
		}
		//logger.debug("(-)switchToVacantMap(-)");
		return result;
	}

	/**
	 * Associates the specified value with the specified key in this map. If the
	 * map previously contained a mapping for this key, the old value is
	 * replaced.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated.
	 * @param value
	 *            value to be associated with the specified key.
	 * @return previous value associated with specified key, or null if there
	 *         was no mapping for key. A null return can also indicate that the
	 *         HashMap previously associated null with the specified key.
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public V put(K key, V value) throws Exception {
		//logger.debug("(+)put(+)");
		//logger.debug("Args:key->" + key);
		//logger.debug("Args:value->" + value);
		V returnValue = null;
		writeCurrentMap();
		if (this.get(key) != null) {
			;/* To ensure that map containing this key becomes current map */
		} else if (currentEntries < maxEntries) {
			currentEntries++;
		} else {
			if (!switchToVacantMap()) {
				writeCurrentMap();
				createCurrentMap();
			}
			currentEntries++;
		}
		returnValue = currentMap.put(key, value);
		currentMapChanged = true;
		//logger.debug("(-)put(-)");
		return returnValue;
	}

	/**
	 * Returns a string representation of this map. The string representation
	 * consists of a list of key-value mappings in the order returned by the
	 * map's entrySet view's iterator, enclosed in braces ("{}"). Adjacent
	 * mappings are separated by the characters ", " (comma and space). Each
	 * key-value mapping is rendered as the key followed by an equals sign ("=")
	 * followed by the associated value.
	 * 
	 * @return a String representation of this map.
	 */
	@Override
	public String toString() {
		//logger.debug("(+)toString(+)");
		String prefix = "{";
		String suffix = "}";
		StringBuffer result = new StringBuffer();
		String string = "";
		try {
			String mapString = currentMap.toString();
			mapString = mapString.substring(mapString.indexOf("{") + 1,
					mapString.indexOf("}"));
			result.append(mapString);
			result.append(", ");
			Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
			for (Entry<File, Long> entry : entrySet) {
				if ((currentMapFile != null && currentMapFile.exists() && !entry
						.getKey().getAbsolutePath().equals(
								currentMapFile.getAbsolutePath()))
						|| (currentMapFile == null)) {
					Map<K, V> map = readMap(entry.getKey());
					mapString = map.toString();
					mapString = mapString.substring(mapString.indexOf("{") + 1,
							mapString.indexOf("}"));
					result.append(mapString);
					result.append(", ");
				}
			}
			if (result.lastIndexOf(" ") >= 0)
				result.deleteCharAt(result.lastIndexOf(" "));
			if (result.lastIndexOf(",") >= 0)
				result.deleteCharAt(result.lastIndexOf(","));
			if (result.length() > 0)
				string = "[" + result + "]";
			string = prefix + string + suffix;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		//logger.debug("(-)toString(-)");
		return string;
	}

	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result
	// + ((backupDir == null) ? 0 : backupDir.hashCode());
	// result = prime * result
	// + ((backupFilesMap == null) ? 0 : backupFilesMap.hashCode());
	// result = prime * result
	// + (int) (currentEntries ^ (currentEntries >>> 32));
	// result = prime * result
	// + ((currentMap == null) ? 0 : currentMap.hashCode());
	// result = prime * result + (currentMapChanged ? 1231 : 1237);
	// result = prime * result
	// + ((currentMapFile == null) ? 0 : currentMapFile.hashCode());
	// result = prime * result + (int) (maxEntries ^ (maxEntries >>> 32));
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass()
	// && !HashMap.class.equals(obj.getClass()))
	// return false;
	// try {
	// if (HashMap.class.equals(obj.getClass())) {
	// if (!toHashMap().equals(obj))
	// return false;
	// } else if (!toHashMap().equals(
	// ((PersistentHashMap<K,V>) obj).toHashMap()))
	// return false;
	// } catch (Exception ex) {
	// throw new RuntimeException(ex);
	// }
	// return true;
	// }

	/**
	 * Returns a HashMap representation of this PersistentHashMap. Since a
	 * PersistentHashMap can be potentially bigger than the system memory size,
	 * there is a risk of running out of heap space by calling this method
	 * 
	 * @return HashMap representation of this PersistentHashMap
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public HashMap<K, V> toHashMap() throws Exception {
		//logger.debug("(+)toMap(+)");
		Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
		HashMap<K, V> resultMap = new HashMap<K, V>();
		resultMap.putAll(currentMap);
		for (Entry<File, Long> entry : entrySet) {
			// System.out.println(entry.getKey());
			if ((currentMapFile != null && currentMapFile.exists() && !entry
					.getKey().getAbsolutePath().equals(
							currentMapFile.getAbsolutePath()))
					|| (currentMapFile == null))
				resultMap.putAll(readMap(entry.getKey()));
		}
		//logger.debug("(-)toMap(-)");
		return resultMap;
	}

	/**
	 * Returns true if this map contains a mapping for the specified key
	 * 
	 * @param key
	 *            key whose presence in this map is to be tested.
	 * @return true if this map contains a mapping for the specified key
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public boolean containsKey(Object key) throws Exception {
		//logger.debug("(+)containsKey(+)");
		boolean result = false;
		result = currentMap.containsKey(key);
		if (!result) {
			Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
			for (Entry<File, Long> entry : entrySet) {
				if ((currentMapFile != null && currentMapFile.exists() && !entry
						.getKey().getAbsolutePath().equals(
								currentMapFile.getAbsolutePath()))
						|| (currentMapFile == null))
					result = readMap(entry.getKey()).containsKey(key);
				if (result)
					break;
			}
		}
		//logger.debug("(-)containsKey(-)");
		return result;
	}

	/**
	 * Returns true if this map maps one or more keys to this value. More
	 * formally, returns true if and only if this map contains at least one
	 * mapping to a value v such that (value==null ? v==null : value.equals(v)).
	 * 
	 * @param value
	 *            value whose presence in this map is to be tested.
	 * @return true if this map maps one or more keys to this value.
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public boolean containsValue(Object value) throws Exception {
		//logger.debug("(+)containsValue(+)");
		boolean result = false;
		result = currentMap.containsValue(value);
		if (!result) {
			Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
			for (Entry<File, Long> entry : entrySet) {
				if ((currentMapFile != null && currentMapFile.exists() && !entry
						.getKey().getAbsolutePath().equals(
								currentMapFile.getAbsolutePath()))
						|| (currentMapFile == null))
					result = readMap(entry.getKey()).containsValue(value);
				if (result)
					break;
			}
		}
		//logger.debug("(-)containsValue(-)");
		return result;
	}

	// private Set<Map.Entry<K, V>> entrySet() throws Exception {
	// //logger.debug("(+)entrySet(+)");
	// Set<Map.Entry<K, V>> resultSet = new HashSet<Map.Entry<K, V>>();
	// resultSet.addAll(currentMap.entrySet());
	// Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
	// for (Entry<File, Long> entry : entrySet) {
	// if ((currentMapFile != null && currentMapFile.exists() && !entry
	// .getKey().getAbsolutePath().equals(
	// currentMapFile.getAbsolutePath()))
	// || (currentMapFile == null))
	// resultSet.addAll(readMap(entry.getKey()).entrySet());
	// }
	// logger.debug("(-)entrySet(-)");
	// return resultSet;
	//	}

	/**
	 * Returns true if this map contains no key-value mappings.
	 * 
	 * @return true if this map contains no key-value mappings.
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public boolean isEmpty() throws Exception {
		//logger.debug("(+)isEmpty(+)");
		boolean empty = true;
		if (!currentMap.isEmpty())
			empty = false;
		else {
			Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
			for (Entry<File, Long> entry : entrySet) {
				if ((currentMapFile != null && currentMapFile.exists() && !entry
						.getKey().getAbsolutePath().equals(
								currentMapFile.getAbsolutePath()))
						|| (currentMapFile == null)) {
					empty = empty && readMap(entry.getKey()).isEmpty();
				}
				if (!empty)
					break;
			}
		}
		//logger.debug("(-)isEmpty(-)");
		return empty;
	}

	/**
	 * Copies all of the mappings from the specified map to this map (optional
	 * operation). These mappings will replace any mappings that this map had
	 * for any of the keys currently in the specified map.
	 * 
	 * @param map
	 *            mappings to be stored in this map.
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public void putAll(Map map) throws Exception {
		//logger.debug("(+)putAll(+)");
		if (map == null)
			throw new NullPointerException("Specified map is null!!");
		else {
			Set<Entry<K, V>> entrySet = map.entrySet();
			for (Entry<K, V> entry : entrySet)
				this.put(entry.getKey(), entry.getValue());
		}
		//logger.debug("(-)putAll(-)");
	}

	/**
	 * Returns the number of key-value mappings in this map. If the map contains
	 * more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 * 
	 * @return the number of key-value mappings in this map.
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public int size() throws Exception {
		//logger.debug("(+)size(+)");
		int returnSize;
		long size = currentMap.size();
		Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
		for (Entry<File, Long> entry : entrySet) {
			if ((currentMapFile != null && currentMapFile.exists() && !entry
					.getKey().getAbsolutePath().equals(
							currentMapFile.getAbsolutePath()))
					|| (currentMapFile == null))
				size += readMap(entry.getKey()).size();
		}
		if (size > Integer.MAX_VALUE)
			returnSize = Integer.MAX_VALUE;
		else
			returnSize = (int) size;
		//logger.debug("(-)size(-)");
		return returnSize;
	}

	/**
	 * Removes the mapping for this key from this map if present (optional
	 * operation).
	 * 
	 * @param key
	 *            key whose mapping is to be removed from the map.
	 * @return previous value associated with specified key, or null if there
	 *         was no entry for key. (A null return can also indicate that the
	 *         map previously associated null with the specified key, if the
	 *         implementation supports null values.)
	 * @throws Exception
	 *             if an internal error occurred
	 */
	public V remove(Object key) throws Exception {
		//logger.debug("(+)remove(+)");
		//logger.debug("Arg:key->" + key);
		V returnValue = null;
		if (currentMap.containsKey(key)) {
			returnValue = currentMap.remove(key);
			currentEntries--;
		} else {
			Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
			for (Entry<File, Long> entry : entrySet) {
				if ((currentMapFile != null && currentMapFile.exists() && !entry
						.getKey().getAbsolutePath().equals(
								currentMapFile.getAbsolutePath()))
						|| (currentMapFile == null)) {
					Map<K, V> tempMap = readMap(entry.getKey());
					if (tempMap.containsKey(key)) {
						returnValue = tempMap.remove(key);
						entry.setValue(entry.getValue() - 1);
						writeMap(tempMap, entry.getKey());
						break;
					}
				}
			}
		}
		// logger.debug("(-)remove(-)");
		return returnValue;
	}
}
