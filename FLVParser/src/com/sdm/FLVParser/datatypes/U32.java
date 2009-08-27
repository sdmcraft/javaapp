package com.sdm.FLVParser.datatypes;

public class U32 {
	private byte[] value;

	public U32(byte[] value) throws Exception {
		if (value.length != 4)
			throw new Exception("Invalid byte array length. It should be 4");
		else
			this.value = value;
	}

	public byte[] getValue() {
		return value;
	}

}
