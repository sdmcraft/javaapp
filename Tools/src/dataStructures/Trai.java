package dataStructures;

public class Trai extends Tree {

	public void add(int item) {
		add(new Integer(item).toString().toCharArray());
	}

	private void add(char[] charArray) {

		Tree root = this;
		for (char c : charArray) {
			root = add(c, root);
		}
	}

	private static Tree add(char c, Tree root) {
		for (Tree node : root.getChildren()) {
			if (node.getValue() == c - '0') {
				return node;
			}
		}
		Tree node = new Tree(c - '0');
		root.addChild(node);
		return node;
	}

	public static void main(String[] args) {
		Trai trai = new Trai();
		trai.add(12121212);
		trai.add(123123);
		System.out.println(trai.doDFS());
	}
}
