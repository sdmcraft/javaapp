package misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SOPJ {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 2)
			System.out.println("Usage:$SOPJ <inputfile> <outputfile>");
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(args[0]));
			writer = new BufferedWriter(new FileWriter(args[1]));
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
						writer.write(n.toString());
						writer.newLine();
					}
				}
				writer.newLine();
			}
		} finally {
			if (reader != null)
				reader.close();
			if (writer != null)
				writer.close();
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
