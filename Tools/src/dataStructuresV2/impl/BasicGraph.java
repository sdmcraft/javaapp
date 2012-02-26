package dataStructuresV2.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;

public class BasicGraph<T> implements Graph<T> {

	private final Set<Node<T>> nodes = new HashSet<Node<T>>();
	private final Set<Edge<T>> edges = new HashSet<Edge<T>>();

	public BasicGraph(Set<Node<T>> nodes, Set<Edge<T>> edges)
			throws InvalidDataException {
		super();
		for (Node<T> node : nodes) {
			this.nodes.add(node);
		}
		for (Edge<T> edge : edges) {
			Node<T>[] endpoints = edge.getEndpoints();
			for (Node<T> endpoint : endpoints) {
				if (!this.nodes.contains(endpoint)) {
					this.nodes.clear();
					this.edges.clear();
					throw new InvalidDataException(
							"Edge endpoint does not belong to graph nodes: "
									+ endpoint);
				}
			}
			this.edges.add(edge);
		}
	}

	@Override
	public Set<Node<T>> getNodes() {

		return Collections.unmodifiableSet(nodes);
	}

	@Override
	public Set<Edge<T>> getEdges() {
		return Collections.unmodifiableSet(edges);
	}

	@Override
	public String getDiagram() {
		StringBuilder diagram = new StringBuilder();
		diagram.append("digraph G {\n");
		for (Edge<T> edge : edges) {
			diagram.append("\"").append(
					edge.getEndpoints()[0].getValue().toString()).append(
					"\"->\"").append(
					edge.getEndpoints()[1].getValue().toString())
					.append("\"\n");
		}
		diagram.append("}");
		return diagram.toString();
	}
}
