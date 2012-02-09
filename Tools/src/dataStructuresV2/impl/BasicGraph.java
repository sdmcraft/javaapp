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

	@Override
	public String getDiagram() {
		StringBuilder diagram = new StringBuilder();
		diagram.append("digraph G {\n");
		for (Edge<T> edge : edges) {
			diagram.append("\"").append(edge.getEndpoints()[0].getValue().toString())
					.append("\"->\"").append(edge.getEndpoints()[1].getValue().toString())
					.append("\"\n");
		}
		diagram.append("}");
		return diagram.toString();
	}
}
