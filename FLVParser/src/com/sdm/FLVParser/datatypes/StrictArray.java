package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

public class StrictArray extends AMFValue {
	private U32 arrayCount;
	private AMFValue[] value;

	public StrictArray(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.STRICT_ARRAY_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF strict array type!", in);
		}

		arrayCount = new U32(in);
		System.out.println("Strict array length:" + arrayCount.getIntValue());
		value = new AMFValue[arrayCount.getIntValue()];
		for (int i = 0; i < arrayCount.getIntValue(); i++) {
			value[i] = Tools.sniffer(in);
		}
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			for (int i = value.length; i >= 0; i--)
				value[i].unread(in);
		if (arrayCount != null)
			arrayCount.unread(in);
		if (marker != null)
			marker.unread(in);
	}
}
