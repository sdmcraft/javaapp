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
		System.out.println("InterfaceServlet.doGet");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String action = request.getParameter("action");

			if ("put".equalsIgnoreCase(action)) {
				String key = request.getParameter("key");
				String value = request.getParameter("value");
				String mapId = request.getParameter("map-id");
				writer.print(State.put(mapId, key, value));
			} else if ("get".equalsIgnoreCase(action)) {
				String key = request.getParameter("key");
				String mapId = request.getParameter("map-id");
				writer.print(State.get(mapId, key));
			}else if ("ping".equalsIgnoreCase(action)) {
				writer.print("ok");
				response.addHeader("status", "ok");
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
