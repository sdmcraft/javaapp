/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author satyam
 */
public class HTMLMailDemo {

	static int cid = 0;
	static Map<Integer, String> map = new HashMap<Integer, String>();

	public static void main1(String[] args) throws Exception {

		Document doc = Jsoup
				.parse(new File(
						"D:\\temp\\services.1306962443456\\content\\geometrixx\\en\\services.html"),
						"UTF-8");
		Elements images = doc.getElementsByTag("img");
		for (Element image : images) {
			String src = image.attr("src");
			map.put(cid++, src);
			image.attr("src", "cid:" + cid);
		}

		Properties props = new Properties();
		props.put("mail.smtp.host", "inner-relay-1.corp.adobe.com");
		props.put("mail.smtp.from", "satyam@adobe.com");
		props.put("mail.smtp.auth", "true");

		// props.put("mail.smtp.starttls.enable", "true");

		Authenticator auth = new SMTPAuthenticator();

		Session mailSession = Session.getDefaultInstance(props, auth);

		MimeMessage message = new MimeMessage(mailSession);
		// message.setFrom(new InternetAddress("m.satyadeep@gmail.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"satyam@adobe.com"));
		// message.addRecipient(Message.RecipientType.TO, new InternetAddress(
		// "rgarg@adobe.com"));
		// message.addRecipient(Message.RecipientType.TO, new InternetAddress(
		// "gsatija@adobe.com"));

		message.setSubject("Test HTML mail");

		Multipart multipart = new MimeMultipart();
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		// fill message
		// String htmlText =
		// "<H1>Hello</H1><img src=\"cid:image\"><H1>Another image</H1><img src=\"cid:image2\">";
		System.out.println(doc.toString());
		messageBodyPart.setContent(doc.toString(), "text/html");

		multipart.addBodyPart(messageBodyPart);

		for (int cid : map.keySet()) {
			messageBodyPart = new MimeBodyPart();
			String imageFile = "D:\\temp\\services.1306962443456\\content\\geometrixx\\en\\"
					+ map.get(cid);
			DataSource source = new FileDataSource(imageFile);
			messageBodyPart.setDataHandler(new DataHandler(source));
			// messageBodyPart.setFileName(fileAttachment);
			messageBodyPart.setHeader("Content-ID", "<" + cid + ">");
			multipart.addBodyPart(messageBodyPart);
		}

		// // Part two is attachment
		// messageBodyPart = new MimeBodyPart();
		// String imageFile = "d:\\temp\\test.gif";
		// DataSource source = new FileDataSource(imageFile);
		// messageBodyPart.setDataHandler(new DataHandler(source));
		// // messageBodyPart.setFileName(fileAttachment);
		// messageBodyPart.setHeader("Content-ID", "<image>");
		//
		// multipart.addBodyPart(messageBodyPart);
		//
		// messageBodyPart = new MimeBodyPart();
		// imageFile = "d:\\temp\\test2.gif";
		// source = new FileDataSource(imageFile);
		// messageBodyPart.setDataHandler(new DataHandler(source));
		// // messageBodyPart.setFileName(fileAttachment);
		// messageBodyPart.setHeader("Content-ID", "<image2>");
		//
		// multipart.addBodyPart(messageBodyPart);

		// Put parts in message
		message.setContent(multipart);

		for (int i = 0; i < multipart.getCount(); i++) {
			BodyPart part = multipart.getBodyPart(i);
			System.out.println("---- part " + i + " ------");

			Enumeration<Header> e = part.getAllHeaders();
			while (e.hasMoreElements()) {
				Header header = e.nextElement();
				System.out.println(header.getName() + ": " + header.getValue());
			}
		}

		Transport.send(message);

		// System.out.println(doc.toString());
	}

