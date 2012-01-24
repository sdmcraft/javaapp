package dataStructuresV2.impl;

import dataStructuresV2.Edge;
import dataStructuresV2.Node;

public class EdgeImpl<T> implements Edge<T> {

	private final Node<T>[] endPoints;
	private final int weight;

	public EdgeImpl(Node<T>[] endPoints, int weight) {
		this.endPoints = endPoints;
		this.weight = weight;
	}

	@Override
	public Node<T>[] getEndpoints() {
		return endPoints;
	}

	@Override
	public int getWeight() {
		return weight;
	}

}
