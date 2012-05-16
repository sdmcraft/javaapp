package dataStructuresV2.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;

public class GraphUtils {

	public final <T> List<Node<T>> shortestDistance(Graph<T> graph,
			Node<T> startNode, Node<T> endNode) throws Exception {
		Set<Node<T>> visitedNodes = new HashSet<>();
		Map<Node<T>, List<Node<T>>> pathMap = new HashMap<Node<T>, List<Node<T>>>();
		class NodeDistance implements Comparable<NodeDistance> {
			private final Node<T> node;
			private int distance = Integer.MAX_VALUE;

			private NodeDistance(Node<T> node) {
				this.node = node;
			}

			private NodeDistance(Node<T> node, int distance) {
				this.node = node;
				this.distance = distance;
			}

			@Override
			public int compareTo(NodeDistance o) {
				return Integer.valueOf(distance).compareTo(
						Integer.valueOf(o.distance));
			}

		}

		List<NodeDistance> nodeList = new ArrayList<>();
		NodeDistance nodeDistance = new NodeDistance(startNode, 0);
		nodeList.add(nodeDistance);
		while (nodeList.size() > 0) {
			NodeDistance currentNodeDistance = nodeList.remove(0);
			Node<T> node = currentNodeDistance.node;
			Set<Node<T>> neighbours = graph.getNeighbours(node);

			for (Node<T> neighbour : neighbours) {

				Set<Edge<T>> edgesToNeighbour = graph.getEdges(node, neighbour);
				int minEdgeWeight = Integer.MAX_VALUE;
				for (Edge<T> edge : edgesToNeighbour) {
					if (edge.getWeight().intValue() < minEdgeWeight) {
						minEdgeWeight = edge.getWeight().intValue();
					}
				}

				int neighbourIndex = -1;
				for (NodeDistance nd : nodeList) {
					if (neighbour.equals(nd.node)
							&& !visitedNodes.contains(nd.node)) {
						neighbourIndex = nodeList.indexOf(nd);
						break;
					}
				}

				if (neighbourIndex == -1) {
					NodeDistance neighbourDistance = new NodeDistance(
							neighbour, minEdgeWeight);
					nodeList.add(neighbourDistance);
					List<Node<T>> path = new ArrayList<>();
					path.add(neighbour);
					pathMap.put(neighbour, path);
				} else {
					if (nodeList.get(neighbourIndex).distance > currentNodeDistance.distance
							+ minEdgeWeight) {
						nodeList.get(neighbourIndex).distance = currentNodeDistance.distance
								+ minEdgeWeight;
						pathMap.get(neighbour).add(
								nodeList.get(neighbourIndex).node);
					}
				}
			}
			visitedNodes.add(node);
			Collections.sort(nodeList);
		}

		return null;
	}
}
