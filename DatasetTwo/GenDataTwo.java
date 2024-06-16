package DatasetTwo;

import java.util.*;
import java.io.*;

public class GenDataTwo {

    public GenDataTwo() {
        genData();
    }

    private void genData() {
        // Initialize the ArrayList to store HashMaps
        ArrayList<HashMap<String, Object>> starList = genStars();

        // Save the generated star data to a file
        saveData(starList);
    }

    // Generate 20 sets of Stars with random value
    private static ArrayList<HashMap<String, Object>> genStars() {
        ArrayList<HashMap<String, Object>> starList = new ArrayList<>();

        // Generate data for 20 stars
        for (char starName = 'A'; starName <= 'T'; starName++) {
            HashMap<String, Object> starData = new HashMap<>();
            starData.put("name", "Star " + starName);
            starData.put("x", genRandomValue(2, 3, Character.toString(starName), 1023)); // 0 to 999
            starData.put("y", genRandomValue(2, 3, Character.toString(starName), 97538)); // 0 to 999
            starData.put("z", genRandomValue(2, 3, Character.toString(starName), 19678)); // 0 to 999
            starData.put("weight", genRandomValue(2, 3, Character.toString(starName), 314)); // 0 to 999
            starData.put("profit", genRandomValue(2, 3, Character.toString(starName), 306)); // 0 to 999

            // Add the HashMap to the ArrayList
            starList.add(starData);
        }
        return starList;
    }

    // Save Stars into a text file
    private static void saveData(ArrayList<HashMap<String, Object>> starList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("DatasetTwo/Dataset2.txt"))) {
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

    // Generate random value according to digits in summation of Student IDs
    private static int genRandomValue(int minDigits, int maxDigits, String starName, int randomValue) {
        // Allows different randomizer data
        Random rand = new Random(starName.hashCode() + randomValue);
        // 1221303804 + 1211100965 = 2432404769
        Integer[] numbers = { 0, 2, 3, 4, 6, 7, 9 };

        // Determine the number of digits randomly within the specified range
        int numDigits = rand.nextInt((maxDigits - minDigits) + 1) + minDigits;

        StringBuilder value = new StringBuilder();
        for (int i = 0; i < numDigits; i++) {

            // Randomly shuffles array
            List<Integer> numbersList = Arrays.asList(numbers);
            Collections.shuffle(numbersList, new Random(randomValue));
            numbersList.toArray(numbers);

            // Get random index from the shuffled array
            int randomIndex = rand.nextInt(numbers.length);
            value.append(numbersList.get(randomIndex));
        }
        return Integer.parseInt(value.toString());
    }
}
