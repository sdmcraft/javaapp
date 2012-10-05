package org.sdm.timerecord.business.model.db;

import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.sdm.timerecord.business.exception.BusinessException;
import org.sdm.timerecord.business.exception.FailureCode;

@Entity
@Table(name = "TR_ACL")
public class AclEntry implements java.security.acl.AclEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "PRINCIPAL_ID", referencedColumnName = "ID")
	private final Principal principal;

	@ManyToOne
	@JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID")
	private final Resource resource;

	private transient final Enumeration<Permission> permissions;

	@Column(name = "PERMISSIONS", nullable = false)
	private long permissionValue;

	public AclEntry(Principal principal, Resource resource,
			Enumeration<Permission> permissions) {
		super();
		this.principal = principal;
		this.resource = resource;
		this.permissions = permissions;
		this.permissionValue = permissionsToLong(permissions);
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

	public Enumeration<Permission> getPermissions() {
		return permissions;
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

	public boolean setPrincipal(java.security.Principal arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	// This method has a unit test
	private static final long permissionsToLong(
			Enumeration<Permission> permissions) {
		long result = 0;
		while (permissions.hasMoreElements()) {
			Permission permission = permissions.nextElement();
			result += ((org.sdm.timerecord.business.model.Permission) permission)
					.getLong();
		}
		return result;
	}

	// This method has a unit test
	private static final Enumeration<Permission> longToPermissions(long value) throws BusinessException {
		Long mask = 1L;
		List<Permission> permissions = new ArrayList<Permission>();

		for (int i = 0; i < Long.toBinaryString(value).length(); i++) {
			Permission permission = null;
			try {
				permission = new org.sdm.timerecord.business.model.Permission(
						value & mask);
			} catch (BusinessException ex) {
				if (ex.getCode() != FailureCode.INVALID_DATA)
					throw ex;
			}
			if (permission != null)
				permissions.add(permission);

			mask = mask << 1;
		}
		return Collections.enumeration(permissions);
	}

}
