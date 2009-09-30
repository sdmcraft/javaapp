package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;

public class U8 {
	private byte value;

	public U8(byte value) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final U8 other = (U8) obj;
		if (value != other.value)
			return false;
		return true;
	}

	public void unread(PushbackInputStream in) throws Exception {
		in.unread(value);
	}
}
