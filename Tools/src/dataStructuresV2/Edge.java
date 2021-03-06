package dataStructuresV2;

public interface Edge<T>
{
    public Node<T>[] getEndpoints();

    public String getDiagramFragment();

    public Number getWeight();

    public boolean isOrigin(Node<T> node);

    public boolean hasSameEndpoints(Edge<T> edge);
}
