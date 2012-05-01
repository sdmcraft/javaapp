package dataStructuresV2.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;

public class GraphUtils {

	public final <T> List<Node<T>> shortestDistance(Graph<T> graph,
			Node<T> startNode, Node<T> endNode) throws Exception{
		Set<Node<T>> visitedNodes = new HashSet<>();
		

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

		List<NodeDistance> nodeList = new ArrayList<>();
		NodeDistance nodeDistance = new NodeDistance(startNode, 0);
		nodeList.add(nodeDistance);
		while(nodeList.size() > 0)
		{
			Node<T> node = nodeList.remove(0).node;
			Set<Node<T>> neighbours = graph.getNeighbours(node);
			NodeDistance neighbourDistance = null;
			for(Node<T> neighbour : neighbours)
			{
				int neighbourIndex = nodeList.indexOf(neighbour);
				if(neighbourIndex == -1)
				{
					Set<Edge<T>> edgesToNeighbour = graph.getEdges(node, neighbour);
					int minEdgeWeight = Integer.MAX_VALUE;					
					for(Edge<T> edge : edgesToNeighbour)
					{
						if(edge.getWeight().intValue() < minEdgeWeight)
						{
							minEdgeWeight = edge.getWeight().intValue();							
						}
					}
					neighbourDistance = new NodeDistance(neighbour, minEdgeWeight);
				}
				else
				{
					// Add code here 01-May-2012
				}
						
			}
		}

		return null;
	}
}
