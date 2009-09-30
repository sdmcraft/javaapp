package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

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
}
