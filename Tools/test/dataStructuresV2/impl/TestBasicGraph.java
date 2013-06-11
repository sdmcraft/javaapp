package dataStructuresV2.impl;

import dataStructuresV2.Edge;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.Set;


@RunWith(PowerMockRunner.class)
public class TestBasicGraph
{
    @BeforeClass
    public static void setup() {}

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

            Set<Edge<String>> edges = basicGraph.getEdges(mockNodes.iterator().next(), mockNodes.iterator().next());
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
}
