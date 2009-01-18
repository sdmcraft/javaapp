package algorithms;

public class CharStackImpl implements CharStack {

    private char elements[];
    private int top;

    public CharStackImpl(int size) throws Exception{
        if(size < 0)
            throw new Exception("Size cannot be < 0");
        top = -1;
        elements = new char[size];
    }


    public char pop() throws Exception {
        if (top < 0) {
            throw new Exception("Nothing to pop!!!");
        }
        return elements[top--];
    }

    public void push(char item) throws Exception {
        if (top >= elements.length - 1) {
            throw new Exception("Stack full. Cannot push!!!");
        }
        elements[++top] = item;
    }

    public char peek() throws Exception{
        if (top < 0) {
            throw new Exception("Nothing to peek!!!");
        }
        return elements[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
