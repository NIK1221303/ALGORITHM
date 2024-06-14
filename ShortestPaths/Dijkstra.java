package ShortestPaths;

import java.util.*;
import MinimumSpanningTree.Edge;
import DatasetTwo.StarRoutes;

public class Dijkstra {

    private Set<String> visited; // all visited node put here
    private Map<String, Integer> shortestDist; // store shortest distance from Star A
    private Map<String, PriorityQueue<Edge>> graph; // this graph store all edges for each star, each String key have
                                                    // priorityQueue, so always first value for each key is the Edge
                                                    // with min distance
    private final StarRoutes starRoutes;
    private static final int INFINITE = Integer.MAX_VALUE;

    public Dijkstra(StarRoutes starRoutes) {
        this.starRoutes = starRoutes;
        visited = new HashSet<>();
        shortestDist = new HashMap<>();
        graph = new HashMap<>();

    }

    private void initializeEdges() {
        for (String star1 : starRoutes.getStarRoutes().keySet()) {
            PriorityQueue<Edge> edges = new PriorityQueue<>();
            for (Map.Entry<String, Integer> entry : starRoutes.getConnectedStars(star1).entrySet()) {
                String star2 = entry.getKey();
                int distance = entry.getValue();
                edges.add(new Edge(star1, star2, distance)); // add all edges for star1, ascending order of distance
            }
            graph.put(star1, edges); // now add star1 and it's edges into 'graph'(Map)
        }
    }

    public PriorityQueue<Edge> getEdges(String e) {

        return graph.get(e);
    }

    public void InitializeToInfinite(int minDistance) {

        shortestDist.clear();
        for (String star : graph.keySet()) {
            for (Edge edge : getEdges(star)) {

                if (!shortestDist.containsKey(edge.star2)) {
                    shortestDist.put(edge.star2, minDistance); // set all distance to infinite (max value)
                }
            }
        }

    }

    public void calcShortestDistance(String currentNode, int currentDist) {
        int previous = INFINITE;
        String nextNode = null;
        visited.add(currentNode); // Star A

        for (Edge edge : getEdges(currentNode)) { // Get edges of Star A

            if (!visited.contains(edge.star2)) { // If the edge is not inside visited,

                if (edge.distance + currentDist < shortestDist.get(edge.star2)) {
                    shortestDist.put(edge.star2, edge.distance + currentDist); // update min distance
                }

                //
                if (previous > edge.distance + currentDist) { //
                    previous = edge.distance + currentDist; // update currentDist
                    nextNode = edge.star2; // assign node with shortest distance edge

                    for (Map.Entry<String, Integer> entry : shortestDist.entrySet()) {

                        if (visited.contains(entry.getKey())) {
                            continue;
                        }

                        if (entry.getValue() < previous) {

                            previous = entry.getValue();
                            nextNode = entry.getKey();

                        }

                    }
                }
            }
        }

        if (nextNode == null) {

            for (Map.Entry<String, Integer> entry : shortestDist.entrySet()) {

                if (visited.contains(entry.getKey())) {

                    continue;
                }

                if (entry.getValue() < previous) {

                    previous = entry.getValue();
                    nextNode = entry.getKey();

                }

            }
            if (nextNode != null) {
                calcShortestDistance(nextNode, previous);
            }
            return;
        }

        calcShortestDistance(nextNode, previous);// should only call this when done calculated all min distance to every
                                                 // edge

    }

    public void shortestPath(String startNode) {
        int infinite = INFINITE;
        initializeEdges();
        InitializeToInfinite(infinite); // initialize shortest distance to infinite first
        shortestDist.put(startNode, 0); // set start node distance to 0
        calcShortestDistance(startNode, 0); // start calculate shortest distance

        // this is output that need to store in
        // txt file-------------------------------------------------
        for (Map.Entry<String, Integer> entry : shortestDist.entrySet()) {
            System.out.println("Shortest Distance from Star A to " + entry.getKey() + ": " + entry.getValue());
        }
        // ------------------------------------------------------------------------------------------
    }
}