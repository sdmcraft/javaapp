package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Graph<T> {

	private Map<T, Integer> intCount;
	private Set<GraphNode<T>> vertices;
	private String diagram;
	private Matrix adjacencyMatrix;

	private Graph() {
		vertices = new HashSet<GraphNode<T>>();
		diagram = "digraph G {\n";
		intCount = new HashMap<T, Integer>();
	}

	private void clearProcessedFlag() {
		for (GraphNode<T> node : vertices)
			node.processed = false;
	}

	private void clearDiagram() {
		clearProcessedFlag();
		diagram = "digraph G {\n";
		intCount = new HashMap<T, Integer>();
		for (GraphNode<T> node : vertices) {
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
		for (GraphNode<T> node : vertices) {
			node.nodeID = node.getValue().toString();
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
		for (GraphNode<T> node : vertices) {
			if (node.nodeColor != null && !node.nodeColor.isEmpty()) {
				diagram += "\"" + node.nodeID + "\"[color=" + node.nodeColor
						+ "];\n";
			}
			for (GraphNode<T> neighbour : node.getNeighbours()) {
				String color = null;
				diagram += "\"" + node.nodeID + "\"" + "->" + "\""
						+ neighbour.nodeID + "\""
						+ (color != null ? "[color=" + color + "]" : "")
						+ ";\n";
			}
		}
	}

	private static <T> Graph<T> generate(T[] contents) {
		if (contents.length != 0) {
			return build(Matrix.buildConnected(contents.length,
					contents.length, 0.8), contents);
		} else
			return null;
	}

	private static <T> Graph<T> build(Matrix adjacencyMatrix, T[] contents) {
		Graph<T> graph = new Graph<T>();
		graph.adjacencyMatrix = adjacencyMatrix;
		List<GraphNode<T>> nodes = new ArrayList<GraphNode<T>>();
		for (int i = 0; i < adjacencyMatrix.numRows(); i++) {
			GraphNode<T> node = new GraphNode<T>(contents[i]);
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
		for (GraphNode<T> node : nodes) {
			System.out.print(node.value + "->");
			for (GraphNode<T> neighbour : node.getNeighbours())
				System.out.print(neighbour.value + ",");
			System.out.println();
		}
		graph.vertices = new HashSet<GraphNode<T>>(nodes);

		return graph;

	}

	private boolean tarjan() throws Exception {
		int index = 0;
		Stack<GraphNode<T>> stack = new Stack<GraphNode<T>>();
		for (GraphNode<T> node : vertices) {
			if (node.tarjanIndex == -1) {
				if (strongConnect(node, index, stack))
					return true;
			}
		}
		return false;
	}

	private static <T> boolean strongConnect(GraphNode<T> node, int index,
			Stack<GraphNode<T>> stack) {
		node.tarjanIndex = index;
		node.tarjanLowLink = index;
		index++;
		stack.push(node);

		for (GraphNode<T> neighbour : node.neighbours) {
			if (neighbour.tarjanIndex == -1) {
				if (strongConnect(neighbour, index, stack))
					return true;
				node.tarjanLowLink = node.tarjanLowLink < neighbour.tarjanLowLink ? node.tarjanLowLink
						: neighbour.tarjanLowLink;
			} else if (stack.contains(neighbour)) {
				node.tarjanLowLink = node.tarjanLowLink < neighbour.tarjanIndex ? node.tarjanLowLink
						: neighbour.tarjanIndex;
			}
		}
		if (node.tarjanIndex == node.tarjanLowLink) {
			Set<GraphNode<T>> scc = new HashSet<GraphNode<T>>();
			GraphNode<T> poppedNode = null;
			do {
				poppedNode = stack.pop();
				scc.add(poppedNode);
			} while (!node.equals(poppedNode));
			System.out.println(scc);
			if (scc.size() > 1)
				return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		Matrix adjacencyMatrix = new Matrix(new int[][] { { 0, 1, 0, 0, 0 },
				{ 0, 0, 1, 0, 0 }, { 0, 0, 0, 1, 0 }, { 1, 0, 0, 0, 1 },
				{ 0, 1, 0, 0, 0 } });
		/*
		 * Matrix adjacencyMatrix = new Matrix(new int[][]{
		 * {0,1,0},{0,0,1},{1,0,0}});
		 */
		// Graph graph = build(adjacencyMatrix);
		Graph<String> graph;
		do {			
			graph = Graph.generate(new String[]{"1","2","3","4","5","6","7","8","9","10"});
			/*
			 * if (graph.tarjan()) System.out.println("This graph has cycle!!");
			 */
		} while (graph.tarjan());
		System.out.println(graph.getDiagram());
	}

}
