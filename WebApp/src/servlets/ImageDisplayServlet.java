package servlets;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*create table images(id int not null AUTO_INCREMENT, image LONGBLOB, primary key(id));*/

public class ImageDisplayServlet extends HttpServlet {

	Connection dbConnection = null;

	@Override
	public void init() {

		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		// String dbName = "TimeRecord";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "welcome";
		// String userName = "admin";
		// String password = "admin";
		int test;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			PreparedStatement ps = dbConnection
					.prepareStatement("SELECT image FROM images where id = ?");
			ps.setInt(1, Integer.parseInt(req.getParameter("image-id")));
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				Blob imageBlob = rs.getBlob("image");

				InputStream in = new BufferedInputStream(
						imageBlob.getBinaryStream());
				String mimeType = URLConnection.guessContentTypeFromStream(in);
				in.close();
				System.out.println("Mime Type:" + mimeType);
				
				byte[] imgData = imageBlob.getBytes(1,(int)imageBlob.length());				
				OutputStream out = resp.getOutputStream();
				out.write(imgData);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
