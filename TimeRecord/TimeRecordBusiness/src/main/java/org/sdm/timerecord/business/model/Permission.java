package org.sdm.timerecord.business.model;

import org.sdm.timerecord.business.exception.BusinessException;
import org.sdm.timerecord.business.exception.FailureCode;

public class Permission implements java.security.acl.Permission {

	public enum PermissionType {
		READ(1L << 0), MODIFY(1L << 1);
		private final long value;

		private PermissionType(long value) {
			this.value = value;
		}

		public long getValue() {
			return value;
		}
	}

	public final PermissionType type;

	public Permission(PermissionType type) {
		this.type = type;
	}

	public Permission(long value) throws BusinessException {
		for (PermissionType type : PermissionType.values()) {
			if (value == type.getValue()) {
				this.type = type;
				return;
			}
		}
		throw new BusinessException(FailureCode.INVALID_DATA);
	}

	public long getLong() {
		return type.value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Permission other = (Permission) obj;
		if (type != other.getType())
			return false;
		return true;
	}

	public PermissionType getType() {
		return type;
	}

}
