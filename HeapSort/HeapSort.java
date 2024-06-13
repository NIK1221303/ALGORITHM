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

            for (int i = n / 2 - 1; i >= 0; i--) { // i = parent index
                heapify(data.getSet(setIndex).getData(), i, n);// make initial heap
            }

            sortHeap(data.getSet(setIndex).getData(), n); // sort the heap

            long endTime = System.nanoTime();

            long duration = (endTime - startTime); // in nanoseconds
            System.out.println("Execution time in nanoseconds: " + (duration) + "ns\n");

            // Append sorted set to text file
            try (FileWriter fileWriter = new FileWriter("HeapSort/HeapAlgo.txt", true)) { // true for append mode
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

    public void heapify(ArrayList<Integer> data, int parent, int heapsize) {

        int child = parent * 2 + 1; // Find left child

        if (child >= heapsize) {
            return; // if child index is greater than the size, return
        }

        if (child + 1 < heapsize && data.get(child + 1) > data.get(child)) {
            child++; // if child+1(right child) greater than child (left child), do child++, now
                     // child have change to right child
        }

        //swap child and parent if greater
        if (data.get(child) > data.get(parent)) {
            int temp; 
        temp = data.get(child);
        data.set(child, data.get(parent));
        data.set(parent, temp);

        heapify(data, child, heapsize);
        }

        
        return;

    }

    public void sortHeap(ArrayList<Integer> data1, int n) { // largest at root , put into last index, minus 1 heapsize,
                                                            // call heapify

        for (int i = n - 1; i > 0; i--) {
            int temp = data1.get(0);
            data1.set(0, data1.get(i));
            data1.set(i, temp);
            heapify(data1, 0, i); // reduce heap size to i as last index is no longer need heapifying
        }

    }
}