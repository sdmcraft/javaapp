package servlets;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/binary");
		resp.setHeader("Content-Disposition", "attachment; filename=file;");

		InputStream in = new BufferedInputStream(new FileInputStream(req
				.getParameter("file")));
		OutputStream out = resp.getOutputStream();
		byte b;
		while ((b = (byte) in.read()) != -1) {
			out.write(b);
		}
		out.close();
	}
}
