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

import org.jdom.Content;

/**
 * 
 * @author satyam
 */
public class MailDemo {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		// props.put("mail.smtp.host", "smtp.sflab.macromedia.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		Authenticator auth = new SMTPAuthenticator();

		Session mailSession = Session.getDefaultInstance(props, auth);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress("m.satyadeep@gmail.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"m.satyadeep@gmail.com"));
		message.setSubject("Hi");
		// message.setText("Hi Dear Fans!!Hope you all are doing well!! \n"
		// + "Best Wishes.");
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		// fill message
		messageBodyPart.setText("Hi");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		   
		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		String fileAttachment = "/home/satyam/Desktop/sourceforge-cmds";
		DataSource source = new FileDataSource(fileAttachment);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(fileAttachment);
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
			String username = "m.satyadeep";
			String password = "gmail114394p";
			return new PasswordAuthentication(username, password);
		}
	}
}
