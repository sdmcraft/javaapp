package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

public class U32 extends UType {

	public U32(byte[] valueBytes) throws Exception {
		if (valueBytes.length != 4)
			throw new Exception("Invalid byte array length. It should be 4");
		else {
			this.valueBytes = valueBytes;
			intValue = (int) Tools.byteArrToInt(valueBytes);
		}
	}

	public U32(PushbackInputStream in) throws Exception {
		valueBytes = new byte[4];
		if (4 != in.read(valueBytes))
			throw new Exception("Error reading from input stream");
		intValue = (int) Tools.byteArrToInt(valueBytes);
	}

	public void setIntValue(int intValue) throws Exception {
		byte[] valueBytes = Tools.intToByteArr(intValue);
		this.valueBytes = valueBytes;
		this.intValue = intValue;
	}

}
