package tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataStructures.ArrayStack;
import dataStructures.BitArray;

public class AlgoUtils {

	public static int binarySearch(int[] array, int value) {
		int begin = 0;
		int end = array.length - 1;
		int mid = -1;
		int place = -1;
		while (begin < end && begin >= 0 && end >= 0) {
			mid = (begin + end) / 2;
			// System.out
			// .println("begin:" + begin + " mid:" + mid + " end:" + end);

			if (value == array[mid]) {
				return mid;
			} else if (value < array[mid]) {
				end = mid - 1;
				place = end;
			} else if (value > array[mid]) {
				begin = mid + 1;
				place = begin;
			}
			if (begin == end) {
				if (value > array[begin])
					place = begin + 1;
				break;
			}
		}
		return place;
	}

	public static void bubbleSort(int[] elements) {
		for (int outer = elements.length; outer > 0; outer--) {
			for (int inner = 0; inner < outer - 1; inner++) {
				if (elements[inner] > elements[inner + 1]) {
					int temp = elements[inner];
					elements[inner] = elements[inner + 1];
					elements[inner + 1] = temp;
				}
			}
		}
	}

	public static void selectionSort(int[] elements) {
		for (int outer = 0; outer < elements.length; outer++) {
			int min = outer;
			for (int inner = outer; inner < elements.length; inner++) {
				if (elements[inner] < elements[min]) {
					min = inner;
				}
			}
			int temp = elements[min];
			elements[min] = elements[outer];
			elements[outer] = temp;
		}
	}

	public static void insertionSort(int[] elements) {
		for (int outer = 1; outer < elements.length; outer++) {
			int temp = elements[outer];
			int inner = outer;
			while (inner > 0 && temp < elements[inner - 1]) {
				elements[inner] = elements[inner - 1];
				inner--;
			}
			elements[inner] = temp;
		}
	}

	public static void shellSort(int[] elements) {
		int h = 1;
		while (h <= elements.length / 3) {
			h = 3 * h + 1;
		}
		while (h > 0) {
			for (int outer = h; outer < elements.length; outer = outer + h) {
				int temp = elements[outer];
				int inner = outer;
				while (inner > (h - 1) && temp < elements[inner - h]) {
					elements[inner] = elements[inner - h];
					inner -= h;
				}
				elements[inner] = temp;
			}
			h = (h - 1) / 3;
		}
	}

	public static char[] topNchars(char[] input, int n) {
		char[] result = new char[n];
		int[] charCounts = new int[26];
		for (int i = 0; i < input.length; i++) {
			charCounts[input[i] - 'a']++;
		}

		int[] rankedIndices = rankIndices(charCounts, n, "max");

		for (int i = 0; i < rankedIndices.length; i++) {
			result[i] = (char) ('a' + rankedIndices[i]);
		}

		System.out.println("Top " + n + " occurrances: ");
		for (char i : result)
			System.out.print(" " + i);

		return result;

	}

	public static void quickSort(int[] input) {
		int start = 0;
		int end = input.length - 1;
		quickSort(input, start, end);
	}

	private static void quickSort(int[] input, int start, int end) {
		int pivot = input[(start + end) / 2];
		int[] ptrs = partition(input, pivot, start, end, null);

		if (start < ptrs[1])
			quickSort(input, start, ptrs[1]);
		if (ptrs[0] < end)
			quickSort(input, ptrs[0], end);

	}

	private static int[] rankIndices(int[] input, int k, String mode) {
		int[] origIndex = new int[input.length];
		for (int i = 0; i < origIndex.length; i++) {
			origIndex[i] = i;
		}
		quickSelect(input, 0, input.length - 1, k, origIndex, mode);
		int[] result = new int[k];

		for (int i = 0; i < k; i++)
			result[i] = origIndex[i];
		return result;
	}

	private static int quickSelect(int[] input, int start, int end, int k,
			int[] origIndex, String mode) {
		int pivot = input[(start + end) / 2];
		int rank = -1;
		while (rank != k) {
			rank = partition(input, pivot, start, end, origIndex, mode)[2];
			// System.out.println("\nRank of " + pivot + " = "+rank);
			if (rank < k)
				rank = quickSelect(input, rank + 1, end, k, origIndex, mode);
			else if (rank > k)
				rank = quickSelect(input, start, rank - 1, k, origIndex, mode);
		}
		return rank;
	}

