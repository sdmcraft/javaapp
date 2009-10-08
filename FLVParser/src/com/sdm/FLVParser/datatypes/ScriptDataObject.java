package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class ScriptDataObject {
	private Marker objectNameType;
	private ObjectContent objectData;

	public ScriptDataObject(PushbackInputStream in) throws Exception {
		objectNameType = new Marker(in);
		if (!Markers.STRING_MARKER.equals(objectNameType)) {
			throw new InvalidDataException(
					"Invalid object name type!! Must be 2!!");
		}
		objectData = new ObjectContent(in);
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (objectData != null)
			objectData.unread(in);
		if (objectNameType != null)
			objectNameType.unread(in);
	}
}
