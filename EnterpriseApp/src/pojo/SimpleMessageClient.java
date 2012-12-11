package pojo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class SimpleMessageClient {
	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();
		ConnectionFactory cf = (ConnectionFactory) context
				.lookup("/ConnectionFactory");
		Queue queue = (Queue) context.lookup("queue/testQueue");
		Connection connection = cf.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		MessageProducer sender = session.createProducer(queue);
		for (int i = 1; i <= 10; i++) {
			String messageText = String.format("This is message number %s.", i);
			TextMessage message = session.createTextMessage(messageText);
			sender.send(message);
		}
	}
}
