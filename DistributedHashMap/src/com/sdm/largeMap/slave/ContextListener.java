package com.sdm.largeMap.slave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	String masterServerURL;
	String slaveServerURL;
	private static HttpURLConnection connection;

	@Override
	public void contextDestroyed(ServletContextEvent ctxEvent) {
		try {
			URL registerURL = new URL(masterServerURL
					+ "action=deregister&slaveUrl="
					+ URLEncoder.encode(slaveServerURL, "UTF-8"));

			connection = (HttpURLConnection) registerURL.openConnection();
			connection.setRequestMethod("GET");
			String masterResponse = convertStreamToString(connection.getInputStream());
			if("ok".equalsIgnoreCase(masterResponse))
				throw new Exception("Failed to deregister myself");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent ctxEvent) {
		masterServerURL = ctxEvent.getServletContext().getInitParameter(
				"masterServerURL");
		slaveServerURL = ctxEvent.getServletContext().getInitParameter(
				"slaveServerURL");		
		try {
			URL registerURL = new URL(masterServerURL
					+ "action=register&slaveUrl="
					+ URLEncoder.encode(slaveServerURL, "UTF-8"));

			connection = (HttpURLConnection) registerURL.openConnection();
			connection.setRequestMethod("GET");
			String masterResponse = convertStreamToString(connection.getInputStream());
			if("ok".equalsIgnoreCase(masterResponse))
				throw new Exception("Failed to register myself");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String convertStreamToString(InputStream is) throws IOException {
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
}
