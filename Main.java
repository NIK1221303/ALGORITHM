import java.util.Scanner;

public class Main {

    public static SelectionSort sel;
    public static HeapSort heap;
    public static MinSpanningTree minSTree;
    public static DataOnes dataOne;
    public static DataTwo dataTwo;

    public Main() {
        new GenDataOne();
        new GenDataTwo();
        dataOne = new DataOnes();
        dataTwo = new DataTwo();
        sel = new SelectionSort(dataOne);
        heap = new HeapSort(dataOne);
        minSTree = new MinSpanningTree(dataTwo);
    }

    public static void main(String[] args) {

        Main main = new Main(); // Create an instance of Main

        Scanner input = new Scanner(System.in);

        System.out.println("Choose Algorithm:\n1. Selection Sort\n2. Heap Sort\n3. Minimun Spanning Tree\n");
    
        int choice = input.nextInt();

        if (choice == 1)
            sel.sort();
        else if (choice == 2) 
            heap.sort();
        else 
            minSTree.sort();

        input.close();
    }

}
