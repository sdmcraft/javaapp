/*
 * Copyright (c) 2002, Marco Hunsicker. All rights reserved.
 *
 * The contents of this file are subject to the Common Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://jalopy.sf.net/license-cpl.html
 *
 * Copyright (c) 2001-2002 Marco Hunsicker
 */
package dataStructuresV2.impl;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;

import dataStructuresV2.exception.InvalidDataException;

import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.NodeFactory;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
 */
@RunWith(PowerMockRunner.class)
public class TestBasicGraph
{
    //~ Methods --------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    @BeforeClass
    public static void setup()
    {
    }


    /**
     * DOCUMENT ME!
     */
    @Test
    public void testAddEdge()
    {
        Node<String> mockNode1 = Utils.createMockNodes(1).iterator().next();
        Node<String> mockNode2 = Utils.createMockNodes(1).iterator().next();
        Edge mockEdge1 = Utils.createMockEdge(mockNode1, mockNode2);
        Set mockNodes = new HashSet();
        mockNodes.add(mockNode1);
        mockNodes.add(mockNode2);

        BasicGraph basicGraph = new BasicGraph();

        try
        {
            basicGraph.addEdge(mockEdge1);
            Assert.fail();
        }
        catch (InvalidDataException e)
        {
            // expected
        }

        try
        {
            Utils.setField(basicGraph, BasicGraph.class, "nodes", mockNodes);
            basicGraph.addEdge(mockEdge1);

            Set edges = (Set) Utils.getField(basicGraph, BasicGraph.class, "edges");
            Assert.assertEquals(1, edges.size());
            Assert.assertEquals(mockEdge1, edges.iterator().next());
        }
        catch (Exception ex)
        {
            Assert.fail();
        }

        try
        {
            basicGraph.addEdge(mockEdge1);
            Assert.fail();
        }
        catch (InvalidDataException e)
        {
            // expected
        }
    }


    /**
     * DOCUMENT ME!
     */
    @Test
    public void testAddNode()
    {
        Node<String> mockNode1 = Utils.createMockNodes(1).iterator().next();
        Node<String> mockNode2 = Utils.createMockNodes(1).iterator().next();
        Set mockNodes = new HashSet();
        mockNodes.add(mockNode1);
        mockNodes.add(mockNode2);

        BasicGraph basicGraph = new BasicGraph();

        try
        {
            basicGraph.addNode(mockNode1);
            basicGraph.addNode(mockNode2);
        }
        catch (Exception ex)
        {
            Assert.fail();
        }

        try
        {
            basicGraph.addNode(mockNode1);
            Assert.fail();
        }
        catch (InvalidDataException ex)
        {
            // Expected
        }
    }


    /**
     * DOCUMENT ME!
     */
    @Test
    public void testBreadthFirstTraversal()
    {
        try
        {
            //----------Undirected Graphs---------//
            int[][] adj1 =
                new int[][]
                {
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE }
                };

            int[][] adj2 =
                new int[][]
                {
                    {
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, 1
                    },
                    {
                        1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE, 1
                    },
                    {
                        1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE
                    }
                };

            int[][] adj3 =
                new int[][]
                {
                    {
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, Integer.MAX_VALUE
                    },
                    {
                        1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE, 1
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE
                    }
                };

            int[][] adj4 =
                new int[][]
                {
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE, 1
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE
                    }
                };

