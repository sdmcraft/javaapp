package dataStructuresV2.impl;

import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.GraphUtils;
import dataStructuresV2.utils.NodeFactory;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class TestGraphUtil
{
    /*
     * NodeDistance comparison must compare the distances.
     */
    @Test
    public void testGetDistances_undirected()
    {
        int[][] adj2 = new int[][]
            {
                { 1 }
            };
        int[][] adj3 = new int[][]
            {
                { Integer.MAX_VALUE, 1 },
                { 1, Integer.MAX_VALUE }
            };
        int[][] adj4 = new int[][]
            {
                { 1, 1 },
                { 1, Integer.MAX_VALUE }
            };
        int[][] adj5 = new int[][]
            {
                { Integer.MAX_VALUE, 1, 5 },
                { 1, Integer.MAX_VALUE, 2 },
                { 5, 2, Integer.MAX_VALUE }
            };

        int[][] adj6 = new int[][]
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

        Node node1 = NodeFactory.getNode(string1);
        Node node2 = NodeFactory.getNode(string2);
        Node node3 = NodeFactory.getNode(string3);
        Node node4 = NodeFactory.getNode(string4);
        Node node5 = NodeFactory.getNode(string5);
        Node node6 = NodeFactory.getNode(string6);

        Map<Node<String>, List<Node<String>>> expectedPathMap = new HashMap<Node<String>, List<Node<String>>>();
        List<Node<String>> expectedPath1 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath2 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath3 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath4 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath5 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath6 = new ArrayList<Node<String>>();
        Map<Node<String>, List<Node<String>>> pathMap = new HashMap<Node<String>, List<Node<String>>>();

        try
        {
            //adj2
            expectedPathMap.clear();
            expectedPathMap.put(node1, expectedPath1);

            Graph<String> graph = GraphFactory.getGraph(adj2, new String[] { string1 }, false);
            GraphUtils.getDistances(graph, node1, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);

            //adj3
            expectedPathMap.clear();
            expectedPath1.clear();
            expectedPathMap.put(node1, expectedPath1);

            expectedPath2.clear();
            expectedPath2.add(node2);
            expectedPathMap.put(node2, expectedPath2);

            graph = GraphFactory.getGraph(adj3, new String[] { string1, string2 }, false);
            GraphUtils.getDistances(graph, node1, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);

            //adj4
            expectedPathMap.clear();
            expectedPath1.clear();
            expectedPathMap.put(node1, expectedPath1);

            expectedPath2.clear();
            expectedPath2.add(node2);
            expectedPathMap.put(node2, expectedPath2);

            graph = GraphFactory.getGraph(adj4, new String[] { string1, string2 }, false);
            GraphUtils.getDistances(graph, node1, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);

            //adj5
            expectedPathMap.clear();
            expectedPath1.clear();
            expectedPathMap.put(node1, expectedPath1);

            expectedPath2.clear();
            expectedPath2.add(node2);
            expectedPathMap.put(node2, expectedPath2);

            expectedPath3.clear();
            expectedPath3.add(node2);
            expectedPath3.add(node3);
            expectedPathMap.put(node3, expectedPath3);

            graph = GraphFactory.getGraph(adj5, new String[] { string1, string2, string3 }, false);
            GraphUtils.getDistances(graph, node1, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);

            expectedPathMap.clear();
            expectedPath1.clear();
            expectedPath1.add(node1);
            expectedPathMap.put(node1, expectedPath1);

            expectedPath2.clear();
            expectedPathMap.put(node2, expectedPath2);

            expectedPath3.clear();
            expectedPath3.add(node3);
            expectedPathMap.put(node3, expectedPath3);

            graph = GraphFactory.getGraph(adj5, new String[] { string1, string2, string3 }, false);
            GraphUtils.getDistances(graph, node2, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);

            //adj6
            expectedPathMap.clear();
            expectedPath1.clear();
            expectedPathMap.put(node1, expectedPath1);

            expectedPath2.clear();
            expectedPath2.add(node2);
            expectedPathMap.put(node2, expectedPath2);

            expectedPath3.clear();
            expectedPath3.add(node2);
            expectedPath3.add(node3);
            expectedPathMap.put(node3, expectedPath3);

            expectedPath4.clear();
            expectedPath4.add(node4);
            expectedPathMap.put(node4, expectedPath4);

            expectedPath5.clear();
            expectedPath5.add(node2);
            expectedPath5.add(node5);
            expectedPathMap.put(node5, expectedPath5);

            expectedPath6.clear();
            expectedPath6.add(node6);
            expectedPathMap.put(node6, expectedPath6);

            graph = GraphFactory.getGraph(adj6, new String[] { string1, string2, string3, string4, string5, string6 }, false);
            GraphUtils.getDistances(graph, node1, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);
        }
        catch (InvalidDataException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testGetDistances_directed()
    {
        int[][] adj2 = new int[][]
            {
                { 1 }
            };
        int[][] adj3 = new int[][]
            {
                { Integer.MAX_VALUE, 1 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE }
            };
        int[][] adj4 = new int[][]
            {
                { 1, 1 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE }
            };
        int[][] adj5 = new int[][]
            {
                { Integer.MAX_VALUE, 1, 5 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, 2 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE }
            };

        int[][] adj6 = new int[][]
            {
                { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1 },
                { 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE }
            };
        
        int[][] adj7 = new int[][]
                {
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE }
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

        Node node1 = NodeFactory.getNode(string1);
        Node node2 = NodeFactory.getNode(string2);
        Node node3 = NodeFactory.getNode(string3);
        Node node4 = NodeFactory.getNode(string4);
        Node node5 = NodeFactory.getNode(string5);
        Node node6 = NodeFactory.getNode(string6);

        Map<Node<String>, List<Node<String>>> expectedPathMap = new HashMap<Node<String>, List<Node<String>>>();
        List<Node<String>> expectedPath1 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath2 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath3 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath4 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath5 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath6 = new ArrayList<Node<String>>();
        Map<Node<String>, List<Node<String>>> pathMap = new HashMap<Node<String>, List<Node<String>>>();

        try
        {
            //adj2
            expectedPathMap.clear();
            expectedPathMap.put(node1, expectedPath1);

            Graph<String> graph = GraphFactory.getGraph(adj2, new String[] { string1 }, true);
            GraphUtils.getDistances(graph, node1, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);

            //adj3
            expectedPathMap.clear();
            expectedPath1.clear();
            expectedPathMap.put(node1, expectedPath1);

            expectedPath2.clear();
            expectedPath2.add(node2);
            expectedPathMap.put(node2, expectedPath2);

            graph = GraphFactory.getGraph(adj3, new String[] { string1, string2 }, true);
            GraphUtils.getDistances(graph, node1, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);

            //adj4
            expectedPathMap.clear();
            expectedPath1.clear();
            expectedPathMap.put(node1, expectedPath1);

            expectedPath2.clear();
            expectedPath2.add(node2);
            expectedPathMap.put(node2, expectedPath2);

            graph = GraphFactory.getGraph(adj4, new String[] { string1, string2 }, true);
            GraphUtils.getDistances(graph, node1, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);

            //adj5
            expectedPathMap.clear();
            expectedPath1.clear();
            expectedPathMap.put(node1, expectedPath1);

            expectedPath2.clear();
            expectedPath2.add(node2);
            expectedPathMap.put(node2, expectedPath2);

            expectedPath3.clear();
            expectedPath3.add(node2);
            expectedPath3.add(node3);
            expectedPathMap.put(node3, expectedPath3);

            graph = GraphFactory.getGraph(adj5, new String[] { string1, string2, string3 }, true);
            GraphUtils.getDistances(graph, node1, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);

            expectedPathMap.clear();

            expectedPath2.clear();
            expectedPathMap.put(node2, expectedPath2);

            expectedPath3.clear();
            expectedPath3.add(node3);
            expectedPathMap.put(node3, expectedPath3);

            graph = GraphFactory.getGraph(adj5, new String[] { string1, string2, string3 }, true);
            GraphUtils.getDistances(graph, node2, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);

            //adj6
            expectedPathMap.clear();
            expectedPath1.clear();
            expectedPathMap.put(node1, expectedPath1);

            expectedPath2.clear();
            expectedPath2.add(node2);
            expectedPathMap.put(node2, expectedPath2);

            expectedPath3.clear();
            expectedPath3.add(node2);
            expectedPath3.add(node3);
            expectedPathMap.put(node3, expectedPath3);

            expectedPath4.clear();
            expectedPath4.add(node4);
            expectedPathMap.put(node4, expectedPath4);

            expectedPath5.clear();
            expectedPath5.add(node2);
            expectedPath5.add(node5);
            expectedPathMap.put(node5, expectedPath5);

            expectedPath6.clear();
            expectedPath6.add(node6);
            expectedPathMap.put(node6, expectedPath6);

            graph = GraphFactory.getGraph(adj6, new String[] { string1, string2, string3, string4, string5, string6 }, true);
            GraphUtils.getDistances(graph, node1, pathMap);
            Assert.assertEquals(expectedPathMap, pathMap);
            
            //adj7
            expectedPathMap.clear();
            expectedPath1.clear();
            expectedPathMap.put(node1, expectedPath1);

            expectedPath2.clear();
            expectedPath2.add(node2);
            expectedPathMap.put(node2, expectedPath2);

            expectedPath3.clear();
            expectedPath3.add(node2);
            expectedPath3.add(node3);
            expectedPathMap.put(node3, expectedPath3);

            expectedPath4.clear();
            expectedPath4.add(node2);
            expectedPath4.add(node3);
            expectedPath4.add(node4);
            expectedPathMap.put(node4, expectedPath4);

            expectedPath5.clear();
            expectedPath5.add(node2);
            expectedPath5.add(node3);
            expectedPath5.add(node4);
            expectedPath5.add(node5);
            expectedPathMap.put(node5, expectedPath5);

            expectedPath6.clear();
            expectedPath6.add(node2);
            expectedPath6.add(node3);
            expectedPath6.add(node4);
            expectedPath6.add(node5);
            expectedPath6.add(node6);
            expectedPathMap.put(node6, expectedPath6);

            graph = GraphFactory.getGraph(adj7, new String[] { string1, string2, string3, string4, string5, string6 }, true);            
            GraphUtils.getDistances(graph, node1, pathMap);
            System.out.println(graph.getDiagram());
            Assert.assertEquals(expectedPathMap, pathMap);

        }
        catch (InvalidDataException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail();
        }
    }
}
