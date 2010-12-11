package tools;

import java.util.List;

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
				bitArray.shift(input[i] - offset);
				offset = input[i];
			}
			int index = input[i] - offset;
			bitArray.increment(index);
		}
		for (int j = 0, i = 0; i < bitArray.length(); i++) {
			for (int k = 0; k < bitArray.get(i); k++) {
				input[j] = i + offset;
				j++;
			}
		}
	}

	private static String[] longestIncSubseq(int[] input) throws Exception {
		ArrayStack[] stackStore = new ArrayStack[input.length];

		for (int k = 0; k < input.length; k++) {
			stackStore[k] = new ArrayStack(input.length);
			int max = k;
			for (int j = 0; j < k; j++) {
				if (input[k] > input[j]) {
					if (stackStore[j].getSize() >= stackStore[max].getSize()) {
						max = j;
					}
				}
			}
			stackStore[k] = stackStore[max].cloneMe();
			while (!stackStore[k].isEmpty()
					&& Integer.parseInt(stackStore[k].peek()) > input[k])
				stackStore[k].pop();
			stackStore[k].push(Integer.toString(input[k]));
		}

		int max = 0;
		for (int i = 0; i < stackStore.length; i++) {
			if (stackStore[i].getSize() > stackStore[max].getSize())
				max = i;
		}

		return stackStore[max].toArray();
	}

	public static void main(String[] args) throws Exception {
		int[] input = new int[] { 2,4,8,10,11,12,13,14,5,6,7,3,4,5,0,7,8};
		String[] output = longestIncSubseq(input);
		for (String i : output) {
			System.out.print(" " + i);
		}

	}
}
