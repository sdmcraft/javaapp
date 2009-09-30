package com.sdm.FLVParser.datatypes;

import java.io.InputStream;

public class AMFUndef extends AMFValue {
	public AMFUndef(InputStream in) throws Exception {
		marker = new Marker(in);
	}

}
