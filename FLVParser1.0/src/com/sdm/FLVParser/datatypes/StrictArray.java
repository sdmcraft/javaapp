package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Class StrictArray.
 */
public class StrictArray extends AMFValue {
	
	/** The array count. */
	private U32 arrayCount;
	
	/** The value. */
	private AMFValue[] value;

	/**
	 * Instantiates a new strict array.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public StrictArray(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.STRICT_ARRAY_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF strict array type!", in);
		}

		arrayCount = new U32(in);
		////System.out.println("Strict array length:" + arrayCount.getIntValue());
		value = new AMFValue[arrayCount.getIntValue()];
		for (int i = 0; i < arrayCount.getIntValue(); i++) {
			value[i] = Tools.sniffer(in);
		}
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			for (int i = value.length; i >= 0; i--)
				value[i].unread(in);
		if (arrayCount != null)
			arrayCount.unread(in);
		if (marker != null)
			marker.unread(in);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		arrayCount.write(out);
		for (AMFValue amfVal : value)
			amfVal.write(out);
	}
}
