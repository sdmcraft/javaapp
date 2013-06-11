package tools;

import dataStructures.BinarySearchTree;
import dataStructures.BinaryTree;
import dataStructures.DoublyLinkedList;


public class DSUtils
{
    public static boolean arrayCompare(int[] src, int[] tgt)
    {
        if ((src == null) || (tgt == null) || (src.length != tgt.length))
        {
            return false;
        }
        else
        {
            for (int i = 0; i < src.length; i++)
            {
                if (src[i] != tgt[i])
                {
                    return false;
                }
            }
        }

        return true;
    }

    public static int[] arrayCopy(int[] src)
    {
        int[] result = new int[src.length];

        for (int i = 0; i < src.length; i++)
        {
            result[i] = src[i];
        }

        return result;
    }

    public static int[] stringArrayToIntArray(String[] data)
    {
        int[] result = new int[data.length];
        int i = 0;

        for (String s : data)
        {
            result[i++] = Integer.parseInt(s);
        }

        return result;
    }

    public static String[] intArrayToStringArray(int[] data)
    {
        String[] result = new String[data.length];
        int i = 0;

        for (int a : data)
        {
            result[i++] = Integer.toString(a);
        }

        return result;
    }

    public static double[] stringArrayToDoubleArray(String[] data)
    {
        double[] result = new double[data.length];
        int i = 0;

        for (String s : data)
        {
            result[i++] = Double.parseDouble(s);
        }

        return result;
    }

    public static String booleanArrayToBitString(boolean[] input)
    {
        StringBuilder sb = new StringBuilder();

        for (boolean b : input)
        {
            if (b)
            {
                sb.append('1');
            }
            else
            {
                sb.append('0');
            }
        }

        return sb.toString();
    }

    public static boolean[] bitStringToBooleanArray(String bitStr)
    {
        boolean[] bits = new boolean[bitStr.length()];

        for (int i = 0; i < bitStr.length(); i++)
        {
            if (bitStr.charAt(i) == '1')
            {
                bits[i] = true;
            }
        }

        return bits;
    }

    public static DoublyLinkedList getDoublyLinkedList(BinaryTree bt)
    {
        if (bt == null)
        {
            return null;
        }
        else
        {
            DoublyLinkedList leftList = getDoublyLinkedList(bt.getLeft());
            DoublyLinkedList rtList = getDoublyLinkedList(bt.getRight());

            DoublyLinkedList doublyLinkedList = new DoublyLinkedList(bt.getValue());

            if (rtList != null)
            {
                doublyLinkedList.setNext(rtList.getFirstNode());
                rtList.getFirstNode().setPrevious(doublyLinkedList);
            }

            if (leftList != null)
            {
                doublyLinkedList.setPrevious(leftList.getLastNode());
                leftList.getLastNode().setNext(doublyLinkedList);
            }

            return doublyLinkedList;
        }
    }

    public static void main(String[] args) throws Exception
    {
        BinarySearchTree bst = new BinarySearchTree();
        bst.build(5);
        System.out.println(bst.getDiagram());

        DoublyLinkedList doublyLinkedList = getDoublyLinkedList(bst);
        System.out.println(doublyLinkedList);
    }
}
