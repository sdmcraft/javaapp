package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

import com.sdm.FLVParser.utils.Tools;

public class UTF8 {
	private U16 length;
	private U8[] utfData;

	public UTF8(InputStream in) throws Exception {
		byte[] lengthBytes = new byte[2];
		if (2 != in.read(lengthBytes))
			throw new Exception("Error reading from input stream");
		this.length = new U16(lengthBytes);
		
		int decLength = Tools.U16toInt(this.length);
		byte[] utfDataBytes = new byte[decLength];

		if (decLength != in.read(utfDataBytes))
			throw new Exception("Error reading from input stream");
		this.utfData = Tools.byteArrToU8Arr(utfDataBytes);
	}
}
