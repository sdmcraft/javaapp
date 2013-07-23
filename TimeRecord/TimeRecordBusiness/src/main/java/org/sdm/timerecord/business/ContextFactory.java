package org.sdm.timerecord.business;

import org.sdm.timerecord.business.Context;

import java.security.Principal;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface ContextFactory
{
    public Context getContext(List<Principal> principals);
}
