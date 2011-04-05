package dataStructures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.IOUtils;

public class Tree implements Cloneable, Serializable {

	protected List<Tree> children;
	protected String value;
	private String nodeID;
	private String diagram;
	private Map<String, Integer> intCount;
	private int terminalCount;
	private Tree sibling;

	protected int depth = 0;
	private int height = 0;

	@Override
	public Object clone() {
		try {
			return IOUtils.deepCopy(this);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public Tree() {
		children = new ArrayList<Tree>();
		diagram = "digraph G {\n";
		intCount = new HashMap<String, Integer>();
	}

	public Tree(String value) {
		children = new ArrayList<Tree>();
		this.value = value;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void addChild(Tree child) {
		children.add(child);
	}

	private static void clearDiagram(Tree root) {
		if (root == null)
			return;
		root.diagram = "digraph G {\n";
		root.nodeID = null;
		root.intCount = new HashMap<String, Integer>();
		root.terminalCount = 0;
		for (Tree child : root.children) {
			clearDiagram(child);
		}
	}

	public String getDiagram() throws Exception {
		clearDiagram(this);
		setLevels();
		preDiagram();
		getDiagram(this);
		diagram += "}";
		return diagram;
	}

	public Tree getChild(char value) {
		Tree result = null;
		String valueString = new String(new char[] { value });
		for (Tree child : children) {
			if (valueString.equals(child.getValue())) {
				result = child;
				break;
			}
		}
		return result;
	}

	private void preDiagram() {
		nodeID = value + "(" + depth + ")";
		preDiagram(this);
	}

	private void preDiagram(Tree root) {
		if (root == null)
			return;
		if (root.getChildren() != null && root.getChildren().size() > 0) {
			for (Tree child : root.getChildren()) {
				String value = child.getValue();
				String nodeID = value;
				if (child.depth > 0)
					nodeID += "(" + child.depth + ")";
				if (intCount.containsKey(nodeID)) {
					int count = intCount.get(nodeID);
					for (int i = 0; i < count; i++) {
						nodeID += "*";
					}
					intCount.put(nodeID, count + 1);
				} else {
					intCount.put(nodeID, 1);
				}
				child.nodeID = nodeID;
				preDiagram(child);
			}
		}

		if (root instanceof BinaryTree) {
			if (((BinaryTree) root).getLeft() == null)
				terminalCount++;
			if (((BinaryTree) root).getRight() == null)
				terminalCount++;
		}
	}

	public void setLevels() throws Exception {
		ArrayQueue queue = new ArrayQueue(100);
		queue.insert(this);
		while (!queue.empty()) {
			Tree root = (Tree) queue.remove();
			for (Tree child : root.getChildren()) {
				child.depth = root.depth + 1;
				queue.insert(child);
			}
		}
	}

	public void childCount() throws Exception {
		ArrayQueue queue = new ArrayQueue(100);
		queue.insert(this);
		while (!queue.empty()) {
			Tree root = (Tree) queue.remove();
			System.out.println(root.value + "-->" + root.children.size());
			for (Tree child : root.getChildren()) {
				queue.insert(child);
			}
		}
	}

	public boolean gapNoMoreThanOne() throws Exception {
		this.setLevels();
		int upper = -1;
		int lower = -1;

		/* TODO Currently limited to 100 nodes */
		ArrayQueue queue = new ArrayQueue(100);
		queue.insert(this);
		while (!queue.empty()) {
			Tree root = (Tree) queue.remove();

			/* If this is a leaf */
			if (root.getChildren() == null || root.getChildren().size() == 0) {
				/* This is the first leaf we are encountering */
				if (upper == -1 && lower == -1) {
					upper = root.depth + 1;
					lower = root.depth - 1;
				}
				/* We haven't narrowed the range yet */
				else if (upper - lower > 1) {
					if (root.depth == upper)
						lower = upper - 1;
					else if (root.depth == lower)
						upper = lower + 1;
				}
				/* This leaf is outside the permissible range */
				if (root.depth < lower || root.depth > upper)
					return false;
			} else {
				for (Tree child : root.getChildren()) {
					queue.insert(child);
				}
			}
		}
		return true;

	}

	private ArrayQueue doBFT() throws Exception {
		ArrayQueue workQueue = new ArrayQueue(100);
		ArrayQueue resultQueue = new ArrayQueue(100);
		workQueue.insert(this);
		resultQueue.insert(this);
		while (!workQueue.empty()) {
			Tree root = (Tree) workQueue.remove();
			for (Tree child : root.getChildren()) {
				workQueue.insert(child);
				resultQueue.insert(child);
			}
		}
		return resultQueue;
	}

	private void getDiagram(Tree root) {
		if (root.sibling != null) {
			diagram += "\"" + root.nodeID + "\"" + "->" + "\""
					+ root.sibling.nodeID + "\"" + "[constraint=false];\n";
		}
		if (root.getChildren() != null && root.getChildren().size() > 0) {
			for (Tree child : root.getChildren()) {
				String color = null;
				if (root instanceof BinaryTree) {
					BinaryTree bt = (BinaryTree) root;

					if (child == bt.getLeft())
						color = "green";
					else if (child == bt.getRight())
						color = "red";

					BinaryTree btChild = (BinaryTree) child;
					if (btChild.getLeft() == null) {
						diagram += "null" + terminalCount + " [shape=point];\n";
						diagram += "\"" + child.nodeID + "\"" + "->" + "\""
								+ "null" + terminalCount
								+ "\" [color=green];\n";
						terminalCount--;
					}
					if (btChild.getRight() == null) {
						diagram += "null" + terminalCount + " [shape=point];\n";
						diagram += "\"" + child.nodeID + "\"" + "->" + "\""
								+ "null" + terminalCount + "\" [color=red];\n";
						terminalCount--;
					}

				}
				diagram += "\"" + root.nodeID + "\"" + "->" + "\""
						+ child.nodeID + "\""
						+ (color != null ? "[color=" + color + "]" : "")
						+ ";\n";
				getDiagram(child);
			}
		}
	}

	public boolean search(String string) {
		if (string.length() == 0)
			return true;
		else {
			Tree child = getChild(string.charAt(0));
			if (child == null)
				return false;
			else {
				return child.search(string.substring(1));
			}
		}
	}

	public void siblingify() throws Exception {
		siblingify(this);
	}

	private void siblingify(Tree root) throws Exception {
		ArrayQueue queue = doBFT();
		boolean firstPass = true;
		Tree lastNode = null;
		Tree currentNode;
		while (!queue.empty()) {
			currentNode = (Tree) queue.remove();
			if (!firstPass && lastNode != null
					&& lastNode.depth == currentNode.depth) {
				lastNode.sibling = currentNode;
			}
			firstPass = false;
			lastNode = currentNode;
		}
	}

	public int getHeight() {
		return getHeight(this);
	}

	private int getHeight(Tree root) {
		int maxChildHeight = 0;
		for (Tree child : root.getChildren()) {
			int childHeight = getHeight(child) + 1;
			if (maxChildHeight < childHeight)
				maxChildHeight = childHeight;
		}
		root.height += maxChildHeight;
		return root.height;
	}

}
