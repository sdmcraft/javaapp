package algorithms;

import java.io.Serializable;

/**
 *
 * @author sdmahesh
 */
public class LinkedListNode implements Serializable{
    public Object data;
    public LinkedListNode next;
    public LinkedListNode prev;
    
    /** Creates a new instance of LinkedListNode */
    public LinkedListNode(Object item) {
        data=item;
    }
    
    public LinkedListNode() {
    }
}
