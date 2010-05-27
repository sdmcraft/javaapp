package com.sdm.largeMap.slave;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sdm.largeMap.utils.Utils;

public class ContextListener implements ServletContextListener {

	private static HttpURLConnection connection;

	@Override
	public void contextDestroyed(ServletContextEvent ctxEvent) {
		try {
			URL registerURL = new URL(State.masterServerURL
					+ "action=deregister&slaveUrl="
					+ URLEncoder.encode(State.slaveServerURL, "UTF-8"));

			connection = (HttpURLConnection) registerURL.openConnection();
			connection.setRequestMethod("POST");
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
		System.out.println("(+)com.sdm.largeMap.slave.contextInitialized(+)");
//		State.masterServerURL = ctxEvent.getServletContext().getInitParameter(
//				"masterServerURL");
//		State.slaveServerURL = ctxEvent.getServletContext().getInitParameter(
//				"slaveServerURL");
//		try {
//			URL registerURL = new URL(State.masterServerURL
//					+ "?action=register&slaveUrl="
//					+ URLEncoder.encode(State.slaveServerURL, "UTF-8"));
//
//			connection = (HttpURLConnection) registerURL.openConnection();
//			connection.setRequestMethod("GET");
//			String masterResponse = Utils.convertStreamToString(connection
//					.getInputStream());
//			if ("ok".equalsIgnoreCase(masterResponse))
//				throw new Exception("Failed to register myself");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		System.out.println("(-)com.sdm.largeMap.slave.contextInitialized(-)");
	}

}
