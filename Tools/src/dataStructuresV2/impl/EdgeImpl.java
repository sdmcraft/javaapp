package dataStructuresV2.impl;

import dataStructuresV2.Edge;
import dataStructuresV2.Node;

public class EdgeImpl<T> implements Edge<T> {

	private final Node<T>[] endPoints;
	private final Number weight;

	public EdgeImpl(Node<T>[] endPoints, Number weight) {
		this.endPoints = endPoints;
		this.weight = weight;
	}

	@Override
	public Node<T>[] getEndpoints() {
		return endPoints;
	}

	@Override
	public Number getWeight() {
		return weight;
	}

	@Override
	public String getDiagramFragment() {
		return endPoints[0].getDiagramFragment() + "->"
				+ endPoints[1].getDiagramFragment() + "[dir=\"none\"]";
	}

}
