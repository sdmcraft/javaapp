package dataStructuresV2.utils;

import dataStructuresV2.DirectedEdge;
import dataStructuresV2.Edge;
import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.exception.InvalidDataException;
import dataStructuresV2.exception.InvalidDataException.Code;
import dataStructuresV2.impl.BasicGraph;
import dataStructuresV2.impl.SimpleGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GraphFactory
{
    // UT:TestGraphFactory.testGetGraph
    public static <T> Graph<T> getGraph(Set<T> values, int edges) throws InvalidDataException
    {
        Set<Edge<T>> edgeSet = new HashSet<Edge<T>>();
        Graph<T> graph = new BasicGraph<T>();

        List<Node<T>> nodes = new ArrayList<Node<T>>();

        for (T value : values)
        {
            Node<T> node = NodeFactory.getNode(value);
            graph.addNode(node);
            nodes.add(node);
        }

        for (int i = 0; i < edges;)
        {
            int randomIndex = (int) Math.round((nodes.size() - 1) * Math.random());
            Node<T> endPoint1 = nodes.get(randomIndex);
            randomIndex = (int) Math.round((nodes.size() - 1) * Math.random());

            Node<T> endPoint2 = nodes.get(randomIndex);
            Node<T>[] endpoints = new Node[] { endPoint1, endPoint2 };
            Edge<T> edge = EdgeFactory.getEdge(endpoints, Math.random());

            try
            {
                graph.addEdge(edge);
                i++;
            }
            catch (InvalidDataException ex)
            {
                // Ignore and try with another edge
            }
        }

        return graph;
    }

    // UT:TestGraphFactory.testGetGraphWithAdjMatrix
    public static <T> Graph<T> getGraph(int[][] adjMatrix, T[] values, boolean directed, Class graphClass) throws InvalidDataException
    {
    	Graph<T> graph = null;
    	if(BasicGraph.class.equals(graphClass))
    	{
    		graph = new BasicGraph<T>();
    	}
    	else if(SimpleGraph.class.equals(graphClass))
    	{
    		if(directed)
    		{
    			throw new InvalidDataException(InvalidDataException.Code.INVALID, "Directed edges not allowed in simple graph!!");	
    		}
    		graph = new SimpleGraph<T>();
    	}

        if ((adjMatrix.length < 1) || (adjMatrix.length != values.length))
        {
            throw new InvalidDataException(InvalidDataException.Code.INVALID, "Invalid adjacency matrix for making a graph!!");
        }

        for (int i = 0; i < adjMatrix.length; i++)
        {
            if (adjMatrix.length != adjMatrix[i].length)
            {
                throw new InvalidDataException(InvalidDataException.Code.INVALID, "Invalid adjacency matrix for making a graph!!");
            }
        }

        if (!directed)
        {
            for (int i = 0; i < adjMatrix.length; i++)
            {
                for (int j = 0; j < adjMatrix.length; j++)
                {
                    if (adjMatrix[i][j] != adjMatrix[j][i])
                    {
                        throw new InvalidDataException(InvalidDataException.Code.INVALID, "Invalid adjacency matrix for making a non-directional graph. It is not symmetric across the diagonal!!");
                    }
                }
            }
        }

        List<Node<T>> nodeList = new ArrayList<Node<T>>();

        for (T value : values)
        {
            Node<T> node = NodeFactory.getNode(value);
            nodeList.add(node);
            graph.addNode(node);
        }

        for (int i = 0; i < adjMatrix.length; i++)
        {
            for (int j = 0; j < adjMatrix.length; j++)
            {
                if ((i != j) && (adjMatrix[i][j] != Integer.MAX_VALUE))
                {
                    Edge edge = null;

                    if (directed)
                    {
                        edge = EdgeFactory.getDirectedEdge(new Node[] { nodeList.get(i), nodeList.get(j) }, adjMatrix[i][j]);
                    }
                    else
                    {
                        edge = EdgeFactory.getEdge(new Node[] { nodeList.get(i), nodeList.get(j) }, adjMatrix[i][j]);
                    }

                    try
                    {
                    	if(!graph.getEdges().contains(edge))
                    	{
                    		graph.addEdge(edge);
                    	}
                    }
                    catch (InvalidDataException ex)
                    {
                        if (ex.code != Code.DUPLICATE)
                        {
                            throw ex;
                        }
                    }
                }
            }
        }

        return graph;
    }

    // UT:TestGraphFactory.testGetSimpleGraph
    public static <T> Graph<T> getSimpleGraph(Set<T> values, int edges) throws InvalidDataException
    {
        if (edges > ((values.size() * (values.size() - 1)) / 2))
        {
            throw new InvalidDataException(InvalidDataException.Code.INVALID, "Numbers of edges exceeds the maximum possible for a simple graph");
        }
        else
        {
            SimpleGraph<T> simpleGraph = new SimpleGraph<T>();
            List<Node<T>> nodes = new ArrayList<Node<T>>();

            for (T value : values)
            {
                Node<T> node = NodeFactory.getNode(value);
                nodes.add(node);
                simpleGraph.addNode(node);
            }

            for (int i = 0; i < edges;)
            {
                int randomIndex = (int) Math.round((nodes.size() - 1) * Math.random());
                Node<T> endPoint1 = nodes.get(randomIndex);
                Node<T> endPoint2 = null;

                randomIndex = (int) Math.round((nodes.size() - 1) * Math.random());
                endPoint2 = nodes.get(randomIndex);

                Node<T>[] endpoints = new Node[] { endPoint1, endPoint2 };
                Edge<T> edge = EdgeFactory.getEdge(endpoints, 1);

                try
                {
                    simpleGraph.addEdge(edge);
                    i++;
                }
                catch (Exception ex)
                {
                    // Failed to add edge, try again with other endpoints
                }
            }

            return simpleGraph;
        }
    }
}
