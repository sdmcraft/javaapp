package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.Tools;

public class AMFObject extends AMFValue {
	private UTF8 classname;
	private ObjectProperty[] value;

	public AMFObject(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.OBJECT_MARKER.equals(marker.getValue())) {
			throw new InvalidDataException(
					"Invalid marker for AMF Object type!", in);
		}
		List<ObjectProperty> propertyList = new ArrayList<ObjectProperty>();
		ObjectProperty property = Tools.readObjectProperty(in);
		while (!(property instanceof ObjectEnd)) {
			propertyList.add(property);
			property = Tools.readObjectProperty(in);
		}
		propertyList.add(property);
		value = (ObjectProperty[]) propertyList.toArray();
	}

	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			for (int i = value.length; i >= 0; i--)
				value[i].unread(in);
		if (classname != null)
			classname.unread(in);
	}
}
