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

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;
import dataStructuresV2.utils.EdgeFactory;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.NodeFactory;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.3 $
  */
public class TestSimpleGraph
{
    //~ Methods --------------------------------------------------------------------------

    /**
     * DOCUMENT ME!
     */
    @Test
    public void testCanAdd_undirected()
    {
        try
        {
            String string1 = "a";
            String string2 = "b";
            String string3 = "c";

            Set<String> strings = new HashSet<String>();
            strings.add(string1);
            strings.add(string2);
            strings.add(string3);

            Node<String> node1 = NodeFactory.getNode(string1);
            Node<String> node2 = NodeFactory.getNode(string2);
            Node<String> node3 = NodeFactory.getNode(string3);

            Graph<String> simpleGraph = GraphFactory.getSimpleGraph(strings, 3);

            try
            {
                Edge edge = EdgeFactory.getEdge(new Node[] { node1, node1 }, 2);
                simpleGraph.addEdge(edge);
                Assert.fail();
            }
            catch (InvalidDataException ex)
            {
                Assert.assertEquals(InvalidDataException.Code.INVALID, ex.code);
            }

            try
            {
                Edge edge = EdgeFactory.getEdge(new Node[] { node1, node2 }, 2);
                simpleGraph.addEdge(edge);
                Assert.fail();
            }
            catch (InvalidDataException ex)
            {
                Assert.assertEquals(InvalidDataException.Code.INVALID, ex.code);
            }

            // Success case
            int[][] adjMatrix =
                new int[][]
                {
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { 1, Integer.MAX_VALUE, 1 },
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE }
                };
            String[] values = new String[] { "a", "b", "c", };
            simpleGraph = GraphFactory.getGraph(adjMatrix, values, false, SimpleGraph.class);
            Edge edge = EdgeFactory.getEdge(new Node[] { node1, node3 }, 2);
            simpleGraph.addEdge(edge);

            
        }
        catch (InvalidDataException e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
