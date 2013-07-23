package org.sdm.timerecord.business.impl;

import org.sdm.timerecord.business.Context;
import org.sdm.timerecord.business.ContextFactory;

import java.security.Principal;

import java.util.List;

import javax.ejb.Stateless;


@Stateless
public class ContextFactoryImpl implements ContextFactory
{
    @Override
    public Context getContext(List<Principal> principals)
    {
        return new ContextImpl(principals);
    }
}
