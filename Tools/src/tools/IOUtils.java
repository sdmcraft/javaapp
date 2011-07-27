package tools;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.PushbackInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataStructures.ArrayStack;

public class IOUtils {
	public static List<String> delimitedReader(String delimiter, File f)
			throws IOException, InterruptedException {

		List<String> result = new ArrayList<String>();
		List<Byte> content = new ArrayList<Byte>();
		byte[] delimBytes = delimiter.getBytes();

		PushbackInputStream pbin = new PushbackInputStream(new FileInputStream(
				f), delimBytes.length);

		/* Buffer to read and find if this is a delimiter */
		byte[] buffer = new byte[delimBytes.length];
		int b;

		try {
			while (pbin.read(buffer) != -1) {
				if (!byteArrayEquals(buffer, delimBytes)) {
					/* Not a delimiter, unread */
					pbin.unread(buffer);
					b = pbin.read();
					content.add(new Byte((byte) b));
				} else {
					byte[] contentArr = new byte[content.size()];
					int count = 0;
					for (byte x : content) {
						contentArr[count++] = x;
					}
					content.clear();
					result.add(new String(contentArr));
				}
			}
		} finally {
			pbin.close();
		}
		return result;
	}

	public static boolean byteArrayEquals(byte[] a, byte[] b) {
		boolean result = true;
		if (a == null || b == null)
			result = false;
		else if (a.length != b.length)
			result = false;
		else {
			int count = 0;
			for (byte x : a) {
				if (b[count++] != x) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	public final static boolean compare(File f1, File f2) throws Exception {
		boolean result = false;
		if (f1 == f2)
			result = true;
		else if (f1 == null || f2 == null)
			result = false;
		else if ((f1.isDirectory() && f2.isFile())
				|| (f1.isFile() && f2.isDirectory()))
			result = false;
		else if (f1.isDirectory() && f2.isDirectory()) {
			if (f1.list().length != f2.list().length)
				result = false;
			else {
				result = true;
				for (File f : f1.listFiles()) {
					FilenameFilter filter = getFilenameFilter(f.getName());
					File[] fileList = f2.listFiles(filter);
					if (fileList.length != 1) {
						result = false;
						break;
					} else
						result = result & compare(f, fileList[0]);
				}
			}
		} else if (f1.isFile() && f2.isFile()) {
			if (!f1.getName().equals(f2.getName()))
				result = false;
			else if (f1.getAbsolutePath().equals(f2.getAbsolutePath()))
				result = true;
			else if (f1.length() != f2.length())
				result = false;
			else {
				InputStream i1 = null;
				InputStream i2 = null;

				try {
					i1 = new FileInputStream(f1);
					i2 = new FileInputStream(f2);
					int b1, b2;
					do {
						b1 = i1.read();
						b2 = i2.read();
					} while (b1 == b2 && b1 != -1);
					result = b1 == -1;
				} finally {
					i1.close();
					i2.close();
				}
			}
		}
		return result;
	}

	final static FilenameFilter getFilenameFilter(final String name) {
		return new FilenameFilter() {
			public boolean accept(File dir, String fileName) {
				return fileName.equals(name);
			}
		};

	}

	public final static FilenameFilter getFileExtFilter(final String ext) {
		return new FilenameFilter() {
			public boolean accept(File dir, String fileName) {
				return fileName.endsWith("." + ext);
			}
		};
	}

	public static final void deleteFolder(File folder) throws Exception {
		if (folder.isDirectory()) {
			for (File file : folder.listFiles())
				deleteFolder(file);
		}
		if (!folder.delete())
			throw new Exception("Unable to delete directory!");
	}

	public static Object deserializeObject(File f) throws Exception {
		Object object = null;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(f));
			object = in.readObject();
		} finally {
			if (in != null)
				in.close();
		}
		return object;
	}

	public static void serializeObject(Object o, File f) throws Exception {
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(o);
		} finally {
			if (out != null)
				out.close();
		}
	}

