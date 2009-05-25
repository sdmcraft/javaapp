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
    

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((prev == null) ? 0 : prev.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LinkedListNode))
			return false;
		LinkedListNode other = (LinkedListNode) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (prev == null) {
			if (other.prev != null)
				return false;
		} else if (!prev.equals(other.prev))
			return false;
		return true;
	}


	public LinkedListNode() {
    }
}
