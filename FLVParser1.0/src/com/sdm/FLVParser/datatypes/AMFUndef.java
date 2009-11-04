package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class AMFUndef.
 */
public class AMFUndef extends AMFValue {
	
	/**
	 * Instantiates a new aMF undef.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public AMFUndef(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.UNDEFINED_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException("Invalid marker for AMF Null type!",
					in);
		}

	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (marker != null)
			marker.unread(in);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
	}

}
