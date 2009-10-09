package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

public class FLVBody {
	List<FLVBodyComponent> componentList = new ArrayList<FLVBodyComponent>();

	public FLVBody(PushbackInputStream in) throws Exception {
		while (in.available() > 0)
			componentList.add(new FLVBodyComponent(in));
	}
}
