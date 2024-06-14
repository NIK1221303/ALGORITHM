import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import DatasetOne.*;
import DatasetTwo.*;
import HeapSort.HeapSort;
import SelectionSort.SelectionSort;
import ShortestPaths.Dijkstra;
import MinimumSpanningTree.Kruskal;
import DynamicProgramming.Knapsack;

public class Main {

    public static DataOnes dataOne;
    public static DataTwo dataTwo;
    public static SelectionSort selection;
    public static HeapSort heap;
    public static StarRoutes starRoutes;
    public static Knapsack knapsack;
    public static Kruskal kruskal;
    public static Dijkstra dijkstra;

    public Main() {
        new GenDataOne();
        new GenDataTwo();
        dataOne = new DataOnes();
        dataTwo = new DataTwo();
        heap = new HeapSort(dataOne);
        selection = new SelectionSort(dataOne);
        starRoutes = new StarRoutes(dataTwo);
        kruskal = new Kruskal(starRoutes);
        dijkstra = new Dijkstra(starRoutes);

        knapsack = new Knapsack(dataTwo, 12);
    }

    public static void main(String[] args) throws IOException {

        // Create an instance of Main
        new Main();

        Scanner input = new Scanner(System.in);

        System.out.println(
                "Choose Algorithm:\n1. Selection Sort\n2. Heap Sort\n3. Shortest Path (IN PROGRESS)\n4. Minimum Spanning Tree (IN PROGRESS)\n5. Dynamic Programming (IN PROGRESS)");

        int choice = input.nextInt();

        if (choice == 1)
            selection.sort();
        else if (choice == 2)
            heap.sort();
        else if (choice == 3) {
            dijkstra.shortestPath("Star A");
        } else if (choice == 4) {
            kruskal.findMinimumSpanningTree();
        } else if (choice == 5)
            knapsack.solve();
        //     else if (choice == 6) {

        //     // EXTRA CODES // DONT DELETE //

        //     // list out all connection between a star
        //     System.out.println("Distances from Star A:");
        //     Map<String, Integer> connections = starRoutes.getConnectedStars("Star A");
        //     for (Map.Entry<String, Integer> entry : connections.entrySet()) {
        //         System.out.println("To " + entry.getKey() + ": " + entry.getValue() + " units");
        //     }

        //     // sample distance between stars
        //     System.out.println(starRoutes.getDistanceBetween("Star A", "Star B"));
        // }
        input.close();
    }
}