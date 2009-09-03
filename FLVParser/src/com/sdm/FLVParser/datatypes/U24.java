package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

public class U24 {
	private byte[] value;

	public U24(byte[] value) throws Exception {
		if (value.length != 3)
			throw new Exception("Invalid byte array length. It should be 3");
		else
			this.value = value;
	}

	public U24(InputStream in) throws Exception {
		value = new byte[3];
		if (3 != in.read(value))
			throw new Exception("Error reading from input stream");
	}

	public byte[] getValue() {
		return value;
	}

}
