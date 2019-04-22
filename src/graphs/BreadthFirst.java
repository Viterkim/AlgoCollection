package graphs;

import datatypes.Queue;
import java.util.ArrayList;
import java.util.HashMap;

// Search depending on how "far" away Vertices are, the "more" jumps from point A, the later it gets searched
public class BreadthFirst<Type extends Comparable<Type>> {

    // Graph as AdjacencyList
    AdjacencyList<Type> graph;
    // Hashmap already explored
    HashMap<Type, Boolean> visited;
    // Hashmap for parents (for writing the path)
    HashMap<Type, ArrayList<Type>> parents;
    // Queue (first in first out) so we get the elements closest to our point, prioritized first.
    Queue<Type> unvisited;
    // Lookup so we can make sure we don't double add an unvisited vertex
    HashMap<Type, Boolean> unvisitedLookup;

    public BreadthFirst(AdjacencyList<Type> graph) {
        this.graph = graph;
        this.visited = new HashMap<>();
        this.parents = new HashMap<>();
        this.unvisited = new Queue<>();
        this.unvisitedLookup = new HashMap<>();
    }

    // Returns shortest path
    public ArrayList<Type> getPath(Type a, Type b){
        // Declare array where we store the shortest path from a to b
        ArrayList<Type> path = search(a, b);
        path.add(b);
        return path;
    }

    // Returns the path of parents when it finds the right object
    private ArrayList<Type> search(Type current, Type destination){
        addToVisited(current);
        // Is this the right point?
        if (current.equals(destination)){
            return parents.get(current);
        }

        // Add unvisited neighbours as unvisited.
        ArrayList<Type> neighbours = graph.getNeighbours(current);
        for (Type t : neighbours) {
            // We can't double add it to unvisited, so we have to check
            if(!isVisited(t) && !isInUnvisited(t)){
                addToUnvisited(t);
            }
        }

        // Add the parent
        // add current vertex as parent to the next vertex
        // child, parent
        Type next = removeFirstUnvisited();
        if(next != null){
            addParent(next, current);
            System.out.println("Going to " + next + " from " + current + " to search for " + destination);
            return search(next, destination);
        }

        // Destination was never found
        System.out.println("Destination never found");
        return null;
    }

    private ArrayList<Type> getParents(Type child){
        if (parents.get(child) != null){
            return parents.get(child);
        }
        return null;
    }

    // Checks if there already is a parents ArrayList
    // If there isn't make a new one, and add the parent.
    // If there is, add the parent to it
    private void addParent(Type child, Type parent){
        ArrayList<Type> newParents = new ArrayList<>();

        if(parent != null && parents.get(parent) != null){
            newParents.addAll(parents.get(parent));
        }
        newParents.add(parent);
        parents.put(child, newParents);
    }

    // Add an element to the unvisited queue (first in, first out)
    private void addToUnvisited(Type vertex){
        unvisited.enqueue(vertex);
        unvisitedLookup.put(vertex, true);
    }

    // Remove and get an element from the unvisited queue (first in, first out)
    private Type removeFirstUnvisited(){
        if(unvisited.size() > 0){
            return unvisited.dequeue();
        }
        return null;
    }

    private boolean isInUnvisited(Type vertex){
        return unvisitedLookup.get(vertex) != null;
    }

    // Adds a vertex to the list of already visited vertices
    private void addToVisited(Type vertex){
        visited.put(vertex, true);
    }

    // Checks if we visited a vertex before
    private boolean isVisited(Type vertex){
        return visited.get(vertex) != null;
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

        BreadthFirst<Integer> bfs = new BreadthFirst<>(graph);
        ArrayList<Integer> path = bfs.getPath(3,4);
        System.out.println(path.toString());
    }
}
