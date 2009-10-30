package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class AMFLongString extends AMFValue {
	private UTF8Long value;

	public AMFLongString(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.LONG_STRING_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF Long String type!", in);
		}

		value = new UTF8Long(in);
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (marker != null)
			marker.unread(in);

	}

	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		value.write(out);
	}
}
