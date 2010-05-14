package com.sdm.largeMap.master;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class State {

	private final static Map<String,String> registeredSlaves = new ConcurrentHashMap<String,String>();
	private final static Map<String,String> map = new HashMap<String, String>();
	

	public final static Map<String,String> getRegisteredSlaves() {
		return registeredSlaves;
	}
}
