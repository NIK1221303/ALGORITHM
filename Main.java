import java.util.Scanner;

public class Main {

    public static SelectionSort sel;
    public static HeapSort heap;
    public static DataOnes data;

    public Main() {
        data = new DataOnes();
        sel = new SelectionSort(data);
        heap = new HeapSort(data);
    }

    public static void main(String[] args) {

        Main main = new Main(); // Create an instance of Main

        Scanner input = new Scanner(System.in);

        int choice = input.nextInt();

        if (choice == 1)
            sel.sort();
        else 
            heap.sort();
        
        // user input 
        // user choice (heap /  selection)
        // if statement ... sort

       
        input.close();
    }

}
