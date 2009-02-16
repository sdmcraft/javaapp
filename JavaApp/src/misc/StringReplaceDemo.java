package misc;

import java.io.*;

public class StringReplaceDemo {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		replace(new File("C:\\sdm\\temp\\pest.txt"), "hello", "hi");

	}

	private static void replace(File file, String oldString, String newString)
			throws Exception {
		LineNumberReader lineReader = null;
		FileWriter writer = null;
		try {
			File temp = new File(file.getParent() + File.separator + "temp");
			lineReader = new LineNumberReader(new FileReader(file));
			writer = new FileWriter(temp);
			String line = null;
			int lineNum = 0;
			while ((line = lineReader.readLine()) != null) {
				writer.write(line.replace(oldString, newString));
				if(lineNum != lineReader.getLineNumber())
				{
					lineNum = lineReader.getLineNumber();
					writer.write("\n");
				}				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			writer.close();
			lineReader.close();
		}
	}

}
