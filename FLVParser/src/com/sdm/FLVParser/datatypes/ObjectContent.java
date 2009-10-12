package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.Tools;

public class ObjectContent extends ObjectProperty {
	private UTF8 name;
	private AMFValue value;

	public ObjectContent(PushbackInputStream in) throws Exception {
		try {
			name = new UTF8(in);
			value = Tools.sniffer(in);
		} catch (InvalidDataException ex) {			
			unread(in);
			throw ex;
		}
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (name != null)
			name.unread(in);
	}
}
