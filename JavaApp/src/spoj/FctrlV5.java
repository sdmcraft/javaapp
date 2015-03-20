package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FctrlV5 {

	public static void main(String[] args) {
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			// reader = new BufferedReader(new InputStreamReader(
			// new FileInputStream("test.txt")));
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

	public static int method(String numString) {
		int num = Integer.parseInt(numString);
		int divisor = 5;
		int zeroCount = 0;
		while (true) {
			int result = num / divisor;
			if (result == 0)
				break;
			zeroCount += result;
			divisor *= 5;
		}
		return zeroCount;
	}

	public static void main1(String[] args) {

	}

}
