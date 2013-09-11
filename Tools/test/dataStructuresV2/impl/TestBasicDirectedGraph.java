package dataStructuresV2.impl;

import dataStructuresV2.DirectedGraph;
import dataStructuresV2.Node;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.NodeFactory;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


public class TestBasicDirectedGraph
{
    @Test
    public void testReverseEdges()
    {
        try
        {
            int[][] dir_adj1 = new int[][]
                {
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE }
                };

            int[][] expected_reversed_graph = new int[][]
                {
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE }
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

            
            DirectedGraph<String> graph = GraphFactory.getDirectedGraph(dir_adj1, new String[] { string1, string2, string3, string4, string5, string6 }, BasicDirectedGraph.class);
            DirectedGraph<String> workingGraph = GraphFactory.getDirectedGraph(dir_adj1, new String[] { string1, string2, string3, string4, string5, string6 }, BasicDirectedGraph.class);
            DirectedGraph<String> expectedGraph = GraphFactory.getDirectedGraph(expected_reversed_graph, new String[] { string1, string2, string3, string4, string5, string6 }, BasicDirectedGraph.class);
            Assert.assertEquals(graph, workingGraph);            
            workingGraph.reverseEdges();
            Assert.assertEquals(expectedGraph, workingGraph);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Assert.fail();
        }
    }
}
