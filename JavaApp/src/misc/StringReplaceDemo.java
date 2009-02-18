package misc;

import java.io.*;

public class StringReplaceDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		replace(new File("C:\\sdm\\temp\\pest.txt"), "hello", "hi");
		System.out.println("hi1");
		// readReplace("C:\\sdm\\temp\\pest.txt", "hello", "hi");

	}

	private static void replace(File file, String oldString, String newString)
			throws Exception {
		LineNumberReader lineReader = null;
		BufferedWriter writer = null;
		File temp = null;
		boolean change = false;
		try {
			temp = new File(file.getParent() + File.separator + "temp");
			lineReader = new LineNumberReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(temp));
			String line = null;
			int lineNum = 0;
			while ((line = lineReader.readLine()) != null) {
				if (line.contains(oldString))
					change = true;
				writer.write(line.replace(oldString, newString));
				if (lineNum != lineReader.getLineNumber()) {
					lineNum = lineReader.getLineNumber();
					writer.newLine();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			writer.close();
			lineReader.close();
		}
		if (change) {
			String oldFile = file.getAbsolutePath();
			file.delete();
			temp.renameTo(new File(oldFile));
		} else
			temp.delete();
	}

	public static void readReplace(String fname, String oldPattern,
			String replPattern) throws Exception {
		String line;
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(fname);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					fis));
			while ((line = reader.readLine()) != null) {
				line = line.replaceAll(oldPattern, replPattern);
				sb.append(line + "\n");
			}
			reader.close();
			BufferedWriter out = new BufferedWriter(new FileWriter(fname));
			out.write(sb.toString());
			out.close();
		} catch (Exception e) {
			throw e;
		}
	}

}
