	package org.sdm.timerecord.web.process;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sdm.timerecord.business.action.CreateUserRemote;

public class UserProfileUpdate extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			properties.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");
			InitialContext jndiContext = new InitialContext(properties);
			System.out.println("Hello from demo servlet");
			CreateUserRemote userCreationAction = (CreateUserRemote) jndiContext
					.lookup("TimeRecordEnterprise-1.0/CreateUser/remote");
			userCreationAction.createUser("Satya Deep", new byte[0]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
