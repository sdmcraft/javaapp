package org.meetmejava;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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

// TODO: Auto-generated Javadoc
/**
 * The Class Conference.
 */
public class Conference extends Observable {

	/** The meet me room. */
	private final MeetMeRoom meetMeRoom;

	/** The conference user map. */
	private final Map<String, User> conferenceUserMap; /*
														 * A map of conference
														 * users to their
														 * respective meetme
														 * userIDs
														 */

	/** The recording name. */
	private String recordingName;

	/** The context. */
	private final Context context;

	/** The conference id. */
	private final String conferenceId;

	/** The recording. */
	private boolean recording;

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(Conference.class
			.getName());

	// private final String conferenceId;

	/**
	 * Instantiates a new conference.
	 * 
	 * @param conferenceId
	 *            the conference id
	 * @param recordingNumber
	 *            the recording number
	 * @param context
	 *            the context
	 */
	public Conference(String conferenceId, Context context) {

		meetMeRoom = context.getAsteriskServer().getMeetMeRoom(conferenceId);
		this.context = context;
		this.conferenceUserMap = meetMeUsersToConferenceUserMap(meetMeRoom
				.getUsers());
		this.conferenceId = conferenceId;
	}

	public void init() {
		context.getStartedConferences().put(conferenceId, this);
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
			MeetMeMuteAction muteAction = new MeetMeMuteAction(conferenceId,
					conferenceUserMap.get(userId).getUserNumber());

			context.getConnection().sendAction(muteAction);
		} else {
			MeetMeUnmuteAction unmuteAction = new MeetMeUnmuteAction(
					conferenceId, conferenceUserMap.get(userId).getUserNumber());

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
	 * Request dial out.
	 * 
	 * @param phoneNumber
	 *            the phone number
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String requestDialOut(String phoneNumber) throws Exception {
		Map<String, String> dialOutLock = new HashMap<String, String>();
		synchronized (dialOutLock) {
			context.getDialOutLocks().put(phoneNumber, dialOutLock);
			URL url = new URL(context.getAsteriskExtURL()
					+ "?context=call&action=meetme-dialout&channel="
					// TODO Channel hardcoded to SIP for now
					+ URLEncoder.encode("SIP", "UTF-8") + "&number="
					+ URLEncoder.encode(phoneNumber, "UTF-8") + "&room="
					+ URLEncoder.encode(conferenceId, "UTF-8"));
			URLConnection httpConn = url.openConnection();
			httpConn.connect();
			httpConn.getInputStream();
			// TODO This should be a timed wait. On timeout, throw dialout
			// failure
			while (!dialOutLock.containsKey("user-id"))
				dialOutLock.wait();
		}
		String userId = dialOutLock.get("user-id");
		context.getDialOutLocks().remove(phoneNumber);
		return userId;
	}

	/**
	 * Request start recording.
	 * 
	 * @param recordingName
	 *            the recording name
	 * @throws Exception
	 *             the exception
	 */
	public void requestStartRecording(String recordingName) throws Exception {
		this.recordingName = recordingName;

	}

	/**
	 * Request stop recording.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void requestStopRecording() throws Exception {
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
	 * Destroy.
	 */
	public void destroy() {
		for (User user : conferenceUserMap.values())
			user.destroy();
		context.getStartedConferences().remove(conferenceId);
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
	public String getConferenceId() {
		return conferenceId;
	}

	/**
	 * Gets the recording path.
	 * 
	 * @return the recording path
	 */
	public String getRecordingPath() {
		return context.getTempRecDir() + File.separator + recordingName;
	}

	// public static void main(String[] args) throws Exception{
	// Context context = new Context();
	// Conference conference = new Conference("6300",context);
	// conference.getUsersFromServer();
	// }
}
