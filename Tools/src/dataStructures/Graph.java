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
			for (Graph neighbour : neighbours)
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
		this.clearProcessedFlag();
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

	private void getDiagram(Graph root) {
		if (root.nodeColor != null && !root.nodeColor.isEmpty()) {
			diagram += "\"" + root.nodeID + "\"[color=" + root.nodeColor
					+ "];\n";
		}

		if (root.getNeighbours() != null && root.getNeighbours().size() > 0) {
			for (Graph neighbour : root.getNeighbours()) {
				if (!neighbour.processed) {
					String color = null;
					diagram += "\"" + root.nodeID + "\"" + "->" + "\""
							+ neighbour.nodeID + "\""
							+ (color != null ? "[color=" + color + "]" : "")
							+ ";\n";
					getDiagram(neighbour);
				}
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

	public static Graph generate(int numNodes, int maxVal, int maxNeighbours) {
		if (numNodes == 0)
			return null;
		else {
			Matrix adjacencyMatrix = Matrix.build(numNodes, numNodes, 0.5);
			List<Graph> nodes = new ArrayList<Graph>();
			for (int i = 0; i < numNodes; i++) {
				nodes.add(new Graph(
						Integer.toString((int) (Math.random() * maxVal))));
			}
			for (int row = 0; row < adjacencyMatrix.numRows(); row++) {
				for (int col = 0; col < adjacencyMatrix.numCols(); col++) {
					if (adjacencyMatrix.get(row, col) == 1) {
						nodes.get(row).addNeighbour(nodes.get(col));
					}
				}
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

	public static void main(String[] args) throws Exception {
		Graph graph = Graph.generate(6, 50, 3);
		graph.clearProcessedFlag();
		System.out.println(graph.getDiagram());
	}

}
