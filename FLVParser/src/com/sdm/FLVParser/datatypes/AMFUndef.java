package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class AMFUndef extends AMFValue {
	public AMFUndef(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.UNDEFINED_MARKER.equals(marker)) {
			unread(in);
			throw new InvalidDataException("Invalid marker for AMF Null type!");
		}

	}
	public void unread(PushbackInputStream in) throws Exception {
		if (marker != null)
			marker.unread(in);
	}

}
