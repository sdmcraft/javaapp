package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import com.sdm.FLVParser.utils.PushbackInputStream;

// TODO: Auto-generated Javadoc
/**
 * An abstract class for unsigned number types.
 * 
 * @author satyam
 */
public abstract class UType {
	
	/** The byte array representation of the associated value. */
	protected byte[] valueBytes;

	/** The integer representation of the associated value. */
	protected int intValue;

	/**
	 * Returns the associated value as a byte array.
	 * 
	 * @return Associated value as a byte array
	 */
	public byte[] getValueBytes() {
		return valueBytes;
	}

	/**
	 * Returns the associated value as an integer.
	 * 
	 * @return Associated value as an integer
	 */
	public int getIntValue() {
		return intValue;
	}

	/**
	 * Sets the integer value associated with this UType.
	 * 
	 * @param value Integer value associated with this UType
	 * 
	 * @throws Exception If an internal error occurred
	 */
	abstract public void setIntValue(int value) throws Exception;

	/**
	 * Writes this object to the specified output stream.
	 * 
	 * @param out Output stream to write to
	 * 
	 * @throws Exception If an error occurred while writing
	 */
	public void write(OutputStream out) throws Exception {
		out.write(valueBytes);
	}

	/**
	 * Unreads this object and pushes back whatever is read to the input stream.
	 * 
	 * @param in Input stream for unreading
	 * 
	 * @throws Exception If an error occurred while unread
	 */
	public void unread(PushbackInputStream in) throws Exception {
		// //System.out.println("Unreading " + valueBytes.length + " bytes");
		in.unread(valueBytes);
	}

	/**
	 * Returns the string representation of this Utype. The associated integer
	 * value is returned as a string
	 * 
	 * @return String representation of the associated integer value
	 */
	@Override
	public String toString() {
		return Integer.toString(intValue);
	}

}
