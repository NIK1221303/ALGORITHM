package DatasetTwo;
import java.util.*;


import java.io.*;

public class GenDataTwo {

    public GenDataTwo() {
        generateData();
    }

    public void generateData() {
        // Initialize the ArrayList to store HashMaps
        ArrayList<HashMap<String, Object>> starList = generateRandomStars();

        // // Print the generated star data
        // for (HashMap<String, Object> star : starList) {
        //     System.out.println(star);
        // }

        // Save the generated star data to a file
        String fileName = "DatasetTwo/Dataset2.txt";
        saveStarsToFile(starList, fileName);

    }

    public static ArrayList<HashMap<String, Object>> generateRandomStars() {
        ArrayList<HashMap<String, Object>> starList = new ArrayList<>();

        // Generate data for 20 stars
        for (char starName = 'A'; starName <= 'T'; starName++) {
            HashMap<String, Object> starData = new HashMap<>();
            starData.put("name", "Star " + starName);
            starData.put("x", generateRandomValue(2, 3, Character.toString(starName), 1023)); // 0 to 999
            starData.put("y", generateRandomValue(2, 3, Character.toString(starName), 97538)); // 0 to 999
            starData.put("z", generateRandomValue(2, 3, Character.toString(starName), 19678)); // 0 to 999
            starData.put("weight", generateRandomValue(2, 3, Character.toString(starName), 1314)); // 0 to 99
            starData.put("profit", generateRandomValue(2, 3, Character.toString(starName), 65306)); // 0 to 99

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
    private static int generateRandomValue(int minDigits, int maxDigits, String starName, int randomValue) {
        // allows different randomizer data
        Random rand = new Random(starName.hashCode() + randomValue);
        Integer[] allowedDigits = { 3, 6, 4, 5, 8, 1, 9, 0 };

        // determine the number of digits randomly within the specified range
        int numDigits = rand.nextInt((maxDigits - minDigits) + 1) + minDigits;

        StringBuilder value = new StringBuilder();
        for (int i = 0; i < numDigits; i++) {

            // randomly shuffles array
            List<Integer> allowedDigitsList = Arrays.asList(allowedDigits);
            Collections.shuffle(allowedDigitsList, new Random(randomValue));
            allowedDigitsList.toArray(allowedDigits);

            // get random index from the shuffled array
            int randomIndex = rand.nextInt(allowedDigits.length);
            value.append(allowedDigitsList.get(randomIndex));
        }

        return Integer.parseInt(value.toString());
    }
}
