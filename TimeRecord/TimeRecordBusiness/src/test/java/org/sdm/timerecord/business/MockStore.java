package org.sdm.timerecord.business;

import org.easymock.EasyMock;
import org.sdm.timerecord.business.model.Permission;
import org.sdm.timerecord.business.model.Permission.PermissionType;

public class MockStore {

	public static Permission getMockPermission(PermissionType permissionType) {
		Permission mockPermission = EasyMock.createMock(Permission.class);
		switch (permissionType) {
		case MODIFY:
			EasyMock.expect(mockPermission.getLong()).andReturn(1L << 1);
			break;
		case READ:
			EasyMock.expect(mockPermission.getLong()).andReturn(1L << 0);
			break;
		}
		EasyMock.replay(mockPermission);
		return mockPermission;
	}

}
