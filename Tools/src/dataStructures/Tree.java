package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {

	private List<Tree> children;
	private int value;
	public String valueString;
	private String diagram;
	private Map<Integer, Integer> intCount;

	public Tree() {
		children = new ArrayList<Tree>();
		diagram = "digraph G {\n";
		intCount = new HashMap<Integer, Integer>();
	}

	public Tree(int value) {
		children = new ArrayList<Tree>();
		this.value = value;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void addChild(Tree child) {
		children.add(child);
	}

	public String doDFS() {
		doDFS(this);
		diagram += "}";
		return diagram;
	}

	private void doDFS(Tree root) {
		if (root.getChildren() != null && root.getChildren().size() > 0) {
			for (Tree child : root.getChildren()) {
				int value = child.getValue();
				String valueString = value + "";
				if (intCount.containsKey(value)) {
					for (int i = 0; i < intCount.get(value); i++) {
						valueString += "*";
					}
					intCount.put(value, intCount.get(value) + 1);
				} else {
					intCount.put(value, 1);
				}
				child.valueString = valueString;
				diagram += "\"" + root.valueString + "\"" + "->" + "\""
						+ child.valueString + "\"" + ";\n";
				doDFS(child);
			}
		}
	}

}
