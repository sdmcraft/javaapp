package tools;

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

		int[] rankedIndices = rankIndices(charCounts, n);

		for (int i = 0; i < rankedIndices.length; i++) {
			result[i] = (char) ('a' + rankedIndices[i]);
		}
		
		System.out.println("Least " + n +" occurrances: ");
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

	private static int[] rankIndices(int[] input, int k) {
		int[] origIndex = new int[input.length];
		for (int i = 0; i < origIndex.length; i++) {
			origIndex[i] = i;
		}
		quickSelect(input, 0, input.length - 1, k, origIndex);
		int[] result = new int[k];

		for (int i = 0; i < k; i++)
			result[i] = origIndex[i];
		return result;
	}

	public static int quickSelect(int[] input, int k) {
		quickSelect(input, 0, input.length - 1, k, null);
		return input[k];
	}

	private static int quickSelect(int[] input, int start, int end, int k,
			int[] origIndex) {
		int pivot = input[(start + end) / 2];
		int rank = -1;
		while (rank != k) {
			rank = partition(input, pivot, start, end, origIndex)[2];
			// System.out.println("\nRank of " + pivot + " = "+rank);
			if (rank < k)
				rank = quickSelect(input, rank + 1, end, k, origIndex);
			else if (rank > k)
				rank = quickSelect(input, start, rank - 1, k, origIndex);
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

	public static void main(String[] args) throws Exception {
		// System.out.println(binarySearch(new int[] { -3, -2, -1, 1, 2, 3 },
		// 0));
		// System.out
		// .println(binarySearch(new int[] { -3, -2, -1, 0, 1, 2, 3 }, 0));
		// System.out.println(binarySearch(new int[] { -3, -2, -1 }, 0));
		// System.out.println(binarySearch(new int[] { 1, 2, 3 }, 0));
		// System.out.println(binarySearch(new int[] { -1, 1 }, 0));
//		int[] input = new int[5];
//		for (int i = 0; i < 5; i++)
//			input[i] = (int) (Math.random() * 100);
//
//		System.out.println("Initial Array:");
//		for (int i : input)
//			System.out.print(" " + i);
//
//		System.out.println("\n");
//		int[] rankedIndices = rankIndices(input, 2);
//		System.out.println("Smallest 2 ranked indices: ");
//		for (int i : rankedIndices)
//			System.out.print(" " + i);
		//		
		// System.out.println("\n");
		// Thread.sleep(10000);
		// quickSort(new int[] { 1, 1 });
		// quickSort(input);

//		System.out.println("\n");
//		int k = 0;
//		System.out.println(quickSelect(input, k) + " is ranked " + k);
//
//		quickSort(input);
//		System.out.println("\nSorted Array:");
//		for (int i : input)
//			System.out.print(" " + i);
		
		String input = "abcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxbcdefghijklmnopqrstuvwzzz";
		topNchars(input.toCharArray(),3);
	}

}
