package dataStructuresV2.utils;

import dataStructuresV2.Edge;
import dataStructuresV2.Node;
import dataStructuresV2.impl.EdgeImpl;

public class EdgeFactory {
	public static <T> Edge<T> getEdge(Node<T>[] endPoints, Number weight) {
		return new EdgeImpl<T>(endPoints, weight);
	}
	
	public static <T> Edge<T> getEdge(Node<T>[] endPoints) {
		return new EdgeImpl<T>(endPoints);
	}

}
