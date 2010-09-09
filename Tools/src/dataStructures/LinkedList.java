package dataStructures;

public class LinkedList {
	private String value;
	private LinkedList next;

	public LinkedList() {
	}

	public LinkedList(String string) {
		this.value = string.charAt(0) + "";
		this.next = stringToLinkedList(string, 1);
	}

	private static LinkedList stringToLinkedList(String string, int index) {
		if (index >= string.length())
			return null;
		LinkedList linkedList = new LinkedList();
		linkedList.value = string.charAt(index) + "";
		linkedList.next = stringToLinkedList(string, index++);
		return linkedList;
	}
}
