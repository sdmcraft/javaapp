package misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tools.IOUtils;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws Exception,
			InterruptedException {
		String icalText = readFileAsString("C:\\temp\\a.txt");
		System.out.println(icalText);
		icalText = icalText.replaceAll("\r\n|\n", "\\\\n");
		System.out.println(icalText);
	}

	private static String readFileAsString(String filePath)
			throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}
}
