package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.utils.PushbackInputStream;
import com.sdm.FLVParser.utils.Tools;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class ECMAArray.
 */
public class ECMAArray extends AMFValue {
	
	/** The associative count. */
	private U32 associativeCount;
	
	/** The property list. */
	List<ObjectProperty> propertyList;

	/**
	 * Instantiates a new eCMA array.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public ECMAArray(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.ECMA_ARRAY_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF ECMA array type!", in);
		}

		associativeCount = new U32(in);
		// if (associativeCount.getIntValue() <= 0)
			// System.out.println("WARNING:Associative count:"
			// + associativeCount.getIntValue());
		propertyList = new ArrayList<ObjectProperty>();
		ObjectProperty property = Tools.readObjectProperty(in);
		while (!(property instanceof ObjectEnd)) {
			// System.out.println("Adding an object property");
			propertyList.add(property);
			// System.out.println("Going to read an object property");
			property = Tools.readObjectProperty(in);
		}
		propertyList.add(property);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (propertyList != null)
			for (ObjectProperty property : propertyList)
				property.unread(in);
		if (associativeCount != null)
			associativeCount.unread(in);
		if (marker != null)
			marker.unread(in);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		associativeCount.write(out);
		for (ObjectProperty prop : propertyList)
			prop.write(out);
	}

	public U32 getAssociativeCount() {
		return associativeCount;
	}

	public void setAssociativeCount(U32 associativeCount) {
		this.associativeCount = associativeCount;
	}

	public List<ObjectProperty> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<ObjectProperty> propertyList) {
		this.propertyList = propertyList;
	}
}
