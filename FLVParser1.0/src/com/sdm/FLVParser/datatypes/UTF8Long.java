package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import java.nio.charset.Charset;

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
	private String stringValue;

	public U32 getLength() {
		return length;
	}

	public void setLength(U32 length) {
		this.length = length;
	}

	public U8[] getUtfData() {
		return utfData;
	}

	public void setUtfData(U8[] utfData) throws Exception {
		this.utfData = utfData;
		length = new U32(utfData.length);
		stringValue = new String(Tools.U8ArrToByteArr(utfData));
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) throws Exception {
		this.stringValue = stringValue;
		byte[] byteArr = stringValue.getBytes(Charset.forName("UTF-8"));
		utfData = Tools.byteArrToU8Arr(byteArr);
		int decLength = utfData.length;
		length = new U32(decLength);
	}

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
		stringValue = new String(Tools.U8ArrToByteArr(utfData));
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
