package dataStructuresV2.impl;

import dataStructuresV2.DirectedEdge;
import dataStructuresV2.Edge;
import dataStructuresV2.Node;

import java.util.Arrays;


public class EdgeImpl<T> implements Edge<T>
{
    protected final Number weight;
    protected final Node<T>[] endPoints;

    public EdgeImpl(Node<T>[] endPoints, Number weight)
    {
        this.endPoints = endPoints;
        this.weight = weight;
    }

    public EdgeImpl(Node<T>[] endPoints)
    {
        this.endPoints = endPoints;
        this.weight = null;
    }

    @Override
    public Node<T>[] getEndpoints()
    {
        /*
         * Return a copy so that external modifications do not change the edge
         * end points
         */
        return new Node[] { endPoints[0], endPoints[1] };
    }

    @Override
    public Number getWeight()
    {
        return weight;
    }

    @Override
    public String getDiagramFragment()
    {
        return endPoints[0].getDiagramFragment() + "->" + endPoints[1].getDiagramFragment() + "[dir=\"none\"]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + endPoints[0].hashCode() + endPoints[1].hashCode();

        result = (prime * result) + ((weight == null) ? 0 : weight.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        EdgeImpl other = (EdgeImpl) obj;

        if (weight == null)
        {
            if (other.weight != null)
            {
                return false;
            }
        }
        else if (!weight.equals(other.weight))
        {
            return false;
        }

        Node<T>[] reverseEndPoints = new Node[] { other.endPoints[1], other.endPoints[0] };

        if (!Arrays.equals(endPoints, other.endPoints) && !Arrays.equals(endPoints, reverseEndPoints))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return endPoints[0] + "--" + weight + "-->" + endPoints[1];
    }

    @Override
    public boolean isOrigin(Node<T> node)
    {
        //For an undirected edge, both end points are origins
        return this.endPoints[0].equals(node) || this.endPoints[1].equals(node);
    }

    @Override
    public boolean hasSameEndpoints(Edge<T> other)
    {
        Node<T>[] endPoints = other.getEndpoints();
        Node<T>[] reverseEndPoints = new Node[] { endPoints[1], endPoints[0] };

        if (Arrays.equals(this.endPoints, endPoints) || Arrays.equals(this.endPoints, reverseEndPoints))
        {
            return true;
        }

        return false;
    }
}
