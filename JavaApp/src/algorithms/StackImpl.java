package algorithms;

public class StackImpl implements Stack {

    private int elements[];
    private int top;

    public StackImpl(int size) throws Exception{
        if(size < 0)
            throw new Exception("Size cannot be < 0");
        top = -1;
        elements = new int[size];
    }

    public static void main(String[] args) throws Exception {
        StackImpl stack = new StackImpl(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    public int pop() throws Exception {
        if (top < 0) {
            throw new Exception("Nothing to pop!!!");
        }
        return elements[top--];
    }

    public void push(int item) throws Exception {
        if (top >= elements.length - 1) {
            throw new Exception("Stack full. Cannot push!!!");
        }
        elements[++top] = item;
    }

    public int peek() throws Exception{
        if (top < 0) {
            throw new Exception("Nothing to peek!!!");
        }
        return elements[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
