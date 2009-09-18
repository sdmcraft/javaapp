package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

public abstract class UType {
	protected byte[] valueBytes;
	protected int intValue;

	public byte[] getValueBytes() {
		return valueBytes;
	}

	public int getIntValue() {
		return intValue;
	}

	abstract public void setIntValue(int value) throws Exception;

	public void write(OutputStream out) throws Exception {
		out.write(valueBytes);
	}

}
