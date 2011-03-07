package org.meetmejava.client;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.TimeoutException;
import org.meetmejava.Conference;
import org.meetmejava.Context;
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

	/**
	 * Demo.
	 * 
	 * @param ip
	 *            the ip
	 * @param admin
	 *            the admin
	 * @param pwd
	 *            the pwd
	 * @param conferenceNumber
	 *            the conference number
	 * @throws IllegalStateException
	 *             the illegal state exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AuthenticationFailedException
	 *             the authentication failed exception
	 * @throws TimeoutException
	 *             the timeout exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void demo(String ip, String admin, String pwd,
			String conferenceNumber) throws Exception {
		Context context = Context.getInstance(ip, admin, pwd, null);
		Conference conference = Conference.getInstance(conferenceNumber,
				context);
		conference.addObserver(this);
		Thread.sleep(60000);
		conference.destroy();
		context.destroy();
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IllegalStateException
	 *             the illegal state exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws AuthenticationFailedException
	 *             the authentication failed exception
	 * @throws TimeoutException
	 *             the timeout exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void main(String[] args) throws Exception {
		new Client().demo("192.168.1.102", "admin", "P@$$w0rd", "6300");
	}
}
