package spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Toandfro {
	public static void main(String[] args) {
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			int count = 0;
			int columns = 0;
			while (true) {
				count++;
				line = reader.readLine();
				if (count % 2 != 0) {
					columns = Integer.parseInt(line);
					if (columns == 0)
						break;
				}
				result.append(method(columns, line));
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

	public static String method(int cols, String s) {
		int rows = s.length() / cols;
		char[][] matrix = new char[rows][cols];
		int count = 0;
		int mode = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			mode++;
			if (mode % 2 != 0) {
				for (int j = 0; j < cols; j++) {
					matrix[i][j] = s.charAt(count++);
				}
			} else {
				for (int j = cols-1; j >= 0; j--) {
					matrix[i][j] = s.charAt(count++);
				}
			}
		}

		// for (int i = 0; i < rows; i++) {
		// for (int j = 0; j < cols; j++) {
		// System.out.print(matrix[i][j] + " ");
		// }
		// System.out.println();
		// }

		for (int j = 0; j < cols; j++) {
			for (int i = 0; i < rows; i++) {
				sb.append(matrix[i][j]);
			}
		}

		return sb.toString();
	}

}
