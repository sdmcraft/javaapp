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
 * The Class State
 */
public class State {

	/** The connection. */
	private final Connection connection;

	/** The asterisk server. */
	private final AsteriskServer asteriskServer;

	/** The asterisk url. */
	private final String asteriskURL;

	/** The asterisk admin. */
	private final String asteriskAdmin;

	/** The asterisk password. */
	private final String asteriskPassword;

	/** The asterisk ext url. */
	private final String asteriskExtURL;

	/** The temp rec dir. */
	private final String tempRecDir;

	/** The dial out locks. */
	private final Map<String, Map<String, String>> dialOutLocks = new HashMap<String, Map<String, String>>();

	/**
	 * Gets the temp rec dir.
	 * 
	 * @return the temp rec dir
	 */
	public String getTempRecDir() {
		return tempRecDir;
	}

	/** The live event handler. */
	private LiveEventHandler liveEventHandler;

	/** The started conferences. */
	private final Map<String, Conference> startedConferences = new HashMap<String, Conference>();

	/**
	 * Instantiates a new state.
	 * 
	 * @param settings
	 *            the settings
	 * @throws TimeoutException
	 * @throws AuthenticationFailedException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws Exception
	 *             the exception
	 */
	public State(Map<String, String> settings) throws IllegalStateException,
			IOException, AuthenticationFailedException, TimeoutException {
		asteriskURL = settings.get("URL");
		asteriskAdmin = settings.get("ADMIN");
		asteriskPassword = settings.get("PASSWORD");
		asteriskExtURL = settings.get("EXTENSION_URL");
		tempRecDir = settings.get("TEMP_REC_DIR");
		connection = new Connection(asteriskURL, asteriskAdmin,
				asteriskPassword);
		connection.connect();
		asteriskServer = new DefaultAsteriskServer(connection
				.getManagerConnection());

	}

	/**
	 * Inits the.
	 */
	public void init() {
		liveEventHandler = new LiveEventHandler(this);
		liveEventHandler.init();
	}

	/**
	 * Destroy.
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
		return asteriskURL;
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
	 * Gets the asterisk ext url.
	 * 
	 * @return the asterisk ext url
	 */
	String getAsteriskExtURL() {
		return asteriskExtURL;
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
