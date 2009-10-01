package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class XMLDoc extends AMFValue {
	private UTF8Long value;

	public XMLDoc(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.XML_DOCUMENT_MARKER.equals(marker)) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF XML Doc type!");
		}

		value = new UTF8Long(in);
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (marker != null)
			marker.unread(in);

	}

}
