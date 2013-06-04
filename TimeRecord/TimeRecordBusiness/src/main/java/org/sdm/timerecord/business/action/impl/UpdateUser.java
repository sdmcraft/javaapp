package org.sdm.timerecord.business.action.impl;

import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sdm.timerecord.business.Queries;
import org.sdm.timerecord.business.action.UpdateUserRemote;
import org.sdm.timerecord.business.model.db.Resource;
import org.sdm.timerecord.business.model.db.User;

@Stateless
public class UpdateUser implements UpdateUserRemote {

	@PersistenceContext(unitName = "TimeRecord")
	EntityManager em;

	public void method() {
		System.out.println("Hello from session bean");

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void execute(Map<String, String[]> params) throws Exception {
		User user = null;
		if (params.containsKey("id")) {
			user = em.find(User.class, Integer.parseInt(params.get("id")[0]));
			if (user != null)
				user.reset(params);
			else
				throw new Exception("Unable to find user with id:"
						+ params.get("id")[0]);
		} else {
			user = new User(params.get("name")[0], params.get("password")[0], (Resource)Queries.getRootPrincipal());
			
		}
		em.persist(user);
	}

}
