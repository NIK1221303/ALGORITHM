package DatasetTwo;

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
        String fileName = "DatasetTwo/Dataset2.txt"; // Change this to your actual file path
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

    // Get Star
    public Star getStar(String starName) {
        for (Star star : starList) {
            if (starName.equals(star.getName())) {
                return star;
            }
        }
        return null;
    }

    // Get all Stars
    public List<Star> getStars() {
        return starList;
    }

    // Calculate distance between two Stars
    public Integer getStarDistance(String oriStar, String destStar) {

        // Distance Formula
        double distance = Math.sqrt(
                (Math.pow((getStar(destStar).getX() - getStar(oriStar).getX()), 2)) +
                        (Math.pow((getStar(destStar).getY() - getStar(oriStar).getY()), 2)) +
                        (Math.pow((getStar(destStar).getZ() - getStar(oriStar).getZ()), 2)));

        return (int) distance;
    }
}