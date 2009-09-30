package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;

public class Marker {
	private U8 value;

	public Marker(InputStream in) throws Exception {
		value = new U8(in);
	}

	public void write(OutputStream out) throws Exception {
		value.write(out);
	}

	public U8 getValue() {
		return value;
	}

	public void setValue(U8 value) {
		this.value = value;
	}

	public byte getByteValue() {
		return value.getValue();
	}

	public void unread(PushbackInputStream in) throws Exception {
		value.unread(in);
	}

}
