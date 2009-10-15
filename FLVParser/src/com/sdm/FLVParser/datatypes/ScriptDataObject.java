package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.utils.PushbackInputStream;
import com.sdm.FLVParser.utils.Tools;

public class ScriptDataObject {
	private List<AMFValue> amfValueList;

	public ScriptDataObject(PushbackInputStream in) throws Exception {
		amfValueList = new ArrayList<AMFValue>();
		while (in.available() > 0) {
			amfValueList.add(Tools.sniffer(in));
		}
	}

	public void write(OutputStream out) throws Exception {
		for (AMFValue amfVal : amfValueList)
			amfVal.write(out);
	}
}
