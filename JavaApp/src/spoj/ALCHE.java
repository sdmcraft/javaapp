package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ALCHE {
	public static void main(String[] args) {
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			line = reader.readLine();
			while (!"-1 -1".equals(line)) {
				result.append(method(line));
				result.append(System.getProperty("line.separator"));
				line = reader.readLine();
			}
			System.out.println(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	public static String method(String s) {
		int i = Integer.parseInt(s.split(" ")[0]);
		int w = Integer.parseInt(s.split(" ")[1]);
		if (37 * i == 1000 * w)
			return "Y";
		else
			return "N";
	}

}
