package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectProperty.
 */
public abstract class ObjectProperty {
	
	/**
	 * Unread.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public abstract void unread(PushbackInputStream in) throws Exception;
	
	/**
	 * Write.
	 * 
	 * @param out the out
	 * 
	 * @throws Exception the exception
	 */
	public abstract void write(OutputStream out) throws Exception;
}
