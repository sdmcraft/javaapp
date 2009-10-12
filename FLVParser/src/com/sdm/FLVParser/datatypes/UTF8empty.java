package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.Tools;

public class UTF8empty {
	private U16 length;

	public UTF8empty(PushbackInputStream in) throws Exception {
		this.length = new U16(in);
		if (0 != Tools.U16toInt(this.length)) {
			unread(in);
			throw new InvalidDataException("UTF8empty must have a length of 0",
					in);
		}
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (length != null)
			length.unread(in);
	}

}
