package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

public class U24 extends UType {

	public U24(byte[] valueBytes) throws Exception {
		if (valueBytes.length != 3)
			throw new Exception("Invalid byte array length. It should be 3");
		else {
			this.valueBytes = valueBytes;
			intValue = (int) Tools.byteArrToInt(valueBytes);
		}
	}

	public U24(PushbackInputStream in) throws Exception {
		valueBytes = new byte[3];
		if (3 != in.read(valueBytes))
			throw new Exception("Error reading from input stream");
		intValue = (int) Tools.byteArrToInt(valueBytes);
	}

	public void setIntValue(int intValue) throws Exception {
		byte[] valueBytes = Tools.intToByteArr(intValue);
		if (valueBytes[3] != 0)
			throw new Exception("int value too large to fit in U24");
		else {
			this.valueBytes = valueBytes;
			this.intValue = intValue;
		}
	}

}
