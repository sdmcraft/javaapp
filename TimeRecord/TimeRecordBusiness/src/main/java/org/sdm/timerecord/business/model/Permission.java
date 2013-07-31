package org.sdm.timerecord.business.model;

import org.sdm.timerecord.business.exception.BusinessException;
import org.sdm.timerecord.business.exception.FailureCode;


public class Permission implements java.security.acl.Permission
{
    public static Permission READ = new Permission(PermissionType.READ);
    public static Permission MODIFY = new Permission(PermissionType.MODIFY);
    private final PermissionType type;

    private Permission(PermissionType type)
    {
        this.type = type;
    }

    private PermissionType getType()
    {
        return type;
    }

    public static Permission getPermission(long value) throws BusinessException
    {
        if (value == PermissionType.READ.getValue())
        {
            return READ;
        }
        else if (value == PermissionType.MODIFY.getValue())
        {
            return MODIFY;
        }

        throw new BusinessException(FailureCode.INVALID_DATA);
    }

    public long getLong()
    {
        return type.value;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((type == null) ? 0 : type.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (obj instanceof Permission)
        {
            Permission other = (Permission) obj;

            if (type != other.getType())
            {
                return false;
            }
        }

        if (obj instanceof PermissionType)
        {
            if (type != obj)
            {
                return false;
            }
        }

        return true;
    }
    private enum PermissionType
    {
    	MODIFY(1L << 1), READ(1L << 0);
    	private final long value;

        private PermissionType(long value)
        {
            this.value = value;
        }

        public long getValue()
        {
            return value;
        }
        
    }
}
