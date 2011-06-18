package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SAMER08F {
	public static void main(String[] args) {
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				String line = "";
				line = reader.readLine();
				if ("0".equals(line))
					break;
				result.append(method(line));
				result.append(System.getProperty("line.separator"));

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
		int n = Integer.parseInt(s);
		long result = n * (n + 1) * (2 * n + 1) / 6;
		return Long.toString(result);
	}

}
