package algorithms;

public interface Stack {
    public int pop() throws Exception;
    public void push(int item) throws Exception;
    public int peek() throws Exception;   
    public boolean isEmpty();
}
