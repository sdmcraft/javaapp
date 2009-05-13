/*
 * PriorityQueueImpl.java
 *
 * Created on February 1, 2008, 10:36 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithms;

/**
 *
 * @author sdmahesh
 */
public class PriorityQueueImpl extends ArrayQueue implements PriorityQueue {
    
	private int count = 0;
    /** Creates a new instance of PriorityQueueImpl */
    public PriorityQueueImpl(int size) {
        super(size);
    }
    
    private class PriorityItem
    {
    	private final int priority;
    	private final Object item;
    	private PriorityItem(Object item, int priority)
    	{
    		this.priority = priority;
    		this.item = item;
    	}
    }
    
    public void priorityInsert(Object item, int priority) {
    	PriorityItem pitem = new PriorityItem(item,priority);
        if(empty()) {
            front=0;
            rear=0;
            elements[rear]=pitem;
            count=1;
            return;
        }
        if(!isFull()) {
            int i = rear;
            if(item>=elements[rear])
            {
                rear = (rear+1)%elements.length;
                elements[rear] = item;
                count++;
                return;
            }
            while(item < elements[i]) {
                //i=((i-1)>=0)?(i-1)%elements.length:(elements.length+i-1)%elements.length;
                i=(elements.length+i-1)%elements.length;
            }
            i = (i+1)%elements.length;
            int j = rear;
            int shifted=0;
            while(shifted<count) {
                    elements[(j+1)%elements.length] = elements[j%elements.length];                    
                    shifted++;
                if(j==i) {
                    elements[j] = item;
                    count++;
                    rear=(rear+1)%elements.length;
                    return;
                }
                //j=((j-1)>=0)?(j-1)%elements.length:(elements.length+j-1)%elements.length;
                    j=(elements.length+j-1)%elements.length;
            }
        } else throw new RuntimeException("Queue Full!!");
    }
    
    public static void main(String args[]) {
        PriorityQueueImpl q = new PriorityQueueImpl(10);
        //q.scenario();        
        q.bootStrap();
        System.out.println("Bootstrapped Queue:");
        q.display();
        long random = Math.round(Math.random()*100);
        for(int i=0;i< random;i++) {
            long random1 = Math.round(Math.random()*19);
            int random2 = (int)Math.round(Math.random()*1000);
            if(random1%2==0) {
                System.out.println("Inserting:" + random2);
                q.priorityInsert(random2);
            } else{
                System.out.println("Removing");
                System.out.println("Removed:" + q.remove());
            }
            System.out.println("Here is the Queue:");
            q.display();
        }        
    }
    @Override
    public void bootStrap() {
        int random = (int)Math.round(Math.random()*elements.length);
        for(int i=0;i<random;i++) {
            int random1 = (int)Math.round(Math.random()*100);
            priorityInsert(random1);
        }
    }
    
    public void scenario()
    {        
        elements = new int[]{0,0,0,0,331,710,832,834,862,0};
        front=4;
        rear=8;
        count=5;
        System.out.println("Inserting 70");
        priorityInsert(70);
        System.out.println("Here is the Queue:");
        display();        
    }
    
}