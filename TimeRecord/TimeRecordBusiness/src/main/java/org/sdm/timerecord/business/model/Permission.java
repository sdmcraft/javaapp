package org.sdm.timerecord.business.model;

public class Permission implements java.security.acl.Permission {

	public enum PermissionType {
		READ, MODIFY
	}

	public final PermissionType type;

	public Permission(PermissionType type) {
		this.type = type;
	}

}
