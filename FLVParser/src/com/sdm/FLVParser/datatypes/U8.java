package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

public class U8 {
	private byte value;

	public U8(byte value) {
		this.value = value;
	}

	public U8(PushbackInputStream in) throws Exception {
		value = (byte) in.read();
		if (value == -1)
			throw new Exception("Error reading from input stream");
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

	@Override
	public String toString() {
		return Byte.toString(value);
	}
}
