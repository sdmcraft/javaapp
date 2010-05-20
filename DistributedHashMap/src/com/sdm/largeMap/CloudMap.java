package com.sdm.largeMap;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.sdm.largeMap.master.Slave;
import com.sdm.largeMap.master.State;

public class CloudMap implements Map<String, String> {

	private final Map<String, String> cache;
	private final String mapId;

	public CloudMap(final int cacheCapacity, float loadFactor) {
		this.mapId = (String) State.getNextMapId();
		this.cache = new LinkedHashMap<String, String>(cacheCapacity,
				loadFactor, true) {
			protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
				boolean putSucccess = false;				
				if (size() > cacheCapacity) {
					for (Slave slave : State.getRegisteredSlaves()) {
						putSucccess = slave.put(mapId, eldest.getKey(), eldest
								.getValue());
						if (putSucccess)
							break;
					}
				}
				return putSucccess;
			}
		};
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<String, String>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String put(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends String> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<String> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
