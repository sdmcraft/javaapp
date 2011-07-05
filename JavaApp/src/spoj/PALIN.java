package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PALIN {
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
		boolean result = false;
		long input = Long.parseLong(s);
		StringBuilder sb = null;
		while (!result) {
			input++;			
			sb = new StringBuilder(Long.toString(input));
			sb.reverse();			
			result = (input - Long.parseLong(sb.toString())) == 0L;
		}
		return sb.toString();
	}

}
