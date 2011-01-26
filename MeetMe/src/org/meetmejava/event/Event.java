package org.meetmejava.event;

// TODO: Auto-generated Javadoc
/**
 * The Class Event.
 */
public class Event {
	
	/** The type. */
	private final EventType type;
	
	/** The data. */
	private final Object data;

	/**
	 * Instantiates a new event.
	 *
	 * @param type the type
	 */
	public Event(EventType type) {
		this.type = type;
		data = null;
	}

	/**
	 * Instantiates a new event.
	 *
	 * @param type the type
	 * @param data the data
	 */
	public Event(EventType type, Object data) {
		this.type = type;
		this.data = data;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

}
