package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class AMFNull extends AMFValue {
	public AMFNull(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.NULL_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException("Invalid marker for AMF Null type!", in);
		}
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (marker != null)
			marker.unread(in);
	}

}
