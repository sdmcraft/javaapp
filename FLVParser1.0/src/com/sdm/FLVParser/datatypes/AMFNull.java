package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The null type is represented by the null type marker. No further information
 * is encoded for this value.
 */
public class AMFNull extends AMFValue {

	/**
	 * Instantiates a new aMF null.
	 * 
	 * @param in
	 *            Input stream to read from
	 * 
	 * @throws Exception
	 *             If an error occurred while reading
	 */
	public AMFNull(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.NULL_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException("Invalid marker for AMF Null type!",
					in);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
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
	}

}
