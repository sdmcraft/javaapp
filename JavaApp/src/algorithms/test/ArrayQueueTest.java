/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms.test;

import junit.framework.TestCase;
import algorithms.ArrayQueue;
import algorithms.BusinessException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author satyam
 */
public class ArrayQueueTest extends TestCase {

	public ArrayQueueTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testEquals() {
		ArrayQueue instance = new ArrayQueue(10);
		ArrayQueue instance2 = new ArrayQueue(10);
		assertEquals(false, instance.equals(null));
		assertEquals(true, instance.equals(instance));
		assertEquals(true, instance.equals(instance2));
		assertEquals(false, instance.equals("a"));
		instance.insert("a");
		instance.insert("b");
		instance.insert("c");
		instance.remove();
		instance2.insert("a");
		instance2.insert("a1");
		instance2.insert("b");
		instance2.insert("c");
		instance2.remove();
		instance2.remove();
		assertEquals(false, instance.equals(instance2));
		instance.flush();
		instance2.flush();
		instance.insert("a");
		instance.insert("b");
		instance.insert("c");
		instance.remove();
		instance2.insert("a");
		instance2.insert("b");
		instance2.insert("c");
		instance2.remove();
		assertEquals(true, instance.equals(instance2));
		instance.flush();
		instance2.flush();
		instance = new ArrayQueue(new Object[] { null, null, "a", "b", null });
		// instance.display();
		instance2 = new ArrayQueue(5);
		instance2.insert("x");
		instance2.insert("x");
		instance2.insert("a");
		instance2.insert("b");
		instance2.remove();
		instance2.remove();
		// instance2.display();
		assertEquals(true, instance.equals(instance2));
		instance = new ArrayQueue(new Object[] { "a", null, "b" });
		instance2 = new ArrayQueue(3);
		instance2.insert("x");
		instance2.insert("x");
		instance2.insert("b");
		instance2.remove();
		instance2.remove();
		instance2.insert("a");
		assertEquals(true, instance.equals(instance2));

		instance2 = new ArrayQueue(3);
		instance2.insert("x");
		instance2.insert("x");
		instance2.insert("b");
		instance2.remove();
		instance2.insert("a");
		instance2.remove();
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Object[] { "a" });
		instance2 = new ArrayQueue(1);
		instance2.insert("a");
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Object[] { "a", "b" });
		instance2 = new ArrayQueue(2);
		instance2.insert("a");
		instance2.insert("b");
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Object[] { null });
		instance2 = new ArrayQueue(1);
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Object[] { null, null });
		instance2 = new ArrayQueue(2);
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Object[] { null, "a", null });
		instance2 = new ArrayQueue(3);
		instance2.insert("x");
		instance2.insert("a");
		instance2.remove();
		// instance.display();
		// instance2.display();
		assertEquals(true, instance.equals(instance2));

		// instance.display();
	}

	@Test
	public void testPriorityRemove() {
		ArrayQueue instance = new ArrayQueue(new Integer[] { 1, 2, 3, 5, 6,
				null });
		assertEquals(new Integer(1), (Integer) instance.priorityRemove());

		instance = new ArrayQueue(new Integer[] { 6, 5, 4, 3, 2, 1 });
		assertEquals(new Integer(1), (Integer) instance.priorityRemove());

		instance = new ArrayQueue(new Integer[] { 6 });
		assertEquals(new Integer(6), (Integer) instance.priorityRemove());

		try {
			instance = new ArrayQueue(new Integer[] { null });
			instance.priorityRemove();
		} catch (BusinessException ex) {
			assertEquals("Queue empty!!!", ex.getMessage());
		}

		try {
			instance = new ArrayQueue(0);
			instance.priorityRemove();
		} catch (BusinessException ex) {
			assertEquals("Queue empty!!!", ex.getMessage());
		}

		try {
			instance = new ArrayQueue(1);
			instance.priorityRemove();
		} catch (BusinessException ex) {
			assertEquals("Queue empty!!!", ex.getMessage());
		}
		instance = new ArrayQueue(new Integer[] { 1, 1, 1, 1, 1 });
		assertEquals(new Integer(1), (Integer) instance.priorityRemove());

		try {
			instance = new ArrayQueue(new Integer[] { null, null, null });
			instance.priorityRemove();
		} catch (BusinessException ex) {
			assertEquals("Queue empty!!!", ex.getMessage());
		}

		instance = new ArrayQueue(new Integer[] { -1, 1, -1, 1, -1 });
		assertEquals(new Integer(-1), (Integer) instance.priorityRemove());

		instance = new ArrayQueue(new Integer[] { 6, 5, 4, 3, 2, 1 });
		assertEquals(new Integer(1), (Integer) instance.priorityRemove());
		assertEquals(new Integer(2), (Integer) instance.priorityRemove());
		assertEquals(new Integer(3), (Integer) instance.priorityRemove());
		assertEquals(new Integer(4), (Integer) instance.priorityRemove());
		assertEquals(new Integer(5), (Integer) instance.priorityRemove());
		assertEquals(new Integer(6), (Integer) instance.priorityRemove());
	}

	@Test
	public void testPriorityInsert() {
		ArrayQueue instance = new ArrayQueue(new Integer[] { 1, 2, 3, 5, 6,
				null });
		instance.priorityInsert(4);
		assertEquals(true, instance.equals(new ArrayQueue(new Integer[] { 1, 2,
				3, 4, 5, 6 })));

		instance = new ArrayQueue(new Integer[] { null, null });
		instance.priorityInsert(4);
		assertEquals(true, instance.equals(new ArrayQueue(new Integer[] { 4,
				null })));

		instance = new ArrayQueue(new Integer[] { null, 1, 2, 3, 5, 6 });
		// instance.display();
		instance.priorityInsert(4);
		// instance.display();
		ArrayQueue instance2 = new ArrayQueue(
				new Integer[] { 0, 1, 2, 3, 4, 5 });
		instance2.remove();
		instance2.priorityInsert(6);
		// instance2.display();
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Integer[] { 5, 6, null, 1, 2, 3 });
		instance.priorityInsert(4);
		// instance.display();
		instance2 = new ArrayQueue(new Integer[] { -2, -1, 0, 1, 2, 3 });
		instance2.remove();
		instance2.remove();
		instance2.remove();
		instance2.priorityInsert(6);
		instance2.priorityInsert(5);
		instance2.priorityInsert(4);
		// instance2.display();
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Integer[] { 5, 6, null, 1, 2, 3 });
		instance.priorityInsert(4);
		// instance.display();
		instance2 = new ArrayQueue(new Integer[] { -2, -1, 0, 1, 2, 3 });
		instance2.remove();
		instance2.remove();
		instance2.remove();
		instance2.priorityInsert(5);
		instance2.priorityInsert(6);
		instance2.priorityInsert(4);
		// instance2.display();
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Integer[] { null, 1, 2, 3 });
		// instance.display();
		instance.priorityInsert(0);
		// instance.display();
		instance2 = new ArrayQueue(new Integer[] { 0, 1, 2, 3 });
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Integer[] { null });
		// instance.display();
		instance.priorityInsert(0);
		// instance.display();
		instance2 = new ArrayQueue(new Integer[] { 0 });
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Integer[] { null, null, null });
		// instance.display();
		instance.priorityInsert(0);
		// instance.display();
		instance2 = new ArrayQueue(new Integer[] { 0, null, null });
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Integer[] { null, 0, null });
		// instance.display();
		instance.priorityInsert(0);
		// instance.display();
		instance2 = new ArrayQueue(new Integer[] { null, 0, 0 });
		assertEquals(true, instance.equals(instance2));

		instance = new ArrayQueue(new Integer[] { 0 });
		// instance.display();
		try {
			instance.priorityInsert(0);
		} catch (Exception ex) {
			assertEquals("Queue full!!!", ex.getMessage());
		}

	}

	/**
	 * Test of insert method, of class ArrayQueue.
	 */
	// @Test
	// public void testInsert() {
	// Object item = "item";
	// ArrayQueue instance = new ArrayQueue(1);
	// instance.insert(item);
	// // TODO review the generated test code and remove the default call to
	// fail.
	// fail("The test case is a prototype.");
	// }
	//
	// /**
	// * Test of remove method, of class ArrayQueue.
	// */
	// @Test
	// public void testRemove() {
	// System.out.println("remove");
	// ArrayQueue instance = null;
	// Object expResult = null;
	// Object result = instance.remove();
	// assertEquals(expResult, result);
	// // TODO review the generated test code and remove the default call to
	// fail.
	// fail("The test case is a prototype.");
	// }
	//
	// /**
	// * Test of empty method, of class ArrayQueue.
	// */
	// @Test
	// public void testEmpty() {
	// System.out.println("empty");
	// ArrayQueue instance = null;
	// boolean expResult = false;
	// boolean result = instance.empty();
	// assertEquals(expResult, result);
	// // TODO review the generated test code and remove the default call to
	// fail.
	// fail("The test case is a prototype.");
	// }
	//
	/**
	 * Test of display method, of class ArrayQueue.
	 */
	@Test
	public void testDisplay() {
		// System.out.println("display");
		ArrayQueue instance = new ArrayQueue(0);
		assertEquals("Front:-1\nRear:-1\n\n", instance.display());

		instance = new ArrayQueue(1);
		assertEquals("Front:-1\nRear:-1\n\n", instance.display());

		instance = new ArrayQueue(100);
		assertEquals("Front:-1\nRear:-1\n\n", instance.display());

		instance = new ArrayQueue(1);
		instance.insert(new Integer(10));
		assertEquals("Front:0\nRear:0\n10 \n", instance.display());

		instance = new ArrayQueue(10);
		instance.insert(new Integer(10));
		assertEquals("Front:0\nRear:0\n10 \n", instance.display());

		instance = new ArrayQueue(3);
		instance.insert(new Integer(10));
		instance.insert(new Integer(20));
		instance.insert(new Integer(30));
		instance.remove();
		instance.insert(new Integer(40));
		assertEquals("Front:1\nRear:0\n20 30 40 \n", instance.display());

	}
	//
	// /**
	// * Test of getQueue method, of class ArrayQueue.
	// */
	// @Test
	// public void testGetQueue() {
	// System.out.println("getQueue");
	// ArrayQueue instance = null;
	// String expResult = "";
	// String result = instance.getQueue();
	// assertEquals(expResult, result);
	// // TODO review the generated test code and remove the default call to
	// fail.
	// fail("The test case is a prototype.");
	// }
}