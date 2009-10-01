package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.io.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

public class UTF8Long {
	private U32 length;
	private U8[] utfData;

	public UTF8Long(InputStream in) throws Exception {
		this.length = new U32(in);
		int decLength = Tools.U32toInt(this.length);
		utfData = new U8[decLength];
		for (int i = 0; i < decLength; i++)
			utfData[i] = new U8(in);
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (utfData != null) {
			for (U8 u8 : utfData)
				u8.unread(in);
		}
		if (length != null)
			length.unread(in);
	}

}
