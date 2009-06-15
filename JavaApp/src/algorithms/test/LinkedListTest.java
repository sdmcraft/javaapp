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
		assertEquals(true,node.equals(list.deleteFirst()));
		assertEquals(true, list.isEmpty());
		
		list = new LinkedList();
		list.insertFirst(new LinkedListNode(1));		
		list.insertFirst(new LinkedListNode(2));	
		list.insertFirst(new LinkedListNode(3));
		list.insertFirst(new LinkedListNode(4));
		list.insertFirst(new LinkedListNode(5));

		assertEquals(true,(new Integer(5).equals(list.deleteFirst().data)));
		LinkedList list2 = new LinkedList();
		list2.insertFirst(new LinkedListNode(1));		
		list2.insertFirst(new LinkedListNode(2));	
		list2.insertFirst(new LinkedListNode(3));
		list2.insertFirst(new LinkedListNode(4));
		assertEquals(true,list2.equals(list));
		
		assertEquals(true,(new Integer(4).equals(list.deleteFirst().data)));
		list2 = new LinkedList();
		list2.insertFirst(new LinkedListNode(1));		
		list2.insertFirst(new LinkedListNode(2));	
		list2.insertFirst(new LinkedListNode(3));
		assertEquals(true,list2.equals(list));
		
		assertEquals(true,(new Integer(3).equals(list.deleteFirst().data)));
		list2 = new LinkedList();
		list2.insertFirst(new LinkedListNode(1));		
		list2.insertFirst(new LinkedListNode(2));	
		assertEquals(true,list2.equals(list));
		
		assertEquals(true,(new Integer(2).equals(list.deleteFirst().data)));
		list2 = new LinkedList();		
		list2.insertFirst(new LinkedListNode(1));	
		assertEquals(true,list2.equals(list));

		assertEquals(true,(new Integer(1).equals(list.deleteFirst().data)));
		list2 = new LinkedList();
		assertEquals(true,list2.equals(list));
		
		assertEquals(true,null == (list.deleteFirst()));
		list2 = new LinkedList();
		assertEquals(true,list2.equals(list));
		
	}
	
	@Test
	public void testDelete() {
		LinkedList list = new LinkedList();
		list.insertFirst(new LinkedListNode(1));		
		list.insertFirst(new LinkedListNode(2));	
		list.insertFirst(new LinkedListNode(3));
		list.insertFirst(new LinkedListNode(4));
		list.insertFirst(new LinkedListNode(5));

		assertEquals(true,(null == (list.delete(200))));
		assertEquals(true,(new Integer(3).equals(list.delete(3).data)));
		LinkedList list2 = new LinkedList();
		list2.insertFirst(new LinkedListNode(1));		
		list2.insertFirst(new LinkedListNode(2));	
		list2.insertFirst(new LinkedListNode(4));
		list2.insertFirst(new LinkedListNode(5));
		assertEquals(true,list2.equals(list));
		
		assertEquals(true,(new Integer(4).equals(list.delete(4).data)));
		list2 = new LinkedList();
		list2.insertFirst(new LinkedListNode(1));		
		list2.insertFirst(new LinkedListNode(2));	
		list2.insertFirst(new LinkedListNode(5));
		assertEquals(true,list2.equals(list));
		
		assertEquals(true,(new Integer(5).equals(list.delete(5).data)));
		list2 = new LinkedList();
		list2.insertFirst(new LinkedListNode(1));		
		list2.insertFirst(new LinkedListNode(2));	
		assertEquals(true,list2.equals(list));
		
		assertEquals(true,(new Integer(1).equals(list.delete(1).data)));
		list2 = new LinkedList();		
		list2.insertFirst(new LinkedListNode(2));	
		assertEquals(true,list2.equals(list));

		assertEquals(true,(new Integer(2).equals(list.delete(2).data)));
		list2 = new LinkedList();
		assertEquals(true,list2.equals(list));
		
		assertEquals(true,(null == (list.delete(200))));
	}
	
	@Test
	public void testDisplayList()
	{
		
	}
}
