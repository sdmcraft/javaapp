package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

public class ObjectContent implements ObjectProperty {
	private UTF8 name;
	private AMFValue value;
	public void ObjectContent(InputStream in) throws Exception
	{
		name = new UTF8(in);
		
	}
}
