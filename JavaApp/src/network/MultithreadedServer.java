package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
				clientSocket = myService.accept();
				System.out.println("Server accepted a connection request");
				new Thread(new StreamReader(clientSocket)).start();

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

	Socket clientSocket;

	public StreamReader(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		BufferedReader in = null;
		try {
			String line = null;
			in = new BufferedReader(new InputStreamReader(clientSocket
					.getInputStream()));
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (clientSocket != null)
					clientSocket.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}
}
