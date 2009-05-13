package algorithms;

public class ArrayDequeue implements Dequeue {

	private Object[] elements;
	private int front;
	private int rear;

	public ArrayDequeue(int size) {
		elements = new Object[size];
		front = -1;
		rear = -1;
	}

	/**
	 * Creates a queue from the passes object array.The rear and front are
	 * deduced from the array
	 * 
	 * @param elements
	 */
	public ArrayDequeue(Object[] elements) {
		this.elements = elements;
		rear = -1;
		front = -1;
		if (elements == null)
			return;
		/* A single element array which happens to be full */
		if ((elements.length == 1) && (elements[0] != null)) {
			front = rear = 0;
		} /* Single element empty array */
		else if ((elements.length == 1) && (elements[0] == null)) {
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
			final ArrayDequeue other = (ArrayDequeue) obj;
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
		hash = 79 * hash;
		if (this.elements != null) {
			for (Object o : this.elements) {
				if (o != null)
					hash += o.hashCode();
			}
		}
		hash = 79 * hash + this.rear;
		hash = 79 * hash + this.front;
		return hash;
	}

	@Override
	public void frontInsert(Object item) {
		if(elements.length < 1)
			throw new BusinessException("Queue full!!!");
		if (rear == -1 && front == -1) {
			rear = front = 0;
			elements[front] = item;
		} else {
			int newFront = (front - 1 + elements.length) % elements.length;
			if (newFront == rear) {
				throw new BusinessException("Queue full!!!");
			} else {
				elements[newFront] = item;
				front = newFront;
			}
		}
	}

	@Override
	public Object frontRemove() {
		Object returnItem = null;
		if (rear == -1 && front == -1) {
			throw new BusinessException("Queue empty!!!");
		} else {
			returnItem = elements[front];
			elements[front] = null;
			/* one item in the queue - special case */
			if (rear == front) {
				rear = -1;
				front = -1;
			} else {
				front = (front + 1) % elements.length;
			}
		}
		return returnItem;
	}

	@Override
	public void rearInsert(Object item) {
		int newRear = (rear + 1) % elements.length;
		if (newRear == front) {
			throw new BusinessException("Queue full!!!");
		} else {
			elements[newRear] = item;
			rear = newRear;
			/* Queue was empty - special case */
			if (front == -1) {
				front = rear;
			}
		}
	}

	@Override
	public Object rearRemove() {
		Object returnItem = null;
		if (rear == -1 && front == -1) {
			throw new BusinessException("Queue empty!!!");
		} else {
			returnItem = elements[rear];
			elements[rear] = null;
			/* one item in the queue - special case */
			if (rear == front) {
				rear = -1;
				front = -1;
			} else {
				rear = (rear - 1 + elements.length) % elements.length;
			}
		}
		return returnItem;
	}

}
