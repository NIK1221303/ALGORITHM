import java.io.*;
import java.util.*;

public class DataTwo {

    public static HashMap<String, Object> getStarByName(ArrayList<HashMap<String, Object>> starList, String starName) {
        for (HashMap<String, Object> star : starList) {
            if (starName.equals(star.get("name"))) {
                return star;
            }
        }
        return null;
    }

    public static List<Integer> getAllX(ArrayList<HashMap<String, Object>> starList) {
        List<Integer> allX = new ArrayList<>();
        for (HashMap<String, Object> star : starList) {
            allX.add((Integer) star.get("x"));
        }
        return allX;
    }

    public static List<Integer> getAllY(ArrayList<HashMap<String, Object>> starList) {
        List<Integer> allY = new ArrayList<>();
        for (HashMap<String, Object> star : starList) {
            allY.add((Integer) star.get("y"));
        }
        return allY;
    }

    public static List<Integer> getAllZ(ArrayList<HashMap<String, Object>> starList) {
        List<Integer> allZ = new ArrayList<>();
        for (HashMap<String, Object> star : starList) {
            allZ.add((Integer) star.get("y"));
        }
        return allZ;
    }

    public static List<Integer> getAllProfit(ArrayList<HashMap<String, Object>> starList) {
        List<Integer> allProfit = new ArrayList<>();
        for (HashMap<String, Object> star : starList) {
            allProfit.add((Integer) star.get("profit"));
        }
        return allProfit;
    }

    public static List<Integer> getAllWeight(ArrayList<HashMap<String, Object>> starList) {
        List<Integer> allWeight = new ArrayList<>();
        for (HashMap<String, Object> star : starList) {
            allWeight.add((Integer) star.get("weight"));
        }
        return allWeight;
    }



    public static void main(String[] args) {
        // Initialize the ArrayList to store HashMaps
        ArrayList<HashMap<String, Object>> starList = new ArrayList<>();

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

        // // Print out the contents of the ArrayList to verify
        // for (HashMap<String, Object> star : starList) {
        //     System.out.println(star);
        // }


        // Get specific star
        System.out.println(getStarByName(starList, "Star A"));

        // Get specific star value
        System.out.println(getStarByName(starList, "Star A").get("name"));
        System.out.println(getStarByName(starList, "Star A").get("x"));
        System.out.println(getStarByName(starList, "Star A").get("y"));
        System.out.println(getStarByName(starList, "Star A").get("z"));
        System.out.println(getStarByName(starList, "Star A").get("weight"));
        System.out.println(getStarByName(starList, "Star A").get("profit"));

        
        // Retrieve the 'x' values for all stars
        List<Integer> xValues = getAllX(starList);
        // Print the 'x' values
        System.out.println("x values for all stars: " + xValues);


        // later can use getAll... for calculation

    }
}