	private static int[] partition(int[] input, int pivot, int start, int end,
			int[] origIndex) {
		int leftPtr = start;
		int rtPtr = end;
		int rank = 0;

		// System.out.println("start->" + start);
		// System.out.println("end->" + end);
		// System.out.println("pivot->" + pivot);

		while (leftPtr <= rtPtr) {
			while (leftPtr < end && input[leftPtr] < pivot) {
				leftPtr++;
			}
			while (start < rtPtr && input[rtPtr] > pivot) {
				rtPtr--;
			}
			if (leftPtr <= rtPtr) {
				int temp = input[leftPtr];
				input[leftPtr] = input[rtPtr];
				input[rtPtr] = temp;

				if (origIndex != null) {
					temp = origIndex[leftPtr];
					origIndex[leftPtr] = origIndex[rtPtr];
					origIndex[rtPtr] = temp;
				}

				if (pivot == input[leftPtr])
					rank = leftPtr;
				else if (pivot == input[rtPtr])
					rank = rtPtr;

				leftPtr++;
				rtPtr--;
			}
		}
		return new int[] { leftPtr, rtPtr, rank };
	}

	private static int[] partition(int[] input, int pivot, int start, int end,
			int[] origIndex, String mode) {
		int leftPtr = start;
		int rtPtr = end;
		int rank = 0;
		while (leftPtr <= rtPtr) {
			if ("min".equals(mode)) {
				while (leftPtr < end && input[leftPtr] < pivot) {
					leftPtr++;
				}
			} else {
				while (leftPtr < end && input[leftPtr] > pivot) {
					leftPtr++;
				}
			}

			if ("min".equals(mode)) {
				while (start < rtPtr && input[rtPtr] > pivot) {
					rtPtr--;
				}
			} else {
				while (start < rtPtr && input[rtPtr] < pivot) {
					rtPtr--;
				}
			}
			if (leftPtr <= rtPtr) {
				int temp = input[leftPtr];
				input[leftPtr] = input[rtPtr];
				input[rtPtr] = temp;

				if (origIndex != null) {
					temp = origIndex[leftPtr];
					origIndex[leftPtr] = origIndex[rtPtr];
					origIndex[rtPtr] = temp;
				}

				if (pivot == input[leftPtr])
					rank = leftPtr;
				else if (pivot == input[rtPtr])
					rank = rtPtr;

				leftPtr++;
				rtPtr--;
			}
		}
		return new int[] { leftPtr, rtPtr, rank };
	}

	public static void sort(List<String> input, String sortingAlgo) {
		int[] inputArray = new int[input.size()];
		for (int i = 0; i < input.size(); i++) {
			inputArray[i] = Integer.parseInt(input.get(i));
		}
		if ("quicksort".equals(sortingAlgo)) {
			quickSort(inputArray);
		}
		input.clear();
		for (int i = 0; i < inputArray.length; i++) {
			input.add(Integer.toString(inputArray[i]));
		}

	}

	private static ArrayStack stackSort(ArrayStack stack) throws Exception {
		ArrayStack stack2 = new ArrayStack(stack.getSize());
		stack2.push(stack.pop());
		while (!stack.isEmpty()) {
			String val = stack.pop();
			while (!stack2.isEmpty()
					&& Integer.parseInt(val) > Integer.parseInt(stack2.peek())) {
				stack.push(stack2.pop());
			}
			stack2.push(val);
		}
		return stack2;
	}

	private static int[] mergeSort(int[] input) {
		if (input.length == 1)
			return input;
		int[] firstHalf = new int[input.length / 2];
		int[] secondHalf = new int[input.length % 2 == 0 ? input.length / 2
				: input.length / 2 + 1];
		int i = 0;
		for (; i < input.length / 2; i++)
			firstHalf[i] = input[i];
		for (; i < input.length; i++)
			secondHalf[i - input.length / 2] = input[i];
		firstHalf = mergeSort(firstHalf);
		secondHalf = mergeSort(secondHalf);
		return merge(firstHalf, secondHalf);
	}

