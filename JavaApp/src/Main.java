import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class Main {
	public static void main(String[] args) throws Exception {
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
}