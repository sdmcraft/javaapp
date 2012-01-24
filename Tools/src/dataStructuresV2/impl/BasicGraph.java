package dataStructuresV2.impl;

import java.util.Set;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;

public class BasicGraph<T> implements Graph<T> {

	private final Set<Node<T>> nodes;
	private final Set<Edge<T>> edges;

	public BasicGraph(Set<Node<T>> nodes, Set<Edge<T>> edges) {
		super();
		this.nodes = nodes;
		this.edges = edges;
	}

	@Override
	public Set<Node<T>> getNodes() {

		return nodes;
	}

	@Override
	public Set<Edge<T>> getEdges() {
		return edges;
	}

}
