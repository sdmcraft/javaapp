package ejb.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/testQueue") })
public class MessageDrivenBean implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		TextMessage txtMsg = (TextMessage) msg;
		try {
			String message = txtMsg.getText();
			System.out.printf("Received message: %s", message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
}
