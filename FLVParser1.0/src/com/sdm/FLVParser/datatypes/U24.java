package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

/**
 * An unsigned 24-bit integer in big endian (network) byte order
 * 
 * @author satyam
 * 
 */
public class U24 extends UType {

	/**
	 * Constructs a U24 object with specified byte array
	 * 
	 * @param valueBytes
	 *            Associated byte array
	 * @throws Exception
	 *             If an error occurred while constructing
	 */
	public U24(byte[] valueBytes) throws Exception {
		if (valueBytes.length != 3)
			throw new Exception("Invalid byte array length. It should be 3");
		else {
			this.valueBytes = valueBytes;
			intValue = (int) Tools.byteArrToInt(valueBytes);
		}
	}

	/**
	 * Constructs a U24 object by reading from the specified input stream
	 * 
	 * @param in
	 *            Input stream to read from
	 * @throws Exception
	 *             If an error occurred while reading
	 */
	public U24(PushbackInputStream in) throws Exception {
		valueBytes = new byte[3];
		if (3 != in.read(valueBytes))
			throw new Exception("Error reading from input stream");
		intValue = (int) Tools.byteArrToInt(valueBytes);
	}

	/**
	 * Sets the associated integer value
	 * 
	 * @param intValue
	 *            Associated integer value
	 * @throws Exception
	 *             If an the value is too large to fit in a U24
	 */
	public void setIntValue(int intValue) throws Exception {
		byte[] valueBytes = Tools.intToByteArr(intValue);
		if (valueBytes[3] != 0)
			throw new Exception("int value too large to fit in U24");
		else {
			this.valueBytes = valueBytes;
			this.intValue = intValue;
		}
	}

}
