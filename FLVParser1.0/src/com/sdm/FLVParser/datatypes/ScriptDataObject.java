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
		int count = 0;
		while (in.available() > 0) {
			amfValueList.add(Tools.sniffer(in));
			count++;
			//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>AMF val count" + count);
		}
	}

	public void write(OutputStream out) throws Exception {
		for (AMFValue amfVal : amfValueList)
			amfVal.write(out);
	}

	public List<AMFValue> getAmfValueList() {
		return amfValueList;
	}

	public void setAmfValueList(List<AMFValue> amfValueList) {
		this.amfValueList = amfValueList;
	}
}
