package org.sdm.timerecord.web.process;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sdm.timerecord.business.action.Action;
import org.sdm.timerecord.business.action.CreateUserRemote;

public class ActionServlet extends HttpServlet {

	private InitialContext initialContext;
	private String jndiProvider;
	private static final Map<String, String> actionMap = new HashMap<String, String>();
	static {
		actionMap.put("user-profile-update",
				"TimeRecordEnterprise-1.0/CreateUser/remote");
	}

	@Override
	public void init() throws ServletException {
		try {
			jndiProvider = getServletContext()
					.getInitParameter("jndi-provider");
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			properties.put(Context.PROVIDER_URL, jndiProvider);
			initialContext = new InitialContext(properties);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		try {
			Action action = (Action) initialContext.lookup(actionMap
					.get(request.getParameter("action")));
			action.equals(request.getParameterMap());
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}
