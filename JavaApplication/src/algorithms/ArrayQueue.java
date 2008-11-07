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
public class ArrayQueue implements Queue{
    
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
    
    public void insert(Object item) {
        int newRear = (rear+1)%elements.length;
        if(newRear == front)
            throw new BusinessException("Queue full!!!");
        else {
            elements[newRear] = item;
            rear = newRear;
            /*Queue was empty - special case*/
            if(front == -1)
                front = rear;
        }
    }
    
    public Object remove() {
        Object returnItem = null;
        if(rear == -1 && front == -1)
            throw new BusinessException("Queue empty!!!");
        else {
            /*one item in the queue - special case*/
            if(rear==front) {
                returnItem = elements[front];
                rear = -1;
                front = -1;
            } else {
                returnItem = elements[front];
                front = (front+1)%elements.length;
            }
        }
        return returnItem;
    }
    
    public boolean empty() {
        return (rear == -1 && front == -1);
    }
    public void display() {
        int i= front;
        System.out.println("Front:"+front);
        System.out.println("Rear:"+rear);
        while(i != rear) {
            System.out.print(elements[i]+" ");
            i=(i+1)%elements.length;
        }
        /*Single item in queue - special case*/
        if(i == rear && i != -1)
            System.out.print(elements[i]+" ");
        System.out.println("");
    }
    
    public String getQueue() {
        int i= front;
        StringBuffer sb = new StringBuffer();
        while(i != rear) {
            sb.append(elements[i]+" ");
            i=(i+1)%elements.length;
        }
        if(i == rear && i != -1)
            sb.append(elements[i]+" ");
        return new String(sb);
    }
}

