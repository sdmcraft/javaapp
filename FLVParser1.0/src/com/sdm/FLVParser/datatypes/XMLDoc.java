package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLDoc.
 */
public class XMLDoc extends AMFValue {
	
	/** The value. */
	private UTF8Long value;

	/**
	 * Instantiates a new xML doc.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public XMLDoc(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.XML_DOCUMENT_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF XML Doc type!", in);
		}

		value = new UTF8Long(in);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (marker != null)
			marker.unread(in);

	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		value.write(out);
	}

}
