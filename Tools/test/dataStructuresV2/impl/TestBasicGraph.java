package dataStructuresV2.impl;

import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import dataStructuresV2.Node;

public class TestBasicGraph {

	@BeforeClass
	public static void setup() {

	}

	@Test
	public void testConstructor() {
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
}
