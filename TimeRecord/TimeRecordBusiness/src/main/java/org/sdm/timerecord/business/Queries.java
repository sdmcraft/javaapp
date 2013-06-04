package org.sdm.timerecord.business;

import java.security.Principal;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class Queries {

	static InitialContext jndiContext = null;
	static EntityManager em = null;

	static {
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		properties.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");

		try {
			jndiContext = new InitialContext(properties);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EntityManagerFactory emf = null;
		try {
			emf = (EntityManagerFactory) jndiContext
					.lookup("persistence-units/TimeRecord");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// use factory to obtain application-managed entity manager
		em = (EntityManager) emf.createEntityManager();
	}

	public static List<Principal> getPrincipalList() throws Exception {
		Query query = em.createNamedQuery("Principal.listAll");
		List<Principal> results = query.getResultList();
		return results;
	}

	public static Principal getRootPrincipal() {
		Query query = em.createNamedQuery("Principal.getRoot");
		Principal root = (Principal) query.getSingleResult();
		return root;
	}
}
