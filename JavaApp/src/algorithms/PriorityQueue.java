/*
 * PriorityQueue.java
 *
 * Created on February 1, 2008, 10:33 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package algorithms;

/**
 *
 * @author sdmahesh
 */
public interface PriorityQueue extends Queue{
    public void priorityInsert(Object item, int priority);
}
