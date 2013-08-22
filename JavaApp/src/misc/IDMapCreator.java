package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class IDMapCreator {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.out.println("java IDMapCreator <Path to idMap.properties>");
			return;
		}

		File idMapFile = new File(args[0]);
		if (!idMapFile.exists() || !idMapFile.isFile()) {
			System.out.println("Please provide a valid idMap.properties file");
			return;
		}
		Properties idMapProps = new Properties();
		InputStream in = null;
		ObjectOutput out = null;
		try {
			in = new FileInputStream(idMapFile);
			idMapProps.load(in);
			Set entrySet = (idMapProps.entrySet());
			HashMap idMap = new HashMap();

			for (Object entry : entrySet) {
				Entry e = (Map.Entry) entry;
				idMap.put(e.getKey(), e.getValue());
			}
			
			out = new ObjectOutputStream(new FileOutputStream("idMap.ser"));
			out.writeObject(idMap);
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}
	}

}
