package servlets;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ImageUploadServlet extends HttpServlet {

	@Override
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(req);
				for (FileItem item : items) {
					InputStream in = new BufferedInputStream(
							item.getInputStream());
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					int data;
					while ((data = in.read()) != -1) {
						out.write(data);
					}
					byte[] byteArr = out.toByteArray();
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

}
