package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class ScriptDataObjectEnd {
	private Marker objectNameType;
	private ObjectEnd objectEnd;

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

	public void unread(PushbackInputStream in) throws Exception {
		if (objectEnd != null)
			objectEnd.unread(in);
		if (objectNameType != null)
			objectNameType.unread(in);
	}

	public void write(OutputStream out) throws Exception {
		objectNameType.write(out);
		objectEnd.write(out);
	}
}
