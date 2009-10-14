package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

public class ScriptData implements TagData {
	private ScriptDataObject scriptDataObject;

	public ScriptData(PushbackInputStream in) throws Exception {
		scriptDataObject = new ScriptDataObject(in);
		System.out.println("Completed reading a script data object");
	}
}
