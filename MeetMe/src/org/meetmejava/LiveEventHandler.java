package org.meetmejava;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.asteriskjava.live.AsteriskChannel;
import org.asteriskjava.live.AsteriskQueue;
import org.asteriskjava.live.AsteriskQueueEntry;
import org.asteriskjava.live.AsteriskServer;
import org.asteriskjava.live.AsteriskServerListener;
import org.asteriskjava.live.MeetMeRoom;
import org.asteriskjava.live.MeetMeUser;
import org.asteriskjava.live.internal.AsteriskAgentImpl;
import org.asteriskjava.manager.ManagerEventListener;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.MeetMeEndEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class LiveEventHandler.
 */
public class LiveEventHandler implements AsteriskServerListener,
		ManagerEventListener, PropertyChangeListener {

	/** The Constant logger. */
	private final static Logger logger = Logger
			.getLogger(LiveEventHandler.class.getName());

	/** The state. */
	private final State state;

	/**
	 * Instantiates a new live event handler.
	 *
	 * @param state the state
	 */
	public LiveEventHandler(State state) {
		this.state = state;
	}

	/**
	 * Inits the.
	 */
	public void init() {
		AsteriskServer asteriskServer = state.getAsteriskServer();
		asteriskServer.addAsteriskServerListener(this);
		state.getConnection().addManagerEventListener(this);

		for (AsteriskChannel asteriskChannel : asteriskServer.getChannels()) {
			logger.fine(asteriskChannel.toString());
			asteriskChannel.addPropertyChangeListener(this);
		}

		for (AsteriskQueue asteriskQueue : asteriskServer.getQueues()) {
			logger.fine(asteriskQueue.toString());
			for (AsteriskQueueEntry asteriskQueueEntry : asteriskQueue
					.getEntries()) {
				asteriskQueueEntry.getChannel().addPropertyChangeListener(this);
			}
		}

		for (MeetMeRoom meetMeRoom : asteriskServer.getMeetMeRooms()) {
			logger.fine(meetMeRoom.toString());
			for (MeetMeUser user : meetMeRoom.getUsers()) {
				user.addPropertyChangeListener(this);
			}
		}
	}

	/**
	 * Destroy.
	 */
	public void destroy() {
		AsteriskServer asteriskServer = state.getAsteriskServer();
		asteriskServer.removeAsteriskServerListener(this);
		state.getConnection().removeManagerEventListener(this);

		for (AsteriskChannel asteriskChannel : asteriskServer.getChannels()) {
			logger.fine(asteriskChannel.toString());
			asteriskChannel.removePropertyChangeListener(this);
		}

		for (AsteriskQueue asteriskQueue : asteriskServer.getQueues()) {
			logger.fine(asteriskQueue.toString());
			for (AsteriskQueueEntry asteriskQueueEntry : asteriskQueue
					.getEntries()) {
				asteriskQueueEntry.getChannel().removePropertyChangeListener(
						this);
			}
		}

		for (MeetMeRoom meetMeRoom : asteriskServer.getMeetMeRooms()) {
			logger.fine(meetMeRoom.toString());
			for (MeetMeUser user : meetMeRoom.getUsers()) {
				user.removePropertyChangeListener(this);
			}
		}

	}

	/* (non-Javadoc)
	 * @see org.asteriskjava.live.AsteriskServerListener#onNewAgent(org.asteriskjava.live.internal.AsteriskAgentImpl)
	 */
	@Override
	public void onNewAgent(AsteriskAgentImpl agent) {
		logger.fine(agent.toString());
		agent.addPropertyChangeListener(this);
	}

	/* (non-Javadoc)
	 * @see org.asteriskjava.live.AsteriskServerListener#onNewAsteriskChannel(org.asteriskjava.live.AsteriskChannel)
	 */
	@Override
	public void onNewAsteriskChannel(AsteriskChannel channel) {
		logger.fine(channel.toString());
		String id = channel.getId();
		String phoneNumber = AsteriskUtils.getPhoneNumberFromChannel(channel
				.getName());
		Map<String, String> dialOutLock = state.getDialOutLocks().get(
				phoneNumber);
		if (dialOutLock != null) {
			synchronized (dialOutLock) {
				dialOutLock.put("user-id", id);
				dialOutLock.notifyAll();
			}
		}
		channel.addPropertyChangeListener(this);
	}

	/* (non-Javadoc)
	 * @see org.asteriskjava.live.AsteriskServerListener#onNewMeetMeUser(org.asteriskjava.live.MeetMeUser)
	 */
	@Override
	public void onNewMeetMeUser(MeetMeUser user) {
		logger.fine(user.toString());
		try {
			Conference conference = state.getStartedConferences().get(
					user.getRoom().getRoomNumber());
			conference.handleAddConferenceUser(user, AsteriskUtils
					.getUserPhoneNumber(user));
		} catch (Exception ex) {
			logger.log(Level.WARNING, ex.getMessage(), ex);
		}
	}

	/* (non-Javadoc)
	 * @see org.asteriskjava.live.AsteriskServerListener#onNewQueueEntry(org.asteriskjava.live.AsteriskQueueEntry)
	 */
	@Override
	public void onNewQueueEntry(AsteriskQueueEntry queueEntry) {
		logger.fine(queueEntry.toString());
		queueEntry.addPropertyChangeListener(this);
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		logger.fine(propertyChangeEvent.toString());
	}

	/* (non-Javadoc)
	 * @see org.asteriskjava.manager.ManagerEventListener#onManagerEvent(org.asteriskjava.manager.event.ManagerEvent)
	 */
	@Override
	public void onManagerEvent(ManagerEvent managerEvent) {
		logger.fine("Received manager event: " + managerEvent);
		if (managerEvent instanceof MeetMeEndEvent) {
			MeetMeEndEvent endEvent = (MeetMeEndEvent) managerEvent;
			Conference conference = state.getStartedConferences().get(
					endEvent.getMeetMe());
			if (conference != null)
				conference.handleEndConference();
		}

	}

}
