package dataStructures;

/**
 * 
 * @author sdmahesh
 */
public class ArrayQueue {

	protected Object[] elements;
	protected int rear;
	protected int front;

	/**
	 * Creates a new instance of ArrayQueue
	 */
	public ArrayQueue(int size) throws Exception {
		if (size < 0)
			throw new Exception("size cannot be less than 0");
		elements = new Object[size];
		rear = -1;
		front = -1;
	}

	/**
	 * Creates a queue from the passes object array.The rear and front are
	 * deduced from the array
	 * 
	 * @param elements
	 */
	public ArrayQueue(Object[] elements) throws Exception {
		if (elements == null)
			throw new Exception("elements cannot be null");
		this.elements = elements;
		rear = -1;
		front = -1;
		/* A single element array which happens to be full */
		if ((elements.length == 1) && (elements[0] != null)) {
			front = rear = 0;
		} /* Single element empty array */else if ((elements.length == 1)
				&& (elements[0] == null)) {
			;
		} else {
			boolean nullFound = false;
			boolean allNulls = true;
			/* Scan the array and find front and rear */
			for (int i = 0; i < elements.length; i++) {
				/*
				 * If this element is not null and the next one is null, this is
				 * the rear
				 */
				if ((elements[i] != null)
						&& (elements[(i + 1) % elements.length] == null)) {
					rear = i;
					nullFound = true;
				}

				/*
				 * If this element is not null and the previous one is null,
				 * this is the front
				 */
				if ((elements[i] != null)
						&& (elements[(i - 1 + elements.length)
								% elements.length] == null)) {
					front = i;
					nullFound = true;
				}

				/* If this elements is not null, it can't be an all empty array */
				if ((allNulls) && (elements[i] != null)) {
					allNulls = false;
				}
			}

			/* This is an empty array, rear=front=-1 is already set. Do nothing */
			if (allNulls) {
				;
			} /*
			 * No nulls were found, this array is full. rear and front are set
			 * as start and end points of the array
			 */else if (!nullFound) {
				front = 0;
				rear = elements.length - 1;
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		if ((this == obj) && (obj != null)) {
			result = true;
		} else if (obj == null) {
			result = false;
		} else if (getClass() != obj.getClass()) {
			result = false;
		} else {
			final ArrayQueue other = (ArrayQueue) obj;
			if (this.rear != other.rear) {
				result = false;
			} else if (this.front != other.front) {
				result = false;
			} else if (this.elements == other.elements) {
				result = true;
			} else if (this.elements.length != other.elements.length) {
				result = false;
			} else {
				for (int i = 0; i < this.elements.length; i++) {
					if (this.elements[i] == other.elements[i]) {
						continue;
					}
					if (!(this.elements[i].equals(other.elements[i]))) {
						result = false;
						break;
					}
				}
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash
				+ (this.elements != null ? this.elements.hashCode() : 0);
		hash = 79 * hash + this.rear;
		hash = 79 * hash + this.front;
		return hash;
	}

	public synchronized void insert(Object item) throws Exception {
		int newRear = (rear + 1) % elements.length;
		while (newRear == front) {
			System.out.println("Going to wait since the queue is full");
			wait();
			newRear = (rear + 1) % elements.length;
		}
		// System.out.println("Wait over,  proceeding with insert");
		elements[newRear] = item;
		rear = newRear;
		/* Queue was empty - special case */
		if (front == -1) {
			front = rear;
		}
		notifyAll();
	}

	public void priorityInsert(Integer item) throws Exception {
		int newRear = (rear + 1) % elements.length;
		if (((rear + 1) % elements.length) == front) {
			throw new Exception("Queue full!!!");
		}
		/* Queue was empty - special case */
		else if (front == -1) {
			front = newRear;
			elements[newRear] = item;
			rear = newRear;
		} else {
			if ((Integer) (elements[front]) > item) {
				elements[(front - 1 + elements.length) % elements.length] = item;
				front = (front - 1 + elements.length) % elements.length;
			} else {
				while ((Integer) (elements[rear]) > item) {
					elements[(rear + 1) % elements.length] = elements[rear];
					rear = (rear - 1 + elements.length) % elements.length;
				}
				elements[(rear + 1) % elements.length] = item;
				rear = newRear;
			}
		}
	}

	public Object priorityRemove() throws Exception {
		Object returnItem = null;
		if (rear == -1 && front == -1) {
			throw new Exception("Queue empty!!!");
		} else if (rear == front) {
			returnItem = elements[front];
			rear = -1;
			front = -1;
		} else {
			int minIndex = min();
			if (minIndex < 0)
				throw new Exception("Queue empty!!!");
			returnItem = elements[minIndex];
			int ptr = minIndex;
			while (true) {
				if (ptr == front) {
					elements[front] = null;
					front = (front + 1) % elements.length;
					break;
				}
				elements[ptr] = elements[(ptr - 1 + elements.length)
						% elements.length];
				ptr = (ptr - 1 + elements.length) % elements.length;
			}
		}
		return returnItem;
	}

	private int min() {
		int minIndex = -1;
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] != null)
				if (minIndex == -1)
					minIndex = i;
				else if ((Integer) elements[i] < (Integer) elements[minIndex])
					minIndex = i;
		}
		return minIndex;
	}

	public synchronized Object remove() throws Exception {
		Object returnItem = null;
		while (rear == -1 && front == -1) {
			System.out.println("Going to wait since the queue is empty");
			wait();
		}
		// System.out.println("Wait over,  proceeding with remove");
		returnItem = elements[front];
		elements[front] = null;
		/* one item in the queue - special case */
		if (rear == front) {
			rear = -1;
			front = -1;
		} else {
			front = (front + 1) % elements.length;
		}
		notifyAll();
		return returnItem;
	}

	public boolean empty() {
		return (rear == -1 && front == -1);
	}

	public String display() {
		int i = front;
		StringBuffer sb = new StringBuffer();
		sb.append("Front:" + front + "\n");
		sb.append("Rear:" + rear + "\n");
		while (i != rear) {
			sb.append(elements[i] + " ");
			i = (i + 1) % elements.length;
		}
		/* Single item in queue - special case */
		if (i == rear && i != -1) {
			sb.append(elements[i] + " ");
		}
		sb.append("\n");
		// System.out.println(sb.toString());
		return sb.toString();
	}

	public String getQueue() {
		int i = front;
		StringBuffer sb = new StringBuffer();
		while (i != rear) {
			sb.append(elements[i] + " ");
			i = (i + 1) % elements.length;
		}
		if (i == rear && i != -1) {
			sb.append(elements[i] + " ");
		}
		return new String(sb);
	}

	public void flush() {
		elements = new Object[this.elements.length];
		rear = -1;
		front = -1;
	}

	static class Task implements Runnable {

		ArrayQueue queue;
		String mode;

		public Task(ArrayQueue queue, String mode) {
			this.queue = queue;
			this.mode = mode;
		}

		public void run() {
			try {
				for (int i = 0; i < 100; i++) {
					if ("insert".equalsIgnoreCase(mode))
						queue.insert(new Object());
					else
						queue.remove();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ArrayQueue queue = new ArrayQueue(10);
		for (int i = 0; i < 10; i++) {
			new Thread(new Task(queue, "insert")).start();
			new Thread(new Task(queue, "remove")).start();
		}
	}
}
