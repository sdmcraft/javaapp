package web.servlets;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.session.stateless.SessionBeanLocal;

public class DemoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DemoServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init()
	{
		try
		{
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			properties.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");
			InitialContext jndiContext = new InitialContext(properties);
			System.out.println("Hello from demo servlet");
			SessionBeanLocal bean = (SessionBeanLocal)jndiContext.lookup("EnterpriseApp/SessionBean/local");
			bean.method();
			bean.createEmployee();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		
	}

}
