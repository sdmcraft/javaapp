package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class AMFNumber extends AMFValue {
	private AMFDouble value;

	public AMFNumber(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.NUMBER_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF Number type!", in);
		}
		value = new AMFDouble(in);
	}

	public double getDoubleValue() {
		return value.getDoubleValue();
	}

	public void setDoubleValue(double value) throws Exception {
		this.value.setDoubleValue(value);

	}

	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (marker != null)
			marker.unread(in);
	}

}
