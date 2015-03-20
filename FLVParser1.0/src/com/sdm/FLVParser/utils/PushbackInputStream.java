package com.sdm.FLVParser.utils;

import java.io.IOException;
import java.io.InputStream;

public class PushbackInputStream extends java.io.PushbackInputStream {

	private static long bytesRead;

	public PushbackInputStream(InputStream in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public PushbackInputStream(InputStream in, int size) {
		super(in, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int read() throws IOException {
		int result;
		result = super.read();
		if (result != -1)
			bytesRead++;
		return result;
	}

	@Override
	public int read(byte[] bytes) throws IOException {
		int result;
		result = super.read(bytes);
		if (result != -1)
			bytesRead += result;
		return result;
	}

	@Override
	public void unread(int b) throws IOException {
		super.unread(b);
		bytesRead--;
	}

	@Override
	public void unread(byte[] bytes) throws IOException {
		super.unread(bytes);
		bytesRead -= bytes.length;
	}

	public long getBytesRead() {
		return bytesRead;
	}

	public void tellPos() {
		////System.out.println(pos);
	}

}
