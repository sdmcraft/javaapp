package dataStructuresV2.impl;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.easymock.EasyMock;

import dataStructuresV2.Edge;
import dataStructuresV2.Node;

public class Utils {

	public static Set<Node> createMockNodes(int n) {

		Set<Node> set = new HashSet<Node>();
		for (int i = 0; i < n; i++) {
			Node node = EasyMock.createMock(Node.class);
			EasyMock.expect(node.getValue()).andReturn(i);
			set.add(node);
		}
		return set;
	}

	public static Set<Node> createMockNodes(Object[] values) {

		Set<Node> set = new HashSet<Node>();
		for (int i = 0; i < values.length; i++) {
			Node node = EasyMock.createMock(Node.class);
			EasyMock.expect(node.getValue()).andReturn(values[i]);
			set.add(node);
			EasyMock.replay(node);
		}
		return set;
	}
	


	public static Set<Edge> createMockEdges(int n) {

		Set<Edge> set = new HashSet<Edge>();

		for (int i = 0; i < n; i++) {
			Edge edge = EasyMock.createMock(Edge.class);
			set.add(edge);
		}
		return set;
	}

	public static Edge createMockEdge(Node node1, Node node2) {
		Edge edge = EasyMock.createMock(Edge.class);
		EasyMock.expect(edge.getEndpoints()).andReturn(
				new Node[] { node1, node2 }).anyTimes();
		EasyMock.replay(edge);
		return edge;
	}

	public static Object getField(Object object, Class clazz, String fieldName)
			throws Exception {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

	public static void setField(Object object, Class clazz, String fieldName,
			Object value) throws Exception {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(object, value);
	}

}
