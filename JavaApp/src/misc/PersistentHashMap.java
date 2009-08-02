package misc;

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

public class PersistentHashMap<K, V> implements Serializable {

	public static final long serialVersionUID = 1L;
	private long maxEntries = 1000000L;
	private File backupDir = null;
	private Map<File, Long> backupFilesMap = new HashMap<File, Long>();

	private long currentEntries = 0L;
	private Map<K, V> currentMap = new HashMap<K, V>();
	private File currentMapFile = null;
	private boolean currentMapChanged = false;

	public PersistentHashMap(File backupDir, long maxEntries) {
		this.backupDir = backupDir;
		this.maxEntries = maxEntries;
	}

	private void createCurrentMap() {
		currentEntries = 0L;
		currentMap = new HashMap<K, V>();
		currentMapFile = null;
		currentMapChanged = false;
	}

	public V get(Object key) throws Exception {
		V returnValue = null;
		if (currentMap.get(key) != null)
			returnValue = currentMap.get(key);
		else {
			Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
			for (Entry<File, Long> entry : entrySet) {
				switchCurrentMap(entry.getKey());
				if (currentMap.get(key) != null) {
					returnValue = currentMap.get(key);
					break;
				}
			}
		}
		return returnValue;
	}

	private void switchCurrentMap(File mapFile) throws Exception {
		writeCurrentMap();
		readCurrentMap(mapFile);
	}

	private void readCurrentMap(File mapFile) throws Exception {
		currentMapFile = mapFile;
		currentEntries = backupFilesMap.get(currentMapFile);
		currentMap = readMap(mapFile);
		currentMapChanged = false;

	}

	private Map<K, V> readMap(File mapFile) throws Exception {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(mapFile));
			return (Map<K, V>) in.readObject();
		} finally {
			if (in != null)
				in.close();
		}
	}

	private void writeMap(Map<K, V> map, File mapFile) throws Exception {
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(mapFile));
			out.writeObject(map);

		} finally {
			if (out != null)
				out.close();
		}
	}

	private void writeCurrentMap() throws Exception {
		if (currentMapChanged) {
			if (currentMapFile == null || !currentMapFile.exists()) {
				currentMapFile = new File(backupDir, Calendar.getInstance()
						.getTimeInMillis()
						+ ".ser");
			}
			writeMap(currentMap, currentMapFile);
			backupFilesMap.put(currentMapFile, currentEntries);
		}
	}

	public V put(K key, V value) throws Exception {

		V returnValue = null;
		if (this.get(key) != null) {
			;/* To ensure that map containing this key becomes current map */
		} else if (currentEntries < maxEntries) {
			currentEntries++;
		} else {
			writeCurrentMap();
			createCurrentMap();
			currentEntries++;
		}
		returnValue = currentMap.put(key, value);
		currentMapChanged = true;
		return returnValue;
	}
}
