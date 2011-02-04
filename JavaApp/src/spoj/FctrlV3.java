package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FctrlV3 {

	public static int maxVal;

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
			int[] inputArr = new int[testCaseCount];
			int[] outputArr = new int[testCaseCount];
			for (int i = 0; i < testCaseCount; i++) {
				line = reader.readLine();
				inputArr[i] = Integer.parseInt(line);
			}
			maxVal = max(inputArr);
			method(maxVal, inputArr, outputArr);
			for (int i : outputArr) {
				result.append(i);
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

	public static int powerOf2(int num) {
		return Integer.numberOfTrailingZeros(num);
	}

	public static int powerOf5(int num) {
		int count = 0;
		while (num % 5 == 0) {
			num /= 5;
			count++;
		}
		return count;
	}

	public static int max(int[] arr) {
		int max = arr[0];
		for (int i : arr)
			if (max < i)
				max = i;
		return max;
	}

	public static int search(int[] arr, int item) {
		int pos = -1;
		for (int i = 0; i < arr.length; i++)
			if (item == arr[i]) {
				pos = i;
				break;
			}
		return pos;
	}

	public static void method(int num, int inputArr[], int outputArr[]) {
		int power2 = 0;
		int power5 = 0;
		for (int i = 1; i <= num; i++) {
			power2 += powerOf2(i);
			power5 += powerOf5(i);
			int pos = search(inputArr, i);
			if (pos != -1)
				outputArr[pos] = power2 < power5 ? power2 : power5;
		}
	}

	public static void main1(String[] args) {
		System.out.println(powerOf2(1));
		System.out.println(powerOf5(1));
	}

}
