package dataStructures;

class HeapNode {
	int data;
	int chunkNumber;
}

public class Heap {

	int elements[];
	HeapNode nodes[];
	int heapSize;

	/** Creates a new instance of Heap */
	public Heap(int size) {
		elements = new int[size];
		nodes = new HeapNode[size];
	}

	public void insert(int item) {
		if (heapSize == elements.length)
			expandHeap();
		elements[heapSize] = item;
		trickleUp(heapSize++);
	}

	public void insert(HeapNode item) {
		if (heapSize == nodes.length)
			expandHeap(1);
		nodes[heapSize] = item;
		trickleUp(heapSize++, 1);
	}

	public void trickleUp(int index, int generic) {
		int parentIndex = (index - 1) / 2;
		HeapNode item = nodes[index];
		while ((item.data < nodes[parentIndex].data) && (index > 0)) {
			nodes[index] = nodes[parentIndex];
			index = parentIndex;
			parentIndex = (index - 1) / 2;
		}
		nodes[index] = item;
	}

	public void trickleUp(int index) {
		int parentIndex = (index - 1) / 2;
		int item = elements[index];
		while ((item > elements[parentIndex]) && (index > 0)) {
			elements[index] = elements[parentIndex];
			index = parentIndex;
			parentIndex = (index - 1) / 2;
		}
		elements[index] = item;
	}

	public int remove() {
		int result = elements[0];
		elements[0] = elements[--heapSize];
		trickledown(0);
		return result;
	}

	public HeapNode remove(int generic) {
		HeapNode result = nodes[0];
		nodes[0] = nodes[--heapSize];
		trickledown(0, 1);
		return result;
	}

	public void trickledown(int index) {
		int item = elements[index];
		int leftChildIndex = 2 * index + 1;
		int rtChildIndex = 2 * index + 2;
		while ((leftChildIndex < heapSize)
				&& (rtChildIndex < heapSize)
				&& ((item < elements[leftChildIndex]) || (item < elements[rtChildIndex]))) {
			if (elements[leftChildIndex] > elements[rtChildIndex]) {
				elements[index] = elements[leftChildIndex];
				index = leftChildIndex;
			} else {
				elements[index] = elements[rtChildIndex];
				index = rtChildIndex;
			}
			leftChildIndex = 2 * index + 1;
			rtChildIndex = 2 * index + 2;
		}
		elements[index] = item;
	}

	public void trickledown(int index, int generic) {
		HeapNode item = nodes[index];
		int leftChildIndex = 2 * index + 1;
		int rtChildIndex = 2 * index + 2;
		while ((leftChildIndex < heapSize)
				&& (rtChildIndex < heapSize)
				&& ((item.data > nodes[leftChildIndex].data) || (item.data > nodes[rtChildIndex].data))) {
			if (nodes[leftChildIndex].data < nodes[rtChildIndex].data) {
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

	public static void trickledown(int index, int[] arr) {
		int item = arr[index];
		int leftChildIndex = 2 * index + 1;
		int rtChildIndex = 2 * index + 2;
		while ((leftChildIndex < arr.length) && (rtChildIndex < arr.length)
				&& ((item < arr[leftChildIndex]) || (item < arr[rtChildIndex]))) {
			if (arr[leftChildIndex] > arr[rtChildIndex]) {
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
		int newElements[] = new int[elements.length * 2];
		for (int i = 0; i < elements.length; i++)
			newElements[i] = elements[i];
		elements = newElements;
	}

	public void expandHeap(int generic) {
		HeapNode newNodes[] = new HeapNode[nodes.length * 2];
		for (int i = 0; i < nodes.length; i++)
			newNodes[i] = nodes[i];
		nodes = newNodes;
	}

	public void display() {
		for (int i = 0; i < heapSize; i++)
			System.out.print(elements[i] + " ");
	}

	public void display(int generic) {
		for (int i = 0; i < heapSize; i++)
			System.out.print(nodes[i].data + " ");
	}

	public static int[] heapify(int[] arr) {
		for (int i = (arr.length - 1) / 2; i >= 0; i--)
			trickledown(i, arr);
		return arr;
	}

	public static int remove(int[] arr, int size) {
		int result = arr[0];
		arr[0] = arr[size];
		trickledown(0, arr);
		return result;
	}

	public static void main(String[] args) {
		int size = (int) (Math.random() * 20);
		Heap heap = new Heap(size);
		for (int i = 0; i < size; i++) {
			int item = (int) (Math.random() * 100);
			System.out.println("\nInserting: " + item);
			HeapNode node = new HeapNode();
			node.data = item;
			heap.insert(node);
			System.out.println("Heap is:");
			heap.display(1);
		}
		System.out.println("\nRemoving from heap:");
		int size1 = heap.heapSize;
		for (int i = 0; i < size1; i++)
			System.out.print(heap.remove(1).data + " ");
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
	}
}
