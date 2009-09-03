package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

public class U16 {
	private byte[] value;

	public U16(byte[] value) throws Exception {
		if (value.length != 2)
			throw new Exception("Invalid byte array length. It should be 2");
		else
			this.value = value;
	}

	public U16(InputStream in) throws Exception {
		value = new byte[2];
		if (2 != in.read(value))
			throw new Exception("Error reading from input stream");
	}

	public byte[] getValue() {
		return value;
	}

}
