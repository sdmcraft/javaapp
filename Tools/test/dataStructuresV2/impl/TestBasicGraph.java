package dataStructuresV2.impl;

import java.util.Set;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import dataStructuresV2.Edge;
import dataStructuresV2.Node;

@RunWith(PowerMockRunner.class)
public class TestBasicGraph {

	@BeforeClass
	public static void setup() {

	}

	@Test
	public void test1argConstructor() {
		Set<Node> mockNodeSet = Utils.createMockNodes(10);
		try {
			BasicGraph basicGraph = new BasicGraph(mockNodeSet);
			Set<Node> actualNodeSet = (Set<Node>) Utils.getField(basicGraph,
					BasicGraph.class, "nodes");
			Assert.assertEquals(mockNodeSet, actualNodeSet);

			Set<Node> mockNodeSet2 = Utils.createMockNodes(10);
			Assert.assertNotSame(mockNodeSet2, actualNodeSet);

			mockNodeSet.add(Utils.createMockNodes(1).iterator().next());
			Assert.assertNotSame(mockNodeSet, actualNodeSet);

		} catch (Exception ex) {
			Assert.fail();
		}
	}

	@Test
	@PrepareForTest(BasicGraph.class)
	public void test2argConstructor() {

		try {
			Set<Node> mockNodeSet = Utils.createMockNodes(10);
			Set<Edge> mockEdgeSet = Utils.createMockEdges(mockNodeSet);
			BasicGraph partialBasicGraph = PowerMock.createPartialMock(
					BasicGraph.class, new String[] { "addNode", "addEdge" },
					mockNodeSet, mockEdgeSet);
			partialBasicGraph.addNode(EasyMock.isA(Node.class));
			EasyMock.expectLastCall().anyTimes();
			
			partialBasicGraph.addEdge(EasyMock.isA(Edge.class));
			EasyMock.expectLastCall().anyTimes();
			
			PowerMock.replay(partialBasicGraph);

			// BasicGraph basicGraph = new BasicGraph(mockNodeSet, mockEdgeSet);
			// Set<Node> actualNodeSet = (Set<Node>) Utils.getField(basicGraph,
			// BasicGraph.class, "nodes");
			// Set<Edge> actualEdgeSet = (Set<Edge>) Utils.getField(basicGraph,
			// BasicGraph.class, "edges");
			//
			// Assert.assertEquals(mockNodeSet, actualNodeSet);
			//
			// Set<Node> mockNodeSet2 = Utils.createMockNodes(10);
			// Assert.assertNotSame(mockNodeSet2, actualNodeSet);
			//
			// mockNodeSet.add(Utils.createMockNodes(1).iterator().next());
			// Assert.assertNotSame(mockNodeSet, actualNodeSet);
			//
		} catch (Exception ex) {
			Assert.fail();
		}
	}

}
