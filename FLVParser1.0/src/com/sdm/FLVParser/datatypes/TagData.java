package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

public interface TagData {
	public void write(OutputStream out) throws Exception;
}
