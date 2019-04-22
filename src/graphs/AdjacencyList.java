package graphs;

import java.util.ArrayList;
import java.util.HashMap;


// Represent edges in a graph by having a HashMap (to look up the node itself)
// Then have each node have a corresponding ArrayList of all the edges (This could also be a HashMap though).
public class AdjacencyList<Type> {
    HashMap<Type, ArrayList<Type>> graph;

    public AdjacencyList() {
        this.graph = new HashMap<>();
    }

    // Add a new vertex with no edges
    public void addVertex(Type vertex){
        ArrayList<Type> edges = new ArrayList<>();
        graph.put(vertex, edges);
    }

    // If the array doesn't exist, create a new one
    // Add the edge relation to the ArrayList of edges
    public void addUniEdge(Type from, Type to){
        if (!from.equals(to)){
            ArrayList<Type> edges = graph.get(from);
            edges.add(to);
            graph.put(from, edges);
        }
    }

    public void addBiEdge(Type from, Type to){
        addUniEdge(from, to);
        addUniEdge(to, from);
    }

    public boolean hasEdge(Type from, Type to){
        for (Type t : graph.get(from)) {
            if(t.equals(to)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Type> getNeighbours(Type from){
        return graph.get(from);
    }


    public static void main(String[] args) {
        AdjacencyList<Integer> graph = new AdjacencyList<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addBiEdge(1, 2);
        graph.addBiEdge(2, 3);
        graph.addBiEdge(1, 3);
        graph.addBiEdge(3, 4);
        System.out.println(graph.getNeighbours(1));
    }
}
