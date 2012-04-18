package dataStructuresV2.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import dataStructuresV2.Graph;
import dataStructuresV2.Node;

public class GraphUtils {

	public final  <T> List<Node<T>> shortestDistance(Graph<T> graph, Node<T> startNode, Node<T> endNode)
	{
		Set<Node<T>> unvisitedNodes = graph.getNodes();
		Map<Node<T>,Integer> distanceMap = new HashMap<>();
		return null;
	}
}
