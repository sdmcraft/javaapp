package dataStructures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.IOUtils;

public class Graph implements Cloneable, Serializable {

	protected List<Graph> neighbours;
	protected List<Graph> community;

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
		community = new ArrayList<Graph>();
		diagram = "digraph G {\n";
		intCount = new HashMap<String, Integer>();
	}

	public Graph(String value) {
		neighbours = new ArrayList<Graph>();
		community = new ArrayList<Graph>();
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

	private static void clearProcessedFlag(Graph graph) {
		for (Graph node : graph.community)
			node.processed = false;
	}

	protected void clearDiagram() {
		clearDiagram(this);
		clearProcessedFlag(this);
	}

	protected static void clearDiagram(Graph graph) {
		for (Graph node : graph.community) {
			node.diagram = "digraph G {\n";
			node.nodeID = null;
			node.intCount = new HashMap<String, Integer>();
			node.terminalCount = 0;
			node.processed = true;
		}
	}

	public String getDiagram() throws Exception {
		clearDiagram();
		preDiagram();
		getDiagramCore();
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
		clearProcessedFlag(this);
	}

	private void preDiagram(Graph graph) {
		for (Graph node : graph.community) {
			node.nodeID = node.getValue();
			if (intCount.containsKey(node.getValue())) {
				int count = intCount.get(node.getValue());
				for (int i = 0; i < count; i++) {
					node.nodeID += "*";
				}
				intCount.put(node.getValue(), count + 1);
			} else {
				intCount.put(node.getValue(), 1);
			}

		}
	}

	public void neighbourCount() throws Exception {
		ArrayQueue queue = new ArrayQueue(1000);
		queue.insert(this);
		while (!queue.empty()) {
			Graph root = (Graph) queue.remove();
			if (!root.processed) {
				for (Graph neighbour : root.getNeighbours()) {
					if (!neighbour.processed)
						queue.insert(neighbour);
				}
				root.processed = true;
			}
		}
		clearProcessedFlag(this);
	}

	private ArrayQueue doBFT() throws Exception {
		ArrayQueue workQueue = new ArrayQueue(1000);
		ArrayQueue resultQueue = new ArrayQueue(1000);
		workQueue.insert(this);
		resultQueue.insert(this);
		while (!workQueue.empty()) {
			Graph root = (Graph) workQueue.remove();
			for (Graph neighbour : root.getNeighbours()) {
				if (!neighbour.processed) {
					workQueue.insert(neighbour);
					resultQueue.insert(neighbour);
				}
			}
		}
		return resultQueue;
	}

	private void getDiagramCore() {
		for (Graph node : community) {
			if (node.nodeColor != null && !node.nodeColor.isEmpty()) {
				diagram += "\"" + node.nodeID + "\"[color=" + node.nodeColor
						+ "];\n";
			}
			for (Graph neighbour : node.getNeighbours()) {
				String color = null;
				diagram += "\"" + node.nodeID + "\"" + "->" + "\""
						+ neighbour.nodeID + "\""
						+ (color != null ? "[color=" + color + "]" : "")
						+ ";\n";
			}
		}
	}

	public boolean search(String string) {
		if (string.length() == 0)
			return true;
		else {
			Graph neighbour = getNeighbour(string.charAt(0));
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

	public static Graph generate(int numNodes, int maxVal) {
		if (numNodes == 0)
			return null;
		else {
			Matrix adjacencyMatrix = Matrix.buildConnected(numNodes, numNodes,
					0.8);
			System.out.println(adjacencyMatrix);
			List<Graph> nodes = new ArrayList<Graph>();
			for (int i = 0; i < numNodes; i++) {
				Graph node = new Graph(Integer
						.toString((int) (Math.random() * maxVal)));
				System.out.print(node.value + ",");
				nodes.add(node);
			}
			System.out.println();
			for (int row = 0; row < adjacencyMatrix.numRows(); row++) {
				for (int col = 0; col < adjacencyMatrix.numCols(); col++) {
					if (adjacencyMatrix.get(row, col) == 1) {
						nodes.get(row).addNeighbour(nodes.get(col));
					}
				}
			}
			for (Graph node : nodes) {
				node.community = nodes;
				System.out.print(node.value + "->");
				for (Graph neighbour : node.getNeighbours())
					System.out.print(neighbour.value + ",");
				System.out.println();
			}
			return nodes.get(0);
		}
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
				for (Graph neighbour : neighbours) {
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

	private static boolean detectCycle(Graph graph) {
		graph.processed = true;
		boolean result = false;
		for (Graph neighbour : graph.neighbours) {
			if (neighbour.processed)
				return true;
			else
				result |= detectCycle(neighbour);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		Graph graph = Graph.generate(10, 500);
		while (graph.detectCycle())
			graph = Graph.generate(10, 500);
		clearProcessedFlag(graph);
		System.out.println(graph.getDiagram());

	}

}
