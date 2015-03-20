package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import com.sdm.FLVParser.utils.PushbackInputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class Marker.
 */
public class Marker {
	
	/** The value. */
	private U8 value;

	/**
	 * Instantiates a new marker.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public Marker(PushbackInputStream in) throws Exception {
		value = new U8(in);
	}

	/**
	 * Write.
	 * 
	 * @param out the out
	 * 
	 * @throws Exception the exception
	 */
	public void write(OutputStream out) throws Exception {
		value.write(out);
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public U8 getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value the new value
	 */
	public void setValue(U8 value) {
		this.value = value;
	}

	/**
	 * Gets the byte value.
	 * 
	 * @return the byte value
	 */
	public byte getByteValue() {
		return value.getValue();
	}

	/**
	 * Unread.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public void unread(PushbackInputStream in) throws Exception {
		value.unread(in);
	}

}
