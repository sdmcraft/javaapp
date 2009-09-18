package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.io.OutputStream;

public class Marker {
	private U8 value;

	public Marker(InputStream in) throws Exception {
		value = new U8(in);
	}

	public void write(OutputStream out) throws Exception {
		value.write(out);
	}
}
