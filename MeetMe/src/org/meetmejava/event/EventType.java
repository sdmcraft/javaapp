package org.meetmejava.event;

/**
 * Denotes the type of the event. The type of the event is governed by the cause
 * of the event.
 */
public enum EventType {

	/** A new user joined */
	USER_JOINED,
	/** The conference ended */
	CONFERENCE_ENDED,
	/** The user was muted */
	MUTE,
	/** The user is talking */
	TALKER,
	/** The user left. */
	USER_LEFT
}
