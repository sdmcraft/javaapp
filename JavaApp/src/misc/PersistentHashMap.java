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

import org.apache.log4j.Logger;

public class PersistentHashMap<K, V> implements Serializable {

	public static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(misc.PersistentHashMap.class);

	private long maxEntries = 1000000L;
	private File backupDir = null;
	private Map<File, Long> backupFilesMap = new HashMap<File, Long>();

	private long currentEntries = 0L;
	private Map<K, V> currentMap = new HashMap<K, V>();
	private File currentMapFile = null;
	private boolean currentMapChanged = false;

	public PersistentHashMap(File backupDir, long maxEntries) throws Exception {
		logger.debug("(+)PersistentHashMap(+)");
		logger.debug("Args:backupDir->" + backupDir);
		logger.debug("Args:maxEntries->" + maxEntries);
		this.backupDir = backupDir;
		this.maxEntries = maxEntries;
		if (backupDir.exists())
			Utils.deleteFolder(backupDir);
		backupDir.mkdir();
		logger.debug("(-)PersistentHashMap(-)");
	}

	public void clear() throws Exception {
		logger.debug("(+)clear(+)");
		Utils.deleteFolder(backupDir);
		backupFilesMap = new HashMap<File, Long>();
		currentEntries = 0L;
		currentMap = new HashMap<K, V>();
		currentMapFile = null;
		currentMapChanged = false;
		logger.debug("(-)clear(-)");
	}

	private void createCurrentMap() {
		logger.debug("(+)createCurrentMap(+)");
		currentEntries = 0L;
		currentMap = new HashMap<K, V>();
		currentMapFile = null;
		currentMapChanged = false;
		logger.debug("(-)createCurrentMap(-)");
	}

	public V get(Object key) throws Exception {
		logger.debug("(+)get(+)");
		logger.debug("Args:key->" + key);
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
		logger.debug("(-)get(-)");
		return returnValue;
	}

	private void switchCurrentMap(File mapFile) throws Exception {
		logger.debug("(+)switchCurrentMap(+)");
		logger.debug("Args:mapFile->" + mapFile);
		writeCurrentMap();
		readCurrentMap(mapFile);
		logger.debug("(-)switchCurrentMap(-)");
	}

	private void readCurrentMap(File mapFile) throws Exception {
		logger.debug("(+)readCurrentMap(+)");
		logger.debug("Args:mapFile->" + mapFile);
		currentMapFile = mapFile;
		currentEntries = backupFilesMap.get(currentMapFile);
		currentMap = readMap(mapFile);
		currentMapChanged = false;
		logger.debug("(-)readCurrentMap(-)");
	}

	private Map<K, V> readMap(File mapFile) throws Exception {
		logger.debug("(+)readMap(+)");
		logger.debug("Args:mapFile->" + mapFile);
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(mapFile));
			logger.debug("(-)readMap(-)");
			return (Map<K, V>) in.readObject();
		} finally {
			if (in != null)
				in.close();
		}
	}

	private void writeMap(Map<K, V> map, File mapFile) throws Exception {
		logger.debug("(+)writeMap(+)");
		logger.debug("Args:map->" + map);
		logger.debug("Args:mapFile->" + mapFile);
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(mapFile));
			out.writeObject(map);

		} finally {
			if (out != null)
				out.close();
		}
		logger.debug("(-)writeMap(-)");
	}

	private void writeCurrentMap() throws Exception {
		logger.debug("(+)writeCurrentMap(+)");
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
		logger.debug("(-)writeCurrentMap(-)");
	}

	private boolean switchToVacantMap() throws Exception {
		logger.debug("(+)switchToVacantMap(+)");
		Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
		boolean result = false;
		for (Entry<File, Long> entry : entrySet) {
			if (entry.getValue() < maxEntries) {
				result = true;
				switchCurrentMap(entry.getKey());
				break;
			}
		}
		logger.debug("(-)switchToVacantMap(-)");
		return result;
	}

	public V put(K key, V value) throws Exception {
		logger.debug("(+)put(+)");
		logger.debug("Args:key->" + key);
		logger.debug("Args:value->" + value);
		V returnValue = null;
		writeCurrentMap();
		if (this.get(key) != null) {
			;/* To ensure that map containing this key becomes current map */
		} else if (currentEntries < maxEntries) {
			logger.debug("currentEntries" + currentEntries);
			currentEntries++;
		} else {
			logger.debug("currentEntries" + currentEntries);
			if (!switchToVacantMap()) {
				writeCurrentMap();
				logger.debug("Creating a new current map");
				createCurrentMap();
			}
			currentEntries++;
		}
		returnValue = currentMap.put(key, value);
		currentMapChanged = true;
		logger.debug("(-)put(-)");
		return returnValue;
	}

	public String toString() {
		logger.debug("(+)toString(+)");
		String prefix = "{";
		String suffix = "}";
		StringBuffer result = new StringBuffer();
		String string = "";
		try {
			writeCurrentMap();
			Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
			for (Entry<File, Long> entry : entrySet) {
				Map<K, V> map = readMap(entry.getKey());
				String mapString = map.toString();
				mapString = mapString.substring(mapString.indexOf("{") + 1,
						mapString.indexOf("}"));
				result.append(mapString);
				result.append(", ");
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
		logger.debug("(-)toString(-)");
		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((backupDir == null) ? 0 : backupDir.hashCode());
		result = prime * result
				+ ((backupFilesMap == null) ? 0 : backupFilesMap.hashCode());
		result = prime * result
				+ (int) (currentEntries ^ (currentEntries >>> 32));
		result = prime * result
				+ ((currentMap == null) ? 0 : currentMap.hashCode());
		result = prime * result + (currentMapChanged ? 1231 : 1237);
		result = prime * result
				+ ((currentMapFile == null) ? 0 : currentMapFile.hashCode());
		result = prime * result + (int) (maxEntries ^ (maxEntries >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PersistentHashMap other = (PersistentHashMap) obj;
		if (backupDir == null) {
			if (other.backupDir != null)
				return false;
		} else if (!backupDir.equals(other.backupDir))
			return false;
		if (backupFilesMap == null) {
			if (other.backupFilesMap != null)
				return false;
		} else if (!backupFilesMap.equals(other.backupFilesMap))
			return false;
		if (currentEntries != other.currentEntries)
			return false;
		if (currentMap == null) {
			if (other.currentMap != null)
				return false;
		} else if (!currentMap.equals(other.currentMap))
			return false;
		if (currentMapChanged != other.currentMapChanged)
			return false;
		if (currentMapFile == null) {
			if (other.currentMapFile != null)
				return false;
		} else if (!currentMapFile.equals(other.currentMapFile))
			return false;
		if (maxEntries != other.maxEntries)
			return false;
		return true;
	}

	public HashMap<K, V> toHashMap() throws Exception {
		logger.debug("(+)toMap(+)");
		writeCurrentMap();
		Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
		HashMap<K, V> resultMap = new HashMap<K, V>();
		for (Entry<File, Long> entry : entrySet)
			resultMap.putAll(readMap(entry.getKey()));
		logger.debug("(-)toMap(-)");
		return resultMap;
	}
}
