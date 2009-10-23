package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

public class ScriptData implements TagData {
	private ScriptDataObject scriptDataObject;
	private static int scriptDataCount = 0;

	public ScriptData(PushbackInputStream in) throws Exception {
		scriptDataObject = new ScriptDataObject(in);
		scriptDataCount++;
		//System.out.println("+++++++++++++++++++++++++++" + scriptDataCount);
		// //System.out.println("Completed reading a script data object");
	}

	public void write(OutputStream out) throws Exception {
		scriptDataObject.write(out);
	}
}
