package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class ECMAArray extends AMFValue {
	private U32 associativeCount;
	private ObjectProperty[] value;

	public ECMAArray(PushbackInputStream in) throws Exception {
		associativeCount = new U32(in);
		value = new ObjectProperty[associativeCount.getIntValue()];
		for (int i = 0; i < associativeCount.getIntValue(); i++) {
			try {
				value[i] = new ObjectEnd(in);
			} catch (InvalidDataException ex) {
				value[i] = new ObjectContent(in);
			}
		}
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			for (int i = value.length; i >= 0; i--)
				value[i].unread(in);
		if (associativeCount != null)
			associativeCount.unread(in);
	}
}
