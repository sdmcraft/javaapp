package dataStructures;

public class BinarySearchTree extends BinaryTree
{
    public BinarySearchTree()
    {
        super();
    }

    public BinarySearchTree(String value)
    {
        super(value);
    }

    private void insert(String valueStr)
    {
        int value = Integer.parseInt(valueStr);
        BinarySearchTree root = this;

        if (Integer.parseInt(this.value) >= value)
        {
            if (this.left == null)
            {
                BinarySearchTree bst = new BinarySearchTree(valueStr);
                this.left = bst;
                this.children.add(bst);

                return;
            }
            else
            {
                root = (BinarySearchTree) this.left;
                root.insert(valueStr);

                return;
            }
        }
        else
        {
            if (this.right == null)
            {
                BinarySearchTree bst = new BinarySearchTree(valueStr);
                this.right = bst;
                this.children.add(bst);

                return;
            }
            else
            {
                root = (BinarySearchTree) this.right;
                root.insert(valueStr);

                return;
            }
        }
    }

    public void build(int nodeCount)
    {
        build(this, nodeCount);
    }

    private static void build(BinarySearchTree root, int nodeCount)
    {
        for (int i = 0; i < nodeCount; i++)
        {
            int rand = (int) (Math.random() * 100);
            root.insert(Integer.toString(rand));
        }
    }

    public static void main(String[] args) throws Exception
    {
        BinarySearchTree bst = new BinarySearchTree("50");
        bst.build(10);
        System.out.println(bst.getDiagram());
    }
}
