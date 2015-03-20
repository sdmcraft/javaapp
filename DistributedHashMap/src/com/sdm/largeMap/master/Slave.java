package com.sdm.largeMap.master;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.sdm.largeMap.utils.Utils;

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

	public String put(String mapId, String key, String value)
			throws UnsupportedEncodingException {
		return send("action=put&map-id=" + mapId + "&key="
				+ URLEncoder.encode(key, "UTF-8") + "&value="
				+ URLEncoder.encode(value, "UTF-8"));
	}
	
	public String info()
	{
		return send("action=info");
	}

	private String send(String encodedQuery) {
		String response = null;
		InputStream resultStream = null;
		try {
			URL putURL = new URL(url + "?" + encodedQuery);

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

}
