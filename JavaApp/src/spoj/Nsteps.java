package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nsteps {
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
		long x = Long.parseLong(s.split(" ")[0]);
		long y = Long.parseLong(s.split(" ")[1]);
		if (x >= 0 && x == y) {
			if (x % 2 == 0) {
				return Long.toString(2 * x);
			} else
				return Long.toString(2 * x - 1);
		} else if (x >= 2 && y == x - 2) {
			if (x % 2 == 0) {
				return Long.toString(2 * x - 2);
			} else
				return Long.toString(2 * x - 3);
		} else
			return "No Number";
	}
}
