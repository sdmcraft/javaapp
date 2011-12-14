package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Graph {

	private Map<String, Integer> intCount;
	private Set<GraphNode> vertices;
	private String diagram;
	private Matrix adjacencyMatrix;

	private Graph() {
		vertices = new HashSet<GraphNode>();
		diagram = "digraph G {\n";
		intCount = new HashMap<String, Integer>();
	}

	private void clearProcessedFlag() {
		for (GraphNode node : vertices)
			node.processed = false;
	}

	private void clearDiagram() {
		clearProcessedFlag();
		diagram = "digraph G {\n";
		intCount = new HashMap<String, Integer>();
		for (GraphNode node : vertices) {
			node.nodeID = null;
		}
	}

	private String getDiagram() throws Exception {
		clearDiagram();
		preDiagram();
		getDiagramCore();
		diagram += "}";
		return diagram;
	}

	private void preDiagram() {
		for (GraphNode node : vertices) {
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
		for (GraphNode node : vertices) {
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

	private static Graph generate(int numNodes, int maxVal) {
		if (numNodes != 0) {
			return build(Matrix.buildConnected(numNodes, numNodes, 0.8), maxVal);
		} else
			return null;
	}

	private static Graph build(Matrix adjacencyMatrix, int maxVal) {
		Graph graph = null;
		List<GraphNode> nodes = new ArrayList<GraphNode>();
		for (int i = 0; i < adjacencyMatrix.numRows(); i++) {
			GraphNode node = new GraphNode(Integer.toString((int) (Math
					.random() * maxVal)));
			System.out.print(node.value + ",");
			nodes.add(node);
		}
		System.out.println();
		for (int row = 0; row < graph.adjacencyMatrix.numRows(); row++) {
			for (int col = 0; col < graph.adjacencyMatrix.numCols(); col++) {
				if (graph.adjacencyMatrix.get(row, col) == 1) {
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
		graph.vertices = new HashSet<GraphNode>(nodes);

		return graph;

	}

	private void tarjan() throws Exception {
		int index = 0;
		Stack<GraphNode> stack = new Stack<GraphNode>();
		for (GraphNode node : vertices) {
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
		Graph graph = generate(10, 500);
		System.out.println(graph.getDiagram());
	}

}
