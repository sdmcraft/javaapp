package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

public abstract class AMFValue {
	protected Marker marker;

	public abstract void unread(PushbackInputStream in) throws Exception;

	public abstract void write(OutputStream out) throws Exception;
}
