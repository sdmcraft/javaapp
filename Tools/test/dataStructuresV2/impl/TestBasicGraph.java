package dataStructuresV2.impl;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import dataStructuresV2.Edge;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;

@RunWith(PowerMockRunner.class)
public class TestBasicGraph {

	@BeforeClass
	public static void setup() {

	}

	@Test
	public void testGetEdges() {
		BasicGraph<String> basicGraph = new BasicGraph<String>();

		// ////////////////////////////////////////////
		Node<String> mockNode = Utils.createMockNodes(1).iterator().next();
		try {
			basicGraph.getEdges(mockNode);
			Assert.fail();
		} catch (InvalidDataException e) {
			// Expected
		}
		//////////////////////////////////////////////
		Set<Node> mockNodes = Utils.createMockNodes(5);
		try {
			Utils.setField(basicGraph, BasicGraph.class, "nodes", mockNodes);
			Set<Edge<String>> edges = basicGraph.getEdges(mockNodes.iterator()
					.next());
			Assert.assertEquals(0, edges.size());
		} catch (Exception e) {
			Assert.fail();
		}
		//////////////////////////////////////////////
		Node<String> mockNode1 = Utils.createMockNodes(1).iterator().next();
		Node<String> mockNode2 = Utils.createMockNodes(1).iterator().next();
		Node<String> mockNode3 = Utils.createMockNodes(1).iterator().next();

		mockNodes = new HashSet();
		mockNodes.add(mockNode1);
		mockNodes.add(mockNode2);
		mockNodes.add(mockNode3);
		
		Edge mockEdge = Utils.createMockEdge(mockNode1, mockNode2);

		Set mockEdges = new HashSet();
		mockEdges.add(mockEdge);

		try {
			Utils.setField(basicGraph, BasicGraph.class, "nodes", mockNodes);
			Utils.setField(basicGraph, BasicGraph.class, "edges", mockEdges);
			Set<Edge<String>> edges = basicGraph.getEdges(mockNode1);
			Assert.assertEquals(1, edges.size());
			Assert.assertEquals(mockEdge, edges.iterator().next());

			edges = basicGraph.getEdges(mockNode2);
			Assert.assertEquals(1, edges.size());
			Assert.assertEquals(mockEdge, edges.iterator().next());

			edges = basicGraph.getEdges(mockNode3);
			Assert.assertEquals(0, edges.size());			

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		//////////////////////////////////////////////
	}
}
