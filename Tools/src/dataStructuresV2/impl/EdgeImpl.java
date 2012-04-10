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
		/*
		 * Return a copy so that external modifications do not change the edge
		 * end points
		 */
		return new Node[] { endPoints[0], endPoints[1] };
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

	@Override
	public boolean equals(Object o) {
		boolean equal;
		equal = o instanceof Edge;
		if (equal) {
			Edge<?> edge = (Edge<?>) o;
			equal = this.endPoints[0].equals(edge.getEndpoints()[0]);
			if (equal) {
				equal = this.endPoints[1].equals(edge.getEndpoints()[1]);
			}
			if (equal) {
				equal = this.weight.equals(edge.getWeight());
			}
		}
		return equal;
	}

}
