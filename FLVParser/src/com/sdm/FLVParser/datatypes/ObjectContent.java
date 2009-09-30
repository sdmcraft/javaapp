package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;


public class ObjectContent extends ObjectProperty {
	private UTF8 name;
	private AMFValue value;
	public ObjectContent(PushbackInputStream in) throws Exception
	{
		name = new UTF8(in);
		
	}
}
