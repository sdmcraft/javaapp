package com.sdm.FLVParser.datatypes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

/**
 * 8 byte IEEE-754 double precision floating point value in network byte order
 * (sign bit in low memory).
 * 
 * @author satyam
 * 
 */
public class AMFDouble {
	private double doubleValue;
	private byte[] valueBytes;

	/**
	 * Constructs a AMF double object by reading from the specified input stream
	 * 
	 * @param in
	 *            Input stream to read from
	 * @throws Exception
	 *             If an error occurred while reading
	 */
	public AMFDouble(PushbackInputStream in) throws Exception {
		valueBytes = new byte[8];
		if (8 != in.read(valueBytes))
			throw new Exception("Error reading from input stream");
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new ByteArrayInputStream(valueBytes));
			doubleValue = dis.readDouble();
		} finally {
			if (dis != null)
				dis.close();
		}
	}

	/**
	 * Returns the associated double value
	 * 
	 * @return Associated Double value
	 */
	public double getDoubleValue() {
		return doubleValue;
	}

	/**
	 * Sets the associated double value
	 * 
	 * @param doubleValue
	 *            The associated double value
	 * @throws Exception
	 *             An internal error occurred
	 */
	public void setDoubleValue(double doubleValue) throws Exception {
		this.doubleValue = doubleValue;
		valueBytes = new byte[8];
		DataOutputStream dout = null;
		ByteArrayOutputStream bout = new ByteArrayOutputStream(8);
		try {
			dout = new DataOutputStream(bout);
			dout.writeDouble(doubleValue);
			valueBytes = bout.toByteArray();
		} finally {
			if (dout != null)
				dout.close();
			if (bout != null)
				bout.close();
		}
	}

	/**
	 * Returns the byte array for the associated double value
	 * 
	 * @return The byte array for the associated double value
	 */
	public byte[] getValueBytes() {
		return valueBytes;
	}

	/**
	 * Sets the byte array for the associated double value
	 * 
	 * @param valueBytes
	 *            The byte array for the associated double value
	 * @throws Exception
	 *             An internal error occurred
	 */
	public void setValueBytes(byte[] valueBytes) throws Exception {
		if (valueBytes.length != 8)
			throw new Exception("Invalid byte array!!");
		this.valueBytes = valueBytes;
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new ByteArrayInputStream(valueBytes));
			doubleValue = dis.readDouble();
		} finally {
			if (dis != null)
				dis.close();
		}

	}

	public void unread(PushbackInputStream in) throws Exception {
		if (valueBytes != null)
			in.unread(valueBytes);
	}

	public void write(OutputStream out) throws Exception {
		out.write(valueBytes);
	}
}
