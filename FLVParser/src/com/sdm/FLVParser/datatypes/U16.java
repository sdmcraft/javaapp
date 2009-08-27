package com.sdm.FLVParser.datatypes;

public class U16 {
	private byte[] value;

	public U16(byte[] value) throws Exception {
		if (value.length != 2)
			throw new Exception("Invalid byte array length. It should be 2");
		else
			this.value = value;
	}

	public byte[] getValue() {
		return value;
	}

}
