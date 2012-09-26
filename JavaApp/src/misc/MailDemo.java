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
public class MailDemo {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		// props.put("mail.smtp.host", "smtp.sflab.macromedia.com");
		//props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		Authenticator auth = new SMTPAuthenticator();

		Session mailSession = Session.getDefaultInstance(props, auth);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress("m.satyadeep@gmail.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"m.satyadeep@gmail.com"));
		message.setSubject("Test");
		// message.setText("Hi Dear Fans!!Hope you all are doing well!! \n"
		// + "Best Wishes.");
		MimeBodyPart messageBodyPart = new MimeBodyPart();

		// fill message
		messageBodyPart.setText("Test Body");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		   
		// Part two is attachment
		/*messageBodyPart = new MimeBodyPart();
		String fileAttachment = "/home/satyam/Desktop/sourceforge-cmds";
		DataSource source = new FileDataSource(fileAttachment);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(fileAttachment);
		multipart.addBodyPart(messageBodyPart);*/

		// Put parts in message
		message.setContent(multipart);

		Transport.send(message);

	}

	private static class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = "m.satyadeep";
			String password = "xxxxxx";
			return new PasswordAuthentication(username, password);
		}
	}
}
