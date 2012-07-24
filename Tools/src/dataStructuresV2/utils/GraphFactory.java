package dataStructuresV2.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataStructuresV2.DirectedEdge;
import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;
import dataStructuresV2.impl.BasicGraph;
import dataStructuresV2.impl.SimpleGraph;

public class GraphFactory {

	public static <T> Graph<T> getGraph(Set<T> values, int edges) {
		Set<Node<T>> nodeSet = new HashSet<Node<T>>();
		Set<Edge<T>> edgeSet = new HashSet<Edge<T>>();

		List<Node<T>> nodes = new ArrayList<Node<T>>();
		for (T value : values) {
			Node<T> node = NodeFactory.getNode(value);
			nodes.add(node);
			nodeSet.add(node);
		}
		for (int i = 0; i < edges; i++) {
			int randomIndex = (int) Math.round((nodes.size() - 1)
					* Math.random());
			Node<T> endPoint1 = nodes.get(randomIndex);
			randomIndex = (int) Math.round((nodes.size() - 1) * Math.random());
			Node<T> endPoint2 = nodes.get(randomIndex);
			Node<T>[] endpoints = new Node[] { endPoint1, endPoint2 };
			Edge<T> edge = EdgeFactory.getEdge(endpoints, Math.random());
			edgeSet.add(edge);
		}
		try {
			return new BasicGraph<T>(nodeSet, edgeSet);
		} catch (InvalidDataException e) {
			// This should ideally never happen as we are passing valid data to
			// BasicGraph
			e.printStackTrace();
		}
		return null;
	}

	public static <T> Graph<T> getGraph(int[][] adjMatrix, T[] values,
			boolean directed) throws InvalidDataException {
		BasicGraph<T> basicGraph = null;
		if (adjMatrix.length < 1 || adjMatrix.length != adjMatrix[0].length
				|| adjMatrix.length != values.length) {
			throw new InvalidDataException(
					"Invalid adjacency matrix for making a graph!!");
		} else {
			List<Node<T>> nodeList = new ArrayList<Node<T>>();
			for (T value : values) {
				nodeList.add(NodeFactory.getNode(value));
			}
			basicGraph = new BasicGraph<>(new HashSet<>(nodeList));
			for (int i = 0; i < adjMatrix.length; i++) {
				for (int j = 0; j < adjMatrix.length; j++) {
					if (adjMatrix[i][j] != Integer.MAX_VALUE) {
						Edge edge = null;
						if (directed) {
							edge = EdgeFactory.getDirectedEdge(new Node[] {
									nodeList.get(i), nodeList.get(j) },
									adjMatrix[i][j]);
						} else {
							edge = EdgeFactory.getEdge(
									new Node[] { nodeList.get(i),
											nodeList.get(j) }, adjMatrix[i][j]);
						}
						basicGraph.addEdge(edge);
					}
				}
			}
		}
		return basicGraph;
	}

	public static <T> Graph<T> getSimpleGraph(Set<T> values, int edges)
			throws InvalidDataException {
		if (edges > values.size() * (values.size() - 1) / 2) {
			throw new InvalidDataException(
					"Numbers of edges exceeds the maximum possible for a simple graph");
		} else {
			Set<Node<T>> nodeSet = new HashSet<Node<T>>();

			List<Node<T>> nodes = new ArrayList<Node<T>>();
			for (T value : values) {
				Node<T> node = NodeFactory.getNode(value);
				nodes.add(node);
				nodeSet.add(node);
			}
			SimpleGraph<T> simpleGraph = new SimpleGraph<T>(nodeSet);
			for (int i = 0; i < edges;) {
				int randomIndex = (int) Math.round((nodes.size() - 1)
						* Math.random());
				Node<T> endPoint1 = nodes.get(randomIndex);
				Node<T> endPoint2 = null;

				randomIndex = (int) Math.round((nodes.size() - 1)
						* Math.random());
				endPoint2 = nodes.get(randomIndex);

				Node<T>[] endpoints = new Node[] { endPoint1, endPoint2 };
				Edge<T> edge = EdgeFactory.getEdge(endpoints, 1);
				try {
					simpleGraph.addEdge(edge);
					i++;
				} catch (Exception ex) {
					// Failed to add edge, try again with other endpoints
				}
			}
			return simpleGraph;
		}
	}
}
