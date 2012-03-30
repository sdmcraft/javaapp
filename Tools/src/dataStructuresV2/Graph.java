package dataStructuresV2;

/*TODO Create your own data structures*/
import java.util.Set;

import dataStructuresV2.exception.InvalidDataException;

public interface Graph<T> {
	public Set<Node<T>> getNodes();

	public Set<Edge<T>> getEdges();

	public Set<Edge<T>> getEdges(Node<T> node) throws InvalidDataException;

	public Set<Node<T>> getNeighbours(Node<T> node) throws InvalidDataException;
	
	public void addNode(Node<T> node) throws InvalidDataException;
	
	public void addEdge(Edge<T> edge) throws InvalidDataException;;

	public String getDiagram();
}
