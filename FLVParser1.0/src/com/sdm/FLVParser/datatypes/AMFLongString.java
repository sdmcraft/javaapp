package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * A long string is used in AMF 0 to encode strings that would occupy more than
 * 65535 bytes when UTF-8 encoded. The byte-length header of the UTF-8 encoded
 * string is a 32-bit integer instead of the regular 16-bit integer.
 */
public class AMFLongString extends AMFValue {

	/** The value. */
	private UTF8Long value;

	/**
	 * Constructs a AMFLongString object by reading from the specified input
	 * stream.
	 * 
	 * @param in
	 *            Input stream to read from
	 * 
	 * @throws Exception
	 *             If an error occurred while reading
	 */
	public AMFLongString(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.LONG_STRING_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF Long String type!", in);
		}

		value = new UTF8Long(in);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (marker != null)
			marker.unread(in);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sdm.FLVParser.datatypes.AMFValue#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		value.write(out);
	}

	/**
	 * Returns the value.
	 * 
	 * @return the value
	 */
	public UTF8Long getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(UTF8Long value) {
		this.value = value;
	}
}
