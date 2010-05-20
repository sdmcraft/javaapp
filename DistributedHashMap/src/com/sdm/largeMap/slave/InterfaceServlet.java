package com.sdm.largeMap.slave;

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

			if ("put".equalsIgnoreCase(action)) {
				String key = (String) request.getAttribute("key");
				String value = (String) request.getAttribute("value");
				String mapId = (String) request.getAttribute("map-id");
				writer.print(State.put(mapId, key, value));
			} else if ("get".equalsIgnoreCase(action)) {
				String key = (String) request.getAttribute("key");
				String mapId = (String) request.getAttribute("map-id");
				writer.print(State.get(mapId, key));
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
