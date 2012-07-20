package org.sdm.timerecord.business;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Queries {

	static {
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		properties.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");
		InitialContext jndiContext = null;
		try {
			jndiContext = new InitialContext(properties);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EntityManagerFactory emf = null;
		try {
			emf = (EntityManagerFactory) jndiContext
					.lookup("java:comp/env/persistence/TimeRecord ");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// use factory to obtain application-managed entity manager
		EntityManager em = (EntityManager) emf.createEntityManager();
	}
}
