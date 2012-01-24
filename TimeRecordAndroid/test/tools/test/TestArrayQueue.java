package tools.test;

import junit.framework.Assert;

import org.junit.Test;

import tools.TestUtils;

import dataStructures.ArrayQueue;

public class TestArrayQueue {

	@Test
	public void testConstructor() {
		try {
			Object[] elements = new Object[0];
			ArrayQueue queue = new ArrayQueue(elements);
			TestUtils.inspect(queue, "front", -1);
			TestUtils.inspect(queue, "rear", -1);

			elements = new Object[1];
			queue = new ArrayQueue(elements);
			TestUtils.inspect(queue, "front", -1);
			TestUtils.inspect(queue, "rear", -1);

			elements[0] = new Object();
			queue = new ArrayQueue(elements);
			TestUtils.inspect(queue, "front", 0);
			TestUtils.inspect(queue, "rear", 0);

			elements = new Object[] { null, null, new Object(), new Object(),
					new Object() };
			queue = new ArrayQueue(elements);
			TestUtils.inspect(queue, "front", 2);
			TestUtils.inspect(queue, "rear", 4);

			elements = new Object[] { new Object(), new Object(), new Object(),
					null, null };
			queue = new ArrayQueue(elements);
			TestUtils.inspect(queue, "front", 0);
			TestUtils.inspect(queue, "rear", 2);

			elements = new Object[] { null, new Object(), new Object(),
					new Object(), null };
			queue = new ArrayQueue(elements);
			TestUtils.inspect(queue, "front", 1);
			TestUtils.inspect(queue, "rear", 3);

			elements = new Object[] { new Object(), new Object(), null, null,
					new Object() };
			queue = new ArrayQueue(elements);
			TestUtils.inspect(queue, "front", 4);
			TestUtils.inspect(queue, "rear", 1);

			elements = new Object[] { new Object(), new Object(), new Object(),
					new Object(), new Object() };
			queue = new ArrayQueue(elements);
			TestUtils.inspect(queue, "front", 0);
			TestUtils.inspect(queue, "rear", 4);

		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}

	}

	/*INCOMPLETE*/
	@Test
	public void testEquals() {
		try {
			ArrayQueue queue1 = new ArrayQueue(0);
			ArrayQueue queue2 = new ArrayQueue(0);
			Assert.assertEquals(true, queue1.equals(queue2));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
