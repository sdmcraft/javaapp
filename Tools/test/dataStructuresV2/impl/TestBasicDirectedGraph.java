package dataStructuresV2.impl;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.NodeFactory;

public class TestBasicDirectedGraph {

	@Test
	public void testReverseEdges()
	{
		int[][] dir_adj1 = new int[][]
                {
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE }
                };
		
        String string1 = "a";
        String string2 = "b";
        String string3 = "c";
        String string4 = "d";
        String string5 = "e";
        String string6 = "f";

        Set<String> strings = new HashSet<String>();
        strings.add(string1);
        strings.add(string2);
        strings.add(string3);
        strings.add(string4);
        strings.add(string5);
        strings.add(string6);

        Node node1 = NodeFactory.getNode(string1);
        Node node2 = NodeFactory.getNode(string2);
        Node node3 = NodeFactory.getNode(string3);
        Node node4 = NodeFactory.getNode(string4);
        Node node5 = NodeFactory.getNode(string5);
        Node node6 = NodeFactory.getNode(string6);

        Set<Node<String>> expected = new HashSet<Node<String>>();
        expected.add(node1);
        expected.add(node2);
        expected.add(node3);
        expected.add(node4);
        expected.add(node5);
        expected.add(node6);

        //Graph<String> graph = GraphFactory.getGraph(dir_adj1, new String[]{string1, string2, string3, string4, string5, string6}, true, )
	}
}
