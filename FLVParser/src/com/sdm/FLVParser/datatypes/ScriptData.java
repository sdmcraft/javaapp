package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

public class ScriptData implements TagData {
	private ObjectProperty[] scriptDataObjects;

	public ScriptData(PushbackInputStream in) {
		List<ObjectProperty> objPropertyList = new ArrayList<ObjectProperty>();
	}
}
