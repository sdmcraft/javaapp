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

	// TODO Needs UT
	public static <T> Graph<T> getGraph(Set<T> values, int edges)
			throws InvalidDataException {
		Set<Edge<T>> edgeSet = new HashSet<Edge<T>>();
		Graph<T> graph = new BasicGraph<T>();

		List<Node<T>> nodes = new ArrayList<Node<T>>();
		for (T value : values) {
			Node<T> node = NodeFactory.getNode(value);
			graph.addNode(node);
			nodes.add(node);
		}
		for (int i = 0; i < edges;) {
			int randomIndex = (int) Math.round((nodes.size() - 1)
					* Math.random());
			Node<T> endPoint1 = nodes.get(randomIndex);
			randomIndex = (int) Math.round((nodes.size() - 1) * Math.random());
			Node<T> endPoint2 = nodes.get(randomIndex);
			Node<T>[] endpoints = new Node[] { endPoint1, endPoint2 };
			Edge<T> edge = EdgeFactory.getEdge(endpoints, Math.random());
			try {
				graph.addEdge(edge);
				i++;
			} catch (InvalidDataException ex) {
				//Ignore and try with another edge
			}
		}
		return graph;
	}

	//TODO:Needs UT
	public static <T> Graph<T> getGraph(int[][] adjMatrix, T[] values,
			boolean directed) throws InvalidDataException {
		BasicGraph<T> basicGraph = new BasicGraph<T>();
		if (adjMatrix.length < 1 || adjMatrix.length != adjMatrix[0].length
				|| adjMatrix.length != values.length) {
			throw new InvalidDataException(
					"Invalid adjacency matrix for making a graph!!");
		}
		if (!directed) {
			for (int i = 0; i < adjMatrix.length; i++) {
				for (int j = 0; j < adjMatrix.length; j++) {
					if (adjMatrix[i][j] != adjMatrix[j][i]) {
						throw new InvalidDataException(
								"Invalid adjacency matrix for making a non-directional graph. It is not symmetric across the diagonal!!");
					}
				}
			}
		}
		List<Node<T>> nodeList = new ArrayList<Node<T>>();
		for (T value : values) {
			Node<T> node = NodeFactory.getNode(value);
			nodeList.add(node);
			basicGraph.addNode(node);
		}		
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				if (i != j && adjMatrix[i][j] != Integer.MAX_VALUE) {
					Edge edge = null;
					if (directed) {
						edge = EdgeFactory.getDirectedEdge(new Node[] {
								nodeList.get(i), nodeList.get(j) },
								adjMatrix[i][j]);
					} else {
						edge = EdgeFactory.getEdge(new Node[] {
								nodeList.get(i), nodeList.get(j) },
								adjMatrix[i][j]);
					}
					basicGraph.addEdge(edge);
				}
			}
		}

		return basicGraph;
	}

	//TODO:Needs UT
	public static <T> Graph<T> getSimpleGraph(Set<T> values, int edges)
			throws InvalidDataException {
		if (edges > values.size() * (values.size() - 1) / 2) {
			throw new InvalidDataException(
					"Numbers of edges exceeds the maximum possible for a simple graph");
		} else {
			SimpleGraph<T> simpleGraph = new SimpleGraph<T>();
			List<Node<T>> nodes = new ArrayList<Node<T>>();
			for (T value : values) {
				Node<T> node = NodeFactory.getNode(value);
				nodes.add(node);
				simpleGraph.addNode(node);
			}			
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
