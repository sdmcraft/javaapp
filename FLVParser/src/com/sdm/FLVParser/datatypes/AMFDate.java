package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class AMFDate extends AMFValue {
	private S16 timeZone;
	private AMFDouble value;

	public AMFDate(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.DATE_MARKER.equals(marker)) {
			unread(in);
			throw new InvalidDataException("Invalid marker for AMF Date type!");
		}
		timeZone = new S16(in);
		if (timeZone.getIntValue() != 0) {
			unread(in);
			throw new InvalidDataException(
					"Invalid timezone for AMF Date type! Should always be 0!");
		}
		value = new AMFDouble(in);
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (timeZone != null)
			timeZone.unread(in);
		if (marker != null)
			marker.unread(in);
	}

}
