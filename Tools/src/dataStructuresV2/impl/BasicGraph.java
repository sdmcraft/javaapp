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

import dataStructures.ArrayQueue;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;

import dataStructuresV2.exception.InvalidDataException;

import dataStructuresV2.exception.InvalidDataException.Code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
  *
 * @param <T> DOCUMENT ME!
 */
public class BasicGraph<T>
    implements Graph<T>
{
    //~ Instance variables ---------------------------------------------------------------

    /** DOCUMENT ME! */
    protected final Set<Edge<T>> edges = new HashSet<Edge<T>>();

    /** DOCUMENT ME! */
    protected final Set<Node<T>> nodes = new HashSet<Node<T>>();

    //~ Methods --------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     *
     * @param edge DOCUMENT ME!
     *
     * @throws InvalidDataException DOCUMENT ME!
     */
    @Override
    public final void addEdge(Edge<T> edge)
      throws InvalidDataException
    {
        if (!canAdd(edge))
        {
            throw new InvalidDataException(
                InvalidDataException.Code.INVALID, "Not allowed to add this edge!!");
        }

        if (!edges.add(edge))
        {
            throw new InvalidDataException(
                InvalidDataException.Code.DUPLICATE, "This graph already has this edge!!");
        }
    }


    // UT:TestBasicGraph.testAddNode
    /**
     * DOCUMENT ME!
     *
     * @param node DOCUMENT ME!
     *
     * @throws InvalidDataException DOCUMENT ME!
     */
    @Override
    public final void addNode(Node<T> node)
      throws InvalidDataException
    {
        if (!nodes.add(node))
        {
            throw new InvalidDataException(
                InvalidDataException.Code.DUPLICATE, "This graph already has this node!!");
        }
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public String getDiagram()
    {
        StringBuilder diagram = new StringBuilder();
        diagram.append("digraph G {\n");

        for (Node<T> node : nodes)
        {
            diagram.append(node.getDiagramFragment()).append("\n");
        }

        for (Edge<T> edge : edges)
        {
            diagram.append(edge.getDiagramFragment()).append("\n");
        }

        diagram.append("}");

        return diagram.toString();
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public Set<Edge<T>> getEdges()
    {
        return Collections.unmodifiableSet(edges);
    }


    // UT:TestBasicGraph.testGetEdgesSingleNode
    /**
     * DOCUMENT ME!
     *
     * @param node DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws InvalidDataException DOCUMENT ME!
     */
    @Override
    public Set<Edge<T>> getEdges(Node<T> node)
      throws InvalidDataException
    {
        if (nodes.contains(node))
        {
            Set<Edge<T>> nodeEdges = new HashSet<Edge<T>>();

            for (Edge<T> edge : edges)
            {
                if (edge.isOrigin(node))
                {
                    nodeEdges.add(edge);
                }
            }

            return Collections.unmodifiableSet(nodeEdges);
        }
        else
        {
            throw new InvalidDataException(
                InvalidDataException.Code.INVALID,
                "Specified node does not belong to this graph:" + node);
        }
    }


    // UT:TestBasicGraph.testGetEdgesDoubleNode
    /**
     * DOCUMENT ME!
     *
     * @param node1 DOCUMENT ME!
     * @param node2 DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws InvalidDataException DOCUMENT ME!
     */
    @Override
    public Set<Edge<T>> getEdges(
        Node<T> node1,
        Node<T> node2)
      throws InvalidDataException
    {
        if (!nodes.contains(node1))
        {
            throw new InvalidDataException(
                InvalidDataException.Code.INVALID,
                "Specified node does not belong to this graph:" + node1);
        }

        if (!nodes.contains(node2))
        {
            throw new InvalidDataException(
                InvalidDataException.Code.INVALID,
                "Specified node does not belong to this graph:" + node2);
        }

        Set<Edge<T>> node1Edges = getEdges(node1);
        Set<Edge<T>> edges = new HashSet<Edge<T>>();

        for (Edge<T> edge : node1Edges)
        {
            if (
                edge.getEndpoints()[0].equals(node2)
                || edge.getEndpoints()[1].equals(node2))
            {
                edges.add(edge);
            }
        }

        return Collections.unmodifiableSet(edges);
    }


    // UT:TestBasicGraph.testGetNeighbours
    /**
     * DOCUMENT ME!
     *
     * @param node DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws InvalidDataException DOCUMENT ME!
     */
    @Override
    public Set<Node<T>> getNeighbours(Node<T> node)
      throws InvalidDataException
    {
        Set<Edge<T>> nodeEdges = getEdges(node);
        Set<Node<T>> neighbours = new HashSet<Node<T>>();

        for (Edge<T> edge : nodeEdges)
        {
            if (!edge.getEndpoints()[0].equals(node))
            {
                neighbours.add(edge.getEndpoints()[0]);
            }
            else if (!edge.getEndpoints()[1].equals(node))
            {
                neighbours.add(edge.getEndpoints()[1]);
            }
        }

        return neighbours;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public Set<Node<T>> getNodes()
    {
        return Collections.unmodifiableSet(nodes);
    }


    //TODO:Needs UT
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public boolean isConnected()
    {
        for (Node<T> node : nodes)
        {
            Set<Node<T>> bft = new HashSet<Node<T>>();

            try
            {
                bft.addAll(breadthFirstTraversal(node));

                boolean pass = bft.equals(nodes);

                if (!pass)
                {
                    return false;
                }
            }
            catch (InvalidDataException e)
            {
                // This should never happen
                throw new RuntimeException(e);
            }
        }

        return true;
    }


    // UT:TestBasicGraph.testCanAdd
    /**
     * DOCUMENT ME!
     *
     * @param edge DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected boolean canAdd(Edge<T> edge)
    {
        return nodes.contains(edge.getEndpoints()[0])
        && nodes.contains(edge.getEndpoints()[1]);
    }


    // UT:TestBasicGraph.testBreadthFirstTraversal
    private List<Node<T>> breadthFirstTraversal(Node<T> startNode)
      throws InvalidDataException
    {
        if (!nodes.contains(startNode))
        {
            throw new InvalidDataException(
                Code.INVALID, startNode + " does not exist in the graph");
        }

        List<Node<T>> bftList = new ArrayList<Node<T>>();

        try
        {
            //TODO Fix Deprecated api use
            ArrayQueue queue = new ArrayQueue(nodes.size());
            queue.insert(startNode);

            while (!queue.empty())
            {
                Node<T> node = (Node<T>) queue.remove();
                Set<Node<T>> neighbours = this.getNeighbours(node);

                for (Node<T> neighbour : neighbours)
                {
                    if (!bftList.contains(neighbour) && !queue.contains(neighbour))
                    {
                        queue.insert(neighbour);
                    }
                }

                bftList.add(node);
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bftList;
    }

}
