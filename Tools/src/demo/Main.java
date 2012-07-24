package demo;

import java.util.Iterator;

import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.GraphUtils;

public class Main {
	public static void main(String[] args) throws Exception {
		/*
		 * Set<String> valueSet = new HashSet<>(); valueSet.add("a");
		 * valueSet.add("b"); valueSet.add("c"); valueSet.add("d");
		 * Graph<String> graph = GraphFactory.getSimpleGraph(valueSet, 5);
		 */
		Graph<String> graph = GraphFactory.getGraph(new int[][] {
				{ Integer.MAX_VALUE, 1, 1, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
						Integer.MAX_VALUE },
				{ 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE } }, new String[] {
				"a", "b", "c", "d" }, false);
		System.out.println(graph.getDiagram());

		Iterator<Node<String>> itr = graph.getNodes().iterator();
		Node<String> node1 = itr.next();
		Node<String> node2 = itr.next();
		GraphUtils.shortestDistance(graph, node1, node2);

	}
}
