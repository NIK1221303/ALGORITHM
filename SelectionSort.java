public class SelectionSort implements Sort{

    public DataOnes data;

    public SelectionSort(DataOnes data) {
        this.data = data;
    }

    public void sort() {
        data.retrieveData("Dataset1.txt");

        // Example usage
        System.out.println(data.getSet(0)); // set1
    }

}
