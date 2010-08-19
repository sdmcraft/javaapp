package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree extends Tree {
	private BinaryTree left;
	private BinaryTree right;
	private BinaryTree parent;
	private int nodeCount = 0;

	public BinaryTree() {
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
		build(this, 4);
	}

	private static void build(BinaryTree root, int maxDepth) {

		if (root.depth >= maxDepth)
			return;

		if ((int) (Math.random() * 100) > 40) {
			root.left = new BinaryTree(root);
		}
		if ((int) (Math.random() * 100) > 40) {
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

	private static void inorderSubstitution(BinaryTree root,
			List<String> items, int index) {
		if (root != null) {
			inorderSubstitution(root.left, items, index);
			root.value = items.get(index + 1);
			inorderSubstitution(root.left, items, index + 2);
		}
	}
	
//	private BinaryTree sameShapeBinaryTree()
//	{
//		List<String> inorderTraversal = inorder();
//		
//	}

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.build();
		System.out.println(bt.getDiagram());
		System.out.println(bt.inorder());
	}

}
