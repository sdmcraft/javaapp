package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.io.OutputStream;

public class U8 {
	private byte value;

	public U8(byte value) throws Exception {
		this.value = value;
	}

	public U8(InputStream in) throws Exception {
		int intValue = in.read();
		if (intValue == -1)
			throw new Exception("Error reading from input stream");
		else
			value = (byte) intValue;
	}

	public byte getValue() {
		return value;
	}

	public void setValue(byte value) {
		this.value = value;
	}

	public void write(OutputStream out) throws Exception {
		out.write(value & 0xFF);
	}
}
