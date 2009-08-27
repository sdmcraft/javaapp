package com.sdm.FLVParser.datatypes;

public class U24 {
	private byte[] value;

	public U24(byte[] value) throws Exception {
		if (value.length != 3)
			throw new Exception("Invalid byte array length. It should be 3");
		else
			this.value = value;
	}

	public byte[] getValue() {
		return value;
	}

}
