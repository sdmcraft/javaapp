package servlets;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ImageUploadServlet extends HttpServlet {

	Connection dbConnection = null;

	@Override
	public void init() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "welcome";

		try {
			Class.forName(driver).newInstance();
			dbConnection = DriverManager.getConnection(url + dbName, userName,
					password);
			System.out.println("DBConnection initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException {
		try {
			init();
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
					PreparedStatement ps = dbConnection.prepareStatement("INSERT INTO images(image) VALUES(?)");
					Blob imageBlob = new SerialBlob(byteArr);
					ps.setBlob(1, imageBlob);
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		try {
			if (dbConnection != null)
				dbConnection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
