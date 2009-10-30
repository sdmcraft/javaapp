package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

/**
 * An unsigned byte, 8-bits of data, an octet
 */
public class U8 implements DataType {
	private byte value;

	/**
	 * Constructs a U8 object with specified byte value
	 * 
	 * @param value
	 *            Associated byte value
	 */
	public U8(byte value) {
		this.value = value;
	}

	/**
	 * Constructs a U8 object by reading from the specified input stream
	 * 
	 * @param in
	 *            Input stream to read from
	 * @throws Exception
	 *             If an error occurred while reading
	 */
	public U8(PushbackInputStream in) throws Exception {
		value = (byte) in.read();
		if (value == -1)
			throw new Exception("Error reading from input stream");
	}

	/**
	 * Returns the contained byte value
	 * 
	 * @return Contained byte value
	 */
	public byte getValue() {
		return value;
	}

	/**
	 * Sets the contained byte value
	 * 
	 * @param value
	 *            Byte value to be set
	 */
	public void setValue(byte value) {
		this.value = value;
	}

	/**
	 * Writes this object to the specified output stream
	 * 
	 * @param out
	 *            Output stream to write to
	 * @throws Exception
	 *             If an error occurred while writing
	 */
	public void write(OutputStream out) throws Exception {
		out.write(value & 0xFF);
	}

	/**
	 * Indicates if the object passed as an argument is equal to this object.
	 * Other than the passed object being the same object, same associated value
	 * would also mean equal objects
	 * 
	 * @param The
	 *            object passed for comparison
	 * @return true if the objects are equal else false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final U8 other = (U8) obj;
		if (value != other.value)
			return false;
		return true;
	}

	/**
	 * Unreads this object and pushes back whatever is read to the input stream
	 * 
	 * @param in
	 *            Input stream for unreading
	 * @throws Exception
	 *             If an error occured while unread
	 */
	public void unread(PushbackInputStream in) throws Exception {
		in.unread(value);
	}

	/**
	 * Returns the string representation
	 * 
	 * @return The string representation
	 */
	@Override
	public String toString() {
		return Byte.toString(value);
	}
}
