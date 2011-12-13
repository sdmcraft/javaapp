package dataStructures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import tools.IOUtils;

public class GraphNode implements Cloneable, Serializable {

	protected List<GraphNode> neighbours;
	protected String value;
	protected List<String> contents = new ArrayList<String>();
	protected boolean processed = false;
	protected String nodeID;
	protected String nodeColor;

	protected int tarjanIndex = -1;
	protected int tarjanLowLink = -1;

	@Override
	public Object clone() {
		try {
			return IOUtils.deepCopy(this);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public GraphNode() {
		neighbours = new ArrayList<GraphNode>();
	}

	public GraphNode(String value) {
		neighbours = new ArrayList<GraphNode>();
		this.value = value;
	}

	public List<GraphNode> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<GraphNode> neighbours) {
		this.neighbours = neighbours;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void addNeighbour(GraphNode neighbour) {
		neighbours.add(neighbour);
	}

	public GraphNode getNeighbour(char value) {
		GraphNode result = null;
		String valueString = new String(new char[] { value });
		for (GraphNode neighbour : neighbours) {
			if (valueString.equals(neighbour.getValue())) {
				result = neighbour;
				break;
			}
		}
		return result;
	}

	private ArrayQueue doBFT() throws Exception {
		ArrayQueue workQueue = new ArrayQueue(1000);
		ArrayQueue resultQueue = new ArrayQueue(1000);
		workQueue.insert(this);
		resultQueue.insert(this);
		while (!workQueue.empty()) {
			GraphNode root = (GraphNode) workQueue.remove();
			for (GraphNode neighbour : root.getNeighbours()) {
				if (!neighbour.processed) {
					workQueue.insert(neighbour);
					resultQueue.insert(neighbour);
				}
			}
		}
		return resultQueue;
	}

	public boolean search(String string) {
		if (string.length() == 0)
			return true;
		else {
			GraphNode neighbour = getNeighbour(string.charAt(0));
			if (neighbour == null)
				return false;
			else {
				return neighbour.search(string.substring(1));
			}
		}
	}

	public LinkedList maxValuePath() {
		LinkedList maxValuePath = null;
		Long maxValue = 0L;
		for (GraphNode subTree : neighbours) {
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

	/*
	 * Detects the presence of a linked list as a path inside this graph. Colors
	 * the path if found
	 */
	public boolean detectPath(LinkedList path) {
		boolean result = false;
		if (path.getValue().equals(this.value)) {
			if (this.neighbours == null || this.neighbours.isEmpty()) {
				result = true;
				this.nodeColor = "red";
			} else {
				for (GraphNode neighbour : neighbours) {
					if (!neighbour.processed) {
						result = neighbour.detectPath(path.getNext());
						if (result) {
							this.nodeColor = "red";
							break;
						}
					}
				}
			}
		}
		return result;
	}

	public boolean detectCycle() {
		clearProcessedFlag(this);
		boolean result = detectCycle(this);
		clearProcessedFlag(this);
		return result;
	}

	private static boolean detectCycle(GraphNode graphNode) {
		graphNode.processed = true;
		boolean result = false;
		for (GraphNode neighbour : graphNode.neighbours) {
			if (neighbour.processed)
				return true;
			else
				result |= detectCycle(neighbour);
		}
		return result;
	}

	private void tarjan() throws Exception {
		int index = 0;
		Stack<GraphNode> stack = new Stack<GraphNode>();
		for (GraphNode node : community) {
			if (node.tarjanIndex != -1) {
				Set<GraphNode> scc = strongConnect(node, index, stack);
				if (scc != null)
					System.out.println(scc);
			}
		}
	}

	private static Set<GraphNode> strongConnect(GraphNode node, int index,
			Stack<GraphNode> stack) {
		node.tarjanIndex = index;
		node.tarjanLowLink = index;
		index++;
		stack.push(node);

		for (GraphNode neighbour : node.neighbours) {
			if (neighbour.tarjanIndex == -1) {
				strongConnect(neighbour, index, stack);
				node.tarjanLowLink = node.tarjanLowLink < neighbour.tarjanLowLink ? node.tarjanLowLink
						: neighbour.tarjanLowLink;
			} else if (stack.contains(neighbour)) {
				node.tarjanLowLink = node.tarjanLowLink < neighbour.tarjanIndex ? node.tarjanLowLink
						: neighbour.tarjanIndex;
			}
		}
		if (node.tarjanIndex == node.tarjanLowLink) {
			Set<GraphNode> scc = new HashSet<GraphNode>();
			while (!stack.isEmpty()) {
				scc.add(stack.pop());
			}
			return scc;
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		GraphNode graphNode = GraphNode.generate(10, 500);
		// while (graph.detectCycle())
		// graph = GraphNode.generate(10, 500);
		// clearProcessedFlag(graph);
		System.out.println(graphNode.getDiagram());
		graphNode.tarjan();

	}

}
