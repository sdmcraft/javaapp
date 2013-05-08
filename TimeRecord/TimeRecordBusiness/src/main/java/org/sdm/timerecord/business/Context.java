package org.sdm.timerecord.business;

import java.security.acl.Acl;
import java.security.acl.Permission;

import org.sdm.timerecord.business.exception.PermissionDeniedException;


public interface Context {
	public void checkPermission(Acl acl, Permission permission) throws PermissionDeniedException; 
	
}
