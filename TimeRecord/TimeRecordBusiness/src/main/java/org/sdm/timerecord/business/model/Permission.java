package org.sdm.timerecord.business.model;

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

	public Permission(long value) {
		for (PermissionType type : PermissionType.values()) {
			if (value == type.getValue()) {
				this.type = type;
				return;
			}
		}
		this.type = null;
	}

	public long getLong() {
		return type.value;
	}

}
