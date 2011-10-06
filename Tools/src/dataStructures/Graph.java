package dataStructures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.IOUtils;

public class Graph implements Cloneable, Serializable {

	protected List<Graph> neighbours;
	protected String value;
	protected List<String> contents = new ArrayList<String>();
	protected boolean processed = false;
	protected String nodeID;
	protected String diagram;
	protected String nodeColor;
	private Map<String, Integer> intCount;
	protected int terminalCount;

	@Override
	public Object clone() {
		try {
			return IOUtils.deepCopy(this);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public Graph() {
		neighbours = new ArrayList<Graph>();
		diagram = "digraph G {\n";
		intCount = new HashMap<String, Integer>();
	}

	public Graph(String value) {
		neighbours = new ArrayList<Graph>();
		this.value = value;
	}

	public List<Graph> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<Graph> neighbours) {
		this.neighbours = neighbours;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void addNeighbour(Graph neighbour) {
		neighbours.add(neighbour);
	}

	private void clearProcessedFlag() {
		if (processed == false) {
			// Already flag is clear, return
			return;
		} else {
			this.processed = false;
			for(Graph neighbour: neighbours)
				neighbour.clearProcessedFlag();
		}
	}

	protected void clearDiagram() {
		clearDiagram(this);
		clearProcessedFlag();
	}

	protected static void clearDiagram(Graph graph) {
		if (graph == null || graph.processed)
			return;
		graph.diagram = "digraph G {\n";
		graph.nodeID = null;
		graph.intCount = new HashMap<String, Integer>();
		graph.terminalCount = 0;
		graph.processed = true;
		for (Graph neighbour : graph.neighbours) {
			clearDiagram(neighbour);
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

	public Graph getNeighbour(char value) {
		Graph result = null;
		String valueString = new String(new char[] { value });
		for (Graph neighbour : neighbours) {
			if (valueString.equals(neighbour.getValue())) {
				result = neighbour;
				break;
			}
		}
		return result;
	}

	protected void preDiagram() {
		nodeID = value;
		preDiagram(this);
	}

	private void preDiagram(Graph graph) {
		if (graph == null || graph.processed)
			return;
		if (graph.getNeighbours() != null && graph.getNeighbours().size() > 0) {
			for (Graph neighbour : graph.getNeighbours()) {
				neighbour.nodeID = neighbour.getValue();
				if (intCount.containsKey(neighbour.getValue())) {
					int count = intCount.get(neighbour.getValue());
					for (int i = 0; i < count; i++) {
						neighbour.nodeID += "*";
					}
					intCount.put(neighbour.getValue(), count + 1);
				} else {
					intCount.put(neighbour.getValue(), 1);
				}
				preDiagram(neighbour);
			}
		}
	}

	public void setLevels() throws Exception {
		ArrayQueue queue = new ArrayQueue(1000);
		queue.insert(this);
		while (!queue.empty()) {
			Graph root = (Graph) queue.remove();
			for (Graph child : root.getChildren()) {
				child.depth = root.depth + 1;
				queue.insert(child);
			}
		}
	}

	public void childCount() throws Exception {
		ArrayQueue queue = new ArrayQueue(1000);
		queue.insert(this);
		while (!queue.empty()) {
			Graph root = (Graph) queue.remove();
			// System.out.println(root.value + "-->" + root.children.size());
			for (Graph child : root.getChildren()) {
				queue.insert(child);
			}
		}
	}

	public boolean gapNoMoreThanOne() throws Exception {
		this.setLevels();
		int upper = -1;
		int lower = -1;

		/* TODO Currently limited to 100 nodes */
		ArrayQueue queue = new ArrayQueue(1000);
		queue.insert(this);
		while (!queue.empty()) {
			Graph root = (Graph) queue.remove();

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
				for (Graph child : root.getChildren()) {
					queue.insert(child);
				}
			}
		}
		return true;

	}

	private ArrayQueue doBFT() throws Exception {
		ArrayQueue workQueue = new ArrayQueue(1000);
		ArrayQueue resultQueue = new ArrayQueue(1000);
		workQueue.insert(this);
		resultQueue.insert(this);
		while (!workQueue.empty()) {
			Graph root = (Graph) workQueue.remove();
			for (Graph child : root.getChildren()) {
				workQueue.insert(child);
				resultQueue.insert(child);
			}
		}
		return resultQueue;
	}

	private void getDiagram(Graph root) {
		if (root.sibling != null) {
			diagram += "\"" + root.nodeID + "\"" + "->" + "\""
					+ root.sibling.nodeID + "\"" + "[constraint=false];\n";
		}

		if (root.nodeColor != null && !root.nodeColor.isEmpty()) {
			diagram += "\"" + root.nodeID + "\"[color=" + root.nodeColor
					+ "];\n";
		}

		if (root.getChildren() != null && root.getChildren().size() > 0) {
			for (Graph child : root.getChildren()) {
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
			Graph child = getChild(string.charAt(0));
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

	private void siblingify(Graph root) throws Exception {
		ArrayQueue queue = doBFT();
		boolean firstPass = true;
		Graph lastNode = null;
		Graph currentNode;
		while (!queue.empty()) {
			currentNode = (Graph) queue.remove();
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

	private int getHeight(Graph root) {
		int maxChildHeight = 0;
		for (Graph child : root.getChildren()) {
			int childHeight = getHeight(child) + 1;
			if (maxChildHeight < childHeight)
				maxChildHeight = childHeight;
		}
		root.height += maxChildHeight;
		return root.height;
	}

	public LinkedList maxValuePath() {
		LinkedList maxValuePath = null;
		Long maxValue = 0L;
		for (Graph subTree : neighbours) {
			LinkedList subList = subTree.maxValuePath();
			if (subList.sum() > maxValue) {
				maxValue = subList.sum();
				maxValuePath = subList;
			}
		}
		if (maxValuePath == null)
			return new LinkedList(value, false);
		else
			return maxValuePath.prefix(new LinkedList(value, false));
	}

	public static Graph generate(int levels, int maxVal, int maxChildren) {
		if (levels == 0)
			return null;
		else {
			Graph tree = new Graph(
					Integer.toString((int) (Math.random() * maxVal)));
			for (int i = 0; i < (int) (Math.random() * maxChildren); i++) {
				Graph child = Graph.generate(levels - 1, maxVal, maxChildren);
				if (child != null)
					tree.addChild(child);
			}
			return tree;
		}
	}

	/*
	 * Detects the presence of a linked list as a path inside this tree. Colors
	 * the path if found
	 */
	public boolean detectPath(LinkedList path) {
		boolean result = false;
		if (path.getValue().equals(this.value)) {
			if (this.neighbours == null || this.neighbours.isEmpty()) {
				result = true;
				this.nodeColor = "red";
			} else {
				for (Graph subTree : neighbours) {
					result = subTree.detectPath(path.getNext());
					if (result) {
						this.nodeColor = "red";
						break;
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		Graph tree = Graph.generate(6, 50, 6);
		System.out.println(tree.getDiagram());
		LinkedList maxValPath = tree.maxValuePath();
		tree.detectPath(maxValPath);
		System.out.println(tree.getDiagram());
	}

}
