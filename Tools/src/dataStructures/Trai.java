package dataStructures;

public class Trai extends Tree {

	public void add(String item) {
		add(item.toCharArray());
	}

	private void add(char[] charArray) {

		Tree root = this;
		for (char c : charArray) {
			root = add(c, root);
		}
	}

	private static Tree add(char c, Tree root) {
		String string = new String(new char[]{c});
		for (Tree node : root.getChildren()) {
			if (node.getValue().equals(string)) {
				return node;
			}
		}
		Tree node = new Tree(string);
		root.addChild(node);
		return node;
	}

	public static void main(String[] args) {
		Trai trai = new Trai();
		trai.add("A");
		trai.add("to");
		trai.add("tea");
		trai.add("ted");
		trai.add("ten");
		trai.add("i");
		trai.add("in");
		trai.add("inn");
		//System.out.println(trai.getDiagram());
	}
}
