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

	public static String method(String expression) {
		StringBuilder finalResult = new StringBuilder();
		BigInteger operand1 = null;
		BigInteger operand2 = null;
		BigInteger result = null;
		String operation = null;
		int maxLen = 0;
		StringBuilder firstLine = new StringBuilder();
		StringBuilder secondLine = new StringBuilder();
		StringBuilder partialsSb = new StringBuilder();
		operand1 = new BigInteger(expression.split("[\\+\\-\\*]")[0]);
		operand2 = new BigInteger(expression.split("[\\+\\-\\*]")[1]);

		StringBuilder operand1Sb = new StringBuilder(operand1.toString());
		StringBuilder operand2Sb = new StringBuilder(operand2.toString());
		StringBuilder[] partials = new StringBuilder[operand2Sb.length()];

		if (expression.contains("+")) {
			operation = "+";
			result = operand1.add(operand2);
		} else if (expression.contains("-")) {
			operation = "-";
			result = operand1.subtract(operand2);
		} else if (expression.contains("*")) {
			operation = "*";
			result = operand1.multiply(operand2);
		}

		StringBuilder resultSb = new StringBuilder(result.toString());

		if ("*".equals(operation)) {
			for (int i = operand2Sb.length() - 1, j = 0; i >= 0; i--, j++) {

				BigInteger bi = new BigInteger(operand2Sb.charAt(i) + "");
				partials[j] = new StringBuilder(operand1.multiply(bi)
						.toString());
				partials[j] = new StringBuilder(rightPad(partials[j], j));
			}
			maxLen = max(partials);
			operand2Sb.insert(0, operation);
			if (maxLen < operand1Sb.length()) {
				maxLen = operand1Sb.length();
			}
			if (maxLen < operand2Sb.length()) {
				maxLen = operand2Sb.length();
			}

			if (partials.length > 1) {
				leftPad(secondLine, partials[partials.length - 2].length(), '-');
				leftPad(secondLine, resultSb.length(), '-');
			}
			
			leftPad(firstLine, operand2Sb.length(), '-');
			leftPad(firstLine, partials[0].length(), '-');

			for (int i = 0; i < partials.length; i++) {
				leftPad(partials[i], maxLen);
				partialsSb.append(partials[i]).append('\n');
			}
		}
		
		if ("+".equals(operation) || "-".equals(operation)) {
			operand2Sb.insert(0, operation);
			maxLen = max(operand1Sb.length(), operand2Sb.length(), resultSb
					.length());
			leftPad(firstLine, operand2Sb.length(), '-');
			leftPad(firstLine, resultSb.length(), '-');
		}

		leftPad(firstLine, maxLen);
		leftPad(operand1Sb, maxLen);
		leftPad(operand2Sb, maxLen);
		leftPad(resultSb, maxLen);
		finalResult.append(operand1Sb).append('\n').append(operand2Sb).append(
				'\n').append(firstLine).append('\n');
		if (partialsSb.length() > 0 && partials.length > 1) {
			finalResult.append(partialsSb);
		}

		if (secondLine.length() > 0) {
			leftPad(secondLine, maxLen);
			finalResult.append(secondLine).append('\n');
		}
		finalResult.append(resultSb);
		return finalResult.append('\n').toString();
	}

	private static void leftPad(StringBuilder sb, int length) {
		leftPad(sb, length, ' ');
	}

	private static void leftPad(StringBuilder sb, int length, char c) {
		int strLength = sb.length();
		for (int i = 0; i < length - strLength; i++)
			sb.insert(0, c);
	}

	private static StringBuilder rightPad(StringBuilder sb, int length) {
		for (int i = 0; i < length; i++)
			sb.append(" ");
		return sb;
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

	private static int max(StringBuilder[] arr) {
		int max = Integer.MIN_VALUE;
		for (StringBuilder s : arr) {
			if (max < s.length())
				max = s.length();
		}
		return max;
	}
}
