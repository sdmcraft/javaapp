package tools.test;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import tools.AlgoUtils;
import tools.DSUtils;

public class TestAlgoUtils {

	@Test
	public void testBinarySearch() {
		Assert.assertEquals(3,
				AlgoUtils.binarySearch(new int[] { -3, -2, -1, 1, 2, 3 }, 0));
		Assert.assertEquals(3,
				AlgoUtils.binarySearch(new int[] { -3, -2, -1, 0, 1, 2, 3 }, 0));
		Assert.assertEquals(3,
				AlgoUtils.binarySearch(new int[] { -3, -2, -1 }, 0));
		Assert.assertEquals(0, AlgoUtils.binarySearch(new int[] { 1, 2, 3 }, 0));
		Assert.assertEquals(1, AlgoUtils.binarySearch(new int[] { -1, 1 }, 0));
	}

	@Test
	public void testBubbleSort() {
		int[] arr = new int[] { -3, -2, -1, 1, 2, 3 };
		int[] sorted = new int[] { -3, -2, -1, 1, 2, 3 };
		AlgoUtils.bubbleSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 2, 3, -3, -2, -1 };
		sorted = new int[] { -3, -2, -1, 1, 2, 3 };
		AlgoUtils.bubbleSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1 };
		sorted = new int[] { 1 };
		AlgoUtils.bubbleSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 2 };
		sorted = new int[] { 1, 2 };
		AlgoUtils.bubbleSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 2, 1 };
		sorted = new int[] { 1, 2 };
		AlgoUtils.bubbleSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 3, 2, 1 };
		sorted = new int[] { 1, 2, 3 };
		AlgoUtils.bubbleSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 3, 1, 2 };
		sorted = new int[] { 1, 2, 3 };
		AlgoUtils.bubbleSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 1, 1 };
		sorted = new int[] { 1, 1, 1 };
		AlgoUtils.bubbleSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 1 };
		sorted = new int[] { 1, 1 };
		AlgoUtils.bubbleSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

	}

	@Test
	public void testSelectionSort() {
		int[] arr = new int[] { -3, -2, -1, 1, 2, 3 };
		int[] sorted = new int[] { -3, -2, -1, 1, 2, 3 };
		AlgoUtils.selectionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 2, 3, -3, -2, -1 };
		sorted = new int[] { -3, -2, -1, 1, 2, 3 };
		AlgoUtils.selectionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1 };
		sorted = new int[] { 1 };
		AlgoUtils.selectionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 2 };
		sorted = new int[] { 1, 2 };
		AlgoUtils.selectionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 2, 1 };
		sorted = new int[] { 1, 2 };
		AlgoUtils.selectionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 3, 2, 1 };
		sorted = new int[] { 1, 2, 3 };
		AlgoUtils.selectionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 3, 1, 2 };
		sorted = new int[] { 1, 2, 3 };
		AlgoUtils.selectionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 1, 1 };
		sorted = new int[] { 1, 1, 1 };
		AlgoUtils.selectionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 1 };
		sorted = new int[] { 1, 1 };
		AlgoUtils.selectionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

	}

	@Test
	public void testInsertionSort() {
		int[] arr = new int[] { -3, -2, -1, 1, 2, 3 };
		int[] sorted = new int[] { -3, -2, -1, 1, 2, 3 };
		AlgoUtils.insertionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 2, 3, -3, -2, -1 };
		sorted = new int[] { -3, -2, -1, 1, 2, 3 };
		AlgoUtils.insertionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1 };
		sorted = new int[] { 1 };
		AlgoUtils.insertionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 2 };
		sorted = new int[] { 1, 2 };
		AlgoUtils.insertionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 2, 1 };
		sorted = new int[] { 1, 2 };
		AlgoUtils.insertionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 3, 2, 1 };
		sorted = new int[] { 1, 2, 3 };
		AlgoUtils.insertionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 3, 1, 2 };
		sorted = new int[] { 1, 2, 3 };
		AlgoUtils.insertionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 1, 1 };
		sorted = new int[] { 1, 1, 1 };
		AlgoUtils.insertionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 1 };
		sorted = new int[] { 1, 1 };
		AlgoUtils.insertionSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

	}

	@Test
	public void testShellSort() {
		int[] arr = new int[] { -3, -2, -1, 1, 2, 3 };
		int[] sorted = new int[] { -3, -2, -1, 1, 2, 3 };
		AlgoUtils.shellSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 2, 3, -3, -2, -1 };
		sorted = new int[] { -3, -2, -1, 1, 2, 3 };
		AlgoUtils.shellSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1 };
		sorted = new int[] { 1 };
		AlgoUtils.shellSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 2 };
		sorted = new int[] { 1, 2 };
		AlgoUtils.shellSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 2, 1 };
		sorted = new int[] { 1, 2 };
		AlgoUtils.shellSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 3, 2, 1 };
		sorted = new int[] { 1, 2, 3 };
		AlgoUtils.shellSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 3, 1, 2 };
		sorted = new int[] { 1, 2, 3 };
		AlgoUtils.shellSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 1, 1 };
		sorted = new int[] { 1, 1, 1 };
		AlgoUtils.shellSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

		arr = new int[] { 1, 1 };
		sorted = new int[] { 1, 1 };
		AlgoUtils.shellSort(arr);
		Assert.assertEquals(true, DSUtils.arrayCompare(arr, sorted));

	}

	@Test
	public void testSort() {
		int[] input = new int[Integer.MAX_VALUE/1000];
		for (int i = 0; i < input.length; i++) {
			int sign = (Math.random() - 0.5) > 0 ? 1 : -1;
			input[i] = (int) (sign * Math.random() * Integer.MAX_VALUE);
			if (input[i] == Integer.MAX_VALUE)
				--input[i];
			if (input[i] == Integer.MIN_VALUE)
				++input[i];
		}

		int[] ref = DSUtils.arrayCopy(input);
		Arrays.sort(ref);

		int[] bubble = DSUtils.arrayCopy(input);
		AlgoUtils.bubbleSort(bubble);

		Assert.assertEquals(true, DSUtils.arrayCompare(ref, bubble));
	}
	/*
	 * @Test public void testQuickSort() { int[] arr = new int[] { -3, -2, -1,
	 * 1, 2, 3 }; int[] sorted = new int[] { -3, -2, -1, 1, 2, 3 };
	 * AlgoUtils.quickSort(arr); Assert.assertEquals(true,
	 * DSUtils.arrayCompare(arr, sorted));
	 * 
	 * arr = new int[] { 1, 2, 3, -3, -2, -1 }; sorted = new int[] { -3, -2, -1,
	 * 1, 2, 3 }; AlgoUtils.quickSort(arr); Assert.assertEquals(true,
	 * DSUtils.arrayCompare(arr, sorted));
	 * 
	 * arr = new int[] { 1 }; sorted = new int[] { 1 };
	 * AlgoUtils.quickSort(arr); Assert.assertEquals(true,
	 * DSUtils.arrayCompare(arr, sorted));
	 * 
	 * arr = new int[] { 1, 2 }; sorted = new int[] { 1, 2 };
	 * AlgoUtils.quickSort(arr); Assert.assertEquals(true,
	 * DSUtils.arrayCompare(arr, sorted));
	 * 
	 * arr = new int[] { 2, 1 }; sorted = new int[] { 1, 2 };
	 * AlgoUtils.quickSort(arr); Assert.assertEquals(true,
	 * DSUtils.arrayCompare(arr, sorted));
	 * 
	 * arr = new int[] { 3, 2, 1 }; sorted = new int[] { 1, 2, 3 };
	 * AlgoUtils.quickSort(arr); Assert.assertEquals(true,
	 * DSUtils.arrayCompare(arr, sorted));
	 * 
	 * arr = new int[] { 3, 1, 2 }; sorted = new int[] { 1, 2, 3 };
	 * AlgoUtils.quickSort(arr); Assert.assertEquals(true,
	 * DSUtils.arrayCompare(arr, sorted));
	 * 
	 * arr = new int[] { 1, 1, 1 }; sorted = new int[] { 1, 1, 1 };
	 * AlgoUtils.quickSort(arr); Assert.assertEquals(true,
	 * DSUtils.arrayCompare(arr, sorted));
	 * 
	 * arr = new int[] { 1, 1 }; sorted = new int[] { 1, 1};
	 * AlgoUtils.quickSort(arr); Assert.assertEquals(true,
	 * DSUtils.arrayCompare(arr, sorted));
	 * 
	 * 
	 * }
	 */

}
