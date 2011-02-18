package dataStructures;

class HeapNode {
	String data;
	int weight;

	public HeapNode(String data, int weight) {
		this.data = data;
		this.weight = weight;
	}
}

public class Heap {

	HeapNode nodes[];
	int heapSize;

	/** Creates a new instance of Heap */
	public Heap(int size) {
		nodes = new HeapNode[size];
		this.heapSize = 0;
	}

	public void insert(HeapNode item) {
		if (heapSize == nodes.length)
			expandHeap();
		nodes[heapSize] = item;
		trickleUp(heapSize++);
	}

	public void trickleUp(int index) {
		int parentIndex = (index - 1) / 2;
		HeapNode item = nodes[index];
		while ((item.weight > nodes[parentIndex].weight) && (index > 0)) {
			nodes[index] = nodes[parentIndex];
			index = parentIndex;
			parentIndex = (index - 1) / 2;
		}
		nodes[index] = item;
	}

	public HeapNode remove() {
		HeapNode result = nodes[0];
		nodes[0] = nodes[--heapSize];
		nodes[heapSize] = null;
		trickledown(0);
		return result;
	}

	public void trickledown(int index) {
		HeapNode item = nodes[index];
		int leftChildIndex = 2 * index + 1;
		int rtChildIndex = 2 * index + 2;
		while ((leftChildIndex < heapSize)
				&& (rtChildIndex < heapSize)
				&& ((item.weight < nodes[leftChildIndex].weight) || (item.weight < nodes[rtChildIndex].weight))) {
			if (nodes[leftChildIndex].weight > nodes[rtChildIndex].weight) {
				nodes[index] = nodes[leftChildIndex];
				index = leftChildIndex;
			} else {
				nodes[index] = nodes[rtChildIndex];
				index = rtChildIndex;
			}
			leftChildIndex = 2 * index + 1;
			rtChildIndex = 2 * index + 2;
		}
		nodes[index] = item;
	}

	public static void trickledown(int index, HeapNode[] arr) {
		HeapNode item = arr[index];
		int leftChildIndex = 2 * index + 1;
		int rtChildIndex = 2 * index + 2;
		while ((leftChildIndex < arr.length)
				&& (rtChildIndex < arr.length)
				&& ((item.weight < arr[leftChildIndex].weight) || (item.weight < arr[rtChildIndex].weight))) {
			if (arr[leftChildIndex].weight > arr[rtChildIndex].weight) {
				arr[index] = arr[leftChildIndex];
				index = leftChildIndex;
			} else {
				arr[index] = arr[rtChildIndex];
				index = rtChildIndex;
			}
			leftChildIndex = 2 * index + 1;
			rtChildIndex = 2 * index + 2;
		}
		arr[index] = item;
	}

	public void expandHeap() {
		HeapNode newNodes[] = new HeapNode[nodes.length * 2];
		for (int i = 0; i < nodes.length; i++)
			newNodes[i] = nodes[i];
		nodes = newNodes;
	}

	public void display() {
		for (int i = 0; i < heapSize; i++)
			System.out.print(nodes[i].data + " ");
	}

	public static HeapNode[] heapify(HeapNode[] arr) {
		for (int i = (arr.length - 1) / 2; i >= 0; i--)
			trickledown(i, arr);
		return arr;
	}

	public static HeapNode remove(HeapNode[] arr, int size) {
		HeapNode result = arr[0];
		arr[0] = arr[size];
		trickledown(0, arr);
		return result;
	}

	public BinaryTree toBinaryTree() {
		return toBinaryTree(0);
	}

	private BinaryTree toBinaryTree(int index) {
		if (index < heapSize) {
			HeapNode node = this.nodes[index];
			BinaryTree binaryTree = new BinaryTree(node.data + "("
					+ node.weight + ")");

			binaryTree.setLeft(toBinaryTree(2 * index + 1));
			binaryTree.setRight(toBinaryTree(2 * index + 2));
			return binaryTree;

		} else
			return null;
	}

	public String getDiagram() throws Exception {
		Tree tree = toBinaryTree();
		return tree.getDiagram();
	}

	public static void main(String[] args) {
		// int size = (int) (Math.random() * 20);
		// Heap heap = new Heap(size);
		// for (int i = 0; i < size; i++) {
		// int item = (int) (Math.random() * 100);
		// System.out.println("\nInserting: " + item);
		// HeapNode node = new HeapNode();
		// node.data = item;
		// heap.insert(node);
		// System.out.println("Heap is:");
		// heap.display(1);
		// }
		// System.out.println("\nRemoving from heap:");
		// int size1 = heap.heapSize;
		// for (int i = 0; i < size1; i++)
		// System.out.print(heap.remove(1).data + " ");
		/*
		 * int arr[] = new int[size]; for(int i=0;i<size;i++) arr[i] =
		 * (int)(Math.random()*100);
		 * System.out.println("Here is the random array"); for(int
		 * i=0;i<size;i++) System.out.print(arr[i]+" "); heapify(arr);
		 * System.out.println("\nHere is the heapified array"); for(int
		 * i=0;i<size;i++) System.out.print(arr[i]+" ");
		 * System.out.println("\nRemoving from heapified array:"); for(int
		 * i=size-1;i>=0;i--) System.out.print(remove(arr,i)+" ");
		 */

		String text = "In the testing environment we do not currently allow International dial out, "
				+ "the fact it is dialing strikes me as odd as it shouldn't be doing that either. This is "
				+ "something Jeff and I have been trying to get implemented before. We will try to push to get this "
				+ "enabled for your testing. For the meantime though, you will have to do dial ins";
		// String text = "a b c b d c c";
		String[] strings = text.split(" ");
		Heap heap = new Heap(strings.length);
		for (int i = 0; i < strings.length; i++) {
			int j;
			boolean found = false;
			for (j = 0; j < heap.getNodes().length; j++) {
				HeapNode node = heap.getNodes()[j];
				if (node != null) {
					if (node.data.equals(strings[i])) {
						found = true;
						node.weight++;
						heap.trickleUp(j);
						break;
					}
				} else
					break;
			}
			/* The string was already handled by incrementing count */
			if (found)
				continue;
			else {
				heap.insert(new HeapNode(strings[i], 1));
			}
		}

		while (heap.getHeapSize() > 0) {
			HeapNode node = heap.remove();
			System.out.println(node.data + "--->" + node.weight);
		}
	}

	public HeapNode[] getNodes() {
		return nodes;
	}

	public int getHeapSize() {
		return heapSize;
	}
}
