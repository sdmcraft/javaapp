package com.sdm.largeMap.master;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.sdm.largeMap.Utils;

public class Slave {

	private final String url;
	private Double load;
	private static HttpURLConnection connection;

	public Slave(String url, Double load) {
		this.url = url;
		this.load = load;
	}

	public Double getLoad() {
		return load;
	}

	public void setLoad(Double load) {
		this.load = load;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return url + "@" + load;
	}

	public boolean put(String mapId, String key, String value) {
		boolean result;
		try {
			URL putURL = new URL(url + "action=put&map-id=" + mapId + "&key="
					+ URLEncoder.encode(key, "UTF-8") + "&value="
					+ URLEncoder.encode(value, "UTF-8"));

			connection = (HttpURLConnection) putURL.openConnection();
			connection.setRequestMethod("GET");
			String putResponse = Utils.convertStreamToString(connection
					.getInputStream());
			if ("ok".equalsIgnoreCase(putResponse))
				result = true;
			else
				result = false;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

}
