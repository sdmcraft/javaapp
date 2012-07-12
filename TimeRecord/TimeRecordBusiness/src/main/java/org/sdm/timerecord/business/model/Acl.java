package org.sdm.timerecord.business.model;

import java.security.Principal;
import java.security.acl.AclEntry;
import java.security.acl.LastOwnerException;
import java.security.acl.NotOwnerException;
import java.security.acl.Owner;
import java.security.acl.Permission;
import java.util.Collection;
import java.util.Enumeration;

public class Acl implements java.security.acl.Acl {
	
	private Collection<AclEntry> aclEntries;
	private Collection<Owner> owners;
	
	public Acl(Collection<AclEntry> aclEntries)
	{
		this.aclEntries = aclEntries;
	}

	public boolean addOwner(Principal arg0, Principal arg1)
			throws NotOwnerException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteOwner(Principal arg0, Principal arg1)
			throws NotOwnerException, LastOwnerException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOwner(Principal arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addEntry(Principal arg0, AclEntry arg1)
			throws NotOwnerException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkPermission(Principal arg0, Permission arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public Enumeration<AclEntry> entries() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Enumeration<Permission> getPermissions(Principal arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeEntry(Principal arg0, AclEntry arg1)
			throws NotOwnerException {
		// TODO Auto-generated method stub
		return false;
	}

	public void setName(Principal arg0, String arg1) throws NotOwnerException {
		// TODO Auto-generated method stub

	}

}
