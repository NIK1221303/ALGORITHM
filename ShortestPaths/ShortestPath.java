package ShortestPaths;

// this gpt generated code, works to calculate distance between separated nodes.

import java.util.*;

import DatasetTwo.StarRoutes;

public class ShortestPath {

    private final StarRoutes starRoutes;

    public ShortestPath(StarRoutes starRoutes) {
        this.starRoutes = starRoutes;
    }

    private static class Node {
        String star;
        int cost;

        Node(String star, int cost) {
            this.star = star;
            this.cost = cost;
        }
    }

    public List<String> findShortestPath(String start, String end) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String star : starRoutes.getStarRoutes().keySet()) {
            distances.put(star, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (!visited.add(current.star)) {
                continue;
            }

            if (current.star.equals(end)) {
                break;
            }

            // iterate through every current connected star, and pick the star with smallest
            // distance
            for (Map.Entry<String, Integer> neighbor : starRoutes.getConnectedStars(current.star).entrySet()) {
                int newDist = distances.get(current.star) + neighbor.getValue();
                if (newDist < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), newDist);
                    previous.put(neighbor.getKey(), current.star);
                    queue.add(new Node(neighbor.getKey(), newDist));
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}