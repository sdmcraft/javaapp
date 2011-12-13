package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {

	protected Set<GraphNode> community;
	protected String diagram;
	private Map<String, Integer> intCount;
	protected int terminalCount;
	protected Matrix adjacencyMatrix;

	public Graph() {
		community = new HashSet<GraphNode>();
		diagram = "digraph G {\n";
		intCount = new HashMap<String, Integer>();
	}

	private void clearProcessedFlag() {
		for (GraphNode node : community)
			node.processed = false;
	}

	protected void clearDiagram() {
		clearProcessedFlag();
		diagram = "digraph G {\n";
		intCount = new HashMap<String, Integer>();
		terminalCount = 0;

		for (GraphNode node : community) {
			node.nodeID = null;
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

	private void preDiagram() {
		for (GraphNode node : community) {
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

	private void getDiagramCore() {
		for (GraphNode node : community) {
			if (node.nodeColor != null && !node.nodeColor.isEmpty()) {
				diagram += "\"" + node.nodeID + "\"[color=" + node.nodeColor
						+ "];\n";
			}
			for (GraphNode neighbour : node.getNeighbours()) {
				String color = null;
				diagram += "\"" + node.nodeID + "\"" + "->" + "\""
						+ neighbour.nodeID + "\""
						+ (color != null ? "[color=" + color + "]" : "")
						+ ";\n";
			}
		}
	}

	public void generate(int numNodes, int maxVal) {
		if (numNodes == 0)
			return;
		else {
			adjacencyMatrix = Matrix.buildConnected(numNodes, numNodes, 0.8);
			System.out.println(adjacencyMatrix);
			List<GraphNode> nodes = new ArrayList<GraphNode>();
			for (int i = 0; i < numNodes; i++) {
				GraphNode node = new GraphNode(Integer.toString((int) (Math
						.random() * maxVal)));
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
			for (GraphNode node : nodes) {
				System.out.print(node.value + "->");
				for (GraphNode neighbour : node.getNeighbours())
					System.out.print(neighbour.value + ",");
				System.out.println();
			}
			community = new HashSet<GraphNode>(nodes);
		}
	}

}
