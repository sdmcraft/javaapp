package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SOPJ {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		String result = "";
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));			
			String line = reader.readLine();
			int testCaseCount = 0;
			if (line != null)
				testCaseCount = Integer.parseInt(line);
			for (int i = 0; i < testCaseCount; i++) {
				line = reader.readLine();
				if (line != null) {
					int from = Integer.parseInt(line.split(" ")[0]);
					int to = Integer.parseInt(line.split(" ")[1]);
					for (Integer n : getPrimeNumbers(from, to)) {
						result += n;
						result += "\n";
					}
				}
				result += "\n";
			}
			System.out.println(result);
		} finally {
			if (reader != null)
				reader.close();
			System.exit(0);
		}
	}

	public static List<Integer> getPrimeNumbers(int from, int to) {
		List<Integer> result = new ArrayList<Integer>();
		for (int number = from; number <= to; number++) {
			int i;
			for (i = 2; i < number / 2; i++) {
				if (number % i == 0) {
					break;
				}
			}
			if ((i == number / 2 && number != 4) || number == 2 || number == 3) {
				result.add(number);
				if (number != 2)
					number++;
			}
		}
		return result;
	}

}
