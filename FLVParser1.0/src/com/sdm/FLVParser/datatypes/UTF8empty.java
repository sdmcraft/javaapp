package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.PushbackInputStream;
import com.sdm.FLVParser.utils.Tools;

/**
 * An empty UTF-8 is used as a special case to signify no further dynamic
 * properties are present.
 * 
 * @author satyam
 * 
 */
public class UTF8empty {
	private U16 length;

	/**
	 * Constructs a UTF-8 empty object by reading from the specified input
	 * stream
	 * 
	 * @param in
	 *            Input stream to read from
	 * @throws Exception
	 *             If an error occurred while reading
	 */
	public UTF8empty(PushbackInputStream in) throws Exception {
		this.length = new U16(in);
		if (0 != Tools.U16toInt(this.length)) {
			unread(in);
			throw new InvalidDataException("UTF8empty must have a length of 0",
					in);
		}
	}

	/**
	 * Unreads this object and pushes back whatever is read to the input stream
	 * 
	 * @param in
	 *            Input stream for unreading
	 * @throws Exception
	 *             If an error occured while unread
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (length != null)
			length.unread(in);
	}

	/**
	 * Writes this object to the specified output stream
	 * 
	 * @param out
	 *            Output stream to write to
	 * @throws Exception
	 *             If an error occurred while writing
	 */
	public void write(OutputStream out) throws Exception {
		length.write(out);
	}

}
