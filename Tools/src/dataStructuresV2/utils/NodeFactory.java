package dataStructuresV2.utils;

import dataStructuresV2.Node;
import dataStructuresV2.impl.NodeImpl;


public class NodeFactory
{
    public static <T> Node<T> getNode(T value)
    {
        return new NodeImpl<T>(value);
    }
}
