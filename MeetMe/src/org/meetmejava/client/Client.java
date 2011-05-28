package org.meetmejava.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

	Map<String, User> users = new HashMap<String, User>();

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
			users.put(user.getPhoneNumber(), user);
			System.out.println(user.getPhoneNumber()
					+ " joined the audio conference");
			break;
		case CONFERENCE_ENDED:
			users.clear();
			System.out.println("The audio conference ended");
			break;
		case MUTE:
			user = (User) dispatcher;
			System.out.println(user.getPhoneNumber() + " was muted");
			break;
		case UNMUTE:
			user = (User) dispatcher;
			System.out.println(user.getPhoneNumber() + " was unmuted");
			break;
		case TALKING:
			user = (User) dispatcher;
			System.out.println(user.getPhoneNumber() + " talking");
			break;
		case NOT_TALKING:
			user = (User) dispatcher;
			System.out.println(user.getPhoneNumber() + " not talking");
			break;
		case USER_LEFT:
			user = (User) dispatcher;
			users.remove(user.getPhoneNumber());
			System.out.println(user.getPhoneNumber()
					+ " left the audio conference");
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
			String conferenceNumber, String[] phoneNumbers, String extensionUrl)
			throws Exception {
		Context context = Context.getInstance(ip, admin, pwd, extensionUrl);
		Conference conference = Conference.getInstance(conferenceNumber,
				context);
		conference.addObserver(this);
		Thread.sleep(2000);
		for (String phoneNumber : phoneNumbers) {
			System.out.println("User Number:"
					+ context.requestDialOut(phoneNumber, conferenceNumber,
							"SIP") + " dialled out");
		}
		Thread.sleep(10000);
		if (users.containsKey("6001"))
			users.get("6001").requestMuteStateChange();
		Thread.sleep(10000);
		if (users.containsKey("6001"))
			users.get("6001").requestMuteStateChange();
		Thread.sleep(10000);
		if (users.containsKey("6001"))
			users.get("6001").requestHangUp();
		Thread.sleep(10000);
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
		new Client().demo("50.18.44.168", "admin", "P@$$w0rd", "6300",
				new String[] { "6002" },
				"http://50.18.44.168:8080/AsteriskExtension/service");
	}
}
