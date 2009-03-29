package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
}
