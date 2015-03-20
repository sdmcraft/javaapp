package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.utils.PushbackInputStream;
import com.sdm.FLVParser.utils.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Class ScriptDataObject.
 */
public class ScriptDataObject {
	
	/** The amf value list. */
	private List<AMFValue> amfValueList;

	/**
	 * Instantiates a new script data object.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public ScriptDataObject(PushbackInputStream in) throws Exception {
		amfValueList = new ArrayList<AMFValue>();
		int count = 0;
		while (in.available() > 0) {
			amfValueList.add(Tools.sniffer(in));
			count++;
			//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>AMF val count" + count);
		}
	}

	/**
	 * Write.
	 * 
	 * @param out the out
	 * 
	 * @throws Exception the exception
	 */
	public void write(OutputStream out) throws Exception {
		for (AMFValue amfVal : amfValueList)
			amfVal.write(out);
	}

	/**
	 * Gets the amf value list.
	 * 
	 * @return the amf value list
	 */
	public List<AMFValue> getAmfValueList() {
		return amfValueList;
	}

	/**
	 * Sets the amf value list.
	 * 
	 * @param amfValueList the new amf value list
	 */
	public void setAmfValueList(List<AMFValue> amfValueList) {
		this.amfValueList = amfValueList;
	}
}
