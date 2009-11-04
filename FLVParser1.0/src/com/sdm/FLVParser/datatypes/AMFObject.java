package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.Tools;

// TODO: Auto-generated Javadoc
/**
 * The Class AMFObject.
 */
public class AMFObject extends AMFValue {
	// private UTF8 classname;
	/** The value. */
	private ObjectProperty[] value;

	/**
	 * Instantiates a new aMF object.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public AMFObject(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.OBJECT_MARKER.equals(marker.getValue())) {
			throw new InvalidDataException(
					"Invalid marker for AMF Object type!", in);
		}
		////System.out.println("Starting to read an amf object");
		List<ObjectProperty> propertyList = new ArrayList<ObjectProperty>();
		ObjectProperty property = Tools.readObjectProperty(in);
		while (!(property instanceof ObjectEnd)) {
			propertyList.add(property);
			property = Tools.readObjectProperty(in);
			////System.out.println("Done with reading an object property");
		}
		propertyList.add(property);
		////System.out.println("Completed reading an amf object");
		value = new ObjectProperty[propertyList.size()];
		int count = 0;
		for (ObjectProperty prop : propertyList)
			value[count++] = prop;
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			for (int i = value.length; i >= 0; i--)
				value[i].unread(in);
		// if (classname != null)
		// classname.unread(in);
		if (marker != null)
			marker.unread(in);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		// classname.write(out);
		for (ObjectProperty prop : value) {
			prop.write(out);
		}
	}
}
