package algorithms;

import java.io.Serializable;

/**
 * 
 * @author sdmahesh
 */
public class LinkedList implements Serializable {

	protected LinkedListNode first;

	/** Creates a new instance of LinkedListImpl */
	public LinkedList() {
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public void insertFirst(LinkedListNode item) {
		if(item == first)
			return;
		item.next = first;
		first = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LinkedList))
			return false;
		LinkedList other = (LinkedList) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		return true;
	}

	public LinkedListNode deleteFirst() {
		LinkedListNode temp = first;
		first = first.next;
		return temp;
	}

	public LinkedListNode delete(Object key) {
		LinkedListNode current = first;
		LinkedListNode previous = first;
		while ((current != null) && (!current.data.equals(key))) {
			previous = current;
			current = current.next;
		}
		if (current == null)
			System.out.println("Delete failed!!!Key not found!!!");
		LinkedListNode temp = current;
		previous.next = current.next;
		return temp;
	}

	public void displayList() {
		LinkedListNode current = first;
		if (current == null) {
			System.out.println("Empty");
		} else {
			while (current != null) {
				System.out.print(current.data + "-->");
				current = current.next;
			}
			System.out.println("");
		}

	}

	public LinkedListNode find(Object key) {
		LinkedListNode current = first;
		while ((current != null) && (!current.data.equals(key))) {
			current = current.next;
		}
		if (current == null)
			System.out.println("Search failed!!!");
		else if (current.data.equals(key))
			System.out.println("Search successful!!!");
		return current;
	}

	public void insertBefore(Object key, LinkedListNode node) {
		LinkedListNode current = first;
		LinkedListNode previous = first;
		while ((current.data != key) && (current.next != null)) {
			previous = current;
			current = current.next;
		}
		if (current.data.equals(key)) {
			node.next = current;
			previous.next = node;
		} else if (current.next == null) {
			System.out.println("Insert failed as key not found!!!");
		}
	}

	public void insertAfter(Object key, LinkedListNode node) {
		LinkedListNode current = first;
		while ((!current.data.equals(key)) && (current.next != null)) {
			current = current.next;
		}
		if (current.data.equals(key)) {
			node.next = current.next;
			current.next = node;
		} else if (current.next == null) {
			System.out.println("Insert failed as key not found!!!");
		}
	}

	public void insertInSorted(LinkedListNode node) {
		System.out.println("Going to insert:" + node.data);
		LinkedListNode current = first;
		LinkedListNode previous = null;
		if (first == null)
			first = node;
		else {
			while ((current != null)
					&& ((Integer) node.data >= (Integer) current.data)) {
				previous = current;
				current = current.next;
			}
			if (previous == null) {
				first.next = node;
			} else {
				previous.next = node;
			}
			node.next = current;
		}
	}

	public void insertionSort() {
		LinkedListNode current = first;
		LinkedListNode nextNode = first;
		boolean shift = false;
		if (current == null) {
			System.out.println("Nothing to sort!!!");
			return;
		} else {
			while (current.next != null) {
				LinkedListNode inner = current.next;
				LinkedListNode prevOfInner = null;
				while (((Integer) current.data > (Integer) inner.data)
						&& (inner.next != null)) {
					prevOfInner = inner;
					inner = inner.next;
					shift = true;
				}
				if ((shift) && (first == current)) {
					first = current.next;
					nextNode = first;
					current.next = inner;
					prevOfInner.next = current;
					shift = false;
				}
				current = nextNode;
				this.displayList();
			}

		}
	}

	public void bootStrap() {
		int random = (int) Math.round(Math.random() * 20);
		for (int i = 0; i < random; i++) {
			int random1 = (int) Math.round(Math.random() * 100);
			LinkedListNode node = new LinkedListNode(random1);
			insertFirst(node);
		}
	}

	public boolean detectLoop() {
		boolean loopExists = false;
		if (first == null)
			loopExists = false;
		else {
			LinkedListNode fastPointer1 = first;
			LinkedListNode fastPointer2 = fastPointer1.next;
			LinkedListNode slowPointer = first;
			while ((fastPointer1 != null) && (fastPointer2 != null)) {
				slowPointer = slowPointer.next;
				fastPointer1 = fastPointer2.next;
				fastPointer2 = fastPointer1.next;

			}
		}
		return loopExists;
	}

}
