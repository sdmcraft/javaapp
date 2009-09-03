package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

import com.sdm.FLVParser.utils.Tools;

public class UTF8 {
	private U16 length;
	private U8[] utfData;

	public UTF8(InputStream in) throws Exception {
		this.length = new U16(in);
		int decLength = Tools.U16toInt(this.length);
		utfData = new U8[decLength];
		for (int i = 0; i < decLength; i++)
			utfData[i] = new U8(in);
	}
}
