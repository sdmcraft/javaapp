package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

public class FLVBodyComponent {
	private U32 prevTagSize;
	private FLVTag tag;

	public FLVBodyComponent(InputStream in) throws Exception {
		prevTagSize = new U32(in);
		tag = new FLVTag(in);
	}
}
