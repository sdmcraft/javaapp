package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

public abstract class AMFValue {
	protected Marker marker;

	public abstract void unread(PushbackInputStream in) throws Exception;
}
