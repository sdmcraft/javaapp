package pojo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyListener implements MessageListener {

	public void onMessage(Message msg) {		
		System.out.println("(+)onMessage(+)");
		synchronized (Globals.list) {
			try {
				System.out.println("onMessage got the lock");				
				Globals.list.add("Received message: " + ((TextMessage)msg).getText());
				System.out.println("onMessage notified all");
				Globals.list.notifyAll();
			} catch (JMSException e) { 
				e.printStackTrace();
			}
		}
		System.out.println("onMessage released the lock");
		System.out.println("(-)onMessage(-)");
	}

}
