package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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
			writer = new PrintWriter(socket.getOutputStream(), true);
		}

		private void send(String message) {
			writer.println(message);
			writer.flush();
		}

		private void messageInterpreter(String message) throws IOException {
			if ("shutdown".equals(message)) {
				send("Server going to sutdown");
				socket.close();
				server.stop();
			}
			if ("hello".equalsIgnoreCase(message)) {
				send("I accept your hello");
			}
		}

		@Override
		public void run() {
			try {
				String message;
				while ((message = reader.readLine()) != null) {
					System.out.println("[" + name + "]:" + message);
					messageInterpreter(message);
				}
			} catch (SocketException ex) {
				System.out.println("Connection:" + name + " broken");
				connectionMap.remove(name);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			} finally {
				connectionMap.remove(name);
			}
		}

	}

	public CrudeTCPServer(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(port);
		this.connectionMap = new HashMap<String, Connection>();
	}

	public void start() throws Exception {
		try {
			EncryptUtils.generateKeyPair("keys/public.key", "keys/private.key");
			System.out.println("Server ready to accept connections");
			while (true) {
				Socket socket = serverSocket.accept();
				String connectionName = "Connection:" + connectionMap.size()
						+ 1 + "@" + System.currentTimeMillis();
				System.out.println("Created connection:" + connectionName);
				Connection connection = new Connection(socket, connectionName,
						this);
				connectionMap.put(connectionName, connection);
				new Thread(connection).start();
			}
		} catch (SocketException ex) {
			System.out.println("Server will no longer accept connections.");
		}
	}

	public void stop() throws IOException {
		System.out.println("Server going to shutdown");
		serverSocket.close();
		System.out.println("Server shutdown successful");
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		CrudeTCPServer server = new CrudeTCPServer(2001);
		server.start();
	}

}
