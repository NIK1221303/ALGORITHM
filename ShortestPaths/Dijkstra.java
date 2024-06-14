package ShortestPaths;

import java.util.*;
import MinimumSpanningTree.Edge;

import DatasetTwo.StarRoutes;

public class Dijkstra {

    private int minDistance;
    private Set<String> visited; // all visited node put here
    private Map<String, Integer> shortestDist; // Star A , 0
    private Map<String, List<Edge>> graph;
    private final StarRoutes starRoutes;
    private static final int INFINITE = Integer.MAX_VALUE;

    public Dijkstra(StarRoutes starRoutes) {
        this.starRoutes = starRoutes;
        visited = new HashSet<>();

        shortestDist = new HashMap<>();
        graph = new HashMap<>();
        minDistance = INFINITE;
        initializeEdges();

    }

    private void initializeEdges() {
        for (String star1 : starRoutes.getStarRoutes().keySet()) {
            List<Edge> edges = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : starRoutes.getConnectedStars(star1).entrySet()) {
                String star2 = entry.getKey();
                int distance = entry.getValue();
                edges.add(new Edge(star1, star2, distance));
            }
            graph.put(star1, edges);
        }
    }

    public List<Edge> getEdges(String e) {

        return graph.get(e);
    }


    public void InitializeToInfinite(int minDistance) {

        shortestDist.clear();
        for (String star : graph.keySet()) {
            for (Edge edge : getEdges(star)) {

                // System.out.println(edge.star1 + " - " + edge.star2 + ": " + edge.distance);

                if (!shortestDist.containsKey(edge.star2)) {
                    shortestDist.put(edge.star2, minDistance);
                }
            }
        }
    }

    public void calcShortestDistance(String currentNode, int currentDist) {
        int previous = INFINITE;
        String nextNode = null;
        visited.add(currentNode); // Star A
        System.out.println("************************************************");
        System.out.println("CURRENTLY AT NODE " + currentNode); // Star A
        for (Edge edge : getEdges(currentNode)) {
            // int newDist =edge.distance;

            if (!visited.contains(edge.star2)) {
                System.out.println("NOW CALCULATE DISTANCE TO " + edge.star2);
                // newDist +=currentDist; // 0+26
                if (edge.distance + currentDist < shortestDist.get(edge.star2)) {
                    System.out.println("FIND OUT THAT DISTANCE FROM STAR A TO " + edge.star2 + " THROUGH " + edge.star1
                            + " IS =" + (edge.distance + currentDist) + " ,SMALLER THAN PREVIOUS ONE, = "
                            + shortestDist.get(edge.star2) + ",UPDATE DISTANCE");
                    shortestDist.put(edge.star2, edge.distance + currentDist);

                    if (previous > edge.distance + currentDist) {
                        previous = edge.distance + currentDist;
                        nextNode = edge.star2;
                    }

                } else {
                    System.out.println("FIND OUT THAT DISTANCE FROM STAR A TO " + edge.star2 + " THROUGH " + edge.star1
                            + " IS =" + (edge.distance + currentDist) + " ,GREATER THAN PREVIOUS ONE, = "
                            + shortestDist.get(edge.star2) + ",DISTANCE REMAIN");
                }
            }
        }

        if (nextNode == null) {

            for (String visit : visited) {
                for (Edge edge : getEdges(visit)) {
                    if (!visited.contains(edge.star2)) {
                        System.out.println(
                                "THERE IS NO UPDATE DISTANCE IN NODE G, SO WE CHOOSE YET VISITED NODE AS NEXT NODE,"
                                        + edge.star2);
                        System.out.println(edge.distance + "-----");
                        calcShortestDistance(edge.star2, edge.distance);
                        return;

                    }
                }
            }
            return;
        }
        System.out.println("NEXT NODE IS " + nextNode + " WITH SHORTEST DISTANCE FROM STAR A " + previous);
        calcShortestDistance(nextNode, previous);// should only call this when done calculated all min distance to every
                                                 // edge

    }

    public void shortestPath(String startNode) {
        // // objective : from node A find shortestpath to every other node
        // // first go to shortest path among all directly connect nodes to node A (in
        // our case, E,B,C,H,I,F,L,M)

        InitializeToInfinite(minDistance); // initialize shortest distance to infinite first
        shortestDist.put(startNode, 0);
        calcShortestDistance(startNode, 0);

        System.out.println("RESULT:");
        // this is output that need to store in
        // txt file-------------------------------------------------
        for (Map.Entry<String, Integer> entry : shortestDist.entrySet()) {
            System.out.println("Shortest Distance from Star A to " + entry.getKey() + ": " + entry.getValue());
        }
        // ------------------------------------------------------------------------------------------

        System.out.println(" ");
        System.out.println("VISITED NODE LIST BELOW");
        for (String visit : visited) {
            System.out.println(visit); // if have 20 then it has visited all nodes
        }
    }
}
