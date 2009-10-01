package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

public class S16 extends UType {

	public S16(PushbackInputStream in) throws Exception {
		valueBytes = new byte[2];
		if (2 != in.read(valueBytes))
			throw new Exception("Error reading from input stream");
		intValue = (int) Tools.byteArrToInt(valueBytes);
	}

	public void setIntValue(int value) throws Exception {
		byte[] valueBytes = Tools.intToByteArr(intValue);
		if (valueBytes[2] != 0 || valueBytes[3] != 0)
			throw new Exception("int value too large to fit in U16");
		else {
			this.valueBytes = valueBytes;
			this.intValue = intValue;
		}
	}
}
