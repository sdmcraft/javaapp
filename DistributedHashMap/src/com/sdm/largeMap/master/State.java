package com.sdm.largeMap.master;

import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

public class State {

	private static final TreeSet<Slave> registeredSlaves = new TreeSet<Slave>(
			new SlaveLoadComparator());
	private static AtomicLong mapIdCounter = new AtomicLong(0);

	public static final TreeSet<Slave> getRegisteredSlaves() {
		return registeredSlaves;
	}

	public static final String getNextMapId() {
		return Long.toString(mapIdCounter.addAndGet(1));
	}

	public static void main(String[] args) {
		registeredSlaves.add(new Slave("1", 0.5));
		registeredSlaves.add(new Slave("2", 0.75));
		registeredSlaves.add(new Slave("3", 0.0));
		System.out.println(registeredSlaves);
	}
}
