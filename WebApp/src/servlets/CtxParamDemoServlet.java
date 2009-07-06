package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CtxParamDemoServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = getServletContext();
		System.out.println("Context Parameter: "
				+ context.getInitParameter("ctxParam"));
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		out.write("<HTML>");
		out.write("<BODY>");
		out.write("Context Parameter: "
				+ this.getServletContext().getInitParameter("ctxParam"));
		out.write("</BODY>");
		out.write("</HTML>");

	}
}
