package org.meetmejava;

public class Extension {
	
	private final String context;
	private final String number;
	public Extension(String context, String number) {
		super();
		this.context = context;
		this.number = number;
	}
	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

}
