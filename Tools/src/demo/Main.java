package demo;

import dataStructuresV2.Graph;
import dataStructuresV2.Node;
import dataStructuresV2.impl.BasicDirectedGraph;
import dataStructuresV2.utils.GraphFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Main
{
    public static void main(String[] args) throws Exception
    {
//        int[][] dir_adj1 = new int[][]
//            {
//                { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
//                { Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
//                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
//                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
//                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1 },
//                { 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE }
//            };

        int[][] dir_adj1 = new int[][]
                {
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1 },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE }
                };

        String string1 = "a";
        String string2 = "b";
        String string3 = "c";
        String string4 = "d";
        String string5 = "e";
        String string6 = "f";

        Graph<String> graph = GraphFactory.getDirectedGraph(dir_adj1, new String[] { string1, string2, string3, string4, string5, string6 }, BasicDirectedGraph.class);
        System.out.println(graph.getDiagram());
        ((BasicDirectedGraph<String>) graph).reverseEdges();
        ((BasicDirectedGraph<String>) graph).dfsLoop();
        System.out.println(graph.getDiagram());

        for (Node<String> node : graph.getNodes())
        {
            System.out.println(node + "====" + node.getProperties());
        }

        ((BasicDirectedGraph<String>) graph).reverseEdges();

        List<Node<String>> nodeList = new ArrayList<Node<String>>();
        nodeList.addAll(graph.getNodes());
        Collections.sort(nodeList, new FinishTimeComparator<String>());
        System.out.println(nodeList);
        ((BasicDirectedGraph<String>) graph).dfsLoop(nodeList);

        for (Node<String> node : graph.getNodes())
        {
            System.out.println(node + "====" + node.getProperties());
        }

        Map<String, Set<Node>> nodesByLeader = new HashMap<String, Set<Node>>();

        for (Node<String> node : graph.getNodes())
        {
            String leader = (String) node.getProperty("leader");

            if (nodesByLeader.get(leader) == null)
            {
                nodesByLeader.put(leader, new HashSet<Node>());
            }

            nodesByLeader.get(leader).add(node);
        }

        System.out.println(nodesByLeader);
    }
}


class FinishTimeComparator<T> implements Comparator<Node<T>>
{
    @Override
    public int compare(Node<T> node1, Node<T> node2)
    {
        return ((Integer) node1.getProperty("finishingTime")).compareTo(((Integer) node2.getProperty("finishingTime")));
    }
}
