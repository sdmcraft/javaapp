package dataStructuresV2.impl;

import dataStructuresV2.Node;

import java.util.HashMap;
import java.util.Map;


public class NodeImpl<T> implements Node<T>
{
    private final Map<String, Object> propertyMap = new HashMap<String, Object>();
    private final T value;

    public NodeImpl(T value)
    {
        this.value = value;
    }

    @Override
    public T getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return value.toString();
    }

    @Override
    public String getDiagramFragment()
    {
        return "\"" + value.toString() + "\"";
    }

    @Override
    public Object getProperty(String property)
    {
        return propertyMap.get(property);
    }

    @Override
    public void setProperty(String property, Object value)
    {
        propertyMap.put(property, value);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((value == null) ? 0 : value.hashCode());

        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
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
}
