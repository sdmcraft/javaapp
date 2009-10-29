package network;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket myService = null;
		try {
			myService = new ServerSocket(9002);
			System.out.println("Server waiting to accept connections");
			while (true) {
				Socket clientSocket = null;
				InputStream in = null;
				try {
					clientSocket = myService.accept();
					System.out.println("Server accepted a connection request");
					in = new BufferedInputStream(clientSocket.getInputStream());
					new Thread(new StreamReader(in)).start();

				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					if (clientSocket != null)
						clientSocket.close();
					if (in != null)
						in.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (myService != null) {
				try {
					myService.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

}

class StreamReader implements Runnable {
	InputStream in;

	public StreamReader(InputStream in) {
		this.in = in;
	}

	public void run() {
		byte[] buffer = new byte[256];

		try {
			while (in.read(buffer) != -1) {
				System.out.println(buffer);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}
}
