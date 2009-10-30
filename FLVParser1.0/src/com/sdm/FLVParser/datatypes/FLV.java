package com.sdm.FLVParser.datatypes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

public class FLV {
	private FLVHeader header;
	private FLVBody body;

	public FLV(String flvFile) throws Exception {
		PushbackInputStream in = null;
		try {
			in = new PushbackInputStream(new BufferedInputStream(
					new FileInputStream(flvFile), 2048));
			header = new FLVHeader(in);
			body = new FLVBody(in);
		} finally {
			if (in != null)
				in.close();
		}
	}

	public void write(String flvFile) throws Exception {
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(flvFile));
			header.write(out);
			body.write(out);
		} finally {
			if (out != null)
				out.close();
		}
	}

	public static void main(String[] args) throws Exception {
		FLV flv = new FLV("c:\\temp\\mainstream.flv");
		flv.write("c:\\temp\\mainstream1.flv");
	}

	public FLVHeader getHeader() {
		return header;
	}

	public void setHeader(FLVHeader header) {
		this.header = header;
	}

	public FLVBody getBody() {
		return body;
	}

	public void setBody(FLVBody body) {
		this.body = body;
	}
}
