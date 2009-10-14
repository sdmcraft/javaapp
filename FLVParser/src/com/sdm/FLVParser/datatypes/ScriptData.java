package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class ScriptData implements TagData {
	private List<ScriptDataObject> scriptDataObjectList;
	private ScriptDataObjectEnd end;

	public ScriptData(PushbackInputStream in) throws Exception {
		scriptDataObjectList = new ArrayList<ScriptDataObject>();
		while (in.available() > 0) {
			try {
				scriptDataObjectList.add(new ScriptDataObject(in));
			} catch (InvalidDataException ex) {
				ex.printStackTrace();
				end = new ScriptDataObjectEnd(in);
			}
		}
	}
}
