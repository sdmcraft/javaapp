package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilteredServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.write("<HTML>");
		out.write("<BODY>");
		out.write("<H1>Filtered servlet visited "
				+ getServletContext().getAttribute("hitCount") + " times!!!");
		out.write("</BODY>");
		out.write("</HTML>");

	}
}
