package org.sdm.timerecord.business.model.db;

import org.sdm.timerecord.business.exception.BusinessException;
import org.sdm.timerecord.business.exception.FailureCode;

import java.security.acl.Permission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TR_ACL")
public class AclEntry implements java.security.acl.AclEntry
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "PRINCIPAL_ID", referencedColumnName = "ID")
    private Principal principal;
    @ManyToOne
    @JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID")
    private Resource resource;
    @Column(name = "PERMISSIONS", nullable = false)
    private long permissionValue;

    public AclEntry()
    {
        super();
    }

    public AclEntry(Principal principal, Resource resource, Set<Permission> permissions)
    {
        super();
        this.principal = principal;
        this.resource = resource;
        this.permissionValue = permissionsToLong(permissions);
    }

    // This method has a unit test
    @Override
    public boolean addPermission(Permission permission)
    {
        try
        {
            Enumeration<Permission> currentPermissions = longToPermissions(permissionValue);

            while (currentPermissions.hasMoreElements())
            {
                if (currentPermissions.nextElement().equals(permission))
                {
                    return false;
                }
            }
        }
        catch (BusinessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Set<Permission> permissionSet = new HashSet<Permission>();
        permissionSet.add(permission);
        permissionValue += permissionsToLong(permissionSet);

        return true;
    }

    public boolean checkPermission(Permission arg0)
    {
        // TODO Auto-generated method stub
        return false;
    }

    public Principal getPrincipal()
    {
        return principal;
    }

    public boolean isNegative()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public Enumeration<Permission> permissions()
    {
        try
        {
            return longToPermissions(permissionValue);
        }
        catch (BusinessException e)
        {
            throw new RuntimeException(e);
        }
    }

    public boolean removePermission(Permission arg0)
    {
        // TODO Auto-generated method stub
        return false;
    }

    public void setNegativePermissions()
    {
        // TODO Auto-generated method stub
    }

    public boolean setPrincipal(Principal arg0)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean setPrincipal(java.security.Principal arg0)
    {
        // TODO Auto-generated method stub
        return false;
    }

    // This method has a unit test
    private static final long permissionsToLong(Set<Permission> permissions)
    {
        long result = 0;

        for (Permission permission : permissions)
        {
            result += ((org.sdm.timerecord.business.model.Permission) permission).getLong();
        }

        return result;
    }

    // This method has a unit test
    private static final Enumeration<Permission> longToPermissions(long value) throws BusinessException
    {
        Long mask = 1L;
        List<Permission> permissions = new ArrayList<Permission>();

        for (int i = 0; i < Long.toBinaryString(value).length(); i++)
        {
            Permission permission = null;

            try
            {
                permission = org.sdm.timerecord.business.model.Permission.getPermission(value & mask);
            }
            catch (BusinessException ex)
            {
                if (ex.getCode() != FailureCode.INVALID_DATA)
                {
                    throw ex;
                }
            }

            if (permission != null)
            {
                permissions.add(permission);
            }

            mask = mask << 1;
        }

        return Collections.enumeration(permissions);
    }
}
