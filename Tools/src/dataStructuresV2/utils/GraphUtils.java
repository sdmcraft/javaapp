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

	public static final <T> List<Node<T>> shortestDistance(Graph<T> graph,
			Node<T> startNode, Node<T> endNode) throws Exception {
		/*
		 * Set of nodes which have been processed and should be skipped while
		 * processing
		 */
		Set<Node<T>> visitedNodes = new HashSet<>();

		/*
		 * A map of a target node to the path from the source node to this
		 * target node
		 */
		Map<Node<T>, List<Node<T>>> pathMap = new HashMap<Node<T>, List<Node<T>>>();

		/* A class to keep the minimum cost of reaching a node */
		class NodeDistance implements Comparable<NodeDistance> {
			private final Node<T> node;
			private int distance = Integer.MAX_VALUE;

			private NodeDistance(Node<T> node, int distance) {
				this.node = node;
				this.distance = distance;
			}

			@Override
			public int compareTo(NodeDistance o) {
				return Integer.valueOf(distance).compareTo(
						Integer.valueOf(o.distance));
			}

			@Override
			public String toString() {
				return node + "[" + distance + "]";
			}

		}

		/* The node processing queue */
		List<NodeDistance> nodeList = new ArrayList<>();

		/* The start node is trivially at 0 cost to itself */
		NodeDistance nodeDistance = new NodeDistance(startNode, 0);

		/* Enqueue it for processing */
		nodeList.add(nodeDistance);

		/* Loop while we have nodes to process */
		while (nodeList.size() > 0) {

			/* Pick the first node for processing */
			NodeDistance currentNodeDistance = nodeList.remove(0);
			Node<T> currentNode = currentNodeDistance.node;
			/* Get all its neighbors */
			Set<Node<T>> neighbours = graph.getNeighbours(currentNode);

			/* Iterate over the neighbors to process them */
			for (Node<T> neighbour : neighbours) {

				/* Continue to next one if this neighbor is already processed */
				if (visitedNodes.contains(neighbour)) {
					continue;
				}

				/*
				 * If there a multiple edges to a neighbor, get the one with
				 * minimum cost
				 */
				Set<Edge<T>> edgesToNeighbour = graph.getEdges(currentNode,
						neighbour);
				int minEdgeWeight = Integer.MAX_VALUE;
				for (Edge<T> edge : edgesToNeighbour) {
					if (edge.getWeight().intValue() < minEdgeWeight) {
						minEdgeWeight = edge.getWeight().intValue();
					}
				}

				/*
				 * Check if this neighbor is already present in the processing
				 * queue. If yes, get its index.
				 */
				int neighbourIndex = -1;
				for (NodeDistance nd : nodeList) {
					if (neighbour.equals(nd.node)) {
						neighbourIndex = nodeList.indexOf(nd);
						break;
					}
				}

				/*
				 * If the neighbor was not already present in the processing
				 * queue, add it.
				 */
				if (neighbourIndex == -1) {
					NodeDistance neighbourDistance = new NodeDistance(
							neighbour, currentNodeDistance.distance
									+ minEdgeWeight);
					nodeList.add(neighbourDistance);
					List<Node<T>> path = pathMap.containsKey(currentNode) ? pathMap
							.get(currentNode) : new ArrayList<Node<T>>();

					path.add(neighbour);
					pathMap.put(neighbour, path);
				} /*
				 * The neighbor was already present in the list. If we have a
				 * lesser cost via the current node to this neighbor than what
				 * the neighbor already has, update it with this lesser cost.
				 */else {
					if (nodeList.get(neighbourIndex).distance > currentNodeDistance.distance
							+ minEdgeWeight) {
						nodeList.get(neighbourIndex).distance = currentNodeDistance.distance
								+ minEdgeWeight;
						pathMap.get(neighbour).add(
								nodeList.get(neighbourIndex).node);
					}
				}
			}
			/*
			 * Current node is processed as all its neighbors have been
			 * considered. Add it to processed nodes list.
			 */
			visitedNodes.add(currentNode);
			System.out.println("Node:" + currentNode + " Minimum Cost:"
					+ currentNodeDistance.distance);
			/*
			 * Sort the processing queue to bring the minimum distance node to
			 * the front
			 */
			Collections.sort(nodeList);
		}
		System.out.println(nodeList);
		System.out.println(pathMap);
		return null;
	}
}
