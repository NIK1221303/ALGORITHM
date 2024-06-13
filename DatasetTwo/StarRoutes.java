package DatasetTwo;

import java.util.*;

public class StarRoutes {

    private DataTwo dataTwo;
    private Map<String, Map<String, Integer>> starRoutes;

    public StarRoutes(DataTwo dataTwo) {
        this.dataTwo = dataTwo;
        this.starRoutes = new HashMap<>();
        initializeRoutes();
    }

    private void initializeRoutes() {

        addConnection("Star A", "Star B");
        addConnection("Star A", "Star C");
        addConnection("Star A", "Star E");
        addConnection("Star A", "Star F");
        addConnection("Star A", "Star H");
        addConnection("Star A", "Star I");
        addConnection("Star A", "Star L");
        addConnection("Star A", "Star M");

        addConnection("Star B", "Star A");
        addConnection("Star B", "Star I");
        addConnection("Star B", "Star K");
        addConnection("Star B", "Star F");
        addConnection("Star B", "Star O");
        addConnection("Star B", "Star L");

        addConnection("Star C", "Star D");
        addConnection("Star C", "Star N");
        addConnection("Star C", "Star H");
        addConnection("Star C", "Star A");
        addConnection("Star C", "Star Q");
        addConnection("Star C", "Star M");
        addConnection("Star C", "Star S");

        addConnection("Star D", "Star I");
        addConnection("Star D", "Star H");
        addConnection("Star D", "Star N");
        addConnection("Star D", "Star C");
        addConnection("Star D", "Star S");

        addConnection("Star E", "Star A");
        addConnection("Star E", "Star R");
        addConnection("Star E", "Star S");
        addConnection("Star E", "Star L");

        addConnection("Star F", "Star K");
        addConnection("Star F", "Star B");
        addConnection("Star F", "Star O");
        addConnection("Star F", "Star T");
        addConnection("Star F", "Star L");
        addConnection("Star F", "Star A");
        addConnection("Star F", "Star R");

        addConnection("Star G", "Star O");
        addConnection("Star G", "Star J");
        addConnection("Star G", "Star P");

        addConnection("Star H", "Star N");
        addConnection("Star H", "Star D");
        addConnection("Star H", "Star I");
        addConnection("Star H", "Star C");
        addConnection("Star H", "Star M");
        addConnection("Star H", "Star A");

        addConnection("Star I", "Star D");
        addConnection("Star I", "Star H");
        addConnection("Star I", "Star A");
        addConnection("Star I", "Star B");
        addConnection("Star I", "Star K");

        addConnection("Star J", "Star G");
        addConnection("Star J", "Star O");
        addConnection("Star J", "Star T");
        addConnection("Star J", "Star P");

        addConnection("Star K", "Star I");
        addConnection("Star K", "Star B");
        addConnection("Star K", "Star F");
        addConnection("Star K", "Star O");

        addConnection("Star L", "Star A");
        addConnection("Star L", "Star B");
        addConnection("Star L", "Star F");
        addConnection("Star L", "Star M");
        addConnection("Star L", "Star E");
        addConnection("Star L", "Star R");
        addConnection("Star L", "Star T");

        addConnection("Star M", "Star S");
        addConnection("Star M", "Star Q");
        addConnection("Star M", "Star C");
        addConnection("Star M", "Star H");
        addConnection("Star M", "Star A");
        addConnection("Star M", "Star L");

        addConnection("Star N", "Star D");
        addConnection("Star N", "Star H");
        addConnection("Star N", "Star C");
        addConnection("Star N", "Star Q");

        addConnection("Star O", "Star K");
        addConnection("Star O", "Star G");
        addConnection("Star O", "Star B");
        addConnection("Star O", "Star F");
        addConnection("Star O", "Star T");
        addConnection("Star O", "Star J");

        addConnection("Star P", "Star R");
        addConnection("Star P", "Star T");
        addConnection("Star P", "Star J");
        addConnection("Star P", "Star G");

        addConnection("Star Q", "Star N");
        addConnection("Star Q", "Star C");
        addConnection("Star Q", "Star M");
        addConnection("Star Q", "Star S");

        addConnection("Star R", "Star E");
        addConnection("Star R", "Star L");
        addConnection("Star R", "Star F");
        addConnection("Star R", "Star T");
        addConnection("Star R", "Star P");

        addConnection("Star S", "Star D");
        addConnection("Star S", "Star Q");
        addConnection("Star S", "Star C");
        addConnection("Star S", "Star M");
        addConnection("Star S", "Star E");

        addConnection("Star T", "Star F");
        addConnection("Star T", "Star O");
        addConnection("Star T", "Star J");
        addConnection("Star T", "Star L");
        addConnection("Star T", "Star R");
        addConnection("Star T", "Star P");
    }

    // Connect two Stars with distance between them
    private void addConnection(String star1, String star2) {
        int distance = dataTwo.getStarDistance(star1, star2);
        starRoutes.computeIfAbsent(star1, k -> new HashMap<>()).put(star2, distance);
        starRoutes.computeIfAbsent(star2, k -> new HashMap<>()).put(star1, distance);
    }

    public Map<String, Map<String, Integer>> getStarRoutes() {
        return starRoutes;
    }

    public Map<String, Integer> getConnectedStars(String starName) {
        return starRoutes.getOrDefault(starName, Collections.emptyMap());
    }

    public Integer getDistanceBetween(String star1, String star2) {
        return starRoutes.getOrDefault(star1, Collections.emptyMap()).get(star2);
    }

    // Insert Source Star as a key in a HashMap
        // Add Destination Star in a new HashMap with distance between them at the Source Star HashMaps' value
        // Bidirectional connection
}