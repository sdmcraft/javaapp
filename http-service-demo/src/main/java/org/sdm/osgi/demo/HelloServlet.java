package org.sdm.osgi.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sdm.meetme.client.Client;

public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().write("Hello World");
		try {
			new Client().demo("10.40.61.65", "admin", "P@$$w0rd", "6300",
					new String[] { "SIP/1000" },
					"http://10.40.79.106:8080/AsteriskExtension/service");
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}