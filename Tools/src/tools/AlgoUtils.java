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

	public static void main(String[] args) {
		System.out.println(binarySearch(new int[] { -3, -2, -1, 1, 2, 3 }, 0));
		System.out
				.println(binarySearch(new int[] { -3, -2, -1, 0, 1, 2, 3 }, 0));
		System.out.println(binarySearch(new int[] { -3, -2, -1 }, 0));
		System.out.println(binarySearch(new int[] { 1, 2, 3 }, 0));
		System.out.println(binarySearch(new int[] { -1, 1 }, 0));
	}

}
