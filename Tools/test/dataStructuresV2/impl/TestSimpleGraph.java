package dataStructuresV2.impl;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.NodeFactory;

public class TestSimpleGraph {

	@Test
	public void testCanAdd_undirected() {
        try {

		int[][] adj = new int[][] { { Integer.MAX_VALUE, 1, 1 },
				{ 1, Integer.MAX_VALUE, 1 }, { 1, 1, Integer.MAX_VALUE } };
		
        String string1 = "a";
        String string2 = "b";
        String string3 = "c";

        Set<String> strings = new HashSet<String>();
        strings.add(string1);
        strings.add(string2);
        strings.add(string3);

        Node node1 = NodeFactory.getNode(string1);
        Node node2 = NodeFactory.getNode(string2);
        Node node3 = NodeFactory.getNode(string3);

			GraphFactory.getSimpleGraph(strings, 3);
		} catch (InvalidDataException e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

}
