package dataStructuresV2.impl;

import dataStructuresV2.DirectedEdge;
import dataStructuresV2.Edge;
import dataStructuresV2.exception.InvalidDataException;


public class SimpleGraph<T> extends BasicGraph<T>
{
    public SimpleGraph() throws InvalidDataException
    {
        super();
    }

    // UT:TestSimpleGraph.testCanAdd
    /*a simple graph is an undirected graph that has no loops
     *  (edges connected at both ends to the same vertex) and no more than one edge
     *   between any two different vertices.*/
    @Override
    protected boolean canAdd(Edge<T> edge)
    {
        boolean canAddEdge = super.canAdd(edge);

        if (canAddEdge)
        {
        	//No directed edges allowed in simple graph
            if (edge instanceof DirectedEdge)
            {
            	System.out.println("No directed edges allowed in simple graph");
                return false;
            }

            canAddEdge = !edge.getEndpoints()[0].equals(edge.getEndpoints()[1]);

            if (canAddEdge)
            {
                for (Edge<T> graphEdge : edges)
                {
                    if (edge.hasSameEndpoints(graphEdge))
                    {
                        canAddEdge = false;

                        break;
                    }
                }
            }
        }

        return canAddEdge;
    }
}
