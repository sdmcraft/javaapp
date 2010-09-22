package ami;

import java.io.IOException;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.response.ManagerResponse;

public class Manager {
	private ManagerConnection managerConnection;

	public Manager() throws IOException {
		ManagerConnectionFactory factory = new ManagerConnectionFactory(
				"10.40.47.33", "admin", "P@$$w0rd");

		this.managerConnection = factory.createManagerConnection();
	}

	public void run() throws IOException, AuthenticationFailedException,
			TimeoutException {
		OriginateAction originateAction;
		ManagerResponse originateResponse;

		originateAction = new OriginateAction();
		originateAction.setChannel("SIP/1001");
		originateAction.setContext("incoming");
		originateAction.setExten("2000");
		originateAction.setPriority(new Integer(1));
		originateAction.setTimeout(new Integer(30000));
		//originateAction.setVariable("number", "1001");
		originateAction.setVariable("message", "This is a message");


		// connect to Asterisk and log in
		managerConnection.login();

		// send the originate action and wait for a maximum of 30 seconds for
		// Asterisk
		// to send a reply
		originateResponse = managerConnection
				.sendAction(originateAction, 30000);

		// print out whether the originate succeeded or not
		System.out.println(originateResponse.getResponse());

		// and finally log off and disconnect
		managerConnection.logoff();
	}

	public static void main(String[] args) throws Exception {
		Manager helloManager;

		helloManager = new Manager();
		helloManager.run();
	}

}
