package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class ObjectEnd extends ObjectProperty {
	private UTF8empty empty;
	private Marker endMarker;

	public ObjectEnd(PushbackInputStream in) throws Exception {
		empty = new UTF8empty(in);
		endMarker = new Marker(in);
		if (!endMarker.equals(Markers.OBJECT_END_MARKER)) {
			unread(in);
			throw new InvalidDataException("Invalid object end marker!");
		}
	}

	public void unread(PushbackInputStream in) throws Exception {
		endMarker.unread(in);
		empty.unread(in);
	}
}
