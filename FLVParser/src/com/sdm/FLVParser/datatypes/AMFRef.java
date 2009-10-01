package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class AMFRef extends AMFValue {

	private U16 index;

	public AMFRef(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.REFERENCE_MARKER.equals(marker)) {
			unread(in);
			throw new InvalidDataException("Invalid marker for AMF Ref type!");
		}
		index = new U16(in);
	}
	
	public void unread(PushbackInputStream in) throws Exception {
		if (index != null)
			index.unread(in);
		if (marker != null)
			marker.unread(in);
	}

}
