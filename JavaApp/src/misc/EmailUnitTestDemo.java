package misc;

import java.util.Iterator;

import org.junit.Test;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;

public class EmailUnitTestDemo {

	@Test
	public void testSend() throws Exception {
		SimpleSmtpServer server = SimpleSmtpServer.start();
		Thread.sleep(30000);
		MailDemo.main(null);

		server.stop();

		Iterator emailIter = server.getReceivedEmail();
		while (emailIter.hasNext()) {
			SmtpMessage email = (SmtpMessage) emailIter.next();
			Iterator headerItr = email.getHeaderNames();
			while (headerItr.hasNext()) {
				String header = (String) headerItr.next();
				System.out.println(header + ":" + email.getHeaderValue(header));
			}
		}
	}
}
