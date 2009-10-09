package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

public class FLVBodyComponent {
	private U32 prevTagSize;
	private FLVTag tag;

	public FLVBodyComponent(PushbackInputStream in) throws Exception {
		prevTagSize = new U32(in);
		tag = new FLVTag(in);
	}
}
