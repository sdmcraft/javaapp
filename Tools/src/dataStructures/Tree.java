package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {

	protected List<Tree> children;
	protected String value;
	private String nodeID;
	private String diagram;
	private Map<String, Integer> intCount;
	private Tree sibling;
	private int depth = 0;
	private int height = 0;

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

	public String getDiagram() {
		preDiagram(this);
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

	private void preDiagram(Tree root) {
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
				diagram += "\"" + root.nodeID + "\"" + "->" + "\""
						+ child.nodeID + "\"" + ";\n";
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
