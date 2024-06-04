package DatasetOne;
import java.util.ArrayList;
import java.util.Arrays;

public class DataOne {
    private ArrayList<Integer> data;

    public DataOne(int size) {
        data = new ArrayList<>();
    }

    public void loadData(String line) {
        data = new ArrayList<>();
        Arrays.stream(line.replaceAll("[\\[\\]]", "").split(", "))
              .mapToInt(Integer::parseInt)
              .forEach(data::add);
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
