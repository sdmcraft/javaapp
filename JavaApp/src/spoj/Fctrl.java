package spoj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Fctrl {

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
		int power2 = 0;
		int power5 = 0;
		for (int i = 1; i <= num; i++) {
			power2 += powerOf2(i);
			power5 += powerOf5(i);
		}
		return power2 < power5 ? power2 : power5;
	}

	public static int powerOf2(int num) {
		return Integer.numberOfTrailingZeros(num);
	}

	public static int powerOf5(int num) {
		int count = 0;
		while (num % 5 == 0) {
			num /= 5;
			count++;
		}
		// String numBase5 = Integer.toString(num, 5);
		// return numberOfTrailingZeros(numBase5);
		return count;
	}

	public static int numberOfTrailingZeros(String num) {
		char[] numArr = num.toCharArray();
		int count = 0;
		for (int i = numArr.length - 1; i >= 0; i--)
			if (numArr[i] != '0')
				break;
			else
				count++;
		return count;
	}

	public static void main1(String[] args) {
		System.out.println(powerOf2(1));
		System.out.println(powerOf5(1));
	}

}
