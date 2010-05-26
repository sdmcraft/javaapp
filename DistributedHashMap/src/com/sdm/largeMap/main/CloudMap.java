package com.sdm.largeMap.main;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.sdm.largeMap.utils.Utils;

public class CloudMap implements Map<String, String> {

	private final Map<String, String> cache;
	private final String mapId;
	private final String cloudService;
	private HttpURLConnection connection;

	public CloudMap(final int cacheCapacity, float loadFactor,
			String cloudService) {
		this.cloudService = cloudService;
		this.mapId = cloudGetMapId();
		this.cache = new LinkedHashMap<String, String>(cacheCapacity,
				loadFactor, true) {
			protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
				boolean putSuccess = false;
				if (size() > cacheCapacity) {
					try {
						putSuccess = eldest.getKey().equals(
								cloudPut(eldest.getKey(), eldest.getValue()));
					} catch (UnsupportedEncodingException ex) {
						ex.printStackTrace();
					}
				}
				return putSuccess;
			}
		};
	}

	private String send(String encodedQuery) {
		String response = null;
		InputStream resultStream = null;
		try {
			URL putURL = new URL(cloudService + "?" + encodedQuery);

			connection = (HttpURLConnection) putURL.openConnection();
			connection.setRequestMethod("GET");
			resultStream = connection.getInputStream();
			response = Utils.convertStreamToString(resultStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultStream != null)
					resultStream.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return response;
	}

	private String cloudGetMapId() {
		return send("action=get-map-id");
	}

	private String cloudPut(String key, String value)
			throws UnsupportedEncodingException {
		return send("action=put&map-id=" + mapId + "&key="
				+ URLEncoder.encode(key, "UTF-8") + "&value="
				+ URLEncoder.encode(value, "UTF-8"));
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
