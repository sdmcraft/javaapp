package dataStructuresV2.impl;

import dataStructuresV2.Edge;
import dataStructuresV2.exception.InvalidDataException;

public class SimpleGraph<T> extends BasicGraph<T> {

	public SimpleGraph() throws InvalidDataException {
		super();
	}

	@Override
	//TODO:Needs UT
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
