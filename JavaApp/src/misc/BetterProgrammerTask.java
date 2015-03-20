package misc;

import java.util.ArrayList;
import java.util.List;

public class BetterProgrammerTask {

	public static Change getCorrectChange(int cents) {
		/*
		 * Please implement this method to take cents as a parameter and return
		 * an equal amount in dollars and coins using the minimum number of
		 * coins possible. For example: 164 cents = 1 dollar, 2 quarters, 1 dime
		 * and 4 cents.
		 * 
		 */
		int amount = cents;
		int dollar = amount / 100;
		amount = amount % 100;
		int quarter = amount / 25;
		amount = amount % 25;
		int dime = amount / 10;
		amount = amount % 10;
		int nickel = amount / 5;
		amount = amount % 5;
		return new Change(dollar, quarter, dime, nickel, amount);
	}

	// Please do not change this class
	static class Change {
		private final int _dollars;
		private final int _quarters; // 25 cents
		private final int _dimes; // 10 cents
		private final int _nickels; // 5 cents
		private final int _cents; // 1 cent

		public Change(int dollars, int quarters, int dimes, int nickels,
				int cents) {
			_dollars = dollars;
			_quarters = quarters;
			_dimes = dimes;
			_nickels = nickels;
			_cents = cents;
		}

		public int getDollars() {
			return _dollars;
		}

		public int getQuarters() {
			return _quarters;
		}

		public int getDimes() {
			return _dimes;
		}

		public int getNickels() {
			return _nickels;
		}

		public int getCents() {
			return _cents;
		}
	}

	public static List<Integer> getPrimeNumbers(int from, int to) {
		/*
		 * Please implement this method to return a list of all prime numbers in
		 * the given range (inclusively). A prime number is a natural number
		 * that has exactly two distinct natural number divisors, which are 1
		 * and the prime number itself. The first prime numbers are: 2, 3, 5, 7,
		 * 11, 13
		 */
		List<Integer> result = new ArrayList<Integer>();
		for (int number = from; number <= to; number++) {
			int i;
			for (i = 2; i < number / 2; i++) {
				if (number % i == 0) {
					break;
				}
			}
			if ((i == number / 2 && number != 4) || number == 2 || number == 3) {
				result.add(number);
				if (number != 2)
					number++;
			}
		}
		return result;
	}

	// Please do not change this interface
	public static interface Node {
		int getValue();

		List<Node> getChildren();
	}

	public static List<Node> traverseTreeInDepth(Node root) {
		/*
		 * Please implement this method to traverse the tree in depth and return
		 * a list of all passed nodes.
		 * 
		 * The method shall work optimally with large trees.
		 */
		List<Node> result = new ArrayList<Node>();
		if (root.getChildren() == null)
			result.add(root);
		else {
			for (Node n : root.getChildren()) {
				result.addAll(traverseTreeInDepth(n));
			}
		}
		return result;
	}

	private static int count = 0;

	public static int countWaysToJump(int N) {
		/*
		 * A set of stairs has N steps. You can jump either 1 or 2 steps at a
		 * time. For example, if the stairs is N=4 steps, you can reach the end
		 * in 5 possible ways: 1-1-1-1, or 1-2-1 or 1-1-2 or 2-1-1 or 2-2 Please
		 * implement this method to return the count of the different ways to
		 * reach the end of the stairs with N steps.
		 */
		if (N == 0)
			count++;
		if (N > 0) {
			countWaysToJump(N - 1);
		}
		if (N > 1) {
			countWaysToJump(N - 2);
		}
		return count;
	}

	public static void path(int steps) {

	}

	public static void main(String[] args) {
		Change change = getCorrectChange(0);
		System.out.println(change.getDollars() + " " + change.getQuarters()
				+ " " + change.getDimes() + " " + change.getNickels()
				+ change.getCents());
		System.out.println(getPrimeNumbers(0, 100));
		System.out.println(countWaysToJump(2));
	}
}
