package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

public class Marker {
	private U8 value;

	public Marker(InputStream in) throws Exception {
		value = new U8(in);
	}
}
