package tools.test;

import java.lang.reflect.Field;

import junit.framework.Assert;

import org.junit.Test;

import dataStructures.ArrayQueue;

public class TestArrayQueue {

	@Test
	public void testConstructor() {
		try {
			Object[] elements = new Object[0];
			ArrayQueue queue = new ArrayQueue(elements);

			Class suspect = Class.forName("dataStructures.ArrayQueue");
			Field question = suspect.getField("front");
			Assert.assertEquals(-1, question.get(suspect));
			elements = new Object[1];
			queue = new ArrayQueue(elements);
		} catch (Exception ex) {
			Assert.fail();
		}

	}

}
