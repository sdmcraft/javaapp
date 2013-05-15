package dataStructuresV2.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;

public class BasicGraph<T> implements Graph<T> {

	protected final Set<Node<T>> nodes = new HashSet<Node<T>>();
	protected final Set<Edge<T>> edges = new HashSet<Edge<T>>();

	@Override
	public Set<Node<T>> getNodes() {

		return Collections.unmodifiableSet(nodes);
	}

	@Override
	public Set<Edge<T>> getEdges() {
		return Collections.unmodifiableSet(edges);
	}

	@Override
	// UT:TestBasicGraph.testGetEdgesSingleNode
	public Set<Edge<T>> getEdges(Node<T> node) throws InvalidDataException {
		if (nodes.contains(node)) {
			Set<Edge<T>> nodeEdges = new HashSet<Edge<T>>();
			for (Edge<T> edge : edges) {
				if (edge.isOrigin(node)) {
					nodeEdges.add(edge);
				}
			}
			return Collections.unmodifiableSet(nodeEdges);
		} else {
			throw new InvalidDataException(InvalidDataException.Code.INVALID,
					"Specified node does not belong to this graph:" + node);
		}
	}

	@Override
	// UT:TestBasicGraph.testGetEdgesDoubleNode
	public Set<Edge<T>> getEdges(Node<T> node1, Node<T> node2)
			throws InvalidDataException {
		if (!nodes.contains(node1))
			throw new InvalidDataException(InvalidDataException.Code.INVALID,
					"Specified node does not belong to this graph:" + node1);
		if (!nodes.contains(node2))
			throw new InvalidDataException(InvalidDataException.Code.INVALID,
					"Specified node does not belong to this graph:" + node2);
		Set<Edge<T>> node1Edges = getEdges(node1);
		Set<Edge<T>> edges = new HashSet<Edge<T>>();
		for (Edge<T> edge : node1Edges) {
			if (edge.getEndpoints()[0].equals(node2)
					|| edge.getEndpoints()[1].equals(node2))
				edges.add(edge);
		}
		return Collections.unmodifiableSet(edges);
	}

	@Override
	// UT:TestBasicGraph.testGetNeighbours
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

	@Override
	// UT:TestBasicGraph.testAddNode
	final public void addNode(Node<T> node) throws InvalidDataException {
		if (!nodes.add(node)) {
			throw new InvalidDataException(InvalidDataException.Code.DUPLICATE,
					"This graph already has this node!!");
		}
	}

	@Override
	// UT:TestBasicGraph.testAddEdge
	final public void addEdge(Edge<T> edge) throws InvalidDataException {
		if (!canAdd(edge)) {
			throw new InvalidDataException(InvalidDataException.Code.MISSING,
					"An endpoint of this edge is not present in the graph!!");
		}

		if (!edges.add(edge)) {
			throw new InvalidDataException(InvalidDataException.Code.DUPLICATE,
					"This graph already has this edge!!");
		}

	}

	// UT:TestBasicGraph.testCanAdd
	protected boolean canAdd(Edge<T> edge) {
		return nodes.contains(edge.getEndpoints()[0])
				&& nodes.contains(edge.getEndpoints()[1]);
	}

}
