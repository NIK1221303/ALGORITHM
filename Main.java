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
    public static ShortestPath shortestPath;
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
        shortestPath = new ShortestPath(starRoutes);
        knapsack = new Knapsack(dataTwo, 800);
        kruskal = new Kruskal(starRoutes);
    }

    public static void main(String[] args) throws IOException {

        // Create an instance of Main
        new Main();

        Scanner input = new Scanner(System.in);

        System.out.println(
                "Choose Algorithm:\n1. Selection Sort\n2. Heap Sort\n3. Shortest Path (IN PROGRESS)\n4. Minimum Spanning Tree (IN PROGRESS)\n5. Dynamic Programming (IN PROGRESS)");

        int choice = input.nextInt();

        if (choice == 1)
            sel.sort();
        else if (choice == 2)
            heap.sort();
        else if (choice == 3) {
            // Test the shortest path
            List<String> path = shortestPath.findShortestPath("Star A", "Star O");
            System.out.println("Shortest path from Star A to Star O:");
            for (String star : path) {
                System.out.println(star);
            }
        } else if (choice == 4) {
            kruskal.findMST();
        } else if (choice == 5)
            knapsack.solve();
        else if (choice == 6) {

            // EXTRA CODES // DONT DELETE //

            // list out all connection between a star
            System.out.println("Distances from Star A:");
            Map<String, Integer> connections = starRoutes.getConnectedStars("Star A");
            for (Map.Entry<String, Integer> entry : connections.entrySet()) {
                System.out.println("To " + entry.getKey() + ": " + entry.getValue() + " units");
            }

            // sample distance between stars
            System.out.println(starRoutes.getDistanceBetween("Star A", "Star B"));
        }
        input.close();
    }
}