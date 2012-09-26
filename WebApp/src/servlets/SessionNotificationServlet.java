package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionNotificationServlet extends HttpServlet {

	private static int count = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object lock = session.getAttribute("lock");
		if (lock == null) {
			lock = new HashMap<String, String>();
			((HashMap<String, String>) lock).put("sign", "me" + count++);
			session.setAttribute("lock", lock);
		}
		resp.setContentType("text/event-stream");
		PrintWriter out = resp.getWriter();
		synchronized (lock) {
			try {
				System.out.println("Going for wait on lock:"
						+ ((HashMap<String, String>) lock).get("sign"));
				lock.wait();
				System.out.println("Wait over on lock:"
						+ ((HashMap<String, String>) lock).get("sign"));

				out.print("event: session-state\n");
				out.print("data: session-expired\n\n");
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
		}
	}
}
