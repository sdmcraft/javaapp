package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

public class StrictArray extends AMFValue {
	private U32 arrayCount;
	private AMFValue[] value;

	public StrictArray(PushbackInputStream in) throws Exception {
		arrayCount = new U32(in);
		value = new AMFValue[arrayCount.getIntValue()];
		for (int i = 0; i < arrayCount.getIntValue(); i++) {
			value[i] = Tools.sniffer(in);			
		}
	}
}
