package dataStructuresV2.impl;

import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.GraphUtils;
import dataStructuresV2.utils.GraphUtils.NodeDistance;
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
    public void testGetDistances()
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

        Map<Node<String>, List<Node<String>>> expectedPathMap = new HashMap<Node<String>, List<Node<String>>>();
        List<Node<String>> expectedPath1 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath2 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath3 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath4 = new ArrayList<Node<String>>();
        List<Node<String>> expectedPath5 = new ArrayList<Node<String>>();
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
            
        }
        catch (InvalidDataException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail();
        }
    }
}
