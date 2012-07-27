package org.sdm.timerecord.business.action.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sdm.timerecord.business.Queries;
import org.sdm.timerecord.business.action.UpdatePrincipalRemote;
import org.sdm.timerecord.business.model.db.Principal;

@Stateless
public class UpdatePrincipal implements UpdatePrincipalRemote {

	@PersistenceContext(unitName = "TimeRecord")
	EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void execute(Map<String, String[]> params) throws Exception {				
		Principal principal = null;
		if (params.containsKey("id")) {
			principal = em.find(Principal.class, Integer.parseInt(params.get("id")[0]));
			if (principal != null)
				principal.reset(params);
			else
				throw new Exception("Unable to find principal with id:"
						+ params.get("id")[0]);
		} else {
			principal = new Principal(params.get("name")[0]);
		}
		em.persist(principal);
		List<java.security.Principal> list = Queries.getPrincipalList();
	}

}
