package org.meetmejava;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Logger;

import org.asteriskjava.live.MeetMeRoom;
import org.asteriskjava.live.MeetMeUser;
import org.asteriskjava.manager.action.HangupAction;
import org.asteriskjava.manager.action.MeetMeMuteAction;
import org.asteriskjava.manager.action.MeetMeUnmuteAction;
import org.meetmejava.event.Event;
import org.meetmejava.event.EventType;

/**
 * Conference represents a MeetMe conference on the Asterisk server. This class
 * implements the observable interface. Any class which wants to receive events
 * related to this conference, must add itself as an observer of this class
 * instance.
 */
public class Conference extends Observable {

	/** The meet me room. */
	private final MeetMeRoom meetMeRoom;

	/**
	 * A map of conference users to their respective meetme userIDs
	 */
	private final Map<String, User> conferenceUserMap;

	/** The context. */
	private final Context context;

	/** The conference id. */
	private final String conferenceNumber;

	/** The recording. */
	private boolean recording;

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(Conference.class
			.getName());

	// private final String conferenceNumber;

	/**
	 * Represents a MeetMe conference.
	 * 
	 * @param conferenceNumber
	 *            the number to be dialled in order to join the conference
	 * @param context
	 *            the context for interaction between MeetMe-java and the
	 *            Asterisk server
	 */
	private Conference(String conferenceNumber, Context context) {

		meetMeRoom = context.getAsteriskServer()
				.getMeetMeRoom(conferenceNumber);
		this.context = context;
		this.conferenceUserMap = meetMeUsersToConferenceUserMap(meetMeRoom
				.getUsers());
		this.conferenceNumber = conferenceNumber;
	}

	/**
	 * Initializes the audio conference. This is necessary in order to receive
	 * events for this conference.
	 */
	private void init() {
		context.getConferences().put(conferenceNumber, this);
	}

	/**
	 * Creates a new instance of MeetMe conference.
	 * 
	 * @param conferenceNumber
	 *            the number to be dialled in order to join the conference
	 * @param context
	 *            the context for interaction between MeetMe-java and the
	 *            Asterisk server
	 * @return single instance of Conference
	 */
	public static Conference getInstance(String conferenceNumber,
			Context context) {
		Conference conference = new Conference(conferenceNumber, context);
		conference.init();
		return conference;
	}

	/**
	 * Meet me users to conference user map.
	 * 
	 * @param meetMeUsers
	 *            the meet me users
	 * @return the map
	 */
	private static Map<String, User> meetMeUsersToConferenceUserMap(
			Collection<MeetMeUser> meetMeUsers) {
		Map<String, User> conferenceUserMap = new HashMap<String, User>();
		for (MeetMeUser meetMeUser : meetMeUsers) {
			User conferenceUser = meetMeUserToConferenceUser(meetMeUser);
			conferenceUserMap.put(conferenceUser.getUserId(), conferenceUser);
		}
		return conferenceUserMap;
	}

	/**
	 * Meet me user to conference user.
	 * 
	 * @param meetMeUser
	 *            the meet me user
	 * @return the user
	 */
	private static User meetMeUserToConferenceUser(MeetMeUser meetMeUser) {
		User conferenceUser = new User(meetMeUser);
		return conferenceUser;
	}

	/**
	 * Gets the meet me user.
	 * 
	 * @param userId
	 *            the user id
	 * @return the meet me user
	 */
	private MeetMeUser getMeetMeUser(String userId) {
		for (MeetMeUser meetMeUser : meetMeRoom.getUsers()) {
			if (meetMeUser.getChannel().getId().equals(userId))
				return meetMeUser;
		}
		return null;
	}

	/**
	 * Gets the conference user.
	 * 
	 * @param phoneNumber
	 *            the phone number
	 * @return the conference user
	 */
	private User getConferenceUser(String phoneNumber) {
		for (User conferenceUser : this.conferenceUserMap.values()) {
			if (phoneNumber.equals(conferenceUser.getPhoneNumber()))
				return conferenceUser;
		}
		return null;
	}

	/**
	 * Sets the conference user mute context.
	 * 
	 * @param userId
	 *            the user id
	 * @param mute
	 *            the mute
	 * @throws Exception
	 *             the exception
	 */
	public void setConferenceUserMuteState(String userId, boolean mute)
			throws Exception {
		if (mute) {
			MeetMeMuteAction muteAction = new MeetMeMuteAction(
					conferenceNumber,
					conferenceUserMap.get(userId).getUserNumber());

			context.getConnection().sendAction(muteAction);
		} else {
			MeetMeUnmuteAction unmuteAction = new MeetMeUnmuteAction(
					conferenceNumber, conferenceUserMap.get(userId)
							.getUserNumber());

			context.getConnection().sendAction(unmuteAction);
		}

	}

	/**
	 * Request hangup.
	 * 
	 * @param userId
	 *            the user id
	 * @throws Exception
	 *             the exception
	 */
	public void requestHangup(String userId) throws Exception {
		HangupAction hangupAction = new HangupAction(getMeetMeUser(userId)
				.getChannel().getName());
		context.getConnection().sendAction(hangupAction);
	}

	/**
	 * Request end conference.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void requestEndConference() throws Exception {
		for (String userId : conferenceUserMap.keySet()) {
			requestHangup(userId);
		}
	}

	/**
	 * Handle add conference user.
	 * 
	 * @param user
	 *            the user
	 * @param phoneNumber
	 *            the phone number
	 * @throws Exception
	 *             the exception
	 */
	public void handleAddConferenceUser(MeetMeUser user, String phoneNumber)
			throws Exception {
		User conferenceUser = new User(user);
		conferenceUserMap.put(conferenceUser.getUserId(), conferenceUser);

		setChanged();

		logger.fine("Dispatching user-joined");
		notifyObservers(new Event(EventType.USER_JOINED, conferenceUser));

	}

	/**
	 * Handle remove conference user.
	 * 
	 * @param userId
	 *            the user id
	 */
	public void handleRemoveConferenceUser(String userId) {
		User conferenceUser = conferenceUserMap.get(userId);
		setChanged();

		notifyObservers(new Event(EventType.USER_LEFT, conferenceUser));

		conferenceUserMap.remove(userId);
	}

	/**
	 * Handle end conference.
	 */
	public void handleEndConference() {
		conferenceUserMap.clear();
		setChanged();
		notifyObservers(new Event(EventType.CONFERENCE_ENDED));
	}

	/**
	 * Destroys this conference instance.
	 */
	public void destroy() {
		for (User user : conferenceUserMap.values())
			user.destroy();
		context.getConferences().remove(conferenceNumber);
	}

	/**
	 * Gets the users.
	 * 
	 * @return the users
	 */
	public Map<String, User> getUsers() {
		return conferenceUserMap;
	}

	/**
	 * Gets the context.
	 * 
	 * @return the context
	 */
	public Context getState() {
		return context;
	}

	/**
	 * Checks if is recording.
	 * 
	 * @return true, if is recording
	 */
	public boolean isRecording() {
		return recording;
	}

	/**
	 * Gets the conference id.
	 * 
	 * @return the conference id
	 */
	public String getconferenceNumber() {
		return conferenceNumber;
	}

	@Override
	public String toString() {
		if (meetMeRoom == null)
			return null;
		return meetMeRoom.getRoomNumber();
	}
}
