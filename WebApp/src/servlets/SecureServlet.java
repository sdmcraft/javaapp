package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecureServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		PrintWriter out = resp.getWriter();
		out.println("<HTML>");
		out.println("<BODY>");
		out.println("This is a secure servlet.");
		out.println("</BODY>");
		out.println("</HTML>");
	}
}
