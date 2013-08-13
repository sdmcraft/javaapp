package org.sdm.timerecord.business.action.impl;

import org.sdm.timerecord.business.Context;
import org.sdm.timerecord.business.Queries;
import org.sdm.timerecord.business.action.UpdateUserRemote;
import org.sdm.timerecord.business.model.db.Principal;
import org.sdm.timerecord.business.model.db.Resource;
import org.sdm.timerecord.business.model.db.User;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class UpdateUser implements UpdateUserRemote
{
    @PersistenceContext(unitName = "TimeRecord")
    EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Map<String, String[]> execute(Map<String, String[]> params, Context ctx) throws Exception
    {
        User user = null;

        if (params.containsKey("id"))
        {
            user = em.find(User.class, Integer.parseInt(params.get("id")[0]));

            if (user != null)
            {
                user.reset(params);
            }
            else
            {
                throw new Exception("Unable to find user with id:" + params.get("id")[0]);
            }
        }
        else
        {
            user = new User(params.get("name")[0], params.get("password")[0], (Resource) Queries.getRootPrincipal());
        }
        Query membersGroupQuery = em.createNamedQuery("Principal.getMembersGroup");
        Principal membersGroup = (Principal) membersGroupQuery.getSingleResult();
        Set<Principal> groups = new HashSet<Principal>();
        groups.add(membersGroup);
        user.setGroups(groups);

        em.persist(user);
        //TODO:SDM
        return null;
    }
}
