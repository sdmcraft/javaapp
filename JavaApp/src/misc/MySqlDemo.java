package misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlDemo {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/jbossdb?"
						+ "user=todoapp&password=secretpassword");
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM users");
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
			System.out.println(rs.getString(1) + " " + rs.getString(2));
	}

}
