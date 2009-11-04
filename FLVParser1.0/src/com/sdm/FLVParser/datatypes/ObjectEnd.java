package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectEnd.
 */
public class ObjectEnd extends ObjectProperty {
	
	/** The empty. */
	private UTF8empty empty;
	
	/** The end marker. */
	private Marker endMarker;

	/**
	 * Instantiates a new object end.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public ObjectEnd(PushbackInputStream in) throws Exception {
		empty = new UTF8empty(in);
		endMarker = new Marker(in);
		////System.out.println("endMarker->" + endMarker.getByteValue());
		if (!Markers.OBJECT_END_MARKER.equals(endMarker.getValue())) {
			unread(in);
			throw new InvalidDataException("Invalid object end marker!", in);
		}
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.ObjectProperty#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (empty != null)
			empty.unread(in);
		if (endMarker != null)
			endMarker.unread(in);

	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.ObjectProperty#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		empty.write(out);
		endMarker.write(out);
	}
}
