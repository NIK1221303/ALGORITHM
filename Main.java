import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static SelectionSort sel;
    public static HeapSort heap;
    public static MinSpanningTree minSTree;
    public static DataOnes dataOne;
    public static DataTwo dataTwo;
    public static StarRoutes starRoutes;
    public static PathFinder pathFinder;
    public static Knapsack knapsack;
    public static Kruskal kruskal;


    public Main() {
        new GenDataOne();
        new GenDataTwo();
        dataOne = new DataOnes();
        dataTwo = new DataTwo();
        sel = new SelectionSort(dataOne);
        heap = new HeapSort(dataOne);
        minSTree = new MinSpanningTree(dataTwo);
        starRoutes = new StarRoutes(dataTwo);
        pathFinder = new PathFinder(starRoutes);
        knapsack = new Knapsack(dataTwo, 800);
        kruskal = new Kruskal(starRoutes);
    }

    public static void main(String[] args) throws IOException {

        // Create an instance of Main
        new Main();

        Scanner input = new Scanner(System.in);

        System.out.println("Choose Algorithm:\n1. Selection Sort\n2. Heap Sort\n3. Minimun Spanning Tree\n");

        int choice = input.nextInt();

        if (choice == 1)
            sel.sort();
        else if (choice == 2)
            heap.sort();
        else if (choice == 3)
            minSTree.sort();
        else if (choice == 4)
            knapsack.solve();
        else if (choice == 5) {
            // list out all connection between a star
            System.out.println("Distances from Star A:");
            Map<String, Integer> connections = starRoutes.getConnectedStars("Star A");
            for (Map.Entry<String, Integer> entry : connections.entrySet()) {
                System.out.println("To " + entry.getKey() + ": " + entry.getValue() + " units");
            }

            // sample distance between stars
            System.out.println(starRoutes.getDistanceBetween("Star A", "Star B"));
        } else if (choice == 6) {
            // Test the shortest path
            List<String> path = pathFinder.findShortestPath("Star A", "Star O");
            System.out.println("Shortest path from Star A to Star O:");
            for (String star : path) {
                System.out.println(star);
            }
        } else if (choice == 7) {
            kruskal.findMST();
        }

        input.close();
    }

}
