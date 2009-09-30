package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

public class AMFNull extends AMFValue {
	public AMFNull(InputStream in) throws Exception {
		marker = new Marker(in);
	}
}
