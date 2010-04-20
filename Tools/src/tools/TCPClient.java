package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TCPClient {

	Socket socket;
	PrintWriter writer;
	SocketReader socketReader;

	public TCPClient() {

	}

	private class SocketReader implements Runnable {
		private final Socket socket;
		private final BufferedReader reader;

		private SocketReader(Socket socket) throws IOException {
			this.socket = socket;
			reader = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));

		}

		public void run() {
			try {
				String message;
				while ((message = reader.readLine()) != null) {
					System.out.println("[Server]:" + message);
				}
				System.out.println("Connection ended with server");
			} catch (SocketException ex) {
				System.out.println("Connection with server is broken");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	public void connect(String host, int port) throws UnknownHostException,
			IOException {
		socket = new Socket(host, port);
		writer = new PrintWriter(socket.getOutputStream(), true);
		socketReader = new SocketReader(socket);
		new Thread(socketReader).start();
	}

	public void disconnect() throws IOException {
		socket.close();
	}

	public void send(String message) {
		System.out.println("[Myself]:" + message);
		writer.println(message);
		writer.flush();
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws UnknownHostException,
			IOException, InterruptedException {
		TCPClient client = new TCPClient();
		client.connect("10.40.47.130", 2001);
		client.send("Hello");
		// Thread.sleep(1000);
		client.send("shutdown");
		// client.disconnect();
	}

}
