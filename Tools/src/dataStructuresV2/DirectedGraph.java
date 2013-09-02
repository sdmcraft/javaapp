package dataStructuresV2;

import java.util.Set;


public interface DirectedGraph<T> extends Graph<T>
{
    public Set<Graph<T>> getStronglyConnectedComponents();
    public void reverseEdges();
}
