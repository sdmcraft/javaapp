package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class CrudeTCPServer {

	private final int port;
	private final ServerSocket serverSocket;
	private final Map<String, Connection> connectionMap;

	private class Connection implements Runnable {
		private final Socket socket;
		private final String name;
		private final CrudeTCPServer server;
		private BufferedReader reader;
		private PrintWriter writer;

		private Connection(Socket socket, String name, CrudeTCPServer server)
				throws IOException {
			this.socket = socket;
			this.name = name;
			this.server = server;
			reader = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())));
		}

		private void send(String message) {
			writer.println(message);
			writer.flush();
		}

		private void messageInterpreter(String message) throws IOException {
			if ("shutdown".equals(message)) {
				server.stop();
			}
			if ("hello".equals(message)) {
				send("I accept your hello");
			}
		}

		@Override
		public void run() {
			InputStream in = null;
			try {
				in = socket.getInputStream();
				while (in.available() != 0) {
					String message = reader.readLine();
					System.out.println("[" + name + "]:" + message);
					messageInterpreter(message);
				}
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			} finally {
				try {
					if (in != null)
						in.close();
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
				connectionMap.remove(name);
			}
		}

	}

	public CrudeTCPServer(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(port);
		this.connectionMap = new HashMap<String, Connection>();
	}

	public void start() throws IOException {
		System.out.println("Server ready to accept connections");
		while (true) {
			Socket socket = serverSocket.accept();
			String connectionName = "Connection:" + connectionMap.size() + 1
					+ "@" + System.currentTimeMillis();
			Connection connection = new Connection(socket, connectionName, this);
			connectionMap.put(connectionName, connection);
			new Thread(connection).start();
		}
	}

	public void stop() throws IOException {
		System.out.println("Server going to shutdown");
		serverSocket.close();
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		CrudeTCPServer server = new CrudeTCPServer(2000);
		server.start();
	}

}
