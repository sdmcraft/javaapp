package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

public class AMFNumber extends AMFValue {
	private AMFDouble value;

	public AMFNumber(InputStream in) throws Exception {
		super.marker = new Marker(in);
		value = new AMFDouble(in);

	}

}
