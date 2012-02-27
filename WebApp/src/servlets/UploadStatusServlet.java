package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.UploadProgressListener;

public class UploadStatusServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		UploadProgressListener listener = (UploadProgressListener) req
				.getSession().getAttribute("upload-listener");
		if (listener != null) {
			resp.addHeader("upload-status", Integer.toString(listener
					.getPercentComplete()));
		}
	}

}
