package demo;

import java.util.Iterator;

import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.GraphUtils;

public class Main {
	public static void main(String[] args) throws Exception {

		// Set<String> valueSet = new HashSet<>();
		// valueSet.add("a");
		// valueSet.add("b");
		// valueSet.add("c");
		// valueSet.add("d");
		// valueSet.add("e");
		// valueSet.add("f");
		// valueSet.add("g");
		// valueSet.add("h");
		// Graph<String> graph = GraphFactory.getSimpleGraph(valueSet, 8);

		// Graph<String> graph = GraphFactory.getGraph(new int[][] {
		// { 0, 1, 1, 1 }, { 1, 0, 1, Integer.MAX_VALUE }, { 1, 1, 0, 1 },
		// { 1, Integer.MAX_VALUE, 1, 0 } }, new String[] { "a", "b", "c",
		// "d" }, false);

		Graph<String> graph = GraphFactory.getGraph(new int[][] {
				{ Integer.MAX_VALUE, 1, 3 }, { 1, Integer.MAX_VALUE, 1 },
				{ 3, 1, Integer.MAX_VALUE } }, new String[] { "a", "b", "c", },
				false);

		System.out.println(graph.getDiagram());

		Iterator<Node<String>> itr = graph.getNodes().iterator();
		itr.next();
		Node<String> node1 = itr.next();
		GraphUtils.getDistances(graph, node1);

	}
}
