package algorithms;

public class DequeueAsStack {

	private Dequeue dq = null;

	public DequeueAsStack(int size) {
		dq = new ArrayDequeue(size);
	}

	public Object pop() throws Exception {
		return dq.frontRemove();
	}

	public void push(Object item) throws Exception {
		dq.frontInsert(item);
	}

}
