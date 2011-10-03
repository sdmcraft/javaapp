package dataStructures;

public class Trai extends Tree {

	public void add(String item) {
		add(item.toCharArray());
	}

	public void put(String key, String value) {
		Tree node = add(key.toCharArray());
		node.contents.add(value);
	}

	public String get(String key) {
		return get(key, 0, this);
	}

	private Tree add(char[] charArray) {

		Tree root = this;
		for (char c : charArray) {
			root = add(c, root);
		}
		return root;
	}

	private static String get(String string, int index, Tree root) {
		for (Tree child : root.children) {
			if (child.value.equals(string.charAt(index))) {
				if (index == string.length() - 1)
					return child.contents.get(0);
				else {
					get(string, index++, child);
				}
			}
		}
		return null;
	}

	private static Tree add(char c, Tree root) {
		String string = new String(new char[] { c });
		for (Tree node : root.getChildren()) {
			if (node.getValue().equals(string)) {
				return node;
			}
		}
		Tree node = new Tree(string);
		root.addChild(node);
		return node;
	}

	public static void main(String[] args) throws Exception {
		Trai trai = new Trai();
		/*
		 * trai.add("A"); trai.add("to"); trai.add("tea"); trai.add("ted");
		 * trai.add("ten"); trai.add("tent"); trai.add("tense"); trai.add("i");
		 * trai.add("in"); trai.add("inn"); trai.add("inner");
		 * trai.add("insane"); // System.out.println(trai.getDiagram());
		 * trai.setLevels(); trai.siblingify();
		 * System.out.println(trai.getDiagram()); System.out.println("Height:" +
		 * trai.getHeight());
		 */
		trai.put("a", "apple");
		System.out.println(trai.get("a"));
		System.out.println(trai.getDiagram());
	}
}
