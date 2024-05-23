import java.util.*;
import java.io.*;

public class GenDataTwo {

    public GenDataTwo() {
        generateData();
    }

    public void generateData() {
        // Initialize the ArrayList to store HashMaps
        ArrayList<HashMap<String, Object>> starList = generateRandomStars();

        // Print the generated star data
        for (HashMap<String, Object> star : starList) {
            System.out.println(star);
        }

        // Save the generated star data to a file
        String fileName = "Dataset2.txt";
        saveStarsToFile(starList, fileName);

    }

    public static ArrayList<HashMap<String, Object>> generateRandomStars() {
        ArrayList<HashMap<String, Object>> starList = new ArrayList<>();

        // Generate data for 20 stars
        for (char starName = 'A'; starName <= 'T'; starName++) {
            HashMap<String, Object> starData = new HashMap<>();
            starData.put("name", "Star " + starName);
            starData.put("x", generateRandomValue(3)); // 0 to 999
            starData.put("y", generateRandomValue(3)); // 0 to 999
            starData.put("z", generateRandomValue(3)); // 0 to 999
            starData.put("weight", generateRandomValue(2)); // 0 to 99
            starData.put("profit", generateRandomValue(2)); // 0 to 99

            // Add the HashMap to the ArrayList
            starList.add(starData);
        }
        return starList;
    }

    public static void saveStarsToFile(ArrayList<HashMap<String, Object>> starList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (HashMap<String, Object> star : starList) {
                String starString = String.format("\"%s\", %d, %d, %d, %d, %d",
                        star.get("name"),
                        star.get("x"),
                        star.get("y"),
                        star.get("z"),
                        star.get("weight"),
                        star.get("profit"));
                bw.write(starString);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    private static int generateRandomValue(int numDigits) {
        // insert seed (1211103426 + 1211100965 + 1221303804 = 3643508195)
        // long seed = 3643508195L;
        Random rand = new Random();
        int[] allowedDigits = { 3, 6, 4, 5, 0, 8, 1, 9 };

        StringBuilder value = new StringBuilder();
        for (int i = 0; i < numDigits; i++) {
            int randomIndex = rand.nextInt(allowedDigits.length);
            value.append(allowedDigits[randomIndex]);
        }
        return Integer.parseInt(value.toString());
    }
}
