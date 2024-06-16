package DatasetOne;

import java.util.ArrayList;
import java.util.Arrays;

public class DataOne {
    private ArrayList<Integer> data;

    // Constructor : Allow sets to be made in different sizes
    public DataOne(int size) {
        data = new ArrayList<>();
    }

    // Load a set from text file into arraylist
    public void loadData(String line) {
        data = new ArrayList<>();
        Arrays.stream(line.replaceAll("[\\[\\]]", "").split(", "))
                .mapToInt(Integer::parseInt)
                .forEach(data::add);
    }

    // Get a set
    public ArrayList<Integer> getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
