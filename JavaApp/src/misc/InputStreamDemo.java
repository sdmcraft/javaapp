package misc;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class InputStreamDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		InputStream in = new BufferedInputStream(new FileInputStream(
				"C:\\connectaddin.log"));
		readByte(in);
		readByte(in);
		readByte(in);
		readByte(in);
		in.close();
	}

	public static void readByte(InputStream in) throws Exception {
		System.out.println(in.read());
	}

}
