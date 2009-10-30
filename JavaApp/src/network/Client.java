package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		Socket myClient = null;
		PrintWriter output = null;
		try {
			myClient = new Socket("localhost", 9002);
			output = new PrintWriter(myClient.getOutputStream());
			while (true) {
				output.println(args[0]);
				output.flush();
				System.out.println("Data sent to server");
				Thread.sleep(2000);
			}
		} finally {
			if (output != null)
				output.close();
			if (myClient != null)
				myClient.close();
		}

	}

}
