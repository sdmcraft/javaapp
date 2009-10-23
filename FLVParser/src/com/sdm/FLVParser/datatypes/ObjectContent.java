package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.Tools;

public class ObjectContent extends ObjectProperty {
	private UTF8 name;
	private AMFValue value;

	public ObjectContent(PushbackInputStream in) throws Exception {
		try {
			name = new UTF8(in);
			// System.out.println("Object content name is:"
			// + name.getStringValue());
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

	@Override
	public void write(OutputStream out) throws Exception {
		name.write(out);
		value.write(out);
	}
}