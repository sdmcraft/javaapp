package com.sdm.FLVParser.datatypes;

import java.io.FileInputStream;
import com.sdm.FLVParser.utils.PushbackInputStream;

public class FLV {
	private FLVHeader header;
	private FLVBody body;

	public FLV(String flvFile) throws Exception {
		PushbackInputStream in = null;
		try {
			in = new PushbackInputStream(new FileInputStream(flvFile));
			header = new FLVHeader(in);
			body = new FLVBody(in);
		} finally {
			if (in != null)
				in.close();
		}
	}

	public static void main(String[] args) throws Exception {
		FLV flv = new FLV("c:\\temp\\test.flv");
	}
}
