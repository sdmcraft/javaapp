package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class AMFString.
 */
public class AMFString extends AMFValue {
	
	/** The value. */
	private UTF8 value;
	
	/** The string value. */
	String stringValue;

	/**
	 * Instantiates a new aMF string.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public AMFString(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.STRING_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF String type!", in);
		}

		value = new UTF8(in);
		stringValue = value.getStringValue();
	}

	/**
	 * Gets the string value.
	 * 
	 * @return the string value
	 */
	public String getStringValue() {
		return stringValue;
	}

	/**
	 * Sets the string value.
	 * 
	 * @param stringValue the new string value
	 * 
	 * @throws Exception the exception
	 */
	public void setStringValue(String stringValue) throws Exception {
		this.stringValue = stringValue;
		value = new UTF8(stringValue);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#write(java.io.OutputStream)
	 */
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		value.write(out);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (marker != null)
			marker.unread(in);
	}

}
