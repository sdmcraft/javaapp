package dataStructures;

public class LinkedList implements Cloneable {
	private String value;
	private LinkedList next;

	public LinkedList() {
	}

	public LinkedList(String string) {
		this.value = string.charAt(0) + "";
		this.next = stringToLinkedList(string, 1);
	}

	private static LinkedList stringToLinkedList(String string, int index) {
		System.out.println("Index:" + index);
		if (index >= string.length())
			return null;
		LinkedList linkedList = new LinkedList();
		linkedList.value = string.charAt(index) + "";
		linkedList.next = stringToLinkedList(string, ++index);
		return linkedList;
	}

	@Override
	public String toString() {
		return toString(this);
	}

	private static String toString(LinkedList linkedList) {
		if (linkedList == null) {
			return null;
		} else {
			return linkedList.value + "-->" + toString(linkedList.next);
		}
	}

	@Override
	public Object clone() {
		return clone(this);

	}

	private static LinkedList clone(LinkedList linkedList) {
		if (linkedList == null)
			return null;
		LinkedList clonedLinkedList = new LinkedList();
		clonedLinkedList.value = linkedList.value;
		clonedLinkedList.next = clone(linkedList.next);
		return clonedLinkedList;
	}

	 private static void reverse(LinkedList linkedList, LinkedList parent)
	 {
		if(linkedList == null)
			return;
		else
		{
			reverse(linkedList.next, linkedList);
		}
		linkedList.next = parent;
	 }

	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList("12345");
		System.out.println(linkedList);
		System.out.println(linkedList.clone());
		linkedList.reverse(linkedList, null);
		System.out.println(linkedList);
	}
}
