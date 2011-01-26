package org.meetmejava.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.TimeoutException;
import org.meetmejava.Conference;
import org.meetmejava.State;
import org.meetmejava.User;
import org.meetmejava.event.Event;

// TODO: Auto-generated Javadoc
/**
 * The Class Client.
 */
public class Client implements Observer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable dispatcher, Object eventObject) {

		Event event = (Event) eventObject;
		switch (event.getType()) {
		case USER_JOINED:
			User user = (User) event.getData();
			user.addObserver(this);
			System.out.println("A user joined the audio conference");
			break;
		case CONFERENCE_ENDED:
			System.out.println("The audio conference ended");
			break;
		case MUTE:
			System.out.println("A user was muted");
			break;
		case TALKER:
			System.out.println("A user's talker state changed");
			break;
		case USER_LEFT:
			System.out.println("A user left the audio conference");
			break;
		}

	}

	public void demo(String url, String admin, String pwd, String confId)
			throws IllegalStateException, IOException,
			AuthenticationFailedException, TimeoutException,
			InterruptedException {
		Map<String, String> settings = new HashMap<String, String>();
		settings.put("URL", url);
		settings.put("ADMIN", admin);
		settings.put("PASSWORD", pwd);
		State state = new State(settings);
		state.init();
		Conference conference = new Conference(confId, state);
		conference.init();
		conference.addObserver(this);
		Thread.sleep(60000);
		state.destroy();
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws TimeoutException
	 * @throws AuthenticationFailedException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws TimeoutException
	 * @throws AuthenticationFailedException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IllegalStateException,
			IOException, AuthenticationFailedException, TimeoutException,
			InterruptedException {
		new Client().demo("192.168.1.103", "admin", "P@$$w0rd", "6300");
	}
}
