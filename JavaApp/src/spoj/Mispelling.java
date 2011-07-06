package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Mispelling {
	public static void main(String[] args) {
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			line = reader.readLine();
			int testCaseCount = Integer.parseInt(line);
			for (int i = 0; i < testCaseCount; i++) {
				line = reader.readLine();
				result.append((i+1) + " " + method(line));
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
		int n = Integer.parseInt(s.split(" ")[0]);
		StringBuilder sb = new StringBuilder(s.split(" ")[1]);
		sb.deleteCharAt(n-1);
		return sb.toString();
	}

}
