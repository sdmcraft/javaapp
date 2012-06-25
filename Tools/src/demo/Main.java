package demo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.GraphUtils;

public class Main {
	public static void main(String[] args) throws Exception {
		Set<String> valueSet = new HashSet<>();
		valueSet.add("a");
		valueSet.add("b");
		valueSet.add("c");
		valueSet.add("d");
		Graph<String> graph = GraphFactory.getSimpleGraph(valueSet, 5);
		System.out.println(graph.getDiagram());
		Iterator<Node<String>> itr = graph.getNodes().iterator(); 
		Node<String> node1 = itr.next();
		Node<String> node2 = itr.next();
		GraphUtils.shortestDistance(graph, node1, node2);		
	}
}