            //----------Directed Graphs---------//
            int[][] dir_adj1 =
                new int[][]
                {
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE }
                };

            int[][] dir_adj2 =
                new int[][]
                {
                    {
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, 1
                    },
                    {
                        1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE, 1
                    },
                    {
                        1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE
                    }
                };

            int[][] dir_adj3 =
                new int[][]
                {
                    {
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, Integer.MAX_VALUE
                    },
                    {
                        1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE, 1
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE
                    }
                };

            int[][] dir_adj4 =
                new int[][]
                {
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1,
                        Integer.MAX_VALUE, 1
                    },
                    {
                        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                        Integer.MAX_VALUE, 1, Integer.MAX_VALUE
                    }
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

            //----------Undirected Graph - Success cases----------//
            Graph<String> graph =
                GraphFactory.getGraph(
                    adj1,
                    new String[] { string1, string2, string3, string4, string5, string6 },
                    false, BasicGraph.class);

            Set<Node> actual = new HashSet<Node>();
            actual.addAll(
                (List<Node>) Utils.invoke(
                    graph, "breadthFirstTraversal", graph.getNodes().iterator().next()));
            Assert.assertEquals(expected, actual);

            graph = GraphFactory.getGraph(
                    adj2,
                    new String[] { string1, string2, string3, string4, string5, string6 },
                    false, BasicGraph.class);
            actual.clear();
            actual.addAll(
                (List<Node>) Utils.invoke(
                    graph, "breadthFirstTraversal", graph.getNodes().iterator().next()));
            Assert.assertEquals(expected, actual);

            graph = GraphFactory.getGraph(
                    adj3,
                    new String[] { string1, string2, string3, string4, string5, string6 },
                    false, BasicGraph.class);

            actual.clear();
            actual.addAll(
                (List<Node>) Utils.invoke(
                    graph, "breadthFirstTraversal", graph.getNodes().iterator().next()));
            Assert.assertEquals(expected, actual);
            //----------Undirected Graph - Failure cases----------//
            graph = GraphFactory.getGraph(
                    adj4,
                    new String[] { string1, string2, string3, string4, string5, string6 },
                    false, BasicGraph.class);

            actual.clear();
            actual.addAll(
                (List<Node>) Utils.invoke(
                    graph, "breadthFirstTraversal", graph.getNodes().iterator().next()));
            Assert.assertNotSame(expected, actual);

            //----------Directed Graph - Success cases----------//
            graph = GraphFactory.getGraph(
                    adj1,
                    new String[] { string1, string2, string3, string4, string5, string6 },
                    true, BasicGraph.class);

            actual.clear();
            actual.addAll(
                (List<Node>) Utils.invoke(
                    graph, "breadthFirstTraversal", graph.getNodes().iterator().next()));
            Assert.assertEquals(expected, actual);

            graph = GraphFactory.getGraph(
                    adj2,
                    new String[] { string1, string2, string3, string4, string5, string6 },
                    true, BasicGraph.class);

            actual.clear();
            actual.addAll(
                (List<Node>) Utils.invoke(
                    graph, "breadthFirstTraversal", graph.getNodes().iterator().next()));
            Assert.assertEquals(expected, actual);

            graph = GraphFactory.getGraph(
                    adj3,
                    new String[] { string1, string2, string3, string4, string5, string6 },
                    true, BasicGraph.class);

            actual.clear();
            actual.addAll(
                (List<Node>) Utils.invoke(
                    graph, "breadthFirstTraversal", graph.getNodes().iterator().next()));
            Assert.assertEquals(expected, actual);
            //----------Directed Graph - Failure cases----------//
            graph = GraphFactory.getGraph(
                    adj4,
                    new String[] { string1, string2, string3, string4, string5, string6 },
                    true, BasicGraph.class);

            actual.clear();
            actual.addAll(
                (List<Node>) Utils.invoke(
                    graph, "breadthFirstTraversal", graph.getNodes().iterator().next()));
            Assert.assertNotSame(expected, actual);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }


    /**
     * DOCUMENT ME!
     */
    @Test
    public void testCanAdd()
    {
        Node<String> mockNode1 = Utils.createMockNodes(1).iterator().next();
        Node<String> mockNode2 = Utils.createMockNodes(1).iterator().next();
        Edge mockEdge1 = Utils.createMockEdge(mockNode1, mockNode2);
        Set mockNodes = new HashSet();
        mockNodes.add(mockNode1);
        mockNodes.add(mockNode2);

        BasicGraph basicGraph = new BasicGraph();

        Assert.assertEquals(false, basicGraph.canAdd(mockEdge1));

        try
        {
            Utils.setField(basicGraph, BasicGraph.class, "nodes", mockNodes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }

        Assert.assertEquals(true, basicGraph.canAdd(mockEdge1));
    }


    /**
     * DOCUMENT ME!
     */
    @Test
    public void testGetEdgesDoubleNode()
    {
        BasicGraph<String> basicGraph = new BasicGraph<String>();

        // ////////////////////////////////////////////
        Node<String> mockNode1 = Utils.createMockNodes(1).iterator().next();
        Node<String> mockNode2 = Utils.createMockNodes(1).iterator().next();

        try
        {
            basicGraph.getEdges(mockNode1, mockNode2);
            Assert.fail();
        }
        catch (InvalidDataException e)
        {
            // Expected
        }

        // ////////////////////////////////////////////
        Set<Node> mockNodes = Utils.createMockNodes(5);

        try
        {
            Utils.setField(basicGraph, BasicGraph.class, "nodes", mockNodes);

            Set<Edge<String>> edges =
                basicGraph.getEdges(
                    mockNodes.iterator().next(), mockNodes.iterator().next());
            Assert.assertEquals(0, edges.size());
        }
        catch (Exception e)
        {
            Assert.fail();
        }

        // ////////////////////////////////////////////
        mockNode1 = Utils.createMockNodes(1).iterator().next();
        mockNode2 = Utils.createMockNodes(1).iterator().next();

        Node<String> mockNode3 = Utils.createMockNodes(1).iterator().next();

        mockNodes = new HashSet();
        mockNodes.add(mockNode1);
        mockNodes.add(mockNode2);
        mockNodes.add(mockNode3);

        Edge mockEdge = Utils.createMockEdge(mockNode1, mockNode2);

        Set mockEdges = new HashSet();
        mockEdges.add(mockEdge);

        try
        {
            Utils.setField(basicGraph, BasicGraph.class, "nodes", mockNodes);
            Utils.setField(basicGraph, BasicGraph.class, "edges", mockEdges);

            Set<Edge<String>> edges = basicGraph.getEdges(mockNode1, mockNode1);
            Assert.assertEquals(1, edges.size());
            Assert.assertEquals(mockEdge, edges.iterator().next());

            edges = basicGraph.getEdges(mockNode2, mockNode1);
            Assert.assertEquals(1, edges.size());
            Assert.assertEquals(mockEdge, edges.iterator().next());

            edges = basicGraph.getEdges(mockNode3);
            Assert.assertEquals(0, edges.size());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }

        // ////////////////////////////////////////////
    }


    /**
     * DOCUMENT ME!
     */
    @Test
    public void testGetEdgesSingleNode()
    {
        BasicGraph<String> basicGraph = new BasicGraph<String>();

        // ////////////////////////////////////////////
        Node<String> mockNode = Utils.createMockNodes(1).iterator().next();

        try
        {
            basicGraph.getEdges(mockNode);
            Assert.fail();
        }
        catch (InvalidDataException e)
        {
            // Expected
        }

        // ////////////////////////////////////////////
        Set<Node> mockNodes = Utils.createMockNodes(5);

        try
        {
            Utils.setField(basicGraph, BasicGraph.class, "nodes", mockNodes);

            Set<Edge<String>> edges = basicGraph.getEdges(mockNodes.iterator().next());
            Assert.assertEquals(0, edges.size());
        }
        catch (Exception e)
        {
            Assert.fail();
        }

        // ////////////////////////////////////////////
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

        try
        {
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
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }

        // ////////////////////////////////////////////
    }


    /**
     * DOCUMENT ME!
     */
    @Test
    public void testGetNeighbours()
    {
        Node<String> mockNode1 = Utils.createMockNodes(1).iterator().next();
        Node<String> mockNode2 = Utils.createMockNodes(1).iterator().next();
        Node<String> mockNode3 = Utils.createMockNodes(1).iterator().next();
        Node<String> mockNode4 = Utils.createMockNodes(1).iterator().next();
        Set mockNodes = new HashSet();
        mockNodes = new HashSet();
        mockNodes.add(mockNode1);
        mockNodes.add(mockNode2);
        mockNodes.add(mockNode3);
        mockNodes.add(mockNode4);

        Edge mockEdge1 = Utils.createMockEdge(mockNode1, mockNode2);
        Edge mockEdge2 = Utils.createMockEdge(mockNode2, mockNode3);

        Set mockEdges = new HashSet();
        mockEdges.add(mockEdge1);
        mockEdges.add(mockEdge2);

        BasicGraph basicGraph = new BasicGraph();

        try
        {
            Utils.setField(basicGraph, BasicGraph.class, "nodes", mockNodes);
            Utils.setField(basicGraph, BasicGraph.class, "edges", mockEdges);

            Set<Node<String>> neighbours = basicGraph.getNeighbours(mockNode1);
            Assert.assertEquals(1, neighbours.size());
            Assert.assertEquals(mockNode2, neighbours.iterator().next());

            neighbours = basicGraph.getNeighbours(mockNode2);
            Assert.assertEquals(2, neighbours.size());
            Assert.assertEquals(true, neighbours.contains(mockNode1));
            Assert.assertEquals(true, neighbours.contains(mockNode3));

            neighbours = basicGraph.getNeighbours(mockNode3);
            Assert.assertEquals(1, neighbours.size());
            Assert.assertEquals(true, neighbours.contains(mockNode2));

            neighbours = basicGraph.getNeighbours(mockNode4);
            Assert.assertEquals(0, neighbours.size());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }

        // ////////////////////////////////////////////
    }


    /**
     * DOCUMENT ME!
     */
    @Test
    public void testIsConnected()
    {
        try
        {
            int[][] adj1 = new int[][]
                {
                    { Integer.MAX_VALUE },
                };

            int[][] adj2 = new int[][]
                {
                    { Integer.MAX_VALUE },
                };

            int[][] adj3 =
                new int[][]
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
                    { 1, 1 },
                    { 1, 1 }
                };
            int[][] adj6 =
                new int[][]
                {
                    { Integer.MAX_VALUE, 1, 1 },
                    { 1, Integer.MAX_VALUE, 1 },
                    { 1, 1, Integer.MAX_VALUE },
                };
            int[][] adj7 =
                new int[][]
                {
                    { Integer.MAX_VALUE, 1, 1 },
                    { 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
                };

            int[][] adj8 =
                new int[][]
                {
                    { Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE }
                };

            int[][] adj9 =
                new int[][]
                {
                    { 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, 1 },
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

            //-------Undirected graph - success cases -------//
            Graph<String> graph =
                GraphFactory.getGraph(
                    adj1, new String[] { string1 }, false, BasicGraph.class);

            boolean expected = true;
            Assert.assertEquals(expected, graph.isConnected());

            graph = GraphFactory.getGraph(
                    adj2, new String[] { string1 }, false, BasicGraph.class);

            expected = true;
            Assert.assertEquals(expected, graph.isConnected());

            graph = GraphFactory.getGraph(
                    adj3, new String[] { string1, string2 }, false, BasicGraph.class);

            expected = true;
            Assert.assertEquals(expected, graph.isConnected());

            graph = GraphFactory.getGraph(
                    adj4, new String[] { string1, string2 }, false, BasicGraph.class);

            expected = true;
            Assert.assertEquals(expected, graph.isConnected());

            graph = GraphFactory.getGraph(
                    adj5, new String[] { string1, string2 }, false, BasicGraph.class);

            expected = true;
            Assert.assertEquals(expected, graph.isConnected());

            graph = GraphFactory.getGraph(
                    adj6, new String[] { string1, string2, string3 }, false,
                    BasicGraph.class);

            expected = true;
            Assert.assertEquals(expected, graph.isConnected());

            graph = GraphFactory.getGraph(
                    adj7, new String[] { string1, string2, string3 }, false,
                    BasicGraph.class);

            expected = true;
            Assert.assertEquals(expected, graph.isConnected());

            //-------Undirected graph - failure cases -------//
            graph = GraphFactory.getGraph(
                    adj8, new String[] { string1, string2 }, false, BasicGraph.class);

            expected = false;
            Assert.assertEquals(expected, graph.isConnected());

            graph = GraphFactory.getGraph(
                    adj9, new String[] { string1, string2, string3 }, false,
                    BasicGraph.class);

            expected = false;
            Assert.assertEquals(expected, graph.isConnected());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
