package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class ScriptDataObject {
	private Marker objectNameType;
	private ObjectContent objectData;

	public ScriptDataObject(PushbackInputStream in) throws Exception {
		objectNameType = new Marker(in);
		if (!Markers.STRING_MARKER.equals(objectNameType.getValue())) {
			throw new InvalidDataException(
					"Invalid object name type!! Must be 2!!", in);
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
