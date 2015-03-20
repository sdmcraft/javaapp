package org.sdm.timerecord.business;

import org.sdm.timerecord.business.exception.PermissionDeniedException;

import java.security.acl.Acl;
import java.security.acl.Permission;


public interface Context
{
    public void checkPermission(Acl acl, Permission permission) throws PermissionDeniedException;
}
