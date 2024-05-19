import java.util.ArrayList;

public class SelectionSort implements Sort {

    public DataOnes data;

    public SelectionSort(DataOnes data) {
        this.data = data;
    }

    public void sort() {
        data.retrieveData("Dataset1.txt");

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

        System.out.println(sampleData1);
        System.out.println(sampleData2);

        // ada problem kat sampleData2
        samples.add(sampleData2);
        samples.add(sampleData1);

        

        for (int setIndex = 0; setIndex < samples.size(); setIndex++) {

            System.out.println("## SAMPLE " + (setIndex + 1) + " ##");

            int y = 0; // Bigger Item
            int swapFlag = 0; // false
            int currentItem = samples.get(setIndex).size() - 1;
            int yIndex = 0; // Bigger Item Index
            int xIndex = samples.get(setIndex).get(currentItem); // currentItem Index

            long startTime = System.nanoTime();

            for (int i = currentItem; i >= 0; i--) {
                xIndex = samples.get(setIndex).get(i); // set currentItem

                for (int j = 0; j < i; j++) { // move down one element each iteration
                    if (samples.get(setIndex).get(j) > xIndex) {
                        y = samples.get(setIndex).get(j);
                        yIndex = j;

                        swapFlag = 1; // true
                    }

                    if (j == (i - 1) && swapFlag == 1) { // at last element
                        int temp = xIndex; // temp = 8
                        xIndex = y; // x = 9
                        samples.get(setIndex).set(yIndex, temp); // smaller value take bigger value index
                        samples.get(setIndex).set(i, xIndex); // smaller value take smaller value index

                        swapFlag = 0; // reset
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
            int xIndex = data.getSet(setIndex).getData().get(currentItem); // currentItem Index

            long startTime = System.nanoTime();

            for (int i = currentItem; i >= 0; i--) {
                xIndex = data.getSet(setIndex).getData().get(i); // set currentItem

                for (int j = 0; j < i; j++) { // move down one element each iteration
                    if (data.getSet(setIndex).getData().get(j) > xIndex) {
                        y = data.getSet(setIndex).getData().get(j);
                        yIndex = j;

                        swapFlag = 1; // true
                    }

                    if (j == (i - 1) && swapFlag == 1) { // at last element
                        int temp = xIndex; // temp = 8
                        xIndex = y; // x = 9
                        data.getSet(setIndex).getData().set(yIndex, temp); // smaller value take bigger value index
                        data.getSet(setIndex).getData().set(i, xIndex); // smaller value take smaller value index

                        swapFlag = 0; // reset
                        //System.out.println(data.getSet(setIndex).getData());

                    }
                }
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime); // in nanoseconds
            System.out.println("Execution time in milliseconds: " + (duration) + "ns\n");
        }
    }
}