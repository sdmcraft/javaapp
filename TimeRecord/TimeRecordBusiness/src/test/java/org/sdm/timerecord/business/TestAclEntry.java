package org.sdm.timerecord.business;

import junit.framework.Assert;

import org.junit.Test;
import org.sdm.timerecord.business.model.db.AclEntry;

import java.security.acl.Permission;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;


public class TestAclEntry
{
    @Test
    public void testPermissionsToLong()
    {
        try
        {
            Set<Permission> permissions = new HashSet<Permission>();
            permissions.add(org.sdm.timerecord.business.model.Permission.READ);
            permissions.add(org.sdm.timerecord.business.model.Permission.MODIFY);

            long result = (Long) Util.invokeMethod(null, AclEntry.class, "permissionsToLong", new Class[] { Set.class }, new Object[] { permissions });
            Assert.assertEquals(3L, result);
            // ///////////////////////////////////
            permissions = new HashSet<Permission>();
            result = (Long) Util.invokeMethod(null, AclEntry.class, "permissionsToLong", new Class[] { Set.class }, new Object[] { permissions });

            Assert.assertEquals(0L, result);
            // ///////////////////////////////////
            permissions = new HashSet<Permission>();
            permissions.add(org.sdm.timerecord.business.model.Permission.READ);
            result = (Long) Util.invokeMethod(null, AclEntry.class, "permissionsToLong", new Class[] { Set.class }, new Object[] { permissions });
            Assert.assertEquals(1L, result);
            // ///////////////////////////////////
            permissions = new HashSet<Permission>();
            permissions.add(org.sdm.timerecord.business.model.Permission.MODIFY);
            result = (Long) Util.invokeMethod(null, AclEntry.class, "permissionsToLong", new Class[] { Set.class }, new Object[] { permissions });
            Assert.assertEquals(2L, result);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testLongToPermissions()
    {
        try
        {
            Enumeration<org.sdm.timerecord.business.model.Permission> result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util.invokeMethod(null, AclEntry.class, "longToPermissions",
                    new Class[] { Long.TYPE }, new Object[] { 0L });

            Assert.assertEquals(false, result.hasMoreElements());

            result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util.invokeMethod(null, AclEntry.class, "longToPermissions", new Class[] { Long.TYPE }, new Object[] { 1L });

            Assert.assertEquals(org.sdm.timerecord.business.model.Permission.READ, result.nextElement());
            Assert.assertEquals(false, result.hasMoreElements());

            result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util.invokeMethod(null, AclEntry.class, "longToPermissions", new Class[] { Long.TYPE }, new Object[] { 2L });

            Assert.assertEquals(org.sdm.timerecord.business.model.Permission.MODIFY, result.nextElement());
            Assert.assertEquals(false, result.hasMoreElements());

            result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util.invokeMethod(null, AclEntry.class, "longToPermissions", new Class[] { Long.TYPE }, new Object[] { 3L });

            Assert.assertEquals(org.sdm.timerecord.business.model.Permission.READ, result.nextElement());
            Assert.assertEquals(org.sdm.timerecord.business.model.Permission.MODIFY, result.nextElement());
            Assert.assertEquals(false, result.hasMoreElements());

            result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util.invokeMethod(null, AclEntry.class, "longToPermissions", new Class[] { Long.TYPE }, new Object[] { 4L });

            Assert.assertEquals(false, result.hasMoreElements());

            result = (Enumeration<org.sdm.timerecord.business.model.Permission>) Util.invokeMethod(null, AclEntry.class, "longToPermissions", new Class[] { Long.TYPE }, new Object[] { 5L });

            Assert.assertEquals(org.sdm.timerecord.business.model.Permission.READ, result.nextElement());
            Assert.assertEquals(false, result.hasMoreElements());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testAddPermission()
    {
        try
        {
            Set<Permission> permissions = new HashSet<Permission>();
            AclEntry aclEntry = new AclEntry(null, null, permissions);
            boolean result = aclEntry.addPermission(org.sdm.timerecord.business.model.Permission.READ);
            Assert.assertEquals(true, result);
            Assert.assertEquals(1L, Util.getField(aclEntry, AclEntry.class, "permissionValue"));
            // ///////////////////////////////////
            result = aclEntry.addPermission(org.sdm.timerecord.business.model.Permission.READ);
            Assert.assertEquals(false, result);
            Assert.assertEquals(1L, Util.getField(aclEntry, AclEntry.class, "permissionValue"));
            // ///////////////////////////////////
            result = aclEntry.addPermission(org.sdm.timerecord.business.model.Permission.MODIFY);
            Assert.assertEquals(true, result);
            Assert.assertEquals(3L, Util.getField(aclEntry, AclEntry.class, "permissionValue"));
            // ///////////////////////////////////
            result = aclEntry.addPermission(org.sdm.timerecord.business.model.Permission.MODIFY);
            Assert.assertEquals(false, result);
            Assert.assertEquals(3L, Util.getField(aclEntry, AclEntry.class, "permissionValue"));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail();
        }
    }
}
