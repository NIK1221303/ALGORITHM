package HeapSort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import DatasetOne.DataOnes;

public class HeapSort {
    public DataOnes data;

    public HeapSort(DataOnes data) {
        this.data = data;
    }

    public void sort() {

        data.retrieveData("DatasetOne/Dataset1.txt");

        // Reset the file at the beginning of the sort method
        try (FileWriter fileWriter = new FileWriter("HeapSort/HeapAlgo.txt")) {
            fileWriter.write("");
        } catch (IOException e) {
            System.out.println(e);
        }

        for (int setIndex = 0; setIndex < data.getSets().size(); setIndex++) {

            System.out.println("## SET " + (setIndex + 1) + " ##");

            int n = data.getSet(setIndex).getData().size();

            long startTime = System.nanoTime();

            // i = parent index
            for (int i = n / 2 - 1; i >= 0; i--) {
                // make initial heap
                heapify(data.getSet(setIndex).getData(), i, n);
            }

            // Sort the heap
            sortHeap(data.getSet(setIndex).getData(), n);

            long endTime = System.nanoTime();

            double duration = (endTime - startTime); // in milliseconds
            System.out.println("Execution time in milliseconds: " + (duration / 1000000) + "ms\n");

            // Append sorted set to text file
            try (FileWriter fileWriter = new FileWriter("HeapSort/HeapAlgo.txt", true)) {
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

    public void heapify(ArrayList<Integer> data, int parent, int heapsize) {

        // Find left child
        int child = parent * 2 + 1;

        // If child index is greater than the size, return
        if (child >= heapsize) {
            return;
        }

        // If child+1(right child) greater than child (left child), do child++, now
        // child have change to right child
        if (child + 1 < heapsize && data.get(child + 1) > data.get(child)) {
            child++;
        }

        // Swap child and parent if greater
        if (data.get(child) > data.get(parent)) {
            int temp;
            temp = data.get(child);
            data.set(child, data.get(parent));
            data.set(parent, temp);

            heapify(data, child, heapsize);
        }
        return;
    }

    public void sortHeap(ArrayList<Integer> data1, int n) {

        // Largest at root , put into last index, minus 1 heapsize,
        for (int i = n - 1; i > 0; i--) {
            int temp = data1.get(0);
            data1.set(0, data1.get(i));
            data1.set(i, temp);

            // call heapify
            // reduce heap size to i as last index is no longer need heapifying
            heapify(data1, 0, i);
        }
    }
}