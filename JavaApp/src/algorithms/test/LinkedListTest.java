package algorithms.test;

import org.junit.Test;

import algorithms.LinkedList;
import algorithms.LinkedListNode;

import junit.framework.TestCase;

public class LinkedListTest extends TestCase{
	
	@Test
	public void testEquals()
	{
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
		
		LinkedListNode node2 = new LinkedListNode();
		node2.data = " def";		
		list2 = new LinkedList();
		list2.insertFirst(node2);
		assertEquals(false, list.equals((list2)));
		
		list = new LinkedList();
		assertEquals(false, list.equals((list2)));
	}
	
	@Test
	public void testIsEmpty()
	{
		LinkedList list = new LinkedList();
		assertEquals(true, list.isEmpty());
		LinkedListNode node = new LinkedListNode();
		list.insertFirst(node);
		assertEquals(false, list.isEmpty());
	}
	
	@Test
	public void testInsertFirstDeleteFirst()
	{
		LinkedList list = new LinkedList();		
		LinkedListNode node = new LinkedListNode();
		list.insertFirst(node);
		assertEquals(false, list.isEmpty());
		list.deleteFirst();
		assertEquals(true, list.isEmpty());
	}

}
