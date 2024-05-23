import java.io.*;
import java.util.*;
import java.lang.Math;

public class DataTwo {

    public DataTwo() {
        loadData();
    }

    // Initialize the ArrayList to store HashMaps
    static ArrayList<HashMap<String, Object>> starList = new ArrayList<>();

    private void loadData() {
        // Read data from the text file
        String fileName = "Dataset2.txt"; // Change this to your actual file path
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse the line
                String[] parts = line.split(", ");
                if (parts.length == 6) {
                    HashMap<String, Object> starData = new HashMap<>();
                    starData.put("name", parts[0].replace("\"", ""));
                    starData.put("x", Integer.parseInt(parts[1]));
                    starData.put("y", Integer.parseInt(parts[2]));
                    starData.put("z", Integer.parseInt(parts[3]));
                    starData.put("weight", Integer.parseInt(parts[4]));
                    starData.put("profit", Integer.parseInt(parts[5]));

                    // Add the HashMap to the ArrayList
                    starList.add(starData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Object> getStarByName(ArrayList<HashMap<String, Object>> starList, String starName) {
        for (HashMap<String, Object> star : starList) {
            if (starName.equals(star.get("name"))) {
                return star;
            }
        }
        return null;
    }

    public List<Integer> getAllX(ArrayList<HashMap<String, Object>> starList) {
        List<Integer> allX = new ArrayList<>();
        for (HashMap<String, Object> star : starList) {
            allX.add((Integer) star.get("x"));
        }
        return allX;
    }

    public List<Integer> getAllY(ArrayList<HashMap<String, Object>> starList) {
        List<Integer> allY = new ArrayList<>();
        for (HashMap<String, Object> star : starList) {
            allY.add((Integer) star.get("y"));
        }
        return allY;
    }

    public List<Integer> getAllZ(ArrayList<HashMap<String, Object>> starList) {
        List<Integer> allZ = new ArrayList<>();
        for (HashMap<String, Object> star : starList) {
            allZ.add((Integer) star.get("y"));
        }
        return allZ;
    }

    public List<Integer> getAllProfit(ArrayList<HashMap<String, Object>> starList) {
        List<Integer> allProfit = new ArrayList<>();
        for (HashMap<String, Object> star : starList) {
            allProfit.add((Integer) star.get("profit"));
        }
        return allProfit;
    }

    public List<Integer> getAllWeight(ArrayList<HashMap<String, Object>> starList) {
        List<Integer> allWeight = new ArrayList<>();
        for (HashMap<String, Object> star : starList) {
            allWeight.add((Integer) star.get("weight"));
        }
        return allWeight;
    }

    public List<Integer> getStarDistance() {
        List<Integer> starDistance = new ArrayList<>();

        // get Distance 
        for (int i = 1; i < getAllX(starList).size(); i++) {
            double distance = Math.sqrt(
            (Math.pow(2,(getAllX(starList).get(i) - getAllX(starList).get(i-1)))) + 
            (Math.pow(2,(getAllY(starList).get(i) - getAllY(starList).get(i-1)))) + 
            (Math.pow(2,(getAllZ(starList).get(i) - getAllZ(starList).get(i-1)))));
        
            // cast to Integer
            starDistance.add((int)distance);
        } 
        return starDistance;
    }
}