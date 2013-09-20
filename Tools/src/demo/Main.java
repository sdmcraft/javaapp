package demo;

import dataStructuresV2.Graph;
import dataStructuresV2.impl.BasicDirectedGraph;
import dataStructuresV2.utils.GraphFactory;



public class Main
{
    public static void main(String[] args) throws Exception
    {
    	int[][] dir_adj1 = new int[][]
                {
                    { Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
                    { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1 },
                    { 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE }
                };
    	
        String string1 = "a";
        String string2 = "b";
        String string3 = "c";
        String string4 = "d";
        String string5 = "e";
        String string6 = "f";

    	Graph<String> graph = GraphFactory.getDirectedGraph(dir_adj1, new String[] { string1, string2, string3, string4, string5, string6 }, BasicDirectedGraph.class);
    	System.out.println(graph.getDiagram());
    	((BasicDirectedGraph<String>)graph).dfsLoop();
    	System.out.println(graph.getDiagram());
    }
}
