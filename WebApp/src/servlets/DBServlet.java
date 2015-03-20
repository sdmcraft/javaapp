package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class DBServlet extends HttpServlet {
	static final long serialVersionUID = 1L;

	public DBServlet() {
		super();
	}

	@Override
	public void init() {
		Connection conn = null;
		try {
			// Properties properties = new Properties();
			// properties.put(Context.INITIAL_CONTEXT_FACTORY,
			// "org.jnp.interfaces.NamingContextFactory");
			// properties.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");
			//
			// InitialContext jndiContext = new InitialContext(properties);
			// DataSource mySqlDS =
			// (DataSource)jndiContext.lookup("java:MySqlDS");
			InitialContext jndiContext = new InitialContext();
			DataSource myDS = (DataSource) jndiContext
					.lookup("java:/comp/env/jdbc/breeze");

			conn = myDS.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("select name from pps_accounts");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
