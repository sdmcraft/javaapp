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

public class GraphNode<T> implements Cloneable, Serializable {

	List<GraphNode<T>> neighbours;
	T value;
	boolean processed = false;
	String nodeID;
	String nodeColor;
	int tarjanIndex = -1;
	int tarjanLowLink = -1;

	@Override
	public Object clone() {
		try {
			return IOUtils.deepCopy(this);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public GraphNode() {
		neighbours = new ArrayList<GraphNode<T>>();
	}

	public GraphNode(T value) {
		neighbours = new ArrayList<GraphNode<T>>();
		this.value = value;
	}

	public List<GraphNode<T>> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<GraphNode<T>> neighbours) {
		this.neighbours = neighbours;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public void addNeighbour(GraphNode<T> neighbour) {
		neighbours.add(neighbour);
	}

	public GraphNode<T> getNeighbour(char value) {
		GraphNode<T> result = null;
		String valueString = new String(new char[] { value });
		for (GraphNode<T> neighbour : neighbours) {
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
			GraphNode<T> root = (GraphNode<T>) workQueue.remove();
			for (GraphNode<T> neighbour : root.getNeighbours()) {
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
			GraphNode<T> neighbour = getNeighbour(string.charAt(0));
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
		for (GraphNode<T> subTree : neighbours) {
			LinkedList subList = subTree.maxValuePath();
			if (subList.sum() > maxValue) {
				maxValue = subList.sum();
				maxValuePath = subList;
			}
		}
		if (maxValuePath == null)
			return new LinkedList(value.toString(), false);
		else
			return maxValuePath.prefix(new LinkedList(value.toString(), false));
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
				for (GraphNode<T> neighbour : neighbours) {
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

	@Override
	public String toString() {
		return value.toString();
	}
}
