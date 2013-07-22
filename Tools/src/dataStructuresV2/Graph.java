package dataStructuresV2;

import dataStructuresV2.exception.InvalidDataException;

/*TODO Create your own data structures*/
import java.util.Set;


public interface Graph<T>
{
    public Set<Node<T>> getNodes();

    public Set<Edge<T>> getEdges();

    public Set<Edge<T>> getEdges(Node<T> node) throws InvalidDataException;

    public Set<Edge<T>> getEdges(Node<T> node1, Node<T> node2) throws InvalidDataException;

    public Set<Node<T>> getNeighbours(Node<T> node) throws InvalidDataException;

    public void addNode(Node<T> node) throws InvalidDataException;

    public void addEdge(Edge<T> edge) throws InvalidDataException;
    
    public boolean isConnected();

    public String getDiagram();
}
