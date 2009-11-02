package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

/**
 * An unsigned 32-bit integer in big endian (network) byte order
 * 
 * @author satyam
 * 
 */
public class U32 extends UType {

	/**
	 * Constructs a U32 object with specified byte array
	 * 
	 * @param valueBytes
	 *            Associated byte array
	 * @throws Exception
	 *             If an error occurred while constructing
	 */
	public U32(byte[] valueBytes) throws Exception {
		if (valueBytes.length != 4)
			throw new Exception("Invalid byte array length. It should be 4");
		else {
			this.valueBytes = valueBytes;
			intValue = (int) Tools.byteArrToInt(valueBytes);
		}
	}

	public U32(int value) {
		this.intValue = value;
		this.valueBytes = Tools.intToByteArr(intValue);
	}

	/**
	 * Constructs a U32 object by reading from the specified input stream
	 * 
	 * @param in
	 *            Input stream to read from
	 * @throws Exception
	 *             If an error occurred while reading
	 */
	public U32(PushbackInputStream in) throws Exception {
		valueBytes = new byte[4];
		if (4 != in.read(valueBytes))
			throw new Exception("Error reading from input stream");
		intValue = (int) Tools.byteArrToInt(valueBytes);
	}

	/**
	 * Sets the associated integer value
	 * 
	 * @param intValue
	 *            Associated integer value
	 * @throws Exception
	 *             If an internal error occurred
	 */
	public void setIntValue(int intValue) throws Exception {
		byte[] valueBytes = Tools.intToByteArr(intValue);
		this.valueBytes = valueBytes;
		this.intValue = intValue;
	}

}
