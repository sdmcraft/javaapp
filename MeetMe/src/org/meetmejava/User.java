package org.meetmejava;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.logging.Logger;

import org.asteriskjava.live.MeetMeUser;
import org.meetmejava.event.Event;
import org.meetmejava.event.EventType;

// TODO: Auto-generated Javadoc
/**
 * User represents a participant in a MeetMe conference.
 */
public class User extends Observable implements PropertyChangeListener {

	/** The meet me user. */
	private MeetMeUser meetMeUser;
	
	/** The talking. */
	private boolean talking = false;

	/** The Constant logger. */
	private final static Logger logger = Logger.getLogger(User.class.getName());

	// public User(AsteriskChannel channel, String userId, String phoneNumber,
	// boolean muted, boolean talking) {
	// this.channel = channel;
	// this.userId = userId;
	// this.phoneNumber = phoneNumber;
	// this.muted = muted;
	// this.talking = talking;
	// }

	/**
	 * Instantiates a new user.
	 *
	 * @param meetMeUser the meet me user
	 */
	public User(MeetMeUser meetMeUser) {
		this.meetMeUser = meetMeUser;
		meetMeUser.addPropertyChangeListener(this);
	}
	
	public void requestHangUp()
	{
		this.meetMeUser.getChannel().hangup();
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		logger.fine("Received event:" + evt);
		String propertyName = evt.getPropertyName();
		String propertyValue = evt.getNewValue().toString();
		if ("muted".equals(propertyName)) {
			boolean newMuteState = Boolean.parseBoolean(propertyValue);
			if (newMuteState != meetMeUser.isMuted()) {
				if (newMuteState)
					meetMeUser.mute();
				else
					meetMeUser.unmute();
				setChanged();
				notifyObservers(new Event(EventType.MUTE));
			}
		}
		if ("talking".equals(propertyName)) {
			boolean newTalkState = Boolean.parseBoolean(propertyValue);
			if (newTalkState != talking) {
				talking = newTalkState;
				setChanged();
				notifyObservers(new Event(EventType.TALKER));
			}
		} else if ("state".equals(propertyName) && "LEFT".equals(propertyValue)) {
			destroy();
		}
	}

	/**
	 * Destroy.
	 */
	public void destroy() {
		meetMeUser.removePropertyChangeListener(this);
		setChanged();
		notifyObservers(new Event(EventType.USER_LEFT));

	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return meetMeUser.getChannel().getId();
	}

	/**
	 * Checks if is muted.
	 *
	 * @return true, if is muted
	 */
	public boolean isMuted() {
		return meetMeUser.isMuted();
	}

	/**
	 * Checks if is talking.
	 *
	 * @return true, if is talking
	 */
	public boolean isTalking() {
		return meetMeUser.isTalking();
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return AsteriskUtils.getPhoneNumberFromChannel(meetMeUser.getChannel()
				.getName());
	}

	/**
	 * Gets the user number.
	 *
	 * @return the user number
	 */
	public Integer getUserNumber() {
		return meetMeUser.getUserNumber();
	}

}
