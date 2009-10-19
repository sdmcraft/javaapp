package misc;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class Deserialize {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		HashMap object = null;
		ObjectInputStream in = null;
		BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
		try {
			in = new ObjectInputStream(new FileInputStream(args[0]));
			object = (HashMap)in.readObject();
			String s = object.toString();
			for(String s1 : s.split(","))
			{
				writer.write(s1);
				writer.newLine();
			}
		} finally {
			if (in != null)
				in.close();
		}
	}

}
