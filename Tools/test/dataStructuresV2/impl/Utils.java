package dataStructuresV2.impl;

import dataStructuresV2.Edge;
import dataStructuresV2.Node;
import dataStructuresV2.utils.EdgeFactory;
import dataStructuresV2.utils.NodeFactory;

import org.easymock.EasyMock;

import java.lang.reflect.Field;

import java.util.HashSet;
import java.util.Set;


public class Utils
{
    public static Set<Node> createMockNodes(int n)
    {
        Set<Node> set = new HashSet<Node>();

        for (int i = 0; i < n; i++)
        {
            Node node = EasyMock.createMock(Node.class);
            EasyMock.expect(node.getValue()).andReturn(i);
            set.add(node);
        }

        return set;
    }

    public static Set<Node> createMockNodes(Object[] values)
    {
        Set<Node> set = new HashSet<Node>();

        for (int i = 0; i < values.length; i++)
        {
            Node node = EasyMock.createMock(Node.class);
            EasyMock.expect(node.getValue()).andReturn(values[i]);
            EasyMock.expect(node.hashCode()).andReturn((31 * 1) + ((values[i] == null) ? 0 : values[i].hashCode()));
            set.add(node);
            EasyMock.replay(node);
        }

        return set;
    }

    public static Set<Node> createNodes(Object[] values)
    {
        Set<Node> set = new HashSet<Node>();

        for (int i = 0; i < values.length; i++)
        {
            Node node = NodeFactory.getNode(values[i]);
            set.add(node);
        }

        return set;
    }

    public static Set<Edge> createMockEdges(int n)
    {
        Set<Edge> set = new HashSet<Edge>();

        for (int i = 0; i < n; i++)
        {
            Edge edge = EasyMock.createMock(Edge.class);
            set.add(edge);
        }

        return set;
    }

    public static Edge createMockEdge(Node node1, Node node2)
    {
        Edge edge = EasyMock.createMock(Edge.class);
        EasyMock.expect(edge.getEndpoints()).andReturn(new Node[] { node1, node2 }).anyTimes();
        EasyMock.replay(edge);

        return edge;
    }

    public static <T> Edge<T> createEdge(T value1, T value2, int weight, boolean directed)
    {
        Node<T> node1 = NodeFactory.getNode(value1);
        Node<T> node2 = NodeFactory.getNode(value2);

        if (!directed)
        {
            return EdgeFactory.getEdge(new Node[] { node1, node2 }, weight);
        }
        else
        {
            return EdgeFactory.getDirectedEdge(new Node[] { node1, node2 }, weight);
        }
    }

    public static Object getField(Object object, Class clazz, String fieldName) throws Exception
    {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        return field.get(object);
    }

    public static void setField(Object object, Class clazz, String fieldName, Object value) throws Exception
    {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }
}
