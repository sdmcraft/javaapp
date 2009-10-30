package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;
import com.sdm.FLVParser.utils.Tools;

/**
 * UTF-8 is the abbreviation for 8-bit Unicode Transformation Format. UTF-8
 * strings are typically preceded with a byte-length header followed by a
 * sequence of variable length (1 to 4 octets) encoded Unicode code-points. For
 * UTF-8 long, a 32-bit byte-length is required.
 * 
 * @author satyam
 * 
 */
public class UTF8Long {
	private U32 length;
	private U8[] utfData;

	/**
	 * Constructs a UTF-8 long object by reading from the specified input stream
	 * 
	 * @param in
	 *            Input stream to read from
	 * @throws Exception
	 *             If an error occurred while reading
	 */
	public UTF8Long(PushbackInputStream in) throws Exception {
		this.length = new U32(in);
		int decLength = Tools.U32toInt(this.length);
		utfData = new U8[decLength];
		for (int i = 0; i < decLength; i++)
			utfData[i] = new U8(in);
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
		if (utfData != null) {
			for (U8 u8 : utfData)
				u8.unread(in);
		}
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
		for (U8 u8 : utfData)
			u8.write(out);
	}

}
