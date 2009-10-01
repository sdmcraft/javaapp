package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import java.io.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class AMFString extends AMFValue {
	private UTF8 value;
	String stringValue;

	public AMFString(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.STRING_MARKER.equals(marker)) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF String type!");
		}

		value = new UTF8(in);
		stringValue = value.getStringValue();
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) throws Exception {
		this.stringValue = stringValue;
		value = new UTF8(stringValue);
	}

	public void write(OutputStream out) throws Exception {
		value.write(out);
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (marker != null)
			marker.unread(in);
	}

}
