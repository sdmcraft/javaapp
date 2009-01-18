package algorithms;

public interface CharStack {
    public char pop() throws Exception;
    public void push(char item) throws Exception;
    public char peek() throws Exception;   
    public boolean isEmpty();
}
