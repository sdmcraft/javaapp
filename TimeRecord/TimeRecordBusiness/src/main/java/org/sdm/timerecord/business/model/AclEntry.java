package org.sdm.timerecord.business.model;

import java.security.Principal;
import java.security.acl.Permission;
import java.util.Enumeration;

public class AclEntry implements java.security.acl.AclEntry {

	private final Principal principal;
	private final Resource resource;
	private final Enumeration<Permission> permissions;

	public AclEntry(Principal principal, Resource resource,
			Enumeration<Permission> permissions) {
		super();
		this.principal = principal;
		this.resource = resource;
		this.permissions = permissions;
	}

	public boolean addPermission(Permission arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkPermission(Permission arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Principal getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isNegative() {
		// TODO Auto-generated method stub
		return false;
	}

	public Enumeration<Permission> permissions() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removePermission(Permission arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setNegativePermissions() {
		// TODO Auto-generated method stub

	}

	public boolean setPrincipal(Principal arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
