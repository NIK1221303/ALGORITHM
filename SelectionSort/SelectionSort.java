package SelectionSort;

import java.io.FileWriter;
import java.io.IOException;

import DatasetOne.DataOnes;

public class SelectionSort {

    public DataOnes data;

    public SelectionSort(DataOnes data) {
        this.data = data;
    }

    public void sort() {
        data.retrieveData("DatasetOne/Dataset1.txt");

        // Reset the file at the beginning of the sort method
        try (FileWriter fileWriter = new FileWriter("SelectionSort/SelectionAlgo.txt")) {
            fileWriter.write("");
        } catch (IOException e) {
            System.out.println(e);
        }

        for (int setIndex = 0; setIndex < data.getSets().size(); setIndex++) {

            System.out.println("## SET " + (setIndex + 1) + " ##");

            // Bigger Item
            int y = 0;
            // Bigger Item Index
            int yIndex = 0;
            // Swap flag start as false
            int swapFlag = 0;
            // currentItem is last element in array
            int currentItem = data.getSet(setIndex).getData().size() - 1;
            // currentItem
            int x = data.getSet(setIndex).getData().get(currentItem);

            long startTime = System.nanoTime();

            for (int i = currentItem; i >= 0; i--) {
                // Set currentItem
                x = data.getSet(setIndex).getData().get(i);

                // Move down one element each iteration
                for (int j = 0; j < i; j++) {
                    if (data.getSet(setIndex).getData().get(j) > x) {

                        // Make sure biggest value taken
                        if (data.getSet(setIndex).getData().get(j) > y) {
                            y = data.getSet(setIndex).getData().get(j);
                            yIndex = j;
                        }
                        swapFlag = 1; // true
                    }

                    // At last element before i
                    if (j == (i - 1) && swapFlag == 1) {
                        // Temporary variable takes currentItem value
                        int temp = x;
                        // currentItem swaps with greatest element in array
                        x = y;
                        // Greatest element index takes currentItem value
                        data.getSet(setIndex).getData().set(yIndex, temp);
                        // currentItem index takes greatest element value
                        data.getSet(setIndex).getData().set(i, x);

                        // Reset flag
                        swapFlag = 0;
                        y = 0;
                    }
                }
            }

            long endTime = System.nanoTime();
            double duration = (endTime - startTime);
            System.out.println("Execution time in milliseconds: " + (duration / 1000000) + "ms\n");

            // Append sorted set to text file
            try (FileWriter fileWriter = new FileWriter("SelectionSort/SelectionAlgo.txt", true)) {
                fileWriter.write("## SET " + (setIndex + 1) + " ##\n");
                StringBuilder line = new StringBuilder("[");
                for (Integer value : data.getSet(setIndex).getData()) {
                    line.append(value).append(", ");
                }
                // Remove trailing comma and space, add closing bracket
                fileWriter.write(line.substring(0, line.length() - 2) + "]\n");
                fileWriter.write("\n");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}