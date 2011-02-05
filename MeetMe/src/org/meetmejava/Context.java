package org.meetmejava;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.DefaultAsteriskServer;
import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.TimeoutException;
import org.meetmejava.Conference;

// TODO: Auto-generated Javadoc
/**
 * The Class Context represents provides a context for interaction between
 * MeetMe-java and the Asterisk server.
 */
public class Context {

	/** The connection. */
	private final Connection connection;

	/** The asterisk server. */
	private final AsteriskServer asteriskServer;

	/** The asterisk url. */
	private final String asteriskIp;

	/** The asterisk admin. */
	private final String asteriskAdmin;

	/** The asterisk password. */
	private final String asteriskPassword;

	/** The dial out locks. */
	private final Map<String, Map<String, String>> dialOutLocks = new HashMap<String, Map<String, String>>();

	/** The live event handler. */
	private LiveEventHandler liveEventHandler;

	/** The started conferences. */
	private final Map<String, Conference> startedConferences = new HashMap<String, Conference>();

	/**
	 * Instantiates a new context.
	 * 
	 * @param asteriskIp
	 *            The ip address of the Asterisk server
	 * @param asteriskAdmin
	 *            The admin user on Asterisk who can access Asterisk Manager
	 *            Interface
	 * @param asteriskPassword
	 *            The password of the admin user
	 * @throws IllegalStateException
	 *             the illegal state exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AuthenticationFailedException
	 *             the authentication failed exception
	 * @throws TimeoutException
	 *             the timeout exception
	 */
	private Context(String asteriskIp, String asteriskAdmin,
			String asteriskPassword) throws IllegalStateException,
			IOException, AuthenticationFailedException, TimeoutException {
		this.asteriskIp = asteriskIp;
		this.asteriskAdmin = asteriskAdmin;
		this.asteriskPassword = asteriskPassword;
		connection = new Connection(asteriskIp, asteriskAdmin,
				asteriskPassword);
		connection.connect();
		asteriskServer = new DefaultAsteriskServer(connection
				.getManagerConnection());

	}

	/**
	 * Initializes the context to start receiving events from the Asterisk
	 * server.
	 */
	private void init() {
		liveEventHandler = new LiveEventHandler(this);
		liveEventHandler.init();
	}

	/**
	 * Gets a new instance of a context.
	 * 
	 * @param asteriskIp
	 *            The ip address of the Asterisk server
	 * @param asteriskAdmin
	 *            The admin user on Asterisk who can access Asterisk Manager
	 *            Interface
	 * @param asteriskPassword
	 *            The password of the admin user
	 * @throws IllegalStateException
	 *             the illegal state exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AuthenticationFailedException
	 *             the authentication failed exception
	 * @throws TimeoutException
	 *             the timeout exception
	 */
	public static Context getInstance(String asteriskIp, String asteriskAdmin,
			String asteriskPassword) throws IllegalStateException, IOException,
			AuthenticationFailedException, TimeoutException {
		Context context = new Context(asteriskIp, asteriskAdmin,
				asteriskPassword);
		context.init();
		return context;
	}

	/**
	 * Destroys this context
	 */
	public void destroy() {
		liveEventHandler.destroy();
		connection.disconnect();
	}

	/**
	 * Gets the connection.
	 * 
	 * @return the connection
	 */
	Connection getConnection() {
		return connection;
	}

	/**
	 * Gets the asterisk server.
	 * 
	 * @return the asterisk server
	 */
	AsteriskServer getAsteriskServer() {
		return asteriskServer;
	}

	/**
	 * Gets the asterisk url.
	 * 
	 * @return the asterisk url
	 */
	public String getAsteriskURL() {
		return asteriskIp;
	}

	/**
	 * Gets the asterisk admin.
	 * 
	 * @return the asterisk admin
	 */
	public String getAsteriskAdmin() {
		return asteriskAdmin;
	}

	/**
	 * Gets the asterisk password.
	 * 
	 * @return the asterisk password
	 */
	public String getAsteriskPassword() {
		return asteriskPassword;
	}

	/**
	 * Gets the started conferences.
	 * 
	 * @return the started conferences
	 */
	Map<String, Conference> getStartedConferences() {
		return startedConferences;
	}

	/**
	 * Gets the dial out locks.
	 * 
	 * @return the dial out locks
	 */
	Map<String, Map<String, String>> getDialOutLocks() {
		return dialOutLocks;
	}

}
