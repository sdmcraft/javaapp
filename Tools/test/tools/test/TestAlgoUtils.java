package tools.test;

import junit.framework.Assert;

import org.junit.Test;

import tools.AlgoUtils;

public class TestAlgoUtils {

	@Test
	public void testBinarySearch() {
		Assert.assertEquals(3, AlgoUtils.binarySearch(new int[] { -3, -2, -1,
				1, 2, 3 }, 0));
		Assert.assertEquals(3, AlgoUtils.binarySearch(new int[] { -3, -2, -1,
				0, 1, 2, 3 }, 0));
		Assert.assertEquals(3, AlgoUtils.binarySearch(new int[] { -3, -2, -1 },
				0));
		Assert
				.assertEquals(0, AlgoUtils.binarySearch(new int[] { 1, 2, 3 },
						0));
		Assert.assertEquals(1, AlgoUtils.binarySearch(new int[] { -1, 1 }, 0));
	}

}
