package dataStructuresV2.impl;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;
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
		int[][] adjMatrix = new int[0][0];
		String[] values = new String[] { "1", "2", "3", "4" };
		boolean directed = false;

		try {
			GraphFactory.getGraph(adjMatrix, values, directed);
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertEquals(InvalidDataException.Code.INVALID,
					((InvalidDataException) ex).code);
		}

		adjMatrix = new int[3][2];

		try {
			GraphFactory.getGraph(adjMatrix, values, directed);
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertEquals(InvalidDataException.Code.INVALID,
					((InvalidDataException) ex).code);
		}

		adjMatrix = new int[3][3];

		try {
			GraphFactory.getGraph(adjMatrix, values, directed);
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertEquals(InvalidDataException.Code.INVALID,
					((InvalidDataException) ex).code);
		}

		adjMatrix = new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		values = new String[] { "1", "2", "3", };
		Set<Node> expected = Utils.createNodes(values);
		try {
			Graph graph = GraphFactory.getGraph(adjMatrix, values, directed);
			Assert.assertEquals(3, graph.getEdges().size());
			Assert.assertEquals(expected, graph.getNodes());

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}

		adjMatrix = new int[][] { { 0, 0, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		try {
			GraphFactory.getGraph(adjMatrix, values, directed);
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertEquals(InvalidDataException.Code.INVALID,
					((InvalidDataException) ex).code);
		}

		adjMatrix = new int[][] { { 0, 0, 1 }, { 1, 1 }, { 1, 1, 1 } };
		try {
			GraphFactory.getGraph(adjMatrix, values, directed);
			Assert.fail();
		} catch (Exception ex) {
			Assert.assertEquals(InvalidDataException.Code.INVALID,
					((InvalidDataException) ex).code);
		}

		adjMatrix = new int[][] { { Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
				{ Integer.MAX_VALUE, Integer.MAX_VALUE, 1 },
				{ 1, Integer.MAX_VALUE, Integer.MAX_VALUE } };
		directed = true;
		try {
			Graph graph = GraphFactory.getGraph(adjMatrix, values, directed);
			Assert.assertEquals(3, graph.getEdges().size());
			Edge edge1 = Utils.createEdge("1", "2", 1, true);
			Assert.assertEquals(true, graph.getEdges().contains(edge1));
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}

		adjMatrix = new int[][] { { 0, 0, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		directed = true;
		try {
			Graph graph = GraphFactory.getGraph(adjMatrix, values, directed);
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}

	}

	/*
	 * 1.Failure on passing more than (n-1)/2 edges
	 * 
	 * 2.Validate that all passed values get added as nodes
	 * 
	 * 3.Validate that the number of edges in the graph is the same as the
	 * number of edges passed.
	 * 
	 * Edge tests:
	 * 
	 * 1.Passing empty values set.
	 * 
	 * 2.Passing negative/0 edge number
	 * 
	 * 3.
	 */

	@Test
	public void testGetSimpleGraph() {
		try {
			GraphFactory.getSimpleGraph(new HashSet<Object>(), 0);
			GraphFactory.getSimpleGraph(new HashSet<Object>(), -1);

			Set<String> values = new HashSet<String>();
			values.add("a");
			try {
				GraphFactory.getSimpleGraph(values, 2);
				Assert.fail();
			} catch (InvalidDataException e) {
				Assert.assertEquals(InvalidDataException.Code.INVALID, e.code);
			}

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

			Graph<String> graph = GraphFactory.getSimpleGraph(strings, 5);
			Assert.assertEquals(5, graph.getEdges().size());
			Assert.assertEquals(expected, graph.getNodes());

		} catch (InvalidDataException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
