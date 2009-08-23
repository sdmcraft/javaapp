package com.sdm.FLVParser.datatypes;

public class UI32 {
	private byte[] byteArr;

	public UI32(byte[] byteArr) throws Exception {
		if (byteArr.length != 4)
			throw new Exception("Invalid byte array length. It should be 4");
		else
			this.byteArr = byteArr;
	}

}
