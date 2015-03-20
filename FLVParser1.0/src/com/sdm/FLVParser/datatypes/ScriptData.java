package com.sdm.FLVParser.datatypes;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class ScriptData.
 */
public class ScriptData implements TagData {

	/** The script data object. */
	private ScriptDataObject scriptDataObject;

	/** The script data count. */
	private static int scriptDataCount = 0;

	/**
	 * Instantiates a new script data.
	 * 
	 * @param in
	 *            the in
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public ScriptData(PushbackInputStream in) throws Exception {
		scriptDataObject = new ScriptDataObject(in);
		scriptDataCount++;
		// System.out.println("+++++++++++++++++++++++++++" + scriptDataCount);
		// //System.out.println("Completed reading a script data object");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sdm.FLVParser.datatypes.TagData#write(java.io.OutputStream)
	 */
	public void write(OutputStream out) throws Exception {
		scriptDataObject.write(out);
	}

	/**
	 * Gets the script data object.
	 * 
	 * @return the script data object
	 */
	public ScriptDataObject getScriptDataObject() {
		return scriptDataObject;
	}

	/**
	 * Sets the script data object.
	 * 
	 * @param scriptDataObject
	 *            the new script data object
	 */
	public void setScriptDataObject(ScriptDataObject scriptDataObject) {
		this.scriptDataObject = scriptDataObject;
	}

	/**
	 * Gets the script data count.
	 * 
	 * @return the script data count
	 */
	public static int getScriptDataCount() {
		return scriptDataCount;
	}

	/**
	 * Sets the script data count.
	 * 
	 * @param scriptDataCount
	 *            the new script data count
	 */
	public static void setScriptDataCount(int scriptDataCount) {
		ScriptData.scriptDataCount = scriptDataCount;
	}

	public int byteSize() throws Exception {
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			this.write(out);
			return out.toByteArray().length;
		} finally {
			if (out != null)
				out.close();
		}
	}
}
