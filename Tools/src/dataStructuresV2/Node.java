package dataStructuresV2;

public interface Node<T>
{
    public T getValue();

    public String getDiagramFragment();

    public Object getProperty(String property);

    public void setProperty(String property, Object value);
}
