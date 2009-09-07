package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.io.OutputStream;

public class U32 {
	private byte[] value;

	public U32(byte[] value) throws Exception {
		if (value.length != 4)
			throw new Exception("Invalid byte array length. It should be 4");
		else
			this.value = value;
	}

	public U32(InputStream in) throws Exception {
		value = new byte[4];
		if (4 != in.read(value))
			throw new Exception("Error reading from input stream");
	}

	public byte[] getValue() {
		return value;
	}

	public void write(OutputStream out) throws Exception {
		out.write(value);
	}

}
