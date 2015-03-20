package dataStructuresV2;

import java.util.Map;

public interface Node<T>
{
    public T getValue();

    public String getDiagramFragment();

    public Object getProperty(String property);

    public void setProperty(String property, Object value);
    
    public void clearProperties();
    
    public Map<String,Object> getProperties();
}
