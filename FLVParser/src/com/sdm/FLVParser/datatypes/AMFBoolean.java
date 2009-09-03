package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

import com.sdm.FLVParser.utils.Tools;

public class AMFBoolean extends AMFValue {
	private U8 value;
	private boolean booleanValue;

	public AMFBoolean(InputStream in) throws Exception {
		super.marker = new Marker(in);
		value = new U8(in);
		if (0 == Tools.U8toInt(value))
			booleanValue = false;
		else
			booleanValue = true;
	}
}
