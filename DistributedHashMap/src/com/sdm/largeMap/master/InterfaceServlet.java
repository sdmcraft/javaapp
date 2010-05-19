package com.sdm.largeMap.master;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterfaceServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String action = (String) request.getAttribute("action");

			if ("register".equalsIgnoreCase(action)) {
				String slaveUrl = (String) request.getAttribute("slaveUrl");
				Double slaveLoad = (Double) request.getAttribute("slaveLoad");
				State.getRegisteredSlaves().add(new Slave(slaveUrl, slaveLoad));
				writer.print("ok");
			} else if ("deregister".equalsIgnoreCase(action)) {
				String slaveUrl = (String) request.getAttribute("slaveUrl");
				State.getRegisteredSlaves().remove(slaveUrl);
				writer.print("ok");
			}
		} finally {
			if (writer != null)
				writer.close();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		doGet(request, response);
	}

}
