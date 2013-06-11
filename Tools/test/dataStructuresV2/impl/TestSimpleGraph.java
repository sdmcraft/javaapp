package dataStructuresV2.impl;

import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;
import dataStructuresV2.utils.EdgeFactory;
import dataStructuresV2.utils.GraphFactory;
import dataStructuresV2.utils.NodeFactory;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


public class TestSimpleGraph
{
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
        }
        catch (InvalidDataException e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
