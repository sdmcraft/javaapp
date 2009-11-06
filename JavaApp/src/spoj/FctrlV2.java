package spoj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FctrlV2 {

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
			for (int i = 0; i < testCaseCount; i++) {
				line = reader.readLine();
				inputArr[i] = Integer.parseInt(line);
			}
			maxVal = max(inputArr);
			int[] memory2 = new int[maxVal + 1];
			int[] memory5 = new int[maxVal + 1];
			for (int i : inputArr) {
				for (int j = 1; j <= i; j++) {
					if (memory2[j] == 0)
						memory2[j] = memory2[j - 1] + powerOf2(j);
					if (memory5[j] == 0)
						memory5[j] = memory5[j - 1] + powerOf5(j);
				}
				result
						.append(memory2[i] < memory5[i] ? memory2[i]
								: memory5[i]);
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

	public static void main1(String[] args) {
		System.out.println(powerOf2(1));
		System.out.println(powerOf5(1));
	}

}
