package dataStructuresV2.impl;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.NodeFactory;

public class TestGraphFactory {

	@Test
	public void testGetGraph() {
		try {
			String string1 = "a";
			String string2 = "b";
			String string3 = "c";
			String string4 = "d";
			String string5 = "e";

			Set<String> strings = new HashSet<String>();
			strings.add(string1);
			strings.add(string2);
			strings.add(string3);
			strings.add(string4);
			strings.add(string5);

			Node node1 = NodeFactory.getNode(string1);
			Node node2 = NodeFactory.getNode(string2);
			Node node3 = NodeFactory.getNode(string3);
			Node node4 = NodeFactory.getNode(string4);
			Node node5 = NodeFactory.getNode(string5);

			Set<Node> expected = new HashSet<Node>();
			expected.add(node1);
			expected.add(node2);
			expected.add(node3);
			expected.add(node4);
			expected.add(node5);

			Graph<String> graph = GraphFactory.getGraph(strings, 5);
			Assert.assertEquals(5, graph.getEdges().size());
			Assert.assertEquals(expected, graph.getNodes());
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testGetGraphWithAdjMatrix() {
		int[][] adjMatrix = new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		String[] values = new String[] { "1", "2", "3" };
		boolean directed = false;
		try {
			GraphFactory.getGraph(adjMatrix, values, directed);
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

}
