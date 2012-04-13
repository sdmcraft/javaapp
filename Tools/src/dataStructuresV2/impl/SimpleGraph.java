package dataStructuresV2.impl;

import java.util.Set;

import dataStructuresV2.Edge;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;

public class SimpleGraph<T> extends BasicGraph<T> {

	public SimpleGraph(Set<Node<T>> nodes) throws InvalidDataException {
		super(nodes);
	}

	@Override
	protected boolean canAdd(Edge<T> edge) {
		boolean canAddEdge = super.canAdd(edge);
		if (canAddEdge) {
			canAddEdge = !edge.getEndpoints()[0].equals(edge.getEndpoints()[1]);
			if (canAddEdge) {
				canAddEdge = !edges.contains(edge);
			}
		}
		return canAddEdge;
	}

}
