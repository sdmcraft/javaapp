package com.sdm.largeMap.slave;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterfaceServlet extends HttpServlet {

	private Map<String, String> map = new HashMap<String, String>();

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
				map.put(key, value);
				writer.print("ok");
			} else if ("get".equalsIgnoreCase(action)) {
				String key = (String) request.getAttribute("key");
				String value = map.get(key);
				writer.print("value");
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
