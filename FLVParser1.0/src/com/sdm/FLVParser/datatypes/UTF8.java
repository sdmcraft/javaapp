package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import java.nio.charset.Charset;

import com.sdm.FLVParser.utils.PushbackInputStream;
import com.sdm.FLVParser.utils.Tools;

// TODO: Auto-generated Javadoc
/**
 * UTF-8 is the abbreviation for 8-bit Unicode Transformation Format. UTF-8
 * strings are typically preceded with a byte-length header followed by a
 * sequence of variable length (1 to 4 octets) encoded Unicode code-points.
 * 
 * @author satyam
 */
public class UTF8 {
	
	/** The length. */
	private U16 length;
	
	/** The utf data. */
	private U8[] utfData;
	
	/** The string value. */
	private String stringValue;

	/**
	 * Constructs a UTF-8 object by reading from the specified input stream.
	 * 
	 * @param in Input stream to read from
	 * 
	 * @throws Exception If an error occurred while reading
	 */
	public UTF8(PushbackInputStream in) throws Exception {
		this.length = new U16(in);
		int decLength = Tools.U16toInt(this.length);
		if (decLength <= 0) {
			////System.out.println("WARNING: UTF8 length is " + decLength);
			// unread(in);
			// throw new InvalidDataException("Invalid UTF8 as length is 0!!!",
			// in);
		}
		utfData = new U8[decLength];
		for (int i = 0; i < decLength; i++)
			utfData[i] = new U8(in);
		stringValue = new String(Tools.U8ArrToByteArr(utfData));
		//System.out.println("String:"+stringValue);
	}

	/**
	 * Constructs a UTF-8 object from the specified string.
	 * 
	 * @param stringValue string value of the UTF-8 object
	 * 
	 * @throws Exception If an internal error occurred
	 */
	public UTF8(String stringValue) throws Exception {
		this.stringValue = stringValue;
		byte[] byteArr = stringValue.getBytes(Charset.forName("UTF-8"));
		utfData = Tools.byteArrToU8Arr(byteArr);
		int decLength = utfData.length;
		length = Tools.intToU16(decLength);
	}

	/**
	 * Returns the string value of this UTF-8 object.
	 * 
	 * @return string value of the UTF-8 object
	 */
	public String getStringValue() {
		return stringValue;
	}

	/**
	 * Sets the string value of this UTF-8 object.
	 * 
	 * @param stringValue string value of the UTF-8 object
	 * 
	 * @throws Exception If an internal error occurred
	 */
	public void setStringValue(String stringValue) throws Exception {
		this.stringValue = stringValue;
		byte[] byteArr = stringValue.getBytes(Charset.forName("UTF-8"));
		utfData = Tools.byteArrToU8Arr(byteArr);
		int decLength = utfData.length;
		length = Tools.intToU16(decLength);
	}

	/**
	 * Writes this object to the specified output stream.
	 * 
	 * @param out Output stream to write to
	 * 
	 * @throws Exception If an error occurred while writing
	 */
	public void write(OutputStream out) throws Exception {
		length.write(out);
		byte[] byteArr = stringValue.getBytes(Charset.forName("UTF-8"));
		out.write(byteArr);
	}

	/**
	 * Unreads this object and pushes back whatever is read to the input stream.
	 * 
	 * @param in Input stream for unreading
	 * 
	 * @throws Exception If an error occurred while unread
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (utfData != null) {
			for (U8 u8 : utfData)
				u8.unread(in);
		}
		if (length != null)
			length.unread(in);
	}
}
