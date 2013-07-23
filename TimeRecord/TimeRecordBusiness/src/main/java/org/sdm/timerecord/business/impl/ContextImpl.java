package org.sdm.timerecord.business.impl;

import org.sdm.timerecord.business.Context;
import org.sdm.timerecord.business.exception.PermissionDeniedException;

import java.io.Serializable;

import java.security.Principal;
import java.security.acl.Acl;
import java.security.acl.Permission;

import java.util.ArrayList;
import java.util.List;


public class ContextImpl implements Context, Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final List<Principal> principals = new ArrayList<Principal>();

    public ContextImpl(List<Principal> principals) {}

    @Override
    public void checkPermission(Acl acl, Permission permission) throws PermissionDeniedException
    {
        for (Principal principal : principals)
        {
            if (acl.checkPermission(principal, permission))
            {
                return;
            }
        }

        throw new PermissionDeniedException();
    }
}
