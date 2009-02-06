/*
 * ArrayQueue.java
 *
 * Created on March 21, 2008, 10:23 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package algorithms;

/**
 *
 * @author sdmahesh
 */
public class ArrayQueue implements Queue {

    private Object[] elements;
    private int rear;
    private int front;

    /**
     * Creates a new instance of ArrayQueue
     */
    public ArrayQueue(int size) {
        elements = new Object[size];
        rear = -1;
        front = -1;
    }

    /**
     * Creates a queue from the passes object array.The rear and front are 
     * deduced from the array
     * @param elements
     */
    public ArrayQueue(Object[] elements) {
        this.elements = elements;
        rear = -1;
        front = -1;
        /*A single element array which happens to be full*/
        if ((elements.length == 1) && (elements[0] != null)) {
            front = rear = 0;
        } /*Single element empty array*/ else if ((elements.length == 1) && (elements[0] == null)) {
            ;
        } else {
            boolean nullFound = false;
            boolean allNulls = true;
            /*Scan the array and find front and ear*/
            for (int i = 0; i < elements.length; i++) {
                /*If this element is not null and the next one is null, this is the rear*/
                if ((elements[i] != null) && (elements[(i + 1) % elements.length] == null)) {
                    rear = i;
                    nullFound = true;
                }

                /*If this element is not null and the previous one is null, this is the front*/
                if ((elements[i] != null) && (elements[(i - 1 + elements.length) % elements.length] == null)) {
                    front = i;
                    nullFound = true;
                }

                /*If this elements is not null, it can't be an all empty array*/
                if ((allNulls) && (elements[i] != null)) {
                    allNulls = false;
                }
            }

            /*This is an empty array, rear=front=-1 is already set. Do nothing*/
            if (allNulls) {
                ;
            } /*No nulls were found, this array is full. rear and front are set as start and end points of the array*/ else if (!nullFound) {
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
        hash = 79 * hash + (this.elements != null ? this.elements.hashCode() : 0);
        hash = 79 * hash + this.rear;
        hash = 79 * hash + this.front;
        return hash;
    }

    public void insert(Object item) {
        int newRear = (rear + 1) % elements.length;
        if (newRear == front) {
            throw new BusinessException("Queue full!!!");
        } else {
            elements[newRear] = item;
            rear = newRear;
            /*Queue was empty - special case*/
            if (front == -1) {
                front = rear;
            }
        }
    }

    public void priorityInsert(Integer item) {
        int newRear = (rear + 1) % elements.length;
        if (((rear + 1) % elements.length) == front) {
            throw new BusinessException("Queue full!!!");
        }
        /*Queue was empty - special case*/ 
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

    public Object remove() {
        Object returnItem = null;
        if (rear == -1 && front == -1) {
            throw new BusinessException("Queue empty!!!");
        } else {
            returnItem = elements[front];
            elements[front] = null;
            /*one item in the queue - special case*/
            if (rear == front) {
                rear = -1;
                front = -1;
            } else {
                front = (front + 1) % elements.length;
            }
        }
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
        /*Single item in queue - special case*/
        if (i == rear && i != -1) {
            sb.append(elements[i] + " ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
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
}

