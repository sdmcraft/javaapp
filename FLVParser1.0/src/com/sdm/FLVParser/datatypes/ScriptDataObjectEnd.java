package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class ScriptDataObjectEnd.
 */
public class ScriptDataObjectEnd {
	
	/** The object name type. */
	private Marker objectNameType;
	
	/** The object end. */
	private ObjectEnd objectEnd;

	/**
	 * Instantiates a new script data object end.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public ScriptDataObjectEnd(PushbackInputStream in) throws Exception {
		objectNameType = new Marker(in);
		if (!Markers.STRING_MARKER.equals(objectNameType)) {
			unread(in);
			throw new InvalidDataException(
					"Invalid object name type!! Must be 2!!", in);
		}
		try {
			objectEnd = new ObjectEnd(in);
		} catch (InvalidDataException ex) {
			unread(in);
			throw ex;
		}
	}

	/**
	 * Unread.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (objectEnd != null)
			objectEnd.unread(in);
		if (objectNameType != null)
			objectNameType.unread(in);
	}

	/**
	 * Write.
	 * 
	 * @param out the out
	 * 
	 * @throws Exception the exception
	 */
	public void write(OutputStream out) throws Exception {
		objectNameType.write(out);
		objectEnd.write(out);
	}
}
