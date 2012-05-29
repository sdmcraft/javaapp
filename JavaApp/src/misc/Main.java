package misc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.SocketException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Main {
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws Exception,
			InterruptedException {
		//testSSLSocket();
		writeLoginCsv();
	}

	private static String readFileAsString(String filePath)
			throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;

		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		reader.close();

		return fileData.toString();
	}

	private static void testSSLSocket() throws Exception {
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket m_socket = (SSLSocket) factory.createSocket();
		m_socket.setSoTimeout(10000);
		m_socket.connect(new InetSocketAddress("euaxis.premconf.com", 443),
				10000);
		m_socket.startHandshake();
		try {
			m_socket.setSoTimeout(0);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeLoginCsv() throws Exception
	{
		PrintWriter pw = new PrintWriter("c:\\temp\\users.csv");
		for(int i=1;i<=100;i++)
			pw.println("event-15-" + i + "@adobe.com,breeze");
		pw.close();
	}

}
