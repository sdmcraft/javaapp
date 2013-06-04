package org.sdm.timerecord.business.impl;

import java.security.Principal;
import java.security.acl.Acl;
import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.List;

import org.sdm.timerecord.business.Context;
import org.sdm.timerecord.business.exception.PermissionDeniedException;

public class ContextImpl implements Context {
	
	private final List<Principal> principals = new ArrayList<Principal>();

	public ContextImpl(List<Principal> principals)
	{
		
	}
	@Override
	public void checkPermission(Acl acl, Permission permission)
			throws PermissionDeniedException {
		// TODO Auto-generated method stub

	}

}
