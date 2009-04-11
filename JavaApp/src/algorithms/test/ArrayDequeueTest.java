package algorithms.test;

import junit.framework.TestCase;

import org.junit.Test;

import algorithms.ArrayDequeue;
import algorithms.BusinessException;

public class ArrayDequeueTest extends TestCase {

	@Test
	public void testArrayDequeueObjectArray() {
		new ArrayDequeue(0);
		new ArrayDequeue(null);
		new ArrayDequeue(new Object[1]);
		new ArrayDequeue(new Object[10]);
		new ArrayDequeue(new Object[] { "test" });
		new ArrayDequeue(new Object[] { null, "test", null });
		new ArrayDequeue(new Object[] { "test1", "test2", "test3" });
	}

	@Test
	public void testEquals() {
		ArrayDequeue dq1 = new ArrayDequeue(new Object[] { "test" });
		ArrayDequeue dq2 = null;
		assertEquals(false, dq1.equals(dq2));

		dq1 = new ArrayDequeue(new Object[] { "test" });
		dq2 = dq1;
		assertEquals(true, dq1.equals(dq2));
		assertEquals(true, dq1.hashCode() == dq2.hashCode());

		assertEquals(false, dq1.equals(new Object[] { "test" }));

		dq1 = new ArrayDequeue(new Object[] { "test" });
		dq2 = new ArrayDequeue(new Object[] { "test1", "test2", "test3" });
		assertEquals(false, dq1.equals(dq2));
		assertEquals(false, dq1.hashCode() == dq2.hashCode());

		dq1 = new ArrayDequeue(new Object[] { null, "test2", "test3" });
		dq2 = new ArrayDequeue(new Object[] { "test1", "test2", "test3" });
		assertEquals(false, dq1.equals(dq2));
		assertEquals(false, dq1.hashCode() == dq2.hashCode());

		Object[] objArr = new Object[] { "test1", "test2", "test3" };
		dq1 = new ArrayDequeue(objArr);
		dq2 = new ArrayDequeue(objArr);
		assertEquals(true, dq1.equals(dq2));
		assertEquals(true, dq1.hashCode() == dq2.hashCode());

		dq1 = new ArrayDequeue(new Object[] { null, "test2", "test3", null });
		dq2 = new ArrayDequeue(new Object[] { null, "test2", "test3", null,
				null });
		assertEquals(false, dq1.equals(dq2));
		assertEquals(true, dq1.hashCode() == dq2.hashCode());

		dq1 = new ArrayDequeue(new Object[] { null, "test2", "test3", null });
		dq2 = new ArrayDequeue(new Object[] { null, "test2",
				new String("test3"), null });
		assertEquals(true, dq1.equals(dq2));
		assertEquals(true, dq1.hashCode() == dq2.hashCode());

		dq1 = new ArrayDequeue(new Object[] { null, "test2", "test3", null });
		dq2 = new ArrayDequeue(new Object[] { null, "test2",
				new String("test3a"), null });
		assertEquals(false, dq1.equals(dq2));
		assertEquals(false, dq1.hashCode() == dq2.hashCode());

		dq1 = new ArrayDequeue(new Object[] { null, null });
		dq2 = new ArrayDequeue(new Object[] { null, null });
		assertEquals(true, dq1.equals(dq2));
		assertEquals(true, dq1.hashCode() == dq2.hashCode());

	}

	@Test
	public void testFrontInsert() {
		ArrayDequeue dq = new ArrayDequeue(1);
		dq.frontInsert("test");
		try {
			dq.frontInsert("test");
		} catch (BusinessException ex) {
			assertEquals(true, "Queue full!!!".equals(ex.getMessage()));
		}
		dq = new ArrayDequeue(2);
		dq.frontInsert("test");
		dq.frontInsert("test");
	}
	
	@Test
	public void testFrontRemove() {
		ArrayDequeue dq = new ArrayDequeue(1);
		try {
			dq.frontRemove();
		} catch (BusinessException ex) {
			assertEquals(true, "Queue empty!!!".equals(ex.getMessage()));
		}
		
		dq.frontInsert("test");
		dq.frontRemove();
		
		dq = new ArrayDequeue(2);
		dq.frontInsert("test");
		dq.frontInsert("test");
		dq.frontRemove();
	}
	
	@Test
	public void testRearInsert() {
		ArrayDequeue dq = new ArrayDequeue(1);
		dq.rearInsert("test");
		try {
			dq.rearInsert("test");
		} catch (BusinessException ex) {
			assertEquals(true, "Queue full!!!".equals(ex.getMessage()));
		}
		dq = new ArrayDequeue(2);
		dq.rearInsert("test");
		dq.rearInsert("test");
	}

	@Test
	public void testRearRemove() {
		ArrayDequeue dq = new ArrayDequeue(1);
		try {
			dq.rearRemove();
		} catch (BusinessException ex) {
			assertEquals(true, "Queue empty!!!".equals(ex.getMessage()));
		}
		
		dq.rearInsert("test");
		dq.rearRemove();
		
		dq = new ArrayDequeue(2);
		dq.rearInsert("test");
		dq.rearInsert("test");
		dq.rearRemove();
	}

}
