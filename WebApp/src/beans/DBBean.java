package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBBean {

	private Connection dbConnection;

	public DBBean() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		// String dbName = "TimeRecord";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "welcome";
		// String userName = "admin";
		// String password = "admin";		
		try {
			Class.forName(driver).newInstance();
			dbConnection = DriverManager.getConnection(url + dbName, userName,
					password);
			System.out.println("DBConnection initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Integer> getImageList() throws Exception {
		List<Integer> imageList = new ArrayList<Integer>();
		PreparedStatement ps = dbConnection
				.prepareStatement("SELECT id FROM images");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			imageList.add(rs.getInt("id"));
		}
		rs.close();
		ps.close();
		return imageList;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(new DBBean().getImageList());
	}

}
