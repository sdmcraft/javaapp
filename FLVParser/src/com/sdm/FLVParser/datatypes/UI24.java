package com.sdm.FLVParser.datatypes;

public class UI24 {
	private byte[] byteArr;

	public UI24(byte[] byteArr) throws Exception {
		if (byteArr.length != 3)
			throw new Exception("Invalid byte array length. It should be 3");
		else
			this.byteArr = byteArr;
	}

}
