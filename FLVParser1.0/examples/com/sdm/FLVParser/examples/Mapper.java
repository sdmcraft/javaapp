package com.sdm.FLVParser.examples;

import java.util.List;
import java.util.Map;

import com.sdm.FLVParser.datatypes.FLV;
import com.sdm.FLVParser.datatypes.FLVBody;
import com.sdm.FLVParser.datatypes.FLVBodyComponent;

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
		for(FLVBodyComponent bodyComp : bodyComponentList)
		{
			
		}
	}

}
