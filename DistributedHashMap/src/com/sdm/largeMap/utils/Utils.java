package com.sdm.largeMap.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.sdm.largeMap.slave.State;

public class Utils {
	public static String convertStreamToString(InputStream is)
			throws IOException {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;

			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				is.close();
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	public static String send(String server, String query, String method)
			throws Exception {
		HttpURLConnection connection = null;
		String response = null;
		if ("PUT".equalsIgnoreCase(method)) {
			OutputStreamWriter writer = null;
			try {
				URL url = new URL(server);				
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);				
				connection.setRequestMethod("PUT");
				writer = new OutputStreamWriter(connection.getOutputStream());
				connection.connect();
				writer.write(query);
				writer.flush();
//				response = convertStreamToString(connection.getInputStream());
			} finally {
				try {
					if (writer != null)
						writer.close();
					if (connection != null)
						connection.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return response;
	}

}
