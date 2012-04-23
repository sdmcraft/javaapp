package dataStructuresV2.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import dataStructuresV2.Graph;
import dataStructuresV2.Node;

public class GraphUtils {

	public final <T> List<Node<T>> shortestDistance(Graph<T> graph,
			Node<T> startNode, Node<T> endNode) {
		Set<Node<T>> visitedNodes = new HashSet<>();
		Map<Node<T>, Integer> distanceMap = new HashMap<>();

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

			private void setDistance(int distance) {
				this.distance = distance;
			}

			@Override
			public int compareTo(NodeDistance o) {
				return Integer.valueOf(distance).compareTo(
						Integer.valueOf(o.distance));
			}

		}

		NodeDistance nodeDistance = new NodeDistance(startNode, 0);
		PriorityQueue<NodeDistance> queue = new PriorityQueue<>();
		queue.offer(nodeDistance);

		return null;
	}
}
