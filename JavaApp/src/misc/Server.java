package misc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ServerSocket myService = null;
		Socket clientSocket = null;
		InputStream input = null;
		OutputStream output = null;

		try {
			myService = new ServerSocket(9002);
			System.out.println("Server waiting to accept connection");
			clientSocket = myService.accept();
			input = new BufferedInputStream(clientSocket.getInputStream());

			output = new BufferedOutputStream(new FileOutputStream("temp"), 256);
			byte[] buffer = new byte[256];

			while (input.read(buffer) != -1) {
				output.write(buffer);
			}

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (output != null)
				output.close();
			if (input != null)
				input.close();
			if (clientSocket != null)
				clientSocket.close();
			if (myService != null)
				myService.close();

		}

	}
}
