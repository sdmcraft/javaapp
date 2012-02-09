package dataStructuresV2;

/*TODO Create you own data structures*/
import java.util.Set;

public interface Graph<T> {
	public Set<Node<T>> getNodes();
	public Set<Edge<T>> getEdges();
	public String getDiagram();
}
