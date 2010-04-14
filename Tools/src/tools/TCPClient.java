package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

	Socket socket;
	BufferedReader reader;
	PrintWriter writer;

	public TCPClient() {

	}

	public void connect(String host, int port) throws UnknownHostException,
			IOException {
		socket = new Socket(host, port);
		reader = new BufferedReader(new InputStreamReader(socket
				.getInputStream()));
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream())));
	}

	public void disconnect() throws IOException {	
		socket.close();
	}

	public void send(String message) {
		writer.println(message);
		writer.flush();
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		TCPClient client = new TCPClient();
		client.connect("localhost", 2000);
		client.send("Hello");
		client.send("shutdown");
		client.disconnect();
	}

}
