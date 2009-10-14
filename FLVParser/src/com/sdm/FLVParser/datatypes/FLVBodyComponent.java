package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

public class FLVBodyComponent {
	private U32 prevTagSize;
	private FLVTag tag;

	public FLVBodyComponent(PushbackInputStream in) throws Exception {
		System.out.println("Bytes read:" + in.getBytesRead());
		prevTagSize = new U32(in);		
		System.out.println("Prev tag size:"+prevTagSize.getIntValue());
		tag = new FLVTag(in);
	}
}
