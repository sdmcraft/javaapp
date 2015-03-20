package misc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Socket myClient = null;
		OutputStream output = null;
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream("test.txt"),
					256);
			myClient = new Socket("localhost", 9002);
			output = new BufferedOutputStream(myClient.getOutputStream());
			byte[] buffer = new byte[256];

			while (input.read(buffer) != -1) {
				output.write(buffer);
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (input != null)
				input.close();
			if (output != null)
				output.close();
			if (myClient != null)
				myClient.close();
		}
	}
}
