package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

public interface DataType {
	/**
	 * Writes this object to the specified output stream
	 * 
	 * @param out
	 *            Output stream to write to
	 * @throws Exception
	 *             If an error occurred while writing
	 */
	public void write(OutputStream out) throws Exception;

	/**
	 * Unreads this object and pushes back whatever is read to the input stream
	 * 
	 * @param in
	 *            Input stream for unreading
	 * @throws Exception
	 *             If an error occurred while unread
	 */
	public void unread(PushbackInputStream in) throws Exception;
}
