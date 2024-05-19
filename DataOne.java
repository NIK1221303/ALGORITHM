import java.util.Arrays;

public class DataOne {
    private int[] data;

    public DataOne(int size) {
        data = new int[size];
    }

    public void loadData(String line) {
        data = Arrays.stream(line.replaceAll("[\\[\\]]", "").split(", "))
                     .mapToInt(Integer::parseInt)
                     .toArray();
    }

    public int[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
