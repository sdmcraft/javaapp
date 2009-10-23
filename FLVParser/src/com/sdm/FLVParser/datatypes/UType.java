package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import com.sdm.FLVParser.utils.PushbackInputStream;

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

	public void unread(PushbackInputStream in) throws Exception {	
		////System.out.println("Unreading " + valueBytes.length + " bytes");		
		in.unread(valueBytes);
	}

	@Override
	public String toString() {
		return Integer.toString(intValue);
	}

}
