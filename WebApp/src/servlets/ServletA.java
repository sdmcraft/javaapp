package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletA extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		PrintWriter out = resp.getWriter();
		out.println("<HTML>");
		out.println("<BODY>");
		out.println("Hello from servlet A");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ServletB");
		rd.include(req, resp);
		rd = getServletContext().getRequestDispatcher("/ServletC");
		rd.include(req, resp);
		
		out.println("</BODY>");
		out.println("</HTML>");
		
	}

}
