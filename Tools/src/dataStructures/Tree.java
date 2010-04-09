package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class Tree {

	private List<Tree> children;
	private int value;

	public Tree() {
		children = new ArrayList<Tree>();		
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

}
