package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.UploadProgressListener;

public class UploadStatusServlet extends HttpServlet {
	int count = 0;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		UploadProgressListener listener = (UploadProgressListener) req
				.getSession().getAttribute("upload-listener");
		if (listener != null) {
			System.out.println("Pct Complete#"
					+ Double.toString(listener.getPercentComplete()));
			resp.addHeader("upload-status", Double .toString(listener
					.getPercentComplete()));
		}
		System.out.println("A NULL");
	}

}
