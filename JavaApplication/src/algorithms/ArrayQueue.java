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

    public ArrayQueue(Object[] elements) {
        this.elements = elements;
        rear = -1;
        front = -1;
        if ((elements.length == 1) && (elements[0] != null)) {
            front = rear = 0;
        }
        else if ((elements.length == 1) && (elements[0] == null)) {
            ;
        }
        else {
            boolean nullFound = false;
            boolean allNulls = true;
            for (int i = 0; i < elements.length; i++) {
                if ((elements[i] != null) && (elements[(i + 1) % elements.length] == null)) {
                    rear = i;
                    nullFound = true;
                }
                if ((elements[i] != null) && (elements[(i - 1 + elements.length) % elements.length] == null)) {
                    front = i;
                    nullFound = true;
                }
                if((allNulls)&&(elements[i] != null))
                    allNulls = false;
            }
            if(allNulls)
            {
                ;
            }
            else if(!nullFound)
            {
                front = 0;
                rear = elements.length-1;
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

    public void display() {
        int i = front;
        System.out.println("Front:" + front);
        System.out.println("Rear:" + rear);
        while (i != rear) {
            System.out.print(elements[i] + " ");
            i = (i + 1) % elements.length;
        }
        /*Single item in queue - special case*/
        if (i == rear && i != -1) {
            System.out.print(elements[i] + " ");
        }
        System.out.println("");
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

