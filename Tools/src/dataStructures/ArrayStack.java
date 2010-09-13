package dataStructures;

public class ArrayStack {
	private String[] elements;
	private int top;

	public ArrayStack(int size) throws Exception {
		if (size < 0)
			throw new Exception("Size cannot be < 0");
		top = -1;
		elements = new String[size];
	}

	public ArrayStack(String[] elements) throws Exception {
		top = elements.length - 1;
		this.elements = elements;
	}

	public String pop() throws Exception {
		String result;
		if (top < 0) {
			throw new Exception("Nothing to pop!!!");
		}
		result = elements[top];
		elements[top] = null;
		top--;
		return result;
	}

	public void push(String item) throws Exception {
		if (top >= elements.length - 1) {
			throw new Exception("Stack full. Cannot push!!!");
		}
		elements[++top] = item;
	}

	public String peek() throws Exception {
		if (top < 0) {
			throw new Exception("Nothing to peek!!!");
		}
		return elements[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public int getSize() {
		return elements.length;
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = elements.length - 1; i >= 0; i--)
			result += elements[i] + '\n';
		return result;
	}

}
