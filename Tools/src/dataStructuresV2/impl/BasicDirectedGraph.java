package dataStructuresV2.impl;

import dataStructuresV2.DirectedEdge;
import dataStructuresV2.DirectedGraph;
import dataStructuresV2.Edge;
import dataStructuresV2.Graph;

import java.util.Set;


public class BasicDirectedGraph<T> extends BasicGraph<T> implements DirectedGraph<T>
{
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
}