	private static int[] merge(int[] arr1, int[] arr2) {
		int i = 0;
		int j = 0;
		int k = 0;
		int[] merged = new int[arr1.length + arr2.length];
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j])
				merged[k++] = arr1[i++];
			else
				merged[k++] = arr2[j++];
		}

		while (i < arr1.length)
			merged[k++] = arr1[i++];

		while (j < arr2.length)
			merged[k++] = arr2[j++];

		return merged;

	}

	/* WIP */
	private static void externalSort(String file, int start, int end, int memory)
			throws Exception {
		if ((end - start) <= memory) {
			String[] data = IOUtils.fileToArray(file, start, end);
			int[] intData = DSUtils.stringArrayToIntArray(data);
			quickSort(intData);
			data = DSUtils.intArrayToStringArray(intData);
			IOUtils.replace(file, start, data);
			return;
		}
		externalSort(file, start, start + (end - start) / 2, memory);
		externalSort(file, start + (end - start) / 2 + 1, end, memory);
	}

	/* WIP */
	private static void merge(String file, int start1, int end1, int start2,
			int end2) {

	}

	private static void countingSort(int[] input) {
		int offset = input[0];
		BitArray bitArray = new BitArray();
		for (int i = 0; i < input.length; i++) {
			if (offset > input[i]) {
				bitArray.leftExpand(offset - input[i]);
				offset = input[i];
			}
			int index = input[i] - offset;
			bitArray.increment(index);
		}
		System.out.println(bitArray.toString());
		for (int j = 0, i = 0; i < bitArray.length(); i++) {
			for (int k = 0; k < bitArray.get(i); k++) {
				input[j] = i + offset;
				j++;
			}
		}
	}

	/* http://www.algorithmist.com/index.php/Longest_Increasing_Subsequence */
	private static String[] longestIncSubseq(int[] input) throws Exception {
		ArrayStack[] stackStore = new ArrayStack[input.length];

		for (int k = 0; k < input.length; k++) {
			stackStore[k] = new ArrayStack(input.length);
			int max = 0;
			for (int j = 0; j < k; j++) {
				if (input[k] > input[j]) {
					if (stackStore[j].getSize() >= stackStore[max].getSize()) {
						max = j;
					}
				}
			}
			stackStore[k] = stackStore[max].cloneMe();
			stackStore[k].push(Integer.toString(input[k]));
		}

		int max = 0;
		for (int i = 0; i < stackStore.length; i++) {
			if (stackStore[i].getSize() > stackStore[max].getSize())
				max = i;
		}

		return stackStore[max].toArray();
	}

	static int work = 0;

	/*
	 * KS(w[1..n],v[1..n],C) =
	 * Max(KS(w[2..n],v[2..n],C),KS(w[1..n],v[1..n],C-w[1]) + v[1])
	 */
	public static String[] knapsack(int[][] input, int index, int capacity) {

		work++;
		String contents = "";
		int value;
		if (capacity <= 0 || index >= input.length) {
			return new String[] { "0", "" };
		} else {

			String[] leave = knapsack(input, index + 1, capacity);
			int valueOnLeave = Integer.parseInt(leave[0]);

			boolean canTake = capacity - input[index][0] >= 0;
			String[] take = null;
			int valueOnTake = 0;
			if (canTake) {
				take = knapsack(input, index + 1, capacity - input[index][0]);
				valueOnTake = Integer.parseInt(take[0]) + input[index][1];
				value = valueOnLeave > valueOnTake ? valueOnLeave : valueOnTake;
				if (valueOnLeave > valueOnTake) {
					contents = leave[1];
				} else
					contents = input[index][1] + "," + take[1];

			} else {
				contents = leave[1];
				value = valueOnLeave;
			}
		}
		return new String[] { value + "", contents };
	}

	/*
	 * KS(w[1..n],v[1..n],C) =
	 * Max(KS(w[2..n],v[2..n],C),KS(w[1..n],v[1..n],C-w[1]) + v[1]) Uses the
	 * already calculated results by memoing them in knapsackStore
	 */
	public static String[] knapsack(int[][] input, int index, int capacity,
			String[][][] knapsackStore) {
		if (knapsackStore[index][capacity] == null) {
			work++;
			String contents = "";
			int value;
			if (capacity <= 0 || index >= input.length) {
				knapsackStore[index][capacity] = new String[] { "0", "" };
			} else {
				/* Descision of leaving index item */
				String[] leave = knapsack(input, index + 1, capacity,
						knapsackStore);
				int valueOnLeave = Integer.parseInt(leave[0]);

				/* Descision of taking index item if possible */
				boolean canTake = capacity - input[index][0] >= 0;
				String[] take = null;
				int valueOnTake = 0;
				if (canTake) {
					take = knapsack(input, index + 1, capacity
							- input[index][0], knapsackStore);
					valueOnTake = Integer.parseInt(take[0]) + input[index][1];
					value = valueOnLeave > valueOnTake ? valueOnLeave
							: valueOnTake;
					if (valueOnLeave > valueOnTake) {
						contents = leave[1];
					} else
						contents = input[index][1] + "," + take[1];

				} else {
					contents = leave[1];
					value = valueOnLeave;
				}
				knapsackStore[index][capacity] = new String[] { value + "",
						contents };
			}
		}
		return knapsackStore[index][capacity];
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(binarySearch(new int[] { -3, -2, -1, 1, 2, 3 },
		// 0));
		// System.out
		// .println(binarySearch(new int[] { -3, -2, -1, 0, 1, 2, 3 }, 0));
		// System.out.println(binarySearch(new int[] { -3, -2, -1 }, 0));
		// System.out.println(binarySearch(new int[] { 1, 2, 3 }, 0));
		// System.out.println(binarySearch(new int[] { -1, 1 }, 0));
		// int[] input = new int[5];
		// for (int i = 0; i < 5; i++)
		// input[i] = (int) (Math.random() * 100);
		//
		// System.out.println("Initial Array:");
		// for (int i : input)
		// System.out.print(" " + i);
		//
		// System.out.println("\n");
		// int[] rankedIndices = rankIndices(input, 2);
		// System.out.println("Smallest 2 ranked indices: ");
		// for (int i : rankedIndices)
		// System.out.print(" " + i);
		//		
		// System.out.println("\n");
		// Thread.sleep(10000);
		// quickSort(new int[] { 1, 1 });
		// quickSort(input);

		// System.out.println("\n");
		// int k = 0;
		// System.out.println(quickSelect(input, k) + " is ranked " + k);
		//
		// quickSort(input);
		// System.out.println("\nSorted Array:");
		// for (int i : input)
		// System.out.print(" " + i);

		// String input = "aabbccdddddefghijklmnopqrstuvwxyzzzzzzzzzzzzzzz";
		// topNchars(input.toCharArray(), 3);
		// ArrayStack stack = new ArrayStack(new String[] { "4", "1", "55", "5",
		// "2", "4", "55" });
		//
		// System.out.println(stackSort(stack));
		// int[] arr1 = new int[]{1,3,5,7,9,10};
		// int[] arr2 = new int[]{0,2,4,6,8};
		// int[] arr3 = merge(arr1,arr2);
		// for(int i : arr3)
		// System.out.println(i);
		// int[] input1 = new int[] { 1, 2, 100, 4, 75, 5, 6, 7, 101, 102, 103,
		// 104, 8, 9, 10, 11, 12, 13, 14, 15 };
		// int[] input2 = new int[] { 6, 3, 8, 5, 3, 4, 9, -5, 10, -2, -2, -2,
		// -2,
		// 0, 0, 0, 0, 0, 0, 10000, 10000 };
		//
		// countingSort(input1);
		// for (int i : input1) {
		// System.out.print(" " + i);
		// }
		//
		// System.out.println("\n");
		// bubbleSort(input2);
		// for (int i : input2) {
		// System.out.print(" " + i);
		// }
		//
		//		
		// System.out.println(7/2);
		// for (String s : longestIncSubseq(input1))
		// System.out.println(s);;

		int[][] input = new int[][] { { 0, 0 }, { 5, 10 }, { 7, 2 }, { 2, 6 },
				{ 4, 7 }, { 1, 3 }, { 6, 1 }, { 8, 5 }, { 9, 11 }, { 1, 5 },
				{ 4, 7 }, { 7, 8 } };

		String[] str = knapsack(input, 0, 12);
		System.out.println(str[0]);
		System.out.println(str[1]);
		System.out.println("Work:" + work);

		work = 0;
		str = knapsack(input, 0, 12, new String[input.length + 1][13][]);
		System.out.println(str[0]);
		System.out.println(str[1]);
		System.out.println("Work:" + work);
	}
}
