package org.sdm.timerecord.business.action.impl;

import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sdm.timerecord.business.action.UpdateUserRemote;
import org.sdm.timerecord.business.model.User;

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
			user = em.find(User.class, params.get("id")[0]);
			user.reset(params);
		} else {
			user = new User(params.get("name")[0]);
		}
		em.persist(user);
	}

}
