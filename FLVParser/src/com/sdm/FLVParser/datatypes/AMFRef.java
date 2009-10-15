package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class AMFRef extends AMFValue {

	private U16 index;

	public AMFRef(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.REFERENCE_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException("Invalid marker for AMF Ref type!", in);
		}
		index = new U16(in);
	}
	
	public void unread(PushbackInputStream in) throws Exception {
		if (index != null)
			index.unread(in);
		if (marker != null)
			marker.unread(in);
	}

	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		index.write(out);
	}

}
