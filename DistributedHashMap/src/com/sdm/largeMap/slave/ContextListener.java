package com.sdm.largeMap.slave;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sdm.largeMap.Utils;

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
			String masterResponse = Utils.convertStreamToString(connection
					.getInputStream());
			if ("ok".equalsIgnoreCase(masterResponse))
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
			String masterResponse = Utils.convertStreamToString(connection
					.getInputStream());
			if ("ok".equalsIgnoreCase(masterResponse))
				throw new Exception("Failed to register myself");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
