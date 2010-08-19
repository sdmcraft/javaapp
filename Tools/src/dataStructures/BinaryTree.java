package dataStructures;

public class BinaryTree extends Tree {
	private BinaryTree left;
	private BinaryTree right;
	private BinaryTree parent;
	private int nodeCount = 0;

	public BinaryTree()
	{
		incrementNodeCount();			
	}

	public BinaryTree(BinaryTree parent)
	{
		this.parent = parent;
		incrementNodeCount();			
	}

	private void incrementNodeCount()
	{
		nodeCount++;
		if(parent != null)
			parent.incrementNodeCount();
	}
	public void build() {
		System.out.println("Overall Depth:" + depth);
		build(this, 2);
	}
	
	

	private static void build(BinaryTree root, int maxDepth) {

		if (root.depth >= maxDepth)
			return;

		root.left = new BinaryTree(root);		
		root.right = new BinaryTree(root);		
		
		root.children.add(root.left);
		root.children.add(root.right);

		root.left.depth = root.depth + 1;
		root.right.depth = root.depth + 1;

		root.left.value = root.left.nodeCount + "";
		root.right.value = root.right.nodeCount + "";

		build(root.left, maxDepth);
		build(root.right, maxDepth);

	}

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.build();
		System.out.println(bt.getDiagram());
	}

}
