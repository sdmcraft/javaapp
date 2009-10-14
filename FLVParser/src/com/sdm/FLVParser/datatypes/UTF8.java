package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import java.nio.charset.Charset;

import com.sdm.FLVParser.utils.PushbackInputStream;
import com.sdm.FLVParser.utils.Tools;

public class UTF8 {
	private U16 length;
	private U8[] utfData;
	private String stringValue;

	public UTF8(PushbackInputStream in) throws Exception {
		System.out.println("a->"+in.available());		
		this.length = new U16(in);		
		System.out.println("b->"+in.available());
		int decLength = Tools.U16toInt(this.length);
		if (decLength <= 0) {
			System.out.println("WARNING: UTF8 length is " + decLength);
			//unread(in);
			//throw new InvalidDataException("Invalid UTF8 as length is 0!!!", in);
		}
		utfData = new U8[decLength];
		for (int i = 0; i < decLength; i++)
			utfData[i] = new U8(in);
		stringValue = new String(Tools.U8ArrToByteArr(utfData));
		System.out.println("stingvalue:" + stringValue);
	}

	public UTF8(String stringValue) throws Exception {
		this.stringValue = stringValue;
		byte[] byteArr = stringValue.getBytes(Charset.forName("UTF-8"));
		utfData = Tools.byteArrToU8Arr(byteArr);
		int decLength = utfData.length;
		length = Tools.intToU16(decLength);
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) throws Exception {
		this.stringValue = stringValue;
		byte[] byteArr = stringValue.getBytes(Charset.forName("UTF-8"));
		utfData = Tools.byteArrToU8Arr(byteArr);
		int decLength = utfData.length;
		length = Tools.intToU16(decLength);
	}

	public void write(OutputStream out) throws Exception {
		length.write(out);
		byte[] byteArr = stringValue.getBytes(Charset.forName("UTF-8"));
		out.write(byteArr);
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (utfData != null) {			
			for (U8 u8 : utfData)
				u8.unread(in);
		}
		if (length != null)
			length.unread(in);
	}
}
