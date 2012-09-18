package org.sdm.timerecord.business;

import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.sdm.timerecord.business.model.Permission.PermissionType;
import org.sdm.timerecord.business.model.db.AclEntry;

public class TestAclEntry {

	@Test
	public void testPermissionsToLong() {
		try {
			List<Permission> permissions = new ArrayList<Permission>();
			permissions.add(MockStore.getMockPermission(PermissionType.READ));
			permissions.add(MockStore.getMockPermission(PermissionType.MODIFY));
			long result = (Long) Util.invokeMethod(null, AclEntry.class,
					"permissionsToLong", new Class[] { Enumeration.class },
					new Object[] { Collections.enumeration(permissions) });
			Assert.assertEquals(3L, result);
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

}
