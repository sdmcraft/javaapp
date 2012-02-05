package dataStructuresV2;

public interface Edge<T> {

	public Node<T>[] getEndpoints();

	public Number getWeight();
}
