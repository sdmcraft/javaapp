package org.sdm.timerecord.business.action.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sdm.timerecord.business.action.CreateUserRemote;
import org.sdm.timerecord.business.model.User;


@Stateless
public class CreateUser implements CreateUserRemote{

	@PersistenceContext(name = "TimeRecord")
	EntityManager em;

	public void method() {
		System.out.println("Hello from session bean");

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void createUser(String name) {
		User user = new User(name);
		em.persist(user);
	}

	
}
