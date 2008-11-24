package algorithms;

/**
 * Encapsulates an array and associated operations on it
 * @author satyam
 */
public interface Array {
    /**
     * Inserts an item in the array
     * @param item
     * @return
     */
    public boolean insert(int item);

    /**
     * Displays the array
     */
    public void display();
        
    public void bubbleSort();
}
