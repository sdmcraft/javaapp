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

	/*
	 * Defensively copy the constructor arguments so that external modification
	 * does not break encapsulation
	 */
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
	public Set<Edge<T>> getEdges(Node<T> node) throws InvalidDataException {
		if (nodes.contains(node)) {
			Set<Edge<T>> nodeEdges = new HashSet<Edge<T>>();
			for (Edge<T> edge : edges) {
				if (edge.getEndpoints()[0].equals(node)
						|| edge.getEndpoints()[1].equals(node)) {
					nodeEdges.add(edge);
				}
			}
			return Collections.unmodifiableSet(nodeEdges);
		} else {
			throw new InvalidDataException(
					"Specified node does not belong to this graph:" + node);
		}
	}

	@Override
	public Set<Node<T>> getNeighbours(Node<T> node) throws InvalidDataException {
		Set<Edge<T>> nodeEdges = getEdges(node);
		Set<Node<T>> neighbours = new HashSet<Node<T>>();
		for (Edge<T> edge : nodeEdges) {
			if (!edge.getEndpoints()[0].equals(node)) {
				neighbours.add(edge.getEndpoints()[0]);
			} else if (!edge.getEndpoints()[1].equals(node)) {
				neighbours.add(edge.getEndpoints()[1]);
			}
		}
		return neighbours;
	}

	@Override
	public String getDiagram() {
		StringBuilder diagram = new StringBuilder();
		diagram.append("digraph G {\n");
		for (Node<T> node : nodes) {
			diagram.append(node.getDiagramFragment()).append("\n");
		}
		for (Edge<T> edge : edges) {
			diagram.append(edge.getDiagramFragment()).append("\n");
		}
		diagram.append("}");
		return diagram.toString();
	}
}
