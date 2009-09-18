package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.io.OutputStream;

import com.sdm.FLVParser.utils.Tools;

public class AMFBoolean extends AMFValue {
	private U8 value;
	private boolean booleanValue;

	public AMFBoolean(InputStream in) throws Exception {
		marker = new Marker(in);
		value = new U8(in);
		if (0 == Tools.U8toInt(value))
			booleanValue = false;
		else
			booleanValue = true;
	}

	public boolean isBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) throws Exception {
		this.booleanValue = booleanValue;
		if (booleanValue)
			value = new U8((byte) 1);
		else
			value = new U8((byte) 0);
	}

	public void write(OutputStream out) throws Exception {
		marker.write(out);
		value.write(out);
	}
}
