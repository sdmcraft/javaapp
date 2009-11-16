package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.utils.Tools;

// TODO: Auto-generated Javadoc
/**
 * An unsigned 16-bit integer in big endian (network) byte order.
 * 
 * @author satyam
 */
public class U16 extends UType {
	
	/**
	 * Constructs a U16 object with specified byte array.
	 * 
	 * @param valueBytes Associated byte array
	 * 
	 * @throws Exception If an error occurred while constructing
	 */
	public U16(byte[] valueBytes) throws Exception {
		if (valueBytes.length != 2)
			throw new Exception("Invalid byte array length. It should be 2");
		else {
			this.valueBytes = valueBytes;
			intValue = (int) Tools.byteArrToInt(valueBytes);
		}
	}

	/**
	 * Constructs a U16 object by reading from the specified input stream.
	 * 
	 * @param in Input stream to read from
	 * 
	 * @throws Exception If an error occurred while reading
	 */
	public U16(PushbackInputStream in) throws Exception {
		valueBytes = new byte[2];
		if (2 != in.read(valueBytes))
			throw new Exception("Error reading from input stream");
		System.out.print("U16 value bytes:");
		for (byte b : valueBytes)
			System.out.print(b );
		System.out.println();
		intValue = (int) Tools.byteArrToInt(valueBytes);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.UType#setIntValue(int)
	 */
	@Override
	/**
	 * Sets the associated integer value
	 * 
	 * @param intValue
	 *            Associated integer value
	 * @throws Exception
	 *             If an the value is too large to fit in a U16
	 */
	public void setIntValue(int intValue) throws Exception {
		byte[] valueBytes = Tools.intToByteArr(intValue);
		if (valueBytes[2] != 0 || valueBytes[3] != 0)
			throw new Exception("int value too large to fit in U16");
		else {
			this.valueBytes = valueBytes;
			this.intValue = intValue;
		}
	}

}
