package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FLVBody {
	List<FLVBodyComponent> componentList = new ArrayList<FLVBodyComponent>();

	public FLVBody(InputStream in) throws Exception {
		while (in.available() > 0)
			componentList.add(new FLVBodyComponent(in));
	}
}
