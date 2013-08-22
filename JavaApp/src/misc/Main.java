package misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
		// testSSLSocket();
		// writeLoginCsv();
		// printSQL();
		//writeUserList("event53", 1500);
		//writeGuestList("event1", 10);
		// appendToFile("C:\\temp\\text.txt", "Hello");
		// appendToFile("C:\\temp\\text.txt", "World");
		// Class.forName("misc.Main");
		String name = "xxx";
		String id = "yyy";
		System.out.println("{\"name\" : " + name + " \"id\" : " + id + "}");
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

	private static void writeLoginCsv() throws Exception {
		PrintWriter pw = new PrintWriter("c:\\temp\\users.csv");
		for (int i = 1; i <= 20; i++)
			pw.println("sdmimgtest-" + i + "@adobe.com,breeze");
		pw.close();
	}

	private static void writeUserList(String eventName, int count)
			throws Exception {
		PrintWriter pw = new PrintWriter("c:\\temp\\my-users.csv");
		pw.println("first-name,last-name,login,email,password");
		for (int i = 1; i <= count; i++)
			pw.println(eventName + "-first-" + i + "," + eventName + "-last-"
					+ i + "," + eventName + "user" + i + "@a.com" + ","
					+ eventName + "user" + i + "@a.com,breeze");
		pw.close();
	}

	private static void writeGuestList(String eventName, int count)
			throws Exception {
		PrintWriter pw = new PrintWriter("c:\\temp\\my-guests.csv");		
		for (int i = 1; i <= count; i++)
			pw.println(eventName + "-guest-first-" + i + "," + eventName + "-guest-last-"
					+ i + "," + eventName + "guest" + i + "@a.com");
		pw.close();
	}

	private static void printSQL() {
		System.out
				.println("SELECT TOP "
						+ 100
						+ " "
						+ "actions.action_id, "
						+ "actions.action_type_id, "
						+ "actions.target_acl_id, "
						+ "prefs.lang, "
						+ "actions.body, "
						+ "af.field_id, "
						+ "af.value, "
						+ "actions.status, "
						+ "actions.schedule, "
						+ "actions.date_scheduled "
						+ "FROM "
						+ "pps_actions actions LEFT OUTER JOIN pps_acl_preferences prefs ON actions.target_acl_id=prefs.acl_id, "
						+ "pps_acl_fields af "
						+ "WHERE "
						+ "actions.status in ('N', 'R')"
						+ "AND actions.date_scheduled < CURRENT_TIMESTAMP "
						+ "AND actions.action_id = af.acl_id "
						+ "AND actions.zone_id = ? "
						+ "ORDER BY "
						+ "actions.date_scheduled desc, actions.target_acl_id, actions.action_type_id");
	}

	private static void appendToFile(String file, String msg)
			throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(file, true));
		out.println(msg);
		out.close();
	}
	

}
