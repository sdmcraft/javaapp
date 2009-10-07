package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

public abstract class ObjectProperty {
	public abstract void unread(PushbackInputStream in) throws Exception;
}
