package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

public class AMFNumber extends AMFValue {
	private AMFDouble value;

	public AMFNumber(InputStream in) throws Exception {
		super.marker = new Marker(in);
		value = new AMFDouble(in);
	}

	public double getDoubleValue() {
		return value.getDoubleValue();
	}

	public void setDoubleValue(double value) throws Exception {
		this.value.setDoubleValue(value);

	}

}
