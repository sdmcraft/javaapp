package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class AMFRef.
 */
public class AMFRef extends AMFValue {

	/** The index. */
	private U16 index;

	/**
	 * Instantiates a new aMF ref.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public AMFRef(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.REFERENCE_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException("Invalid marker for AMF Ref type!", in);
		}
		index = new U16(in);
	}
	
	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (index != null)
			index.unread(in);
		if (marker != null)
			marker.unread(in);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		index.write(out);
	}

}
