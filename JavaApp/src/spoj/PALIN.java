package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

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
		BigInteger input = new BigInteger(s);
		StringBuilder sb = null;
		while (!result) {
			input = input.add(new BigInteger("1"));
			sb = new StringBuilder(input.toString());
			sb.reverse();
			result = input.toString().equals(sb.toString());
		}
		return sb.toString();
	}

}
