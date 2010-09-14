package demo;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String sURL = "https://www.google.com/accounts/ServiceLogin?service=mail";
			URL url = new URL(sURL);
			URLConnection httpConn = url.openConnection();
			httpConn.setDoInput(true);
			httpConn.connect();
			InputStream in = httpConn.getInputStream();
			BufferedInputStream bufIn = new BufferedInputStream(in);
			int nbytes;
			do {
				// Echo the response on the Java Console.
				// This is crude, and just for demo purposes.
				byte buf[] = new byte[8192];
				nbytes = bufIn.read(buf, 0, 8192);
				System.out.println(new String(buf, "US-ASCII"));
			} while (nbytes > 0);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

}
