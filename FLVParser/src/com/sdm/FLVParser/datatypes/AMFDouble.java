package com.sdm.FLVParser.datatypes;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class AMFDouble {
	private double value;

	public AMFDouble(InputStream in) throws Exception {
		byte[] valueBytes = new byte[8];
		if (8 != in.read(valueBytes))
			throw new Exception("Error reading from input stream");
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new ByteArrayInputStream(valueBytes));
			value = dis.readDouble();
		} finally {
			if (dis != null)
				dis.close();
		}
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