	/*
	 * Remember that if source or its members do not implement serializable,
	 * this method would not work
	 */
	public static Object deepCopy(Object source) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(source);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		Object copy = ois.readObject();
		return copy;
	}

	public static String replace(String s, Map<String, String> idMap,
			String splitRegex) {
		String oldIds[] = s.split(splitRegex);
		for (String oldId : oldIds) {
			String newId = idMap.get(oldId);
			if (newId != null && !("".equals(newId))) {
				s = s.replace(oldId, newId);
			}
		}
		return s;
	}

	public static void saveKeyToFile(String fileName, BigInteger mod,
			BigInteger exp) throws IOException {
		File file = new File(fileName);
		ObjectOutputStream oout = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)));
		try {
			oout.writeObject(mod);
			oout.writeObject(exp);
		} catch (Exception e) {
			throw new IOException("Unexpected error", e);
		} finally {
			oout.close();
		}
	}

	public static String readFileAsString(String filePath)
			throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1024);
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

	public static String readLineFromFile(int lineNum, String filePath)
			throws IOException {
		LineNumberReader lineReader = new LineNumberReader(new BufferedReader(
				new FileReader(filePath)));
		int count = 0;
		String result = null;
		while (count < lineNum) {
			result = lineReader.readLine();
			count++;
		}
		return result;
	}

	public static int numLinesInFile(String filePath) throws IOException {
		LineNumberReader lineReader = new LineNumberReader(new BufferedReader(
				new FileReader(filePath)));
		int count = 0;
		while (lineReader.readLine() != null) {
			count++;
		}
		lineReader.close();
		return count;
	}

	public static String[] fileToArray(String file, int start, int end)
			throws Exception {
		LineNumberReader lineReader = new LineNumberReader(new BufferedReader(
				new FileReader(file)));
		String[] result = new String[end - start];
		int count = 0;
		while (lineReader.readLine() != null && count < start) {
			count++;
		}
		String line;
		while ((line = lineReader.readLine()) != null && count < end) {
			result[count - start] = line;
			count++;
		}
		lineReader.close();
		return result;
	}

	public static String[] fileToArray(String file) throws Exception {
		List<String> stringList = new ArrayList<String>();
		LineNumberReader lineReader = new LineNumberReader(new BufferedReader(
				new FileReader(file)));

		String line;
		while ((line = lineReader.readLine()) != null) {
			stringList.add(line);
		}
		lineReader.close();
		String[] result = (String[]) stringList.toArray(new String[stringList
				.size()]);

		return result;

	}

	public static void arrayToFile(int[] array, String file) throws Exception {
		PrintWriter pw = new PrintWriter(file);
		for (int i : array) {
			pw.println(i);
		}
		pw.close();
	}

	public static void replace(String file, int start, String[] replacement)
			throws Exception {
		LineNumberReader lineReader = new LineNumberReader(new BufferedReader(
				new FileReader(file)));
		File tempFile = new File(new File(file).getParent(), "tempFile");
		PrintWriter writer = new PrintWriter(tempFile);

		int count = 0;
		String line;
		while ((line = lineReader.readLine()) != null && count < start) {
			writer.println(line);
			count++;
		}
		int i = 0;
		while (lineReader.readLine() != null
				&& count < start + replacement.length) {
			count++;
			writer.println(replacement[i]);
			i++;
		}
		while ((line = lineReader.readLine()) != null) {
			writer.println(line);
		}
		lineReader.close();
		writer.close();
	}

	public static void merge(String file, int start1, int end1, int start2,
			int end2, int buffer) throws Exception {
		ArrayStack stack1 = new ArrayStack(buffer / 3);
		ArrayStack stack2 = new ArrayStack(buffer / 3);
		ArrayStack mergeStack = new ArrayStack(buffer / 3);
		int current1 = start1;
		int current2 = start2;

	}

	private static void FileToStack(String file, int start, ArrayStack stack) {

	}

	public static List<Long> numbersInText(String text) {
		Pattern numberPattern = Pattern.compile("\\s\\d\\d*\\s");
		Matcher matcher = numberPattern.matcher(text);
		List<Long> result = new ArrayList<Long>();

		while (matcher.find()) {
			result.add(new Long(matcher.group().trim()));
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// List<String> result = delimitedReader("xxx", new File(
		// "c:\\temp\\test.txt"));
		// for (String s : result)
		// System.out.println(s);
		// for (int i = 1; i <= 9; i++)
		// System.out.println(readLineFromFile(i, "C:\\temp\\temp.txt"));
		for (long l : numbersInText("abc 13 df 454 bfr 44 a1s"))
			System.out.println(l);
	}

}
