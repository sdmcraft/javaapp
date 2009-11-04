package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectContent.
 */
public class ObjectContent extends ObjectProperty {
	
	/** The name. */
	private UTF8 name;
	
	/** The value. */
	private AMFValue value;

	/**
	 * Instantiates a new object content.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
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

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.ObjectProperty#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (name != null)
			name.unread(in);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.ObjectProperty#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		name.write(out);
		value.write(out);
	}
}