package dataStructures;

public class LinkedList implements Cloneable {
	private String value;
	private LinkedList next;

	public LinkedList() {
	}

	public LinkedList(String string, boolean split) {
		if (split) {
			this.value = string.charAt(0) + "";
			this.next = stringToLinkedList(string, 1);
		} else {
			this.value = string;
			this.next = null;
		}
	}

	public LinkedList(String[] strings, int index) {
		if (index >= strings.length)
			return;
		this.value = strings[index];
		if (index < strings.length - 1)
			this.next = new LinkedList(strings, index + 1);
	}

	public boolean hasNext() {
		return next != null;
	}

	private static LinkedList stringToLinkedList(String string, int index) {
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
			return "null";
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

	private static LinkedList reverse(LinkedList linkedList, LinkedList parent) {
		if (linkedList == null)
			return parent;
		LinkedList start = reverse(linkedList.next, linkedList);
		linkedList.next = parent;
		return start;
	}

	public LinkedList reverse() {
		LinkedList linkedList = clone(this);
		return reverse(linkedList, null);
	}

	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList("12345", true);
		System.out.println(linkedList.reverse());
		System.out.println(linkedList);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LinkedList getNext() {
		return next;
	}

	public void setNext(LinkedList next) {
		this.next = next;
	}

	/* To be tested */
	public Long sum() {
		Long sum = Long.parseLong(this.value);
		if (this.next != null)
			sum += this.next.sum();
		return sum;
	}

	/* To be tested */
	public LinkedList prefix(LinkedList prefixList) {
		prefixList.next = this;
		return prefixList;
	}
}
