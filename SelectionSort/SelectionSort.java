package SelectionSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        // Example usage
        // System.out.println(data.getSet(0).getData().get(0)); // set1

        ArrayList<ArrayList<Integer>> samples = new ArrayList<>();

        ArrayList<Integer> sampleData1 = new ArrayList<>();
        sampleData1.add(1);
        sampleData1.add(2);
        sampleData1.add(5);
        sampleData1.add(3);
        sampleData1.add(9);
        sampleData1.add(4);
        sampleData1.add(8);

        ArrayList<Integer> sampleData2 = new ArrayList<>();
        sampleData2.add(3);
        sampleData2.add(6);
        sampleData2.add(5);
        sampleData2.add(2);
        sampleData2.add(4);

        samples.add(sampleData1);
        samples.add(sampleData2);

        for (int setIndex = 0; setIndex < samples.size(); setIndex++) {

            System.out.println("## SAMPLE " + (setIndex + 1) + " ##");
            System.out.println(samples.get(setIndex));

            int y = 0; // Bigger Item
            int swapFlag = 0; // false
            int currentItem = samples.get(setIndex).size() - 1;
            int yIndex = 0; // Bigger Item Index
            int x = samples.get(setIndex).get(currentItem); // currentItem

            long startTime = System.nanoTime();

            for (int i = currentItem; i >= 0; i--) {
                x = samples.get(setIndex).get(i); // set currentItem

                for (int j = 0; j < i; j++) { // move down one element each iteration
                    if (samples.get(setIndex).get(j) > x) {

                        // make sure biggest value taken
                        if (samples.get(setIndex).get(j) > y) {
                            y = samples.get(setIndex).get(j);
                            yIndex = j;
                        }
                        swapFlag = 1; // true
                    }

                    if (j == (i - 1) && swapFlag == 1) { // at last element
                        int temp = x; // temp = 8
                        x = y; // x = 9
                        samples.get(setIndex).set(yIndex, temp); // smaller value take bigger value index
                        samples.get(setIndex).set(i, x); // smaller value take smaller value index

                        swapFlag = 0; // reset
                        y = 0; // reset
                        System.out.println(samples.get(setIndex));

                    }
                }
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime); // in nanoseconds
            System.out.println("Execution time in nanoseconds: " + (duration) + "ns\n");
        }

        for (int setIndex = 0; setIndex < data.getSets().size(); setIndex++) {

            System.out.println("## SET " + (setIndex + 1) + " ##");

            int y = 0; // Bigger Item
            int swapFlag = 0; // false
            int currentItem = data.getSet(setIndex).getData().size() - 1;
            int yIndex = 0; // Bigger Item Index
            int x = data.getSet(setIndex).getData().get(currentItem); // currentItem

            long startTime = System.nanoTime();

            for (int i = currentItem; i >= 0; i--) {
                x = data.getSet(setIndex).getData().get(i); // set currentItem

                for (int j = 0; j < i; j++) { // move down one element each iteration
                    if (data.getSet(setIndex).getData().get(j) > x) {

                        // make sure biggest value taken
                        if (data.getSet(setIndex).getData().get(j) > y) {
                            y = data.getSet(setIndex).getData().get(j);
                            yIndex = j;
                        }

                        swapFlag = 1; // true
                    }

                    if (j == (i - 1) && swapFlag == 1) { // at last element
                        int temp = x; // temp = 8
                        x = y; // x = 9
                        data.getSet(setIndex).getData().set(yIndex, temp); // smaller value take bigger value index
                        data.getSet(setIndex).getData().set(i, x); // smaller value take smaller value index

                        swapFlag = 0; // reset
                        y = 0; // reset
                    }
                }
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime); // in nanoseconds
            System.out.println("Execution time in nanoseconds: " + (duration) + "ns\n");

            // Append sorted set to text file
            try (FileWriter fileWriter = new FileWriter("SelectionSort/SelectionAlgo.txt", true)) { // true for append
                                                                                                    // mode
                fileWriter.write("## SET " + (setIndex + 1) + " ##\n");
                StringBuilder line = new StringBuilder("[");
                for (Integer value : data.getSet(setIndex).getData()) {
                    line.append(value).append(", ");
                }
                fileWriter.write(line.substring(0, line.length() - 2) + "]\n"); // Remove trailing comma and space, add
                                                                                // closing bracket
                fileWriter.write("\n");
            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }
}