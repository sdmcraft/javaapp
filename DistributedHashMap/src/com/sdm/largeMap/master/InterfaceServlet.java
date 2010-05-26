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
			String action = request.getParameter("action");

			if ("register".equalsIgnoreCase(action)) {
				String slaveUrl = request.getParameter("slaveUrl");
				Double slaveLoad = Double.parseDouble(request
						.getParameter("slaveLoad"));
				State.getRegisteredSlaves().add(new Slave(slaveUrl, slaveLoad));
				writer.print("ok");
			} else if ("deregister".equalsIgnoreCase(action)) {
				String slaveUrl = request.getParameter("slaveUrl");
				State.getRegisteredSlaves().remove(slaveUrl);
				writer.print("ok");
			} else if ("put".equalsIgnoreCase(action)) {
				String putResult = null;
				String key = request.getParameter("key");
				String value = request.getParameter("value");
				String mapId = request.getParameter("map-id");

				for (Slave slave : State.getRegisteredSlaves()) {
					putResult = slave.put(mapId, key, value);
					if (putResult.equals(key))
						break;
				}
				writer.print(putResult);
			} else if ("get-map-id".equalsIgnoreCase(action)) {
				writer.print(State.getNextMapId());
			}else if ("info".equalsIgnoreCase(action)) {
				System.out.println("Master info requested");
				writer.print(State.getInfo());
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
