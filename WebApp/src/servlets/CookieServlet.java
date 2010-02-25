package servlets;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CookieServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession(true);
		Writer out = resp.getWriter();
		out.write("<HTML><BODY>Hi! This is cookie servlet!</BODY></HTML>");
		resp.addCookie(new Cookie("name", "sdm"));
	}

}
