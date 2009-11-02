package com.sdm.FLVParser.examples;

import java.util.List;
import java.util.Map;

import com.sdm.FLVParser.datatypes.AMFLongString;
import com.sdm.FLVParser.datatypes.AMFValue;
import com.sdm.FLVParser.datatypes.FLV;
import com.sdm.FLVParser.datatypes.FLVBody;
import com.sdm.FLVParser.datatypes.FLVBodyComponent;
import com.sdm.FLVParser.datatypes.FLVTag;
import com.sdm.FLVParser.datatypes.ScriptData;
import com.sdm.FLVParser.datatypes.ScriptDataObject;
import com.sdm.FLVParser.datatypes.U32;
import com.sdm.FLVParser.datatypes.UTF8Long;

public class Mapper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void map(String FLVfile, Map<String, String> map)
			throws Exception {
		FLV flv = new FLV(FLVfile);
		FLVBody flvbody = flv.getBody();
		List<FLVBodyComponent> bodyComponentList = flvbody.getComponentList();
		for (FLVBodyComponent bodyComp : bodyComponentList) {
			U32 prevTagSize = bodyComp.getPrevTagSize();
			FLVTag tag = bodyComp.getTag();
			if (tag.getTagType().getValue() == 8) {
				ScriptData scriptData = (ScriptData) tag.getData();
				ScriptDataObject scriptDataObject = scriptData
						.getScriptDataObject();
				for(AMFValue amfVal : scriptDataObject.getAmfValueList())
				{
					if(amfVal instanceof AMFLongString)
					{
						AMFLongString longStr = (AMFLongString)amfVal;
						UTF8Long utfVal = longStr.getValue();
						
					}
				}
			}
		}
	}

}
