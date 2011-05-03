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
import org.asteriskjava.manager.event.HangupEvent;
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

	/** The context. */
	private final Context context;

	/**
	 * Instantiates a new live event handler.
	 * 
	 * @param context
	 *            the context
	 */
	public LiveEventHandler(Context context) {
		this.context = context;
	}

	/**
	 * Inits the.
	 */
	public void init() {
		AsteriskServer asteriskServer = context.getAsteriskServer();
		asteriskServer.addAsteriskServerListener(this);
		context.getConnection().addManagerEventListener(this);

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

		logger.fine("LiveEventHandler registered to receive events..");
	}

	/**
	 * Destroy.
	 */
	public void destroy() {
		AsteriskServer asteriskServer = context.getAsteriskServer();
		asteriskServer.removeAsteriskServerListener(this);
		context.getConnection().removeManagerEventListener(this);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.asteriskjava.live.AsteriskServerListener#onNewAgent(org.asteriskjava
	 * .live.internal.AsteriskAgentImpl)
	 */
	@Override
	public void onNewAgent(AsteriskAgentImpl agent) {
		logger.fine(agent.toString());
		agent.addPropertyChangeListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.asteriskjava.live.AsteriskServerListener#onNewAsteriskChannel(org
	 * .asteriskjava.live.AsteriskChannel)
	 */
	@Override
	public void onNewAsteriskChannel(AsteriskChannel channel) {
		logger.fine("A new channel joined:" + channel.toString());
		String id = channel.getId();
		String phoneNumber = AsteriskUtils.getPhoneNumberFromChannel(channel
				.getName());
		Map<String, String> dialOutLock = context.getDialOutLocks().get(
				phoneNumber);
		if (dialOutLock != null) {
			synchronized (dialOutLock) {
				dialOutLock.put("user-id", id);
				dialOutLock.notifyAll();
			}
		}
		channel.addPropertyChangeListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.asteriskjava.live.AsteriskServerListener#onNewMeetMeUser(org.asteriskjava
	 * .live.MeetMeUser)
	 */
	@Override
	public void onNewMeetMeUser(MeetMeUser user) {
		logger.fine("A new user joined " + user.toString());
		try {
			// String phoneNumber = AsteriskUtils.getUserPhoneNumber(user);
			// logger.fine("Phone Number:" + phoneNumber);
			// Map<String, String> dialOutLock = context.getDialOutLocks().get(
			// phoneNumber);
			// if (dialOutLock != null) {
			// synchronized (dialOutLock) {
			// dialOutLock.put("user-id", user.getChannel().getId());
			// dialOutLock.notifyAll();
			// }
			// /* Sleep for sometime to let notified thread proceed */
			// try {
			// Thread.sleep(500);
			// } catch (InterruptedException ex) {
			// logger.log(Level.WARNING, ex.getMessage(), ex);
			// }
			// }

			Conference conference = context.getConferences().get(
					user.getRoom().getRoomNumber());
			conference.handleAddConferenceUser(user);
		} catch (Exception ex) {
			logger.log(Level.WARNING, ex.getMessage(), ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.asteriskjava.live.AsteriskServerListener#onNewQueueEntry(org.asteriskjava
	 * .live.AsteriskQueueEntry)
	 */
	@Override
	public void onNewQueueEntry(AsteriskQueueEntry queueEntry) {
		logger.fine(queueEntry.toString());
		queueEntry.addPropertyChangeListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejava.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		logger.fine(propertyChangeEvent.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.asteriskjava.manager.ManagerEventListener#onManagerEvent(org.asteriskjava
	 * .manager.event.ManagerEvent)
	 */
	@Override
	public void onManagerEvent(ManagerEvent managerEvent) {
		logger.fine("Received manager event: " + managerEvent);
		if (managerEvent instanceof MeetMeEndEvent) {
			MeetMeEndEvent endEvent = (MeetMeEndEvent) managerEvent;
			Conference conference = context.getConferences().get(
					endEvent.getMeetMe());
			if (conference != null)
				conference.handleEndConference();
		} else if (managerEvent instanceof HangupEvent) {
			HangupEvent hangupEvent = (HangupEvent) managerEvent;
			context.handleChannelHangup(hangupEvent.getUniqueId());
		}
	}

}
