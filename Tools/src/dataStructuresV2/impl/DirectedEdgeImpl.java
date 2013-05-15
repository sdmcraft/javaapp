package dataStructuresV2.impl;

import java.util.Arrays;

import dataStructuresV2.DirectedEdge;
import dataStructuresV2.Node;

public class DirectedEdgeImpl<T> extends EdgeImpl<T> implements DirectedEdge<T> {

	public DirectedEdgeImpl(Node<T>[] endPoints) {
		super(endPoints);
	}

	public DirectedEdgeImpl(Node<T>[] endPoints, Number weight) {
		super(endPoints, weight);
	}

	@Override
	public String getDiagramFragment() {
		return endPoints[0].getDiagramFragment() + "->"
				+ endPoints[1].getDiagramFragment();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (this instanceof DirectedEdge)
			result = prime * result + endPoints[0].hashCode()
					- endPoints[1].hashCode();
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
		DirectedEdgeImpl<T> other = (DirectedEdgeImpl<T>) obj;

		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;

		if (!Arrays.equals(endPoints, other.endPoints))
			return false;

		return true;
	}

	@Override
	public boolean isOrigin(Node<T> node) {
		return this.endPoints[0].equals(node);
	}

}
