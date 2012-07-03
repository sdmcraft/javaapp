/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
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

	public static void main(String[] args) throws Exception {
		sendHtmlEmail();
	}

	public static void sendHtmlEmail() throws Exception {

		int cid = 0;
		List<MimeBodyPart> imageBodyParts = new ArrayList<MimeBodyPart>();

		Document doc = Jsoup.parse(new File(
				"c:\\temp\\html-email-page\\test.html"), "UTF-8");
		Elements images = doc.getElementsByTag("img");
		
		for (Element image : images) {
			String src = image.attr("src");
			image.attr("src", "cid:" + cid);

			File imageFile = new File("c:\\temp\\html-email-page\\" + src);
			
			DataSource source = new FileDataSource(imageFile);

			MimeBodyPart mimeBodyPartHtmlImage = new MimeBodyPart();
			mimeBodyPartHtmlImage.setDataHandler(new DataHandler(source));
			mimeBodyPartHtmlImage.setContentID("<" + cid + ">");
			mimeBodyPartHtmlImage.setDisposition(BodyPart.INLINE);
			mimeBodyPartHtmlImage.setFileName(imageFile.getName());

			imageBodyParts.add(mimeBodyPartHtmlImage);

			cid++;
		}

		Properties props = new Properties();

		/*
		 * props.put("mail.smtp.host", "smtp.gmail.com");
		 * props.put("mail.smtp.starttls.enable", "true");
		 * props.put("mail.smtp.auth", "true"); Session session =
		 * Session.getDefaultInstance(props, new
		 * PasswordAuthenticator("m.satyadeep@gmail.com","xxx"));
		 */

		/*props.put("mail.smtp.host", "inner-relay-1.corp.adobe.com");
		props.put("mail.smtp.from", "satyam@adobe.com");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new PasswordAuthenticator(
				"spuri@adobe.com", "breeze"));*/

		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.from", "satyam@adobe.com");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new PasswordAuthenticator(
				"spuri@adobe.com", "breeze"));
		
		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"m.satyadeep@gmail.com"));
		mimeMessage.setSubject("Test Email");
		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.setSubType("mixed");

		{
			MimeBodyPart mimeBodyPartHTML = new MimeBodyPart();
			{
				MimeMultipart mimeMultipartRelated = new MimeMultipart();
				mimeMultipartRelated.setSubType("related");
				{
					MimeBodyPart mimeBodyPartTextHtml = new MimeBodyPart();
					mimeBodyPartTextHtml.setText(doc.toString(), "UTF-8",
							"html");
					mimeMultipartRelated.addBodyPart(mimeBodyPartTextHtml);
				}

				{
					for (MimeBodyPart mimeBodyPartHtmlImage : imageBodyParts) {
						mimeMultipartRelated.addBodyPart(mimeBodyPartHtmlImage);
					}
				}
				mimeBodyPartHTML.setContent(mimeMultipartRelated);
			}
			mimeMultipart.addBodyPart(mimeBodyPartHTML);
		}

		mimeMessage.setContent(mimeMultipart);
		mimeMessage.saveChanges();
		Transport.send(mimeMessage);

	}

}
