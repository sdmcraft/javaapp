package dataStructures;

public class DoublyLinkedList
{
    private DoublyLinkedList next;
    private DoublyLinkedList previous;
    private String value;

    public DoublyLinkedList(String value)
    {
        this.value = value;
    }

    public void insertAtEnd(String value)
    {
        DoublyLinkedList node = new DoublyLinkedList(value);
        DoublyLinkedList lastNode = getLastNode();
        lastNode.next = node;
        node.previous = lastNode;
    }

    public DoublyLinkedList getFirstNode()
    {
        DoublyLinkedList currentNode = this;

        while (currentNode.previous != null)
        {
            currentNode = currentNode.previous;
        }

        return currentNode;
    }

    public DoublyLinkedList getLastNode()
    {
        DoublyLinkedList currentNode = this;

        while (currentNode.next != null)
        {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public DoublyLinkedList getNext()
    {
        return next;
    }

    public void setNext(DoublyLinkedList next)
    {
        this.next = next;
    }

    public DoublyLinkedList getPrevious()
    {
        return previous;
    }

    public void setPrevious(DoublyLinkedList previous)
    {
        this.previous = previous;
    }

    @Override
    public String toString()
    {
        DoublyLinkedList currentNode = this.getFirstNode();

        StringBuilder sb = new StringBuilder();
        sb.append(currentNode.value);

        while (currentNode.next != null)
        {
            currentNode = currentNode.next;
            sb.append("<->").append(currentNode.value);
        }

        return sb.toString();
    }

    public static void main(String[] args)
    {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList("0");

        for (int i = 1; i < 10; i++)
        {
            doublyLinkedList.insertAtEnd(Integer.toString(i));
        }

        System.out.println(doublyLinkedList);
    }
}
