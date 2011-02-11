package dataStructures;

class HeapNode {
	String data;
	int weight;
}

public class Heap {

	HeapNode nodes[];
	int heapSize;

	/** Creates a new instance of Heap */
	public Heap(int size) {
		nodes = new HeapNode[size];
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
		while ((item.weight < nodes[parentIndex].weight) && (index > 0)) {
			nodes[index] = nodes[parentIndex];
			index = parentIndex;
			parentIndex = (index - 1) / 2;
		}
		nodes[index] = item;
	}

	public HeapNode remove() {
		HeapNode result = nodes[0];
		nodes[0] = nodes[--heapSize];
		trickledown(0);
		return result;
	}

	public void trickledown(int index) {
		HeapNode item = nodes[index];
		int leftChildIndex = 2 * index + 1;
		int rtChildIndex = 2 * index + 2;
		while ((leftChildIndex < heapSize)
				&& (rtChildIndex < heapSize)
				&& ((item.weight > nodes[leftChildIndex].weight) || (item.weight > nodes[rtChildIndex].weight))) {
			if (nodes[leftChildIndex].weight < nodes[rtChildIndex].weight) {
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

		String[] wordList = new String[] { "apple" };
	}
}
