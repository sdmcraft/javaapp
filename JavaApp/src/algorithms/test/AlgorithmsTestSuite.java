package algorithms.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AlgorithmsTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite();

		suite.addTestSuite(ArrayDequeueTest.class);
		suite.addTestSuite(ArrayQueueTest.class);
		suite.addTestSuite(ExpressionEvaluatorTest.class);
		suite.addTestSuite(DequeueAsStackTest.class);
		suite.addTestSuite(LinkedListTest.class);
		return suite;
	}
}
