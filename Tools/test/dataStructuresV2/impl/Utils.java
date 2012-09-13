package dataStructuresV2.impl;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.easymock.EasyMock;

import dataStructuresV2.Node;

public class Utils {

	public static Set<Node> createMockNodes(int n) {

		Set<Node> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			Node node = EasyMock.createMock(Node.class);
			EasyMock.expect(node.getValue()).andReturn(i);
			set.add(node);
		}
		return set;
	}

	public static Object getField(Object object, Class clazz, String fieldName)
			throws Exception {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

}
