package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
	public static List<String> delimitedReader(String delimiter, File f)
			throws IOException, InterruptedException {

		List<String> result = new ArrayList<String>();
		List<Byte> content = new ArrayList<Byte>();
		byte[] delimBytes = delimiter.getBytes();
		
		System.out.println(delimBytes.length);
		
		PushbackInputStream pbin = new PushbackInputStream(new FileInputStream(
				f), delimBytes.length);
		byte[] buffer = new byte[delimBytes.length];
		int b;

		int read = pbin.read(buffer);
		
		System.out.println("Here is the delimiter:");
		for (byte x : delimBytes)
			System.out.print(Integer.toHexString(x) + " ");
		System.out.println("");

		System.out.println("Here is the buffer:");
		for (byte x : buffer)
			System.out.print(Integer.toHexString(x) + " ");
		System.out.println("");

		if (byteArrayEquals(buffer, delimBytes))
		{
			pbin.close();
			return result;
		}
		else
			pbin.unread(buffer);

		while ((b = pbin.read()) != -1) {
			
			content.add(new Byte((byte) b));
			System.out.println("Read " + Integer.toHexString(b)
					+ " and added it to content");

			
			int z = pbin.read(buffer);
			System.out.println("Read buffer returned->"+z);
			if (z != -1) {
				System.out.println("Here is the buffer:");
				for (byte x : buffer)
					System.out.print(Integer.toHexString(x) + " ");
				System.out.println("");

				Thread.sleep(1000);

				if (byteArrayEquals(buffer, delimBytes)) {
					System.out.println("Found a delimiter");
					byte[] contentArr = new byte[content.size()];
					int count = 0;
					for (byte x : content) {
						contentArr[count++] = x;
					}

					content.clear();
					String s = new String(contentArr);
					System.out
							.println("This was read between last and this delimiter->"
									+ s);
					result.add(new String(contentArr));
				} else if(z < buffer.length)
					pbin.unread(buffer);
			}
		}
		pbin.close();
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

	public static void main(String[] args) throws Exception {
		List<String> result = delimitedReader("xxx", new File(
				"/home/sdmahesh/temp/test.txt"));
		for (String s : result)
			System.out.println(s);
	}

	public final static boolean compare(File f1, File f2) throws Exception {
		boolean result = false;
		if (f1 == f2)
			result = true;
		else if(f1== null || f2 == null)
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

	public static final void deleteFolder(File folder) throws Exception {
		if (folder.isDirectory()) {
			for (File file : folder.listFiles())
				deleteFolder(file);
		}
		if (!folder.delete())
			throw new Exception("Unable to delete directory!");
	}

}
