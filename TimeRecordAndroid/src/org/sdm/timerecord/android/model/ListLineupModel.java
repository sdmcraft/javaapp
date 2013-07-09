package org.sdm.timerecord.android.model;

import java.util.ArrayList;

public class ListLineupModel {

	private static ListLineupModel instance;

	private ListLineupModel() {

	}

	public static synchronized ListLineupModel getInstance() {
		if (instance == null) {
			instance = new ListLineupModel();
		}
		return instance;
	}

	public java.util.List<String> getItems() {
		java.util.List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		return list;
	}

}
