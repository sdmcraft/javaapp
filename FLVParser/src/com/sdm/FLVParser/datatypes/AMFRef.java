package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

public class AMFRef extends AMFValue {

	private U16 index;

	public AMFRef(InputStream in) throws Exception {
		marker = new Marker(in);
		index = new U16(in);
	}
}
