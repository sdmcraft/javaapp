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

	// public static char[] topNchars(char[] input, int n) {
	// char[] result = new char[n];
	// int[] charCounts = new int[26];
	// for (int i = 0; i < input.length; i++) {
	// charCounts[input[i] - 'a']++;
	// }
	//
	// for (int i = 0; i < n; i++) {
	// int j = 0;
	// for (; j < n - i; j++) {
	// if (charCounts[j] > charCounts[j + 1]) {
	// int temp = charCounts[j];
	// charCounts[j] = charCounts[j + 1];
	// charCounts[j + 1] = temp;
	// }
	// }
	// //result[i] = charCounts[j] - '';
	// }
	// }

	public static void quickSort(int[] input) {
		int start = 0;
		int end = input.length - 1;
		quickSort(input, start, end);
	}

	private static void quickSort(int[] input, int start, int end) {
		int pivot = input[(start + end) / 2];
		int rank = partition1(input, pivot, start, end);
		System.out.println("Rank:" + rank);
		if (start < rank - 1 && rank >= 0)
			quickSort(input, start, rank - 1);
		if (rank + 1 < end && rank >= 0)
			quickSort(input, rank + 1, end);

	}

	private static int partition1(int[] input, int pivot, int start, int end) {
		int leftPtr = start;
		int rtPtr = end;

		System.out.println("start->" + start);
		System.out.println("end->" + end);
		System.out.println("pivot->" + pivot);

		while (leftPtr < rtPtr && leftPtr <= end-1 && rtPtr >= start) {
			while (leftPtr < rtPtr && input[leftPtr] <= pivot) {
				leftPtr++;
			}
			while (leftPtr < rtPtr && input[rtPtr] >= pivot) {
				rtPtr--;
			}
			if (leftPtr < rtPtr) {
				int temp = input[leftPtr];
				input[leftPtr] = input[rtPtr];
				input[rtPtr] = temp;
			} 
		}

		int rank = 0;
		if (leftPtr <= end-1 && input[leftPtr] == pivot) {
			rank = leftPtr;
		} else if (rtPtr >= start && input[rtPtr] == pivot) {
			rank = rtPtr;
		} else
			rank = -1;

		return rank;
	}

	private static void partition(int[] input, int start, int end) {
		int leftPtr = start;
		int rtPtr = end;
		int pivot = input[(start + end) / 2];
		// System.out.println("start->" + start);
		// System.out.println("end->" + end);
		// System.out.println("pivot->" + pivot);
		while (leftPtr < rtPtr) {
			while (input[leftPtr] < pivot)
				leftPtr++;
			while (input[rtPtr] > pivot)
				rtPtr--;

			int temp = input[leftPtr];
			input[leftPtr] = input[rtPtr];
			input[rtPtr] = temp;
		}

		int rank = 0;
		if (input[leftPtr] == pivot) {
			rank = leftPtr;
		} else if (input[rtPtr] == pivot) {
			rank = rtPtr;
		}
		if (start < rank - 1)
			partition(input, start, rank - 1);
		if (rank + 1 < end)
			partition(input, rank + 1, end);
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(binarySearch(new int[] { -3, -2, -1, 1, 2, 3 },
		// 0));
		// System.out
		// .println(binarySearch(new int[] { -3, -2, -1, 0, 1, 2, 3 }, 0));
		// System.out.println(binarySearch(new int[] { -3, -2, -1 }, 0));
		// System.out.println(binarySearch(new int[] { 1, 2, 3 }, 0));
		// System.out.println(binarySearch(new int[] { -1, 1 }, 0));
		int[] input = new int[14];
		for (int i = 0; i < 14; i++)
			input[i] = (int) (Math.random() * 100);

		System.out.println("Initial Array:");
		for (int i : input)
			System.out.print(" " + i);

		//Thread.sleep(10000);
		//quickSort(new int[] { 1, 1 });
		quickSort(input);
		
		System.out.println("\nModified Array:");
		for (int i : input)
			System.out.print(" " + i);
	}

}
