package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import java.io.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.Tools;

public class AMFBoolean extends AMFValue {
	private U8 value;
	private boolean booleanValue;

	public AMFBoolean(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.BOOLEAN_MARKER.equals(marker)) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF Boolean type!");
		}

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

	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (marker != null)
			marker.unread(in);
	}

}
