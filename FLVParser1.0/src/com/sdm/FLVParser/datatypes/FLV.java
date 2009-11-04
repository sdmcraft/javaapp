package com.sdm.FLVParser.datatypes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class FLV.
 */
public class FLV {
	
	/** The header. */
	private FLVHeader header;
	
	/** The body. */
	private FLVBody body;

	/**
	 * Instantiates a new fLV.
	 * 
	 * @param flvFile the flv file
	 * 
	 * @throws Exception the exception
	 */
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

	/**
	 * Write.
	 * 
	 * @param flvFile the flv file
	 * 
	 * @throws Exception the exception
	 */
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

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		FLV flv = new FLV("c:\\temp\\mainstream.flv");
		flv.write("c:\\temp\\mainstream1.flv");
	}

	/**
	 * Gets the header.
	 * 
	 * @return the header
	 */
	public FLVHeader getHeader() {
		return header;
	}

	/**
	 * Sets the header.
	 * 
	 * @param header the new header
	 */
	public void setHeader(FLVHeader header) {
		this.header = header;
	}

	/**
	 * Gets the body.
	 * 
	 * @return the body
	 */
	public FLVBody getBody() {
		return body;
	}

	/**
	 * Sets the body.
	 * 
	 * @param body the new body
	 */
	public void setBody(FLVBody body) {
		this.body = body;
	}
}
