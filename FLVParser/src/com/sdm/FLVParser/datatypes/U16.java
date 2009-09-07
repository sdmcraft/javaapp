package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.io.OutputStream;
import com.sdm.FLVParser.utils.Tools;

public class U16 {
	private byte[] value;
	private int intValue;

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
		intValue = (int) Tools.byteArrToInt(value);
	}

	public byte[] getValueBytes() {
		return value;
	}

	public int getIntValue() {
		return intValue;
	}

	public void write(OutputStream out) throws Exception {
		out.write(value);
	}

}
