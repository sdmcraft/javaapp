package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

public abstract class ObjectProperty {
	public abstract void unread(PushbackInputStream in) throws Exception;
}
