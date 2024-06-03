import java.util.Map;

public class MinSpanningTree implements Sort{
    
    DataTwo data;
    StarRoutes star;
    
    public MinSpanningTree(DataTwo data) {
        this.data = data;
        star = new StarRoutes(data);
    }

    public void sort() {


        System.out.println(star.getConnectedStars("Star A"));

        System.out.println(shortestDistanceStar("Star A").getName());
    
    }


    public Star shortestDistanceStar(String starName) {
        Map<String, Integer> connections = star.getConnectedStars(starName);
        String closestStarName = null;
        int shortestDistance = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : connections.entrySet()) {
            if (entry.getValue() < shortestDistance) {
                shortestDistance = entry.getValue();
                closestStarName = entry.getKey();
            }
        }

        if (closestStarName != null) {
            return data.getStar(closestStarName);
        }

        return null; // No connected stars or invalid starName
    } 
}