package org.meetmejava;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.DefaultAsteriskServer;
import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.TimeoutException;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.meetmejava.Conference;

// TODO: Auto-generated Javadoc
/**
 * The Class Context represents a context for interaction between MeetMe-java
 * and the Asterisk server.
 */
public class Context {

	private final static Logger logger = Logger.getLogger(Context.class
			.getName());

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

	private final String extensionURL;

	/** The dial out locks. */
	private final Map<String, Map<String, String>> dialOutLocks = new HashMap<String, Map<String, String>>();

	/** The live event handler. */
	private LiveEventHandler liveEventHandler;

	/** The started conferences. */
	private final Map<String, Conference> conferences = new HashMap<String, Conference>();

	private final XMLOutputter xmlOutputter = new XMLOutputter(Format
			.getPrettyFormat());

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
			String asteriskPassword, String extensionURL) throws Exception {
		try {
			logger.fine("Creating a new context for " + asteriskIp + ","
					+ extensionURL);
			this.asteriskIp = asteriskIp;
			this.asteriskAdmin = asteriskAdmin;
			this.asteriskPassword = asteriskPassword;
			connection = new Connection(asteriskIp, asteriskAdmin,
					asteriskPassword);
			connection.connect();
			asteriskServer = new DefaultAsteriskServer(connection
					.getManagerConnection());
			this.extensionURL = extensionURL;
		} catch (Exception ex) {
			throw new Exception(ex);
		}
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
	 * Gets a new instance of Context.
	 * 
	 * @param asteriskIp
	 *            The ip address of the Asterisk server
	 * @param asteriskAdmin
	 *            The admin user on Asterisk who can access Asterisk Manager
	 *            Interface
	 * @param asteriskPassword
	 *            The password of the admin user
	 * @return single instance of Context
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
			String asteriskPassword, String extensionUrl) throws Exception {
		Context context = new Context(asteriskIp, asteriskAdmin,
				asteriskPassword, extensionUrl);
		context.init();
		return context;
	}

	public boolean validateRoomNumber(String roomNumber) throws Exception {
		URL url = new URL(extensionURL
				+ "?context=confirm&action=meetme-room&room-number="
				+ URLEncoder.encode(roomNumber, "UTF-8"));
		logger.info("Posting URL: " + url);
		URLConnection httpConn = url.openConnection();
		httpConn.connect();
		Document response = new SAXBuilder().build(httpConn.getInputStream());
		logger.info(xmlOutputter.outputString(response));
		if (!"true".equals(response.getRootElement().getValue())) {
			return false;
		}
		return true;
	}

	public String requestDialOut(String phoneNumber, String roomNumber)
			throws Exception {
		Map<String, String> dialOutLock = new HashMap<String, String>();
		synchronized (dialOutLock) {
			dialOutLocks.put(phoneNumber, dialOutLock);
			URL url = new URL(extensionURL
					+ "?context=call&action=meetme-dialout&channel="
					// TODO Channel hardcoded to SIP for now
					+ URLEncoder.encode("SIP", "UTF-8") + "&number="
					+ URLEncoder.encode(phoneNumber, "UTF-8") + "&room="
					+ URLEncoder.encode(roomNumber, "UTF-8"));
			URLConnection httpConn = url.openConnection();
			httpConn.connect();
			httpConn.getInputStream();
			// TODO This should be a timed wait. On timeout, throw dialout
			// failure
			while (!dialOutLock.containsKey("user-id"))
				dialOutLock.wait();
		}
		String userId = dialOutLock.get("user-id");
		dialOutLocks.remove(phoneNumber);
		return userId;
	}

	/**
	 * Destroys this context. The context is not usable after it has been
	 * destroyed.
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
	Map<String, Conference> getConferences() {
		return conferences;
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
