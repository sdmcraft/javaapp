package org.meetmejava;

import java.io.IOException;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.ManagerEventListener;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.ManagerAction;
import org.asteriskjava.manager.response.ManagerResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class Connection.
 */
public class Connection {

	/** The manager connection. */
	private final ManagerConnection managerConnection;

	/**
	 * Instantiates a new connection.
	 *
	 * @param address the address
	 * @param uname the uname
	 * @param pwd the pwd
	 */
	public Connection(String address, String uname, String pwd) {
		ManagerConnectionFactory factory = new ManagerConnectionFactory(
				address, uname, pwd);

		this.managerConnection = factory.createManagerConnection();

	}

	/**
	 * Connect.
	 *
	 * @throws IllegalStateException the illegal state exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws AuthenticationFailedException the authentication failed exception
	 * @throws TimeoutException the timeout exception
	 */
	public void connect() throws IllegalStateException, IOException,
			AuthenticationFailedException, TimeoutException {
		managerConnection.login();
	}

	/**
	 * Disconnect.
	 */
	public void disconnect() {
		managerConnection.logoff();
	}

	/**
	 * Gets the manager connection.
	 * 
	 * @return the manager connection
	 */
	public ManagerConnection getManagerConnection() {
		return managerConnection;
	}

	/**
	 * Adds the manager event listener.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addManagerEventListener(ManagerEventListener listener) {
		managerConnection.addEventListener(listener);
	}

	/**
	 * Removes the manager event listener.
	 * 
	 * @param listener
	 *            the listener
	 */
	public void removeManagerEventListener(ManagerEventListener listener) {
		managerConnection.removeEventListener(listener);
	}

	/**
	 * Send action.
	 * 
	 * @param action
	 *            the action
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String sendAction(ManagerAction action) throws Exception {
		ManagerResponse response = managerConnection.sendAction(action);
		return response.getResponse();
	}

}
