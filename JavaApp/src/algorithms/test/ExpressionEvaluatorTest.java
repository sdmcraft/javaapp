package algorithms.test;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.ExpressionEvaluator;

public class ExpressionEvaluatorTest {

	@Test
	public void testEvaluate() throws Exception {
		assertEquals(3, ExpressionEvaluator.evaluate("1+2"));
		assertEquals(1, ExpressionEvaluator.evaluate("1"));
		assertEquals(3, ExpressionEvaluator.evaluate("(1)+(2)"));
		assertEquals(2, ExpressionEvaluator.evaluate("((6/3-2)/7+6*3)/(3+(9+3)/2)"));
	}

}
