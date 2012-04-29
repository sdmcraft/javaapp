package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class ARITH {
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
		BigInteger operand1 = null;
		BigInteger operand2 = null;
		BigInteger result = null;
		String operation = null;
		if (s.contains("+")) {
			operand1 = new BigInteger(s.split("\\+")[0]);
			operand2 = new BigInteger(s.split("\\+")[1]);
			operation = "add";
			result = operand1.add(operand2);
		} else if (s.contains("-")) {
			operand1 = new BigInteger(s.split("-")[0]);
			operand2 = new BigInteger(s.split("-")[1]);
			operation = "subtract";
			result = operand1.subtract(operand2);
		} else if (s.contains("*")) {
			operand1 = new BigInteger(s.split("\\*")[0]);
			operand2 = new BigInteger(s.split("\\*")[1]);
			operation = "multiply";
			result = operand1.multiply(operand2);
		}
		String operand1Str = operand1.toString();
		String operand2Str = operand2.toString();
		String resultStr = result.toString();
		if ("add".equals(operation) || "subtract".equals(operation)) {
			int maxLen = max(operand1Str.length(), operand2Str.length(),
					resultStr.length());
		}
		return null;
	}

	private static String leftPad(String s, int length) {
		int strLength = s.length();
		for (int i = 0; i < length - strLength; i++)
			s = " " + s;
		return s;
	}

	private static String rightPad(String s, int length) {
		int strLength = s.length();
		for (int i = 0; i < length - strLength; i++)
			s = s + " ";
		return s;
	}

	private static int max(int a, int b, int c) {
		if (a >= b && a >= c)
			return a;
		if (b >= a && b >= c)
			return b;
		if (c >= b && c >= a)
			return c;
		return -1;
	}

}
