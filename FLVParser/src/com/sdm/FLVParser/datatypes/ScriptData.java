package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;
import java.util.List;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class ScriptData implements TagData {
	private List<ScriptDataObject> scriptDataObjectList;
	private ScriptDataObjectEnd end;

	public ScriptData(PushbackInputStream in) throws Exception {
		while (true) {
			try {
				scriptDataObjectList.add(new ScriptDataObject(in));
			} catch (InvalidDataException ex) {
				end = new ScriptDataObjectEnd(in);
			}
		}
	}
}
