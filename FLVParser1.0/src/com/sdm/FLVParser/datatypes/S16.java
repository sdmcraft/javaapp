package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Class S16.
 */
public class S16 extends UType {

	/**
	 * Constructs a S16 object by reading from the specified input stream.
	 * 
	 * @param in Input stream to read from
	 * 
	 * @throws Exception If an error occurred while reading
	 */
	public S16(PushbackInputStream in) throws Exception {
		valueBytes = new byte[2];
		if (2 != in.read(valueBytes))
			throw new Exception("Error reading from input stream");
		intValue = (int) Tools.byteArrToInt(valueBytes);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.UType#setIntValue(int)
	 */
	public void setIntValue(int value) throws Exception {
		byte[] valueBytes = Tools.intToByteArr(intValue);
		if (valueBytes[2] != 0 || valueBytes[3] != 0)
			throw new Exception("int value too large to fit in U16");
		else {
			this.valueBytes = valueBytes;
			this.intValue = intValue;
		}
	}
}
