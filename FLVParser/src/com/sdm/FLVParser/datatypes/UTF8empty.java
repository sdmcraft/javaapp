package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

import com.sdm.FLVParser.utils.Tools;

public class UTF8empty {
	private U16 length;

	public UTF8empty(InputStream in) throws Exception {
		byte[] lengthBytes = new byte[2];
		if (2 != in.read(lengthBytes))
			throw new Exception("Error reading from input stream");
		this.length = new U16(lengthBytes);
		if (0 != Tools.U16toInt(this.length))
			throw new Exception("UTF8empty must have a length of 0");
	}
}
