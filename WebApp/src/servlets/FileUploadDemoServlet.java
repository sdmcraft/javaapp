package servlets;

import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadDemoServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		Writer out = null;
		try {
			out = resp.getWriter();
			out.write("<HTML>");
			out.write("<BODY>");
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if (isMultipart) {
				out.write("<BR>We have a multipart request");
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(req);
				out.write("<BR>Uploaded items count:" + items.size());
				for (FileItem item : items)
					out.write("<BR>Uploaded:" + item.getName());
			}
			out.write("</BODY>");
			out.write("</HTML>");
		} catch (Exception ex) {
			throw new ServletException(ex);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception ex) {
					throw new ServletException(ex);
				}
		}
	}

}
