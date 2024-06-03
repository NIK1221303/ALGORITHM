import java.io.*;
import java.util.*;
import java.lang.Math;

public class DataTwo {

    public DataTwo() {
        loadData();
    }

    // Initialize the ArrayList to store HashMaps
    static List<Star> starList = new ArrayList<>();

    private void loadData() {
        // Read data from the text file
        String fileName = "Dataset2.txt"; // Change this to your actual file path
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse the line
                String[] parts = line.split(", ");
                if (parts.length == 6) {
                    // Create a new Star instance and set its properties
                    Star star = new Star();
                    star.setName(parts[0].replace("\"", ""));
                    star.setX(Integer.parseInt(parts[1]));
                    star.setY(Integer.parseInt(parts[2]));
                    star.setZ(Integer.parseInt(parts[3]));
                    star.setWeight(Integer.parseInt(parts[4]));
                    star.setProfit(Integer.parseInt(parts[5]));

                    // Add the HashMap to the ArrayList
                    starList.add(star);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Star getStar(String starName) {
        for (Star star : starList) {
            if (starName.equals(star.getName())) {
                return star;
            }
        }
        return null;
    }

    public List<Star> getStars() {
        return starList;
    }

    public Integer getStarDistance(String oriStar, String destStar) {

        // Distance Formula
        double distance = Math.sqrt(
                        (Math.pow((getStar(destStar).getX() - getStar(oriStar).getX()), 2)) +
                        (Math.pow((getStar(destStar).getY() - getStar(oriStar).getY()), 2)) +
                        (Math.pow((getStar(destStar).getZ() - getStar(oriStar).getZ()), 2)));

        return (int) distance;
    }

    // public List<Integer> getAllX(ArrayList<HashMap<String, Object>> starList) {
    // List<Integer> allX = new ArrayList<>();
    // for (HashMap<String, Object> star : starList) {
    // allX.add((Integer) star.get("x"));
    // }
    // return allX;
    // }

    // public List<Integer> getAllY(ArrayList<HashMap<String, Object>> starList) {
    // List<Integer> allY = new ArrayList<>();
    // for (HashMap<String, Object> star : starList) {
    // allY.add((Integer) star.get("y"));
    // }
    // return allY;
    // }

    // public List<Integer> getAllZ(ArrayList<HashMap<String, Object>> starList) {
    // List<Integer> allZ = new ArrayList<>();
    // for (HashMap<String, Object> star : starList) {
    // allZ.add((Integer) star.get("z"));
    // }
    // return allZ;
    // }

    // public List<Integer> getAllProfit(ArrayList<HashMap<String, Object>>
    // starList) {
    // List<Integer> allProfit = new ArrayList<>();
    // for (HashMap<String, Object> star : starList) {
    // allProfit.add((Integer) star.get("profit"));
    // }
    // return allProfit;
    // }

    // public List<Integer> getAllWeight(ArrayList<HashMap<String, Object>>
    // starList) {
    // List<Integer> allWeight = new ArrayList<>();
    // for (HashMap<String, Object> star : starList) {
    // allWeight.add((Integer) star.get("weight"));
    // }
    // return allWeight;
    // }

    // public List<Integer> getStarDistance() {
    // List<Integer> starDistance = new ArrayList<>();

    // // get Distance
    // for (int i = 1; i < getAllX(starList).size(); i++) {
    // double distance = Math.sqrt(
    // (Math.pow((getAllX(starList).get(i) - getAllX(starList).get(i-1)), 2)) +
    // (Math.pow((getAllY(starList).get(i) - getAllY(starList).get(i-1)), 2)) +
    // (Math.pow((getAllZ(starList).get(i) - getAllZ(starList).get(i-1)), 2)));

    // // cast to Integer
    // starDistance.add((int)distance);
    // }
    // return starDistance;
    // }
}