package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.sdm.FLVParser.utils.Tools;

public class UTF8 {
	private U16 length;
	private U8[] utfData;
	private String stringValue;

	public UTF8(InputStream in) throws Exception {
		this.length = new U16(in);
		int decLength = Tools.U16toInt(this.length);
		utfData = new U8[decLength];
		for (int i = 0; i < decLength; i++)
			utfData[i] = new U8(in);
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
		length = Tools.intToU16(decLength);
	}
	
	public void write(OutputStream out) throws Exception {
		out.write(value);
	}

}
