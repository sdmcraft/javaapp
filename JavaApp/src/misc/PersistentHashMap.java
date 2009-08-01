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
	private long currentEntries = 0L;
	private Map<File, Long> backupFilesMap = new HashMap<File, Long>();
	private Map<K, V> currentMap = new HashMap<K, V>();
	private File backupDir = null;

	public PersistentHashMap(File backupDir, long maxEntries) {
		this.backupDir = backupDir;
		this.maxEntries = maxEntries;
	}

	public V put(K key, V value) throws Exception {

		V returnValue = null;
		if (currentEntries < maxEntries) {
			returnValue = currentMap.put(key, value);
			currentEntries++;
		} else {
			File currentBkpFile = null;
			ObjectOutput out = null;
			ObjectInputStream in = null;
			try {
				currentBkpFile = new File(backupDir, Calendar.getInstance()
						.getTimeInMillis()
						+ ".ser");
				out = new ObjectOutputStream(new FileOutputStream(
						currentBkpFile));
				out.writeObject(currentMap);
				backupFilesMap.put(currentBkpFile, currentEntries);

				Set<Entry<File, Long>> entrySet = backupFilesMap.entrySet();
				boolean createNew = true;
				for (Entry<File, Long> entry : entrySet) {
					if (entry.getValue() < maxEntries) {
						createNew = false;
						in = new ObjectInputStream(new FileInputStream(entry
								.getKey()));
						currentMap = (Map<K, V>) in.readObject();
						currentEntries = entry.getValue();
						returnValue = currentMap.put(key, value);
						currentEntries++;
						break;
					}
				}
				if (createNew) {
					currentMap = new HashMap<K, V>();
					currentEntries = 0;
					returnValue = currentMap.put(key, value);
					currentEntries++;
				}

			} finally {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			}
		}
		return returnValue;
	}
}
