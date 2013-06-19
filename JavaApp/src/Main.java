import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class Main {
	public static void main1(String[] args) throws Exception {
		class PasswordAuthenticator extends Authenticator {
			String m_username;
			String m_password;

			public PasswordAuthenticator(String username, String password) {
				super();
				this.m_username = username;
				this.m_password = password;
			}

			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(m_username, m_password);
			}
		}

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props,
				new PasswordAuthenticator("m.satyadeep@gmail.com", "xxx"));
		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"m.satyadeep@gmail.com"));
		mimeMessage.setSubject("Test Email");
		mimeMessage.setText("Hello World!!");
		Transport.send(mimeMessage);
	}

	public static void fireRequest(String strURL) {
		try {

			URL url = new URL(strURL.toString());

			/*
			 * this is another method of calling the below function. However as
			 * status code itself is sufficient so commenting it out
			 * 
			 * 
			 * 
			 * BufferedReader in = new BufferedReader(new
			 * InputStreamReader(url.openStream())); String inputLine; while
			 * ((inputLine = in.readLine()) != null) {
			 * System.out.println(inputLine); }
			 * 
			 * in.close();
			 */

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(15 * 1000);
			connection.connect();
			if (connection.getResponseCode() == 200) {
				// System.out.println("PASS:-File " + strURL
				// +" Downloaded Successfully");

				// Second Level Verification
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine = "";
				while ((inputLine = in.readLine()) != null) {
					// System.out.println(inputLine);
					// Everything correct
				}
				in.close();
			} else
				System.out.println("FAIL:-File " + strURL
						+ " Failed to Download");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main2(String[] args) {
		for (String s : TimeZone.getAvailableIDs()) {
			//System.out.println(s);
			System.out.println(TimeZone.getTimeZone(s));
		}
	}
	
	public static void main(String[] args) {
		Set<Integer> set = new TreeSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		Iterator<Integer> itr = set.iterator();
		while(itr.hasNext())
		{			
			System.out.println(itr.next());
		}
		
		itr = set.iterator();
		while(itr.hasNext())
		{			
			System.out.println(itr.next());
		}
	}
}