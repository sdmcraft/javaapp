/*
 * Copyright (c) 2002, Marco Hunsicker. All rights reserved.
 *
 * The contents of this file are subject to the Common Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://jalopy.sf.net/license-cpl.html
 *
 * Copyright (c) 2001-2002 Marco Hunsicker
 */
package dataStructuresV2.impl;

import dataStructuresV2.Node;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
  *
 * @param <T> DOCUMENT ME!
 */
public class NodeImpl<T> implements Node<T>
{
    private final Map<String, Object> propertyMap = new HashMap<String, Object>();
    private final T value;

    /**
     * Creates a new NodeImpl object.
     *
     * @param value DOCUMENT ME!
     */
    public NodeImpl(T value)
    {
        this.value = value;
    }

    /**
     * DOCUMENT ME!
     *
     * @param obj DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
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

        if (!(obj instanceof NodeImpl))
        {
            return false;
        }

        NodeImpl other = (NodeImpl) obj;

        if (value == null)
        {
            if (other.value != null)
            {
                return false;
            }
        }
        else if (!value.equals(other.value))
        {
            return false;
        }

        return true;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public String getDiagramFragment()
    {
        return "\"" + value.toString() + "\"";
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public Map<String, Object> getProperties()
    {
        return Collections.unmodifiableMap(propertyMap);
    }

    /**
     * DOCUMENT ME!
     *
     * @param property DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public Object getProperty(String property)
    {
        return propertyMap.get(property);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public T getValue()
    {
        return value;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((value == null) ? 0 : value.hashCode());

        return result;
    }

    /**
     * DOCUMENT ME!
     *
     * @param property DOCUMENT ME!
     * @param value DOCUMENT ME!
     */
    @Override
    public void setProperty(String property, Object value)
    {
        propertyMap.put(property, value);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public String toString()
    {
        return value.toString();
    }

    @Override
    public void clearProperties()
    {
        propertyMap.clear();
    }
}
