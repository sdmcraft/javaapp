package org.sdm.timerecord.business.action.impl;

import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.rowset.serial.SerialBlob;

import org.sdm.timerecord.business.action.CreateUserRemote;
import org.sdm.timerecord.business.model.User;

@Stateless
public class CreateUser implements CreateUserRemote {

	@PersistenceContext(name = "TimeRecord")
	EntityManager em;

	public void method() {
		System.out.println("Hello from session bean");

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void execute(Map<String, String[]> params) throws Exception {

		User user = new User(params.get("name")[0], new SerialBlob(new byte[0]));
		em.persist(user);
	}

}
