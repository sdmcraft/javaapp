package com.sdm.FLVParser.datatypes;

public class UI16 {
	private byte[] byteArr;

	public UI16(byte[] byteArr) throws Exception {
		if (byteArr.length != 2)
			throw new Exception("Invalid byte array length. It should be 2");
		else
			this.byteArr = byteArr;
	}

}
