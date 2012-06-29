package org.sdm.meetme.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.TimeoutException;
import org.sdm.meetme.Conference;
import org.sdm.meetme.Context;
import org.sdm.meetme.User;
import org.sdm.meetme.event.Event;

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
			System.out.println(user.getUserId()
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
					+ conference.requestDialOut(phoneNumber) + " dialled out");
			Thread.sleep(30000);
		}
		if (users.containsKey("SIP/6000")) {

			//users.get("SIP/6000").requestStartRecording();
			Thread.sleep(10000);

			users.get("SIP/6000").requestMuteStateChange();
			Thread.sleep(10000);

			users.get("SIP/6000").requestMuteStateChange();
			Thread.sleep(10000);

			//users.get("SIP/6000").requestStopRecording();
			
			users.get("SIP/6000").requestHangUp();
			Thread.sleep(10000);


		}
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
		new Client().demo("10.40.61.65", "admin", "P@$$w0rd", "6300",
				new String[] { "SIP/1000" },
				"http://10.40.79.106:8080/AsteriskExtension/service");
	}
}
