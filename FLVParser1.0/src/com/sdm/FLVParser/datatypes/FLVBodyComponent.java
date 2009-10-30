package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

public class FLVBodyComponent {
	private U32 prevTagSize;
	private FLVTag tag;

	public FLVBodyComponent(PushbackInputStream in) throws Exception {
		////System.out.println("Bytes read:" + in.getBytesRead());
		prevTagSize = new U32(in);
		////System.out.println("Prev tag size:" + prevTagSize.getIntValue());
		if (in.available() > 0)
			tag = new FLVTag(in);
	}

	public void write(OutputStream out) throws Exception {
		prevTagSize.write(out);
		if (tag != null)
			tag.write(out);
	}

	public U32 getPrevTagSize() {
		return prevTagSize;
	}

	public void setPrevTagSize(U32 prevTagSize) {
		this.prevTagSize = prevTagSize;
	}

	public FLVTag getTag() {
		return tag;
	}

	public void setTag(FLVTag tag) {
		this.tag = tag;
	}
}
