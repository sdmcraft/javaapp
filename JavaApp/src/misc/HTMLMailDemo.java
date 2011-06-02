/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 
 * @author satyam
 */
public class HTMLMailDemo {

	public static void main(String[] args) throws Exception {
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
		message.setSubject("Hi");

		Multipart multipart = new MimeMultipart();
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		// fill message
		String htmlText = "<H1>Hello</H1><img src=\"cid:image\"><H1>Another image</H1><img src=\"cid:image2\">";
        messageBodyPart.setContent(htmlText, "text/html");



		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		String imageFile = "d:\\temp\\test.gif";
		DataSource source = new FileDataSource(imageFile);
		messageBodyPart.setDataHandler(new DataHandler(source));
		//messageBodyPart.setFileName(fileAttachment);
		messageBodyPart.setHeader("Content-ID","<image>");

		multipart.addBodyPart(messageBodyPart);

		
		messageBodyPart = new MimeBodyPart();
		imageFile = "d:\\temp\\test2.gif";
		source = new FileDataSource(imageFile);
		messageBodyPart.setDataHandler(new DataHandler(source));
		//messageBodyPart.setFileName(fileAttachment);
		messageBodyPart.setHeader("Content-ID","<image2>");

		multipart.addBodyPart(messageBodyPart);

		
		// Put parts in message
		message.setContent(multipart);

		Transport.send(message);
		// long end = System.currentTimeMillis();
		// stats[0] = end - start;
		//
		// start = System.currentTimeMillis();
		// for (int i = 0; i < 5; i++) {
		// message = new MimeMessage(mailSession);
		// message.setFrom(new InternetAddress("test-sdm@adobe.com"));
		// message.addRecipient(Message.RecipientType.TO, new
		// InternetAddress("satyam@adobe.com"));
		// message.setSubject("Hi");
		// message.setText("This is a test!!");
		// Transport.send(message);
		// }
		// end = System.currentTimeMillis();
		// stats[1] = end - start;
		//
		// start = System.currentTimeMillis();
		// for (int i = 0; i < 10; i++) {
		// message = new MimeMessage(mailSession);
		// message.setFrom(new InternetAddress("test-sdm@adobe.com"));
		// message.addRecipient(Message.RecipientType.TO, new
		// InternetAddress("satyam@adobe.com"));
		// message.setSubject("Hi");
		// message.setText("This is a test!!");
		// Transport.send(message);
		// }
		// end = System.currentTimeMillis();
		// stats[2] = end - start;
		//
		// start = System.currentTimeMillis();
		// for (int i = 0; i < 50; i++) {
		// message = new MimeMessage(mailSession);
		// message.setFrom(new InternetAddress("test-sdm@adobe.com"));
		// message.addRecipient(Message.RecipientType.TO, new
		// InternetAddress("satyam@adobe.com"));
		// message.setSubject("Hi");
		// message.setText("This is a test!!");
		// Transport.send(message);
		// }
		// end = System.currentTimeMillis();
		// stats[3] = end - start;
		//
		// start = System.currentTimeMillis();
		// for (int i = 0; i < 100; i++) {
		// message = new MimeMessage(mailSession);
		// message.setFrom(new InternetAddress("test-sdm@adobe.com"));
		// message.addRecipient(Message.RecipientType.TO, new
		// InternetAddress("satyam@adobe.com"));
		// message.setSubject("Hi");
		// message.setText("This is a test!!");
		// Transport.send(message);
		// }
		// end = System.currentTimeMillis();
		// stats[4] = end - start;
		//
		// for (long i : stats) {
		// System.out.println(i);
		// }
	}

	private static class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = "spuri@adobe.com";
			String password = "breeze";
			return new PasswordAuthentication(username, password);
		}
	}
}
