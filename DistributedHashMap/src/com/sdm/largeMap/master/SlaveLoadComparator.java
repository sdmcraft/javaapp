package com.sdm.largeMap.master;

import java.util.Comparator;

public class SlaveLoadComparator implements Comparator<Slave> {

	@Override
	public int compare(Slave slave1, Slave slave2) {
		return slave1.getLoad().compareTo(slave2.getLoad());
	}

}
