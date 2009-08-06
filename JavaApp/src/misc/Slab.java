package misc;

import java.io.FileReader;
import java.io.LineNumberReader;

public class Slab {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		double rows = -1;
		double content = -1;
		LineNumberReader lineReader = null;
		String line = null;
		lineReader = new LineNumberReader(new FileReader("C:\\input.txt"));
		boolean finished = false;
		while (!finished) {
			line = lineReader.readLine();
			if (line == null) {
				finished = true;
				break;
			}
			rows = Double.parseDouble(line.split(",")[0]);
			content =Double.parseDouble(line.split(",")[1]) * 1024;

			int bracket = -1;
			if (rows > 1000000L)
				bracket = 6;
			else if (rows > 7000000L
					&& rows <= 1000000L)
				bracket = 5;
			else if (rows > 5000000L
					&& rows <= 7000000L)
				bracket = 4;
			else if (rows > 2000000L
					&& rows <= 5000000L)
				bracket = 3;
			else if (rows > 10000000L
					&& rows <= 2000000L)
				bracket = 2;
			else if (rows >= 0L
					&& rows <= 1000000L)
				bracket = 1;

			double readTime = -1;
			switch (bracket) {
			case 1:
				readTime = rows * 0.0006;
				break;
			case 2:
				readTime = 600 + rows * 0.0003;
				break;
			case 3:
				readTime = 1800 + rows * 0.0001;
				break;
			case 4:
				readTime = 3600 + rows * 0.0001;
				break;
			case 5:
				readTime = 5400 + rows * 0.0001;
				break;
			case 6:
				readTime = 7200 + rows * 0.0001;
				break;
			}

			if (rows > 1000000L)
				bracket = 5;
			else if (rows > 800000L
					&& rows <= 1000000L)
				bracket = 4;
			else if (rows > 500000L
					&& rows <= 800000L)
				bracket = 3;
			else if (rows > 200000L
					&& rows <= 500000L)
				bracket = 2;
			else if (rows >= 0L
					&& rows <= 200000L)
				bracket = 1;

			double writeTime = -1;
			switch (bracket) {
			case 1:
				writeTime = rows * 0.00045;
				break;
			case 2:
				writeTime = 900 + rows * 0.0009;
				break;
			case 3:
				writeTime = 1500 + rows * 0.0018;
				break;
			case 4:
				writeTime = 3600 + rows * 0.0036;
				break;
			case 5:
				writeTime = 5400 + rows * 0.0072;
				break;
			}

			if (content > 51200)
				bracket = 6;
			else if (content > 30720
					&& content <= 51200)
				bracket = 5;
			else if (content > 5120
					&& content <= 30720)
				bracket = 4;
			else if (content > 2048
					&& content <= 5120)
				bracket = 3;
			else if (content > 1024
					&& content <= 2048)
				bracket = 2;
			else if (content >= 0)
				bracket = 1;

			double contentTime = -1;
			switch (bracket) {
			case 1:
				contentTime = content * 1/12;
				break;
			case 2:
				contentTime = 900 + content * 1/15;
				break;
			case 3:
				contentTime = 1500 + content * 1/15;
				break;
			case 4:
				contentTime = 3600 + content * 1/18;
				break;
			case 5:
				contentTime = 5400 + content * 1/54;
				break;
			case 6:
				contentTime = 5400 + content * 1/150;
				break;

			}

			System.out.println(Math.round(readTime/60) + "," + Math.round(writeTime/60) + "," + Math.round(contentTime/60)
					+ "," + Math.round((readTime + writeTime + contentTime)/60));
		}
	}

}
