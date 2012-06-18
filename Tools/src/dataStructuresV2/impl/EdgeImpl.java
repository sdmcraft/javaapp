package dataStructuresV2.impl;

import java.util.Arrays;

import dataStructuresV2.Edge;
import dataStructuresV2.Node;

public class EdgeImpl<T> implements Edge<T> {

	private final Node<T>[] endPoints;
	private final Number weight;

	public EdgeImpl(Node<T>[] endPoints, Number weight) {
		this.endPoints = endPoints;
		this.weight = weight;
	}

	public EdgeImpl(Node<T>[] endPoints) {
		this.endPoints = endPoints;
		this.weight = null;
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

	// @Override
	public boolean equals1(Object o) {
		boolean equal;
		equal = o instanceof Edge;
		if (equal) {
			Edge<?> edge = (Edge<?>) o;
			equal = (this.endPoints[0].equals(edge.getEndpoints()[0]) && this.endPoints[1]
					.equals(edge.getEndpoints()[1]))
					|| (this.endPoints[0].equals(edge.getEndpoints()[1]) && this.endPoints[1]
							.equals(edge.getEndpoints()[0]));
			if (equal) {
				equal = this.weight == edge.getWeight();
				if (!equal) {
					equal = this.weight.equals(edge.getWeight());
				}
			}
		}
		return equal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endPoints[0].hashCode() + endPoints[1].hashCode();
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeImpl other = (EdgeImpl) obj;

		Node<T>[] reverseEndPoints = new Node[] { other.endPoints[1],
				other.endPoints[0] };
		if (!Arrays.equals(endPoints, other.endPoints) && !Arrays.equals(endPoints, reverseEndPoints))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

}
