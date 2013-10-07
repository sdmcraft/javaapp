package dataStructuresV2.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataStructuresV2.DirectedEdge;
import dataStructuresV2.DirectedGraph;
import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;
import dataStructuresV2.exception.InvalidDataException.Code;


public class BasicDirectedGraph<T> extends BasicGraph<T> implements DirectedGraph<T>
{
    int finishingTime = 0;

    @Override
    public Set<Graph<T>> getStronglyConnectedComponents()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected boolean canAdd(Edge<T> edge)
    {
        return (edge instanceof DirectedEdge) && super.canAdd(edge);
    }

    //UT:TestBasicDirectedGraph.testReverseEdges
    @Override
    public void reverseEdges()
    {
        for (Edge<T> edge : edges)
        {
            ((DirectedEdge<T>) edge).reverse();
        }
    }

    public void dfsLoop() throws InvalidDataException
    {
        Node<T> leaderNode = null;

        for (Node<T> node : nodes)
        {
            if ((node.getProperty("processed") == null) || !(Boolean) node.getProperty("processed"))
            {
                leaderNode = node;
                dfs(node, leaderNode);
            }
        }
    }

    public void dfsLoop(List<Node<T>> nodes) throws InvalidDataException
    {
    	Set<Node<T>> nodeSet = new HashSet<Node<T>>(nodes);
    	if(!nodeSet.equals(this.nodes))
    	{
    		throw new InvalidDataException(Code.INVALID, "Passed nodes do not match actual graph nodes");
    	}
    	
        Node<T> leaderNode = null;

        for (Node<T> node : nodes)
        {
            if ((node.getProperty("processed") == null) || !(Boolean) node.getProperty("processed"))
            {
                leaderNode = node;
                dfs(node, leaderNode);
            }
        }
    }

    private void dfs(Node<T> node, Node<T> leaderNode) throws InvalidDataException
    {
        node.setProperty("processed", true);
        node.setProperty("leader", leaderNode.getValue());

        for (Node<T> neighbour : getNeighbours(node))
        {
            if ((neighbour.getProperty("processed") == null) || !(Boolean) neighbour.getProperty("processed"))
            {
                dfs(neighbour, leaderNode);
            }
        }

        finishingTime++;
        node.setProperty("finishingTime", finishingTime);
    }
}
