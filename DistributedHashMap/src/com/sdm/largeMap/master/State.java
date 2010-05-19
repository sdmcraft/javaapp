package com.sdm.largeMap.master;

import java.util.Set;
import java.util.TreeSet;

public class State {

	private static final Set<Slave> registeredSlaves = new TreeSet<Slave>(new SlaveLoadComparator());

	public static final Set<Slave> getRegisteredSlaves() {
		return registeredSlaves;
	}

	public static void main(String[] args) {
		registeredSlaves.add(new Slave("1",0.5));
		registeredSlaves.add(new Slave("2",0.75));
		registeredSlaves.add(new Slave("3",0.0));
		System.out.println(registeredSlaves);
	}
}
