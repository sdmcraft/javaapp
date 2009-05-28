package algorithms.test;

import org.junit.Test;

import algorithms.LinkedListNode;

import junit.framework.TestCase;

public class LinkedListNodeTest extends TestCase {
	@Test
	public void testEquals() {
		LinkedListNode node = new LinkedListNode();		
		assertEquals(true, node.equals(node));
		assertEquals(false, node.equals(null));
		node.data = new Integer(1);
		LinkedListNode node2 = new LinkedListNode();
		assertEquals(false, node.equals(node2));
		assertEquals(false, node2.equals(node));
		node2.data = node.data;
		assertEquals(true, node.equals(node2));
		assertEquals(true, node2.equals(node));
		node2.data = new Integer(2);
		assertEquals(false, node.equals(node2));
		assertEquals(false, node2.equals(node));
		node2.data = new Integer(1);
		assertEquals(true, node.equals(node2));
		assertEquals(true, node2.equals(node));
		LinkedListNode a =  new LinkedListNode();
		LinkedListNode b =  new LinkedListNode();
		node.prev = a;
		assertEquals(false, node.equals(node2));
		assertEquals(false, node2.equals(node));
		node.next = b;
		assertEquals(false, node.equals(node2));
		assertEquals(false, node2.equals(node));
		node2.prev = a;
		assertEquals(false, node.equals(node2));
		assertEquals(false, node2.equals(node));
		node2.next = b;
		assertEquals(true, node.equals(node2));
		assertEquals(true, node2.equals(node));
		LinkedListNode c =  new LinkedListNode();
		LinkedListNode d =  new LinkedListNode();
		node2.prev = c;
		assertEquals(true, node.equals(node2));
		assertEquals(true, node2.equals(node));
		node2.next = d;
		assertEquals(true, node.equals(node2));
		assertEquals(true, node2.equals(node));
		a.data = new Integer(1);
		assertEquals(false, node.equals(node2));
		assertEquals(false, node2.equals(node));
		c.data = new Integer(1);
		assertEquals(true, node.equals(node2));
		assertEquals(true, node2.equals(node));
		b.data = new Integer(1);
		assertEquals(false, node.equals(node2));
		assertEquals(false, node2.equals(node));
		d.data = new Integer(1);
		assertEquals(true, node.equals(node2));
		assertEquals(true, node2.equals(node));
		
		assertEquals(false, node.equals("abc"));
	}
}