	private static class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = "spuri@adobe.com";
			String password = "breeze";
			return new PasswordAuthentication(username, password);
		}
	}

	public static void main(String... args) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host", "inner-relay-1.corp.adobe.com");
		props.put("mail.smtp.from", "satyam@adobe.com");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new PasswordAuthenticator(
				"spuri@adobe.com", "breeze"));

		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"m.satyadeep@gmail.com"));
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"satyam@adobe.com"));

		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.setSubType("alternative");

		MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
		mimeBodyPart1.setText("Hello");
		mimeMultipart.addBodyPart(mimeBodyPart1);

		MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
		
		{
			MimeMultipart mimeMultipartMixed = new MimeMultipart();
			mimeMultipartMixed.setSubType("mixed");
			MimeBodyPart mimeBodyPart2_1 = new MimeBodyPart();
			{
				MimeMultipart mimeMultipartHTML = new MimeMultipart();
				mimeMultipartHTML.setSubType("related");

				MimeBodyPart mimeBodyPart2_1_1 = new MimeBodyPart();
				mimeBodyPart2_1_1
						.setText(
								"<HTML><BODY><b>Hello</b><img src=\"cid:efeghrwewr4t\" /></BODY></HTML>",
								"UTF-8", "html");

				MimeBodyPart mimeBodyPart2_1_2 = new MimeBodyPart();
				DataSource source = new FileDataSource(
						"D:\\temp\\invite\\connect.png");
				mimeBodyPart2_1_2.setDataHandler(new DataHandler(source));
				mimeBodyPart2_1_2.setContentID("<efeghrwewr4t>");
				mimeBodyPart2_1_2.setDisposition(BodyPart.INLINE);
				mimeBodyPart2_1_2.setFileName("connect.png");

				mimeMultipartHTML.addBodyPart(mimeBodyPart2_1_1);
				mimeMultipartHTML.addBodyPart(mimeBodyPart2_1_2);

				mimeBodyPart2_1.setContent(mimeMultipartHTML);
			}
			mimeMultipartMixed.addBodyPart(mimeBodyPart2_1);
			mimeBodyPart2.setContent(mimeMultipartMixed);
		}
		mimeMultipart.addBodyPart(mimeBodyPart2);

		MimeBodyPart mimeBodyPart3 = new MimeBodyPart();
		mimeBodyPart3.setText(readFileAsString("D:\\temp\\invite.ics"),
				"UTF-8", "calendar");
		mimeMultipart.addBodyPart(mimeBodyPart3);

		mimeMessage.setContent(mimeMultipart);

		Transport.send(mimeMessage);
	}

	public static void main2(String... args) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host", "inner-relay-1.corp.adobe.com");
		props.put("mail.smtp.from", "satyam@adobe.com");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new PasswordAuthenticator(
				"spuri@adobe.com", "breeze"));

		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"m.satyadeep@gmail.com"));
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"satyam@adobe.com"));

		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.setSubType("alternative");

		MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
		mimeBodyPart1.setText("Hello");
		mimeMultipart.addBodyPart(mimeBodyPart1);

		MimeBodyPart mimeBodyPart2 = new MimeBodyPart();

		{
			MimeMultipart mimeMultipartHTML = new MimeMultipart();
			mimeMultipartHTML.setSubType("related");

			MimeBodyPart mimeBodyPart2_1 = new MimeBodyPart();
			mimeBodyPart2_1
					.setText(
							"<HTML><BODY><b>Hello</b><img src=\"cid:efeghrwewr4t\" /></BODY></HTML>",
							"UTF-8", "html");

			MimeBodyPart mimeBodyPart2_2 = new MimeBodyPart();
			DataSource source = new FileDataSource(
					"D:\\temp\\invite\\connect.png");
			mimeBodyPart2_2.setDataHandler(new DataHandler(source));
			mimeBodyPart2_2.setContentID("<efeghrwewr4t>");
			mimeBodyPart2_2.setDisposition(BodyPart.INLINE);
			mimeBodyPart2_2.setFileName("connect.png");

			mimeMultipartHTML.addBodyPart(mimeBodyPart2_1);
			mimeMultipartHTML.addBodyPart(mimeBodyPart2_2);

			mimeBodyPart2.setContent(mimeMultipartHTML);
		}

		mimeMultipart.addBodyPart(mimeBodyPart2);

		MimeBodyPart mimeBodyPart3 = new MimeBodyPart();
		mimeBodyPart3.setText(readFileAsString("D:\\temp\\invite.ics"),
				"UTF-8", "calendar");
		mimeMultipart.addBodyPart(mimeBodyPart3);

		mimeMessage.setContent(mimeMultipart);

		Transport.send(mimeMessage);
	}

	public static String readFileAsString(String filePath)
			throws java.io.IOException {
		byte[] buffer = new byte[(int) new File(filePath).length()];
		BufferedInputStream f = null;
		try {
			f = new BufferedInputStream(new FileInputStream(filePath));
			f.read(buffer);
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException ignored) {

				}
		}
		return new String(buffer);
	}

	static class PasswordAuthenticator extends Authenticator {
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

}
