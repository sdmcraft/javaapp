package dataStructures;

public class SuffixTree extends Trai {

	public SuffixTree(String string) {
		for (int i = 0; i < string.length(); i++) {
			add(string.substring(i));
		}
		this.compact();
	}
	
	private void compact() {
		compact(this);
	}

	private void compact(Tree root) {
		while (root.getChildren().size() == 1) {
			root.value += root.getChildren().get(0).value;
			root.children = root.getChildren().get(0).children;
		}
		for (Tree child : root.getChildren()) {
			compact(child);
		}
	}
	
	public static void main(String[] args) {
		SuffixTree suffixTree = new SuffixTree("mississippi");
		System.out.println(suffixTree.getDiagram());
	}

}
