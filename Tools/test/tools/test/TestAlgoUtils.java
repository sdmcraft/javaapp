package tools.test;

import java.io.File;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import tools.AlgoUtils;
import tools.DSUtils;
import tools.IOUtils;

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
	public void testSort() throws Exception {
		int[] input = new int[Integer.MAX_VALUE/10000];
		for (int i = 0; i < input.length; i++) {
			int sign = (Math.random() - 0.5) > 0 ? 1 : -1;
			input[i] = (int) (sign * Math.random() * Integer.MAX_VALUE);
			if (input[i] == Integer.MAX_VALUE)
				--input[i];
			if (input[i] == Integer.MIN_VALUE)
				++input[i];
		}

		int[] ref = DSUtils.arrayCopy(input);
		long time = System.currentTimeMillis();
		Arrays.sort(ref);
		System.out.println("Time consumed(JDK sort):" + (System.currentTimeMillis() - time));
		
		int[] bubble = DSUtils.arrayCopy(input);
		time = System.currentTimeMillis();
		AlgoUtils.bubbleSort(bubble);
		Assert.assertEquals(true, DSUtils.arrayCompare(ref, bubble));
		System.out.println("Time consumed(Bubble sort):" + (System.currentTimeMillis() - time));
		
		
		int[] quick = DSUtils.arrayCopy(input);
		time = System.currentTimeMillis();
		AlgoUtils.quickSort(quick);
		Assert.assertEquals(true, DSUtils.arrayCompare(ref, quick));
		System.out.println("Time consumed(Quick sort):" + (System.currentTimeMillis() - time));

		int[] selection = DSUtils.arrayCopy(input);
		time = System.currentTimeMillis();
		AlgoUtils.selectionSort(selection);
		Assert.assertEquals(true, DSUtils.arrayCompare(ref, selection));
		System.out.println("Time consumed(selection sort):" + (System.currentTimeMillis() - time));

		int[] insertion = DSUtils.arrayCopy(input);
		time = System.currentTimeMillis();
		AlgoUtils.insertionSort(insertion);
		Assert.assertEquals(true, DSUtils.arrayCompare(ref, insertion));
		System.out.println("Time consumed(insertion sort):" + (System.currentTimeMillis() - time));

		int[] shell = DSUtils.arrayCopy(input);
		time = System.currentTimeMillis();
		AlgoUtils.shellSort(shell);
		Assert.assertEquals(true, DSUtils.arrayCompare(ref, shell));
		System.out.println("Time consumed(shell sort):" + (System.currentTimeMillis() - time));

		int[] merge = DSUtils.arrayCopy(input);
		time = System.currentTimeMillis();
		merge = AlgoUtils.mergeSort(merge);
		Assert.assertEquals(true, DSUtils.arrayCompare(ref, merge));
		System.out.println("Time consumed(merge sort):" + (System.currentTimeMillis() - time));

		/*int[] counting = DSUtils.arrayCopy(input);
		time = System.currentTimeMillis();
		AlgoUtils.countingSort(counting);
		Assert.assertEquals(true, DSUtils.arrayCompare(ref, counting));
		System.out.println("Time consumed(counting sort):" + (System.currentTimeMillis() - time));*/

		int[] external = DSUtils.arrayCopy(input);
		IOUtils.arrayToFile(external, "c:\\temp1\\input.txt");
		
		time = System.currentTimeMillis();
		AlgoUtils.externalSort(new File("c:\\temp1\\input.txt"), "C:\\temp1", 10000);
		external = DSUtils.stringArrayToIntArray(IOUtils.fileToArray("C:\\temp1\\output.txt"));
		Assert.assertEquals(true, DSUtils.arrayCompare(ref, external));
		System.out.println("Time consumed(external sort):" + (System.currentTimeMillis() - time));

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
