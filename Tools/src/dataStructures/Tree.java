package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {

	private List<Tree> children;
	private char value;
	private String nodeID;
	private String diagram;
	private Map<Character, Integer> intCount;

	public Tree() {
		children = new ArrayList<Tree>();
		diagram = "digraph G {\n";
		intCount = new HashMap<Character, Integer>();
	}

	public Tree(char value) {
		children = new ArrayList<Tree>();
		this.value = value;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public void addChild(Tree child) {
		children.add(child);
	}

	public String getDiagram() {
		getDiagram(this);
		diagram += "}";
		return diagram;
	}

	private void getDiagram(Tree root) {
		if (root.getChildren() != null && root.getChildren().size() > 0) {
			for (Tree child : root.getChildren()) {
				char value = child.getValue();
				String nodeID = value + "";
				if (intCount.containsKey(value)) {
					for (int i = 0; i < intCount.get(value); i++) {
						nodeID += "*";
					}
					intCount.put(value, intCount.get(value) + 1);
				} else {
					intCount.put(value, 1);
				}
				child.nodeID = nodeID;
				diagram += "\"" + root.nodeID + "\"" + "->" + "\""
						+ child.nodeID + "\"" + ";\n";
				getDiagram(child);
			}
		}
	}
}
