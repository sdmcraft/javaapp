package dataStructures;

import java.util.ArrayList;
import java.util.List;

import tools.AlgoUtils;

public class BinaryTree extends Tree implements Cloneable {
	protected BinaryTree left;
	protected BinaryTree right;
	protected BinaryTree parent;
	protected int nodeCount = 0;

	public BinaryTree() {
		incrementNodeCount();
		value = "1";
	}

	public BinaryTree(String value) {
		super(value);
		incrementNodeCount();
	}

	public BinaryTree(BinaryTree parent) {
		this.parent = parent;
		this.depth = parent.depth + 1;
		incrementNodeCount();
		value = getRootNodeCount(parent) + "";
	}

	private void incrementNodeCount() {
		nodeCount++;
		if (parent != null)
			parent.incrementNodeCount();
	}

	private static int getRootNodeCount(BinaryTree node) {
		if (node.parent == null)
			return node.nodeCount;
		else
			return getRootNodeCount(node.parent);
	}

	public void build() {
		build(this, 6);
	}

	private static void build(BinaryTree root, int maxDepth) {

		if (root.depth >= maxDepth)
			return;

		int randLeft = (int) (Math.random() * 100);
		int randRight = (int) (Math.random() * 100);

		if (randLeft > 50) {
			root.left = new BinaryTree(root);
		}
		if (randRight > 50) {
			root.right = new BinaryTree(root);
		}

		if (root.left != null)
			root.children.add(root.left);
		if (root.right != null)
			root.children.add(root.right);

		if (root.left != null)
			build(root.left, maxDepth);
		if (root.right != null)
			build(root.right, maxDepth);

	}

	private List<String> inorder() {
		return inorder(this);
	}

	private static List<String> inorder(BinaryTree root) {
		List<String> result = new ArrayList<String>();
		if (root != null) {
			result.addAll(inorder(root.left));
			result.add(root.value);
			result.addAll(inorder(root.right));
		}
		return result;
	}

	private static int inorderSubstitution(BinaryTree root, List<String> items,
			int index) {
		if (root != null) {
			index = inorderSubstitution(root.left, items, index);

			root.value = items.get(index);
			index++;

			index = inorderSubstitution(root.right, items, index);
		}
		return index;
	}

	private void sameShapeBinaryTree() {
		List<String> inorderTraversal = inorder();
		System.out.println(inorderTraversal);
		AlgoUtils.sort(inorderTraversal, "quicksort");
		System.out.println(inorderTraversal);
		inorderSubstitution(this, inorderTraversal, 0);
	}

	public static void main(String[] args) throws Exception {
		BinaryTree bt = new BinaryTree();
		bt.build();
		System.out.println(bt.getDiagram());
		//System.out.println(bt.isBalanced());
		// System.out.println(bt.inorder());
		// bt.sameShapeBinaryTree();
		// System.out.println(bt.getDiagram());
	}

	public BinaryTree getLeft() {
		return left;
	}

	public BinaryTree getRight() {
		return right;
	}

	public boolean isBalanced() throws Exception {
		return gapNoMoreThanOne();
	}

	public void setLeft(BinaryTree left) {
		if (left == null)
			return;
		this.left = left;
		if (children.size() > 0)
			children.remove(0);
		children.add(0, left);
		left.parent = this;
	}

	public void setRight(BinaryTree right) {
		if (right == null)
			return;

		this.right = right;
		if (children.size() > 1)
			children.remove(1);
		children.add(1, right);
		right.parent = this;
	}
	
	@Override
	public String getDiagram() throws Exception {
		clearDiagram(this);
		setLevels();
		preDiagram();
		getDiagram(this);
		diagram += "}";
		return diagram;
	}

	private String getDiagram(BinaryTree root) {		
		String color = null;
		BinaryTree leftChild = root.getLeft();
		BinaryTree rightChild = root.getRight();
		if (leftChild == null) {
			diagram += "null" + terminalCount + " [shape=point];\n";
			diagram += "\"" + root.nodeID + "\"" + "->" + "\"" + "null"
					+ terminalCount + "\" [color=green];\n";
			terminalCount--;
		} else {
			diagram += "\"" + root.nodeID + "\"" + "->" + "\""
					+ leftChild.nodeID + "\"[color=green]\n";
			getDiagram(leftChild);
		}
		if (rightChild == null) {
			diagram += "null" + terminalCount + " [shape=point];\n";
			diagram += "\"" + root.nodeID + "\"" + "->" + "\"" + "null"
					+ terminalCount + "\" [color=red];\n";
			terminalCount--;
		} else {
			diagram += "\"" + root.nodeID + "\"" + "->" + "\""
					+ rightChild.nodeID + "\"[color=red]\n";
			getDiagram(rightChild);
		}
		return diagram;
	}

}
