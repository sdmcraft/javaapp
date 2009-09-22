package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.io.OutputStream;

public class AMFString extends AMFValue {
	private UTF8 value;
	String stringValue;

	public AMFString(InputStream in) throws Exception {
		super.marker = new Marker(in);
		value = new UTF8(in);
		stringValue = value.getStringValue();
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) throws Exception {
		this.stringValue = stringValue;
		value = new UTF8(stringValue);
	}

	public void write(OutputStream out) throws Exception {
		value.write(out);
	}

}
