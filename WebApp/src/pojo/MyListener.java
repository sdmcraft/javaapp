package pojo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyListener implements MessageListener {

	public void onMessage(Message msg) {
		synchronized (Globals.list) {
			try {
				System.out.println("(+)onMessage(+)");
				Globals.list.add("Received message: " + ((TextMessage)msg).getText());
				Globals.list.notifyAll();
				System.out.println("(-)onMessage(-)");
			} catch (JMSException e) { 
				e.printStackTrace();
			}
		}

	}

}
