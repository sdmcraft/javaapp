package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class FLVBodyComponent.
 */
public class FLVBodyComponent {
	
	/** The prev tag size. */
	private U32 prevTagSize;
	
	/** The tag. */
	private FLVTag tag;

	/**
	 * Instantiates a new fLV body component.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public FLVBodyComponent(PushbackInputStream in) throws Exception {
		////System.out.println("Bytes read:" + in.getBytesRead());
		prevTagSize = new U32(in);
		////System.out.println("Prev tag size:" + prevTagSize.getIntValue());
		if (in.available() > 0)
			tag = new FLVTag(in);
	}

	/**
	 * Write.
	 * 
	 * @param out the out
	 * 
	 * @throws Exception the exception
	 */
	public void write(OutputStream out) throws Exception {
		prevTagSize.write(out);
		if (tag != null)
			tag.write(out);
	}

	/**
	 * Gets the prev tag size.
	 * 
	 * @return the prev tag size
	 */
	public U32 getPrevTagSize() {
		return prevTagSize;
	}

	/**
	 * Sets the prev tag size.
	 * 
	 * @param prevTagSize the new prev tag size
	 */
	public void setPrevTagSize(U32 prevTagSize) {
		this.prevTagSize = prevTagSize;
	}

	/**
	 * Gets the tag.
	 * 
	 * @return the tag
	 */
	public FLVTag getTag() {
		return tag;
	}

	/**
	 * Sets the tag.
	 * 
	 * @param tag the new tag
	 */
	public void setTag(FLVTag tag) {
		this.tag = tag;
	}
}
