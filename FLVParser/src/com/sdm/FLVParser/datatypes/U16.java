package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

public class U16 extends UType {

	public U16(byte[] valueBytes) throws Exception {
		if (valueBytes.length != 2)
			throw new Exception("Invalid byte array length. It should be 2");
		else {
			this.valueBytes = valueBytes;
			intValue = (int) Tools.byteArrToInt(valueBytes);
		}
	}

	public U16(PushbackInputStream in) throws Exception {
		valueBytes = new byte[2];
		if (2 != in.read(valueBytes))
			throw new Exception("Error reading from input stream");
		intValue = (int) Tools.byteArrToInt(valueBytes);
	}

	public void setIntValue(int intValue) throws Exception {
		byte[] valueBytes = Tools.intToByteArr(intValue);
		if (valueBytes[2] != 0 || valueBytes[3] != 0)
			throw new Exception("int value too large to fit in U16");
		else {
			this.valueBytes = valueBytes;
			this.intValue = intValue;
		}
	}

}
