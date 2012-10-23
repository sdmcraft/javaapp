package org.sdm.timerecord.business;

import java.security.acl.Permission;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.sdm.timerecord.business.model.Permission.PermissionType;
import org.sdm.timerecord.business.model.db.AclEntry;

public class TestAclEntry {

	@Test
	public void testPermissionsToLong() {
		try {
			Set<Permission> permissions = new HashSet<Permission>();
			permissions.add(MockStore.getMockPermission(PermissionType.READ));
			permissions.add(MockStore.getMockPermission(PermissionType.MODIFY));
			long result = (Long) Util.invokeMethod(null, AclEntry.class,
					"permissionsToLong", new Class[] { Set.class },
					new Object[] { permissions });
			Assert.assertEquals(3L, result);
			// ///////////////////////////////////
			permissions = new HashSet<Permission>();
			result = (Long) Util.invokeMethod(null, AclEntry.class,
					"permissionsToLong", new Class[] { Set.class },
					new Object[] { permissions });

			Assert.assertEquals(0L, result);
			// ///////////////////////////////////
			permissions = new HashSet<Permission>();
			permissions.add(MockStore.getMockPermission(PermissionType.READ));
			result = (Long) Util.invokeMethod(null, AclEntry.class,
					"permissionsToLong", new Class[] { Set.class },
					new Object[] { permissions });
			Assert.assertEquals(1L, result);
			// ///////////////////////////////////
			permissions = new HashSet<Permission>();
			permissions.add(MockStore.getMockPermission(PermissionType.MODIFY));
			result = (Long) Util.invokeMethod(null, AclEntry.class,
					"permissionsToLong", new Class[] { Set.class },
					new Object[] { permissions });
			Assert.assertEquals(2L, result);

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testLongToPermissions() {
		try {
			Enumeration<org.sdm.timerecord.business.model.Permission> result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util
					.invokeMethod(null, AclEntry.class, "longToPermissions",
							new Class[] { Long.TYPE }, new Object[] { 0L });

			Assert.assertEquals(false, result.hasMoreElements());

			result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util
					.invokeMethod(null, AclEntry.class, "longToPermissions",
							new Class[] { Long.TYPE }, new Object[] { 1L });

			Assert.assertEquals(PermissionType.READ, result.nextElement().type);
			Assert.assertEquals(false, result.hasMoreElements());

			result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util
					.invokeMethod(null, AclEntry.class, "longToPermissions",
							new Class[] { Long.TYPE }, new Object[] { 2L });

			Assert.assertEquals(PermissionType.MODIFY,
					result.nextElement().type);
			Assert.assertEquals(false, result.hasMoreElements());

			result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util
					.invokeMethod(null, AclEntry.class, "longToPermissions",
							new Class[] { Long.TYPE }, new Object[] { 3L });

			Assert.assertEquals(PermissionType.READ, result.nextElement().type);
			Assert.assertEquals(PermissionType.MODIFY,
					result.nextElement().type);
			Assert.assertEquals(false, result.hasMoreElements());

			result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util
					.invokeMethod(null, AclEntry.class, "longToPermissions",
							new Class[] { Long.TYPE }, new Object[] { 4L });

			Assert.assertEquals(false, result.hasMoreElements());

			result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util
					.invokeMethod(null, AclEntry.class, "longToPermissions",
							new Class[] { Long.TYPE }, new Object[] { 5L });

			Assert.assertEquals(PermissionType.READ, result.nextElement().type);
			Assert.assertEquals(false, result.hasMoreElements());

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testAddPermission() {
		try {
			Set<Permission> permissions = new HashSet<Permission>();
			AclEntry aclEntry = new AclEntry(null, null, permissions);
			boolean result = aclEntry.addPermission(MockStore
					.getMockPermission(PermissionType.READ));
			Assert.assertEquals(true, result);
			Assert.assertEquals(1L,
					Util.getField(aclEntry, AclEntry.class, "permissionValue"));
			// ///////////////////////////////////
			result = aclEntry.addPermission(MockStore
					.getMockPermission(PermissionType.READ));
			Assert.assertEquals(false, result);
			Assert.assertEquals(1L,
					Util.getField(aclEntry, AclEntry.class, "permissionValue"));
			// ///////////////////////////////////
			result = aclEntry.addPermission(MockStore
					.getMockPermission(PermissionType.MODIFY));
			Assert.assertEquals(true, result);
			Assert.assertEquals(3L,
					Util.getField(aclEntry, AclEntry.class, "permissionValue"));
			// ///////////////////////////////////
			result = aclEntry.addPermission(MockStore
					.getMockPermission(PermissionType.MODIFY));
			Assert.assertEquals(false, result);
			Assert.assertEquals(3L,
					Util.getField(aclEntry, AclEntry.class, "permissionValue"));
			
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

}
