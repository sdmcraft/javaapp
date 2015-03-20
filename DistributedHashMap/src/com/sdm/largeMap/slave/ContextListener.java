package com.sdm.largeMap.slave;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.sdm.largeMap.utils.Utils;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent ctxEvent) {

		try {
			String masterResponse = Utils
					.send(State.masterServerURL, "action=deregister&slaveUrl="
							+ State.slaveServerURL, "PUT");

			if (!"ok".equalsIgnoreCase(masterResponse))
				throw new Exception("Failed to deregister myself");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent ctxEvent) {
		try
		{
			Thread.sleep(5000);
		}
		catch(Exception ex)
		{
			
		}
		System.out.println("(+)com.sdm.largeMap.slave.contextInitialized(+)");
		State.masterServerURL = ctxEvent.getServletContext().getInitParameter(
				"masterServerURL");
		State.slaveServerURL = ctxEvent.getServletContext().getInitParameter(
				"slaveServerURL");
		try {
			String masterResponse = Utils.send(State.masterServerURL,
					"action=register&slaveUrl=" + State.slaveServerURL, "PUT");

//			if (!"ok".equalsIgnoreCase(masterResponse))
//				throw new Exception("Failed to register myself");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("(-)com.sdm.largeMap.slave.contextInitialized(-)");
	}

}
