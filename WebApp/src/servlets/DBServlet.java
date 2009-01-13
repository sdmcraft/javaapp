package servlets;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class DBServlet extends HttpServlet {
	static final long serialVersionUID = 1L;
	public DBServlet()
	{
		super();
	}
	
	@Override
	public void init() {
		try {
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			properties.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");

			InitialContext jndiContext = new InitialContext(properties);
			DataSource mySqlDS = (DataSource)jndiContext.lookup("java:MySqlDS");
			System.out.println(mySqlDS);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException 
	{
		
	}
}
