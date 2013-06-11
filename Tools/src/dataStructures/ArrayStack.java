package dataStructures;

public class ArrayStack
{
    private String[] elements;
    private int top;

    public ArrayStack(int size) throws Exception
    {
        if (size < 0)
        {
            throw new Exception("Size cannot be < 0");
        }

        top = -1;
        elements = new String[size];
    }

    public ArrayStack(String[] elements) throws Exception
    {
        top = elements.length - 1;
        this.elements = elements;
    }

    public String pop() throws Exception
    {
        String result;

        if (top < 0)
        {
            throw new Exception("Nothing to pop!!!");
        }

        result = elements[top];
        elements[top] = null;
        top--;

        return result;
    }

    public void push(String item) throws Exception
    {
        if (top >= (elements.length - 1))
        {
            throw new Exception("Stack full. Cannot push!!!");
        }

        elements[++top] = item;
    }

    public String peek() throws Exception
    {
        if (top < 0)
        {
            throw new Exception("Nothing to peek!!!");
        }

        return elements[top];
    }

    public boolean isEmpty()
    {
        return top == -1;
    }

    public int getSize()
    {
        return top + 1;
    }

    @Override
    public String toString()
    {
        String result = "";

        for (int i = elements.length - 1; i >= 0; i--)
        {
            result += (elements[i] + '\n');
        }

        return result;
    }

    private static void reverse(ArrayStack stack) throws Exception
    {
        String val = stack.pop();

        if (!stack.isEmpty())
        {
            reverse(stack);
            swap(stack, val);
        }
        else
        {
            stack.push(val);
        }
    }

    private static void swap(ArrayStack stack, String val) throws Exception
    {
        if (!stack.isEmpty())
        {
            String val2 = stack.pop();
            swap(stack, val);
            stack.push(val2);
        }
        else
        {
            stack.push(val);
        }
    }

    public void reverse() throws Exception
    {
        reverse(this);
    }

    public String[] toArray()
    {
        String[] result = new String[elements.length];

        for (int i = 0; i < elements.length; i++)
        {
            result[i] = elements[i];
        }

        return result;
    }

    public static void main(String[] args) throws Exception
    {
        ArrayStack stack = new ArrayStack("a,b,c,d".split(","));
        System.out.println(stack);
        stack.reverse();
        System.out.println(stack);
    }

    public int getTop()
    {
        return top;
    }

    public ArrayStack cloneMe() throws Exception
    {
        ArrayStack clonedStack = new ArrayStack(elements.length);
        clonedStack.top = this.top;

        String[] result = new String[elements.length];

        for (int i = 0; i < elements.length; i++)
        {
            result[i] = elements[i];
        }

        clonedStack.elements = result;

        return clonedStack;
    }
}
