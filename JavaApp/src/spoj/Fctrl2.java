package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Fctrl2 {

	private static BigInteger[] memory = new BigInteger[101];

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			line = reader.readLine();
			int testCaseCount = Integer.parseInt(line);
			memory[0] = new BigInteger("1");
			for (int i = 0; i < testCaseCount; i++) {
				line = reader.readLine();
				result.append(method(Integer.parseInt(line)));
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

	public static String method(int num) {
		if (memory[num] != null)
			return memory[num].toString();
		else {
			int i = num;
			while (i >= 0 && memory[i] == null)
				i--;
			for (int j = i + 1; j <= num; j++)
				memory[j] = memory[j - 1].multiply(new BigInteger(Integer
						.toString(j)));
		}
		return memory[num].toString();
	}

}
