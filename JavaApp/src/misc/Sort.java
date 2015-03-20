package misc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sort {
	List<Integer> nums;

	public Sort() {
		nums = new ArrayList<Integer>();
	}

	/**
	 * Method which takes an array of numbers from a file and stores them in an
	 * Arraylist or some other datastructure. Method must throw the file not
	 * found exception.
	 */
	public void in(String fn) throws FileNotFoundException {
		FileReader reader = new FileReader(fn);
		Scanner input = new Scanner(reader);

		while (input.hasNextInt()) {
			nums.add(input.nextInt());
		}
	}

	/**
	 * Method which out puts the values in the datastructure.
	 */
	public void out() {
		for (int i = 0; i < nums.size(); i++) {
			System.out.println(nums.get(i));
		}
	}

	/**
	 * Insertion sort method which sorts an array of integers into asceding
	 * order in relation to each other. Good for small arrays.
	 */
	public void insertionSort() {
		for (int i = 1; i <= nums.size() - 1; i++) {
			boolean flag = false;
			// Store the next value
			Integer key = nums.get(i);
			int j = i; // current index

			while ((j > 0) && (key < nums.get(j - 1)))
			// do the loop while the next index is greater than 0, and the value
			// of key is less than value in index j - 1.
			{
				flag = true;
				nums.remove(j);
				nums.add(j, nums.get(j - 1));
				nums.remove(j - 1);
				// nums.get(j) = nums.get(j - 1); //push the space left
				j--;
				nums.add(j,key);
			}
			// System.out.println("Size:" + nums.size());
			// System.out.println(i);
			// System.out.println(j);
			if (flag)
				nums.add(j, key);
		}
	}

	public static void main(String args[]) throws FileNotFoundException {
		Sort s = new Sort();
		s.in("/home/satyam/Desktop/file.txt");
		s.insertionSort();
		s.out();
	}
}