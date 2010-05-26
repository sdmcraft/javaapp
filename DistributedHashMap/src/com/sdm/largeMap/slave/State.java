package com.sdm.largeMap.slave;

import java.util.HashMap;
import java.util.Map;

public class State {

	private static final Map<String, Map<String, String>> mapStore = new HashMap<String, Map<String, String>>();
	public static String masterServerURL;
	public static String slaveServerURL;

	public static Map<String, Map<String, String>> getMapstore() {
		return mapStore;
	}

	public static String put(String mapId, String key, String value) {
		Map<String, String> targetMap = mapStore.get(mapId);
		if (targetMap == null) {
			targetMap = mapStore.put(mapId, new HashMap<String, String>());
		}
		return targetMap.put(key, value);
	}

	public static String get(String mapId, String key) {
		String result = null;
		Map<String, String> targetMap = mapStore.get(mapId);
		if (targetMap != null) {
			result = targetMap.get(key);
		}
		return result;
	}

	public static String getInfo() {
		String result = "";
		result += "Master Server: " + masterServerURL + "\n";
		result += "Slave Server: " + slaveServerURL + "\n";
		for (String key : mapStore.keySet()) {
			result += key + "---->" + mapStore.get(key).toString() + "\n";
		}

		return result;
	}

}
