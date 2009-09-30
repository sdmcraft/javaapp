package com.sdm.FLVParser.datatypes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class AMFDouble {
	private double doubleValue;
	private byte[] valueBytes;

	public AMFDouble(InputStream in) throws Exception {
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

	public double getDoubleValue() {
		return doubleValue;
	}

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

	public byte[] getValueBytes() {
		return valueBytes;
	}

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
}
