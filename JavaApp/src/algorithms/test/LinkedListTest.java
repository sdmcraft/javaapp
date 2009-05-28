package algorithms.test;

import org.junit.Test;

import algorithms.LinkedList;
import algorithms.LinkedListNode;

import junit.framework.TestCase;

public class LinkedListTest extends TestCase {

	@Test
	public void testEquals() {
		LinkedList list = new LinkedList();
		assertEquals(true, list.equals(list));
		assertEquals(false, list.equals(null));
		assertEquals(false, list.equals("abc"));
		assertEquals(true, list.equals(new LinkedList()));
		LinkedListNode node = new LinkedListNode();
		node.data = "abc";
		list.insertFirst(node);
		LinkedList list2 = new LinkedList();
		list2.insertFirst(node);
		assertEquals(true, list.equals((list2)));
		assertEquals(true, list2.equals((list)));

		LinkedListNode node2 = new LinkedListNode();
		node2.data = " def";
		list2 = new LinkedList();
		list2.insertFirst(node2);
		assertEquals(false, list.equals((list2)));
		assertEquals(false, list2.equals((list)));

		list = new LinkedList();
		assertEquals(false, list.equals((list2)));
		assertEquals(false, list2.equals((list)));

		list = new LinkedList();
		list2 = new LinkedList();
		assertEquals(true, list.equals((list2)));
		assertEquals(true, list2.equals((list)));
		node = new LinkedListNode();
		list.insertFirst(node);
		list.displayList();
		assertEquals(false, list.equals((list2)));
		assertEquals(false, list2.equals((list)));
		list2.insertFirst(node);
		list2.displayList();
		assertEquals(true, list.equals((list2)));
		assertEquals(true, list2.equals((list)));
		node2 = new LinkedListNode();
		list2.insertFirst(node);
		list.displayList();
		list2.displayList();
		assertEquals(true, list.equals((list2)));
		assertEquals(true, list2.equals((list)));
		list2 = new LinkedList();
		node2 = new LinkedListNode();
		list2.insertFirst(node2);
		assertEquals(true, list.equals((list2)));
		assertEquals(true, list2.equals((list)));
		node.data = "abc";
		assertEquals(false, list.equals((list2)));
		assertEquals(false, list2.equals((list)));
		node2.data = "def";
		assertEquals(false, list.equals((list2)));
		assertEquals(false, list2.equals((list)));
		node2.data = "abc";
		assertEquals(true, list.equals((list2)));
		assertEquals(true, list2.equals((list)));

	}

	@Test
	public void testIsEmpty() {
		LinkedList list = new LinkedList();
		assertEquals(true, list.isEmpty());
		LinkedListNode node = new LinkedListNode();
		list.insertFirst(node);
		assertEquals(false, list.isEmpty());
	}

	@Test
	public void testInsertFirstDeleteFirst() {
		LinkedList list = new LinkedList();
		LinkedListNode node = new LinkedListNode();
		list.insertFirst(node);
		assertEquals(false, list.isEmpty());
		list.deleteFirst();
		assertEquals(true, list.isEmpty());
	}

}
