package demo;

import java.util.HashSet;
import java.util.Set;

import dataStructuresV2.Graph;
import dataStructuresV2.exception.InvalidDataException;
import dataStructuresV2.utils.GraphFactory;

public class Main {
	public static void main(String[] args) throws InvalidDataException {
		Set<String> valueSet = new HashSet<>();
		valueSet.add("a");
		valueSet.add("b");
		valueSet.add("c");
		valueSet.add("d");
		Graph<String> graph = GraphFactory.getSimpleGraph(valueSet, 5);
		System.out.println(graph.getDiagram());
		
	}
}
