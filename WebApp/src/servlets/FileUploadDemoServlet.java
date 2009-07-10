package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
		BufferedOutputStream fileOut = null;
		BufferedInputStream fileIn = null;
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
				for (FileItem item : items) {
					File uploadFile = new File(getServletContext().getRealPath(
							"/web/content/" + item.getName()));
					if (uploadFile.exists())
						uploadFile.delete();
					uploadFile.createNewFile();
					out.write("<BR>Uploading to " + uploadFile);
					fileOut = new BufferedOutputStream(new FileOutputStream(
							uploadFile), 8192);

					fileIn = new BufferedInputStream(item.getInputStream(),
							8192);
					byte[] buffer = new byte[8192];
					while (fileIn.read(buffer) != -1) {
						fileOut.write(buffer);
					}
					out.write("<BR>Uploaded:" + item.getName());
				}
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
			if (fileIn != null)
				try {
					fileIn.close();
				} catch (Exception ex) {
					throw new ServletException(ex);
				}
			if (fileOut != null)
				try {
					fileOut.close();
				} catch (Exception ex) {
					throw new ServletException(ex);
				}
		}
	}

}
