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

	/*QUICK SORT INCOMPLETE
	private static int partition(int start, int end, int pivot, int[] elements) {
		int leftPtr = start;
		int rtPtr = end-1;
		while (true) {
			while ((leftPtr < end) && (elements[leftPtr] < pivot)) {
				leftPtr++;
			}
			while ((rtPtr > start + 1) && (elements[rtPtr] > pivot)) {
				rtPtr--;
			}
			System.out.println("Left Ptr:" + leftPtr);
			System.out.println("Rt Ptr:" + rtPtr);
			if (leftPtr < rtPtr) {
				int temp = elements[leftPtr];
				elements[leftPtr] = elements[rtPtr];
				elements[rtPtr] = temp;
				leftPtr++;
				rtPtr--;
			} else {
				break;
			}
		}
		if (leftPtr != end) {
			int temp = elements[leftPtr];
			elements[leftPtr] = elements[end];
			elements[end] = temp;
		}
		System.out.println("Pivot location:" + leftPtr);
		return leftPtr;
	}

	private static int partition1(int start, int end, int pivot, int[] elements)
	{
		int[] leftPartition = new int[elements.length];
	}
	public static void quickSort(int[] elements) {
		System.out.println("Calling very first one");
		quickSort(0, elements.length - 1, elements);
	}

	private static void quickSort(int start, int end, int[] elements) {
		System.out.println("start:" + start);
		System.out.println("end:" + end);
		if ((end - start) <= 0) {
			return;
		} else {
			int pivot = elements[end];
			System.out.println("\nPivot:" + pivot);
			int newPivotLoc = partition(start, end, pivot, elements);
			System.out.println("After partitioning:");
			for (int i = start; i < elements.length; i++) {
				System.out.print(elements[i] + " ");
			}
			System.out.println("Calling 1st one");
			quickSort(start, newPivotLoc - 1, elements);
			System.out.println("Calling 2nd one");
			quickSort(newPivotLoc + 1, end, elements);
		}
	}*/

	public static void main(String[] args) {
		System.out.println(binarySearch(new int[] { -3, -2, -1, 1, 2, 3 }, 0));
		System.out
				.println(binarySearch(new int[] { -3, -2, -1, 0, 1, 2, 3 }, 0));
		System.out.println(binarySearch(new int[] { -3, -2, -1 }, 0));
		System.out.println(binarySearch(new int[] { 1, 2, 3 }, 0));
		System.out.println(binarySearch(new int[] { -1, 1 }, 0));
	}

}
