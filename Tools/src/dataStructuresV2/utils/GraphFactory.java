package dataStructuresV2.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;
import dataStructuresV2.impl.BasicGraph;

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

	public static <T> Graph<T> getSimpleGraph(Set<T> values, int edges)
			throws InvalidDataException {
		if (edges > values.size() * (values.size() - 1) / 2) {
			throw new InvalidDataException(
					"Numbers of edges exceeds the maximum possible for a simple graph");
		} else {
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
				Node<T> endPoint2 = null;
				do {
					randomIndex = (int) Math.round((nodes.size() - 1)
							* Math.random());
					endPoint2 = nodes.get(randomIndex);
				} while (endPoint1 != null);
				Node<T>[] endpoints = new Node[] { endPoint1, endPoint2 };
				Edge<T> edge = EdgeFactory.getEdge(endpoints, Math.random());
				edgeSet.add(edge);
			}
			try {
				return new BasicGraph<T>(nodeSet, edgeSet);
			} catch (InvalidDataException e) {
				// This should ideally never happen as we are passing valid data
				// to
				// BasicGraph
				e.printStackTrace();
			}
		}
		return null;
	}
}
