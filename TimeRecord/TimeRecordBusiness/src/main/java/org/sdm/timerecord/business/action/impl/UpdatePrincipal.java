package org.sdm.timerecord.business.action.impl;

import org.sdm.timerecord.business.Context;
import org.sdm.timerecord.business.Queries;
import org.sdm.timerecord.business.action.UpdatePrincipalRemote;
import org.sdm.timerecord.business.model.Permission;
import org.sdm.timerecord.business.model.db.Principal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class UpdatePrincipal implements UpdatePrincipalRemote
{
    @PersistenceContext(unitName = "TimeRecord")
    EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Map<String, Object> execute(Map<String, String[]> params, Context ctx) throws Exception
    {
        Principal principal = null;
        Principal parent = null;

        if (params.containsKey("id"))
        {
            principal = em.find(Principal.class, Integer.parseInt(params.get("id")[0]));
            ctx.checkPermission(principal.getAcl(), Permission.MODIFY);

            principal.reset(params);
        }
        else if (params.containsKey("parent-principal-id"))
        {
            parent = em.find(Principal.class, Integer.parseInt(params.get("id")[0]));

            if (parent == null)
            {
                throw new Exception("Unable to find principal with id:" + params.get("parent-principal-id")[0]);
            }

            else
            {
                Query rootPrincipalQuery = em.createNamedQuery("Principal.getRoot");
                parent = (Principal) rootPrincipalQuery.getSingleResult();
            }

            ctx.checkPermission(parent.getAcl(), Permission.MODIFY);
            principal = new Principal(params.get("name")[0], parent);
        }
        else
        {
            Query membersGroupQuery = em.createNamedQuery("Principal.getMembersGroup");
            parent = (Principal) membersGroupQuery.getSingleResult();
            principal = new Principal(params.get("name")[0], parent);
        }

        em.persist(principal);

        //TODO:SDM
        Map<String, Object> response = new HashMap<String, Object>();

        return response;
    }
}
