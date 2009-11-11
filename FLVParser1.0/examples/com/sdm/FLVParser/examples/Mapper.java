package com.sdm.FLVParser.examples;

import java.io.File;
import java.util.List;
import java.util.Map;

import misc.Utils;

import com.sdm.FLVParser.datatypes.AMFLongString;
import com.sdm.FLVParser.datatypes.AMFNumber;
import com.sdm.FLVParser.datatypes.AMFObject;
import com.sdm.FLVParser.datatypes.AMFString;
import com.sdm.FLVParser.datatypes.AMFValue;
import com.sdm.FLVParser.datatypes.ECMAArray;
import com.sdm.FLVParser.datatypes.FLV;
import com.sdm.FLVParser.datatypes.FLVBody;
import com.sdm.FLVParser.datatypes.FLVBodyComponent;
import com.sdm.FLVParser.datatypes.FLVTag;
import com.sdm.FLVParser.datatypes.ObjectContent;
import com.sdm.FLVParser.datatypes.ObjectProperty;
import com.sdm.FLVParser.datatypes.ScriptData;
import com.sdm.FLVParser.datatypes.ScriptDataObject;
import com.sdm.FLVParser.datatypes.StrictArray;
import com.sdm.FLVParser.datatypes.U24;
import com.sdm.FLVParser.datatypes.U32;
import com.sdm.FLVParser.datatypes.UTF8Long;

public class Mapper {

	public static void main(String[] args) throws Exception {
		File testDataDir = new File("testData");
		File idMapFile = new File(testDataDir, "idMap.ser");
		File origFLVFile = new File(testDataDir, "orig.flv");
		File mappedFLVFile = new File(testDataDir, "mapped.flv");
		Map<String, String> idMap = (Map<String, String>) Utils
				.deserializeObject(idMapFile);
		FLV mappedFLV = map(origFLVFile.getAbsolutePath(), idMap);
		mappedFLV.write(mappedFLVFile.getAbsolutePath());

	}

	public static FLV map(String FLVfile, Map<String, String> map)
			throws Exception {
		FLV flv = new FLV(FLVfile);
		FLVBody flvbody = flv.getBody();
		List<FLVBodyComponent> bodyComponentList = flvbody.getComponentList();
		int change = 0;
		for (FLVBodyComponent bodyComp : bodyComponentList) {
			if (change != 0) {
				int newPrevTagSize = bodyComp.getPrevTagSize().getIntValue()
						+ change;
				bodyComp.setPrevTagSize(new U32(newPrevTagSize));
				change = 0;
			}
			FLVTag tag = bodyComp.getTag();
			if (tag == null)
				continue;
			System.out.println(tag.getDataSize().getIntValue());
			System.out.println(tag.getTagType().getValue());
			if (tag.getTagType().getValue() == 18) {
				ScriptData scriptData = (ScriptData) tag.getData();
				int oldTagSize = scriptData.byteSize();
				ScriptDataObject scriptDataObject = scriptData
						.getScriptDataObject();
				for (AMFValue amfVal : scriptDataObject.getAmfValueList()) {
					mapAMFValue(amfVal, map);
				}
				int newTagSize = scriptData.byteSize();
				change = newTagSize - oldTagSize;
				if (change != 0) {
					tag.setDataSize(new U24(newTagSize));
				}
			}
		}
		return flv;
	}

	public static void mapAMFValue(AMFValue amfVal, Map<String, String> map)
			throws Exception {
		if (amfVal instanceof AMFLongString) {
			AMFLongString longStr = (AMFLongString) amfVal;
			UTF8Long utfVal = longStr.getValue();
			String oldStr = utfVal.getStringValue();
			String newStr = map.get(oldStr);
			if (newStr != null)
				utfVal.setStringValue(newStr);
		} else if (amfVal instanceof AMFNumber) {
			AMFNumber number = (AMFNumber) amfVal;
			double doubleVal = number.getDoubleValue();
			String oldStr = Double.toString(doubleVal);
			String newStr = map.get(oldStr);
			if (newStr != null)
				number.setDoubleValue(Double.parseDouble(newStr));
		} else if (amfVal instanceof AMFObject) {
			AMFObject obj = (AMFObject) amfVal;
			for (ObjectProperty prop : obj.getValue()) {
				if (prop instanceof ObjectContent) {
					ObjectContent cnt = (ObjectContent) prop;
					String oldStr = cnt.getName().getStringValue();
					String newStr = Utils.replace(oldStr, map, "_a|[/-]");
					if (newStr != null)
						cnt.getName().setStringValue(newStr);
					AMFValue val = cnt.getValue();
					mapAMFValue(val, map);
				}
			}
		} else if (amfVal instanceof AMFString) {
			AMFString str = (AMFString) amfVal;
			String oldStr = str.getStringValue();
			String newStr = map.get(oldStr);
			if (newStr != null)
				str.setStringValue(newStr);
		} else if (amfVal instanceof ECMAArray) {
			ECMAArray arr = (ECMAArray) amfVal;
			for (ObjectProperty prop : arr.getPropertyList()) {
				if (prop instanceof ObjectContent) {
					ObjectContent cnt = (ObjectContent) prop;
					String oldStr = cnt.getName().getStringValue();
					String newStr = map.get(oldStr);
					if (newStr != null)
						cnt.getName().setStringValue(newStr);
					AMFValue val = cnt.getValue();
					mapAMFValue(val, map);
				}
			}
		} else if (amfVal instanceof StrictArray) {
			StrictArray arr = (StrictArray) amfVal;
			for (AMFValue val : arr.getValue()) {
				mapAMFValue(val, map);
			}
		}
	}
}
