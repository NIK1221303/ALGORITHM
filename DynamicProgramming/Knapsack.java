package DynamicProgramming;

import java.util.*;

import DatasetTwo.DataTwo;
import DatasetTwo.Star;

import java.io.*;

public class Knapsack {

    private List<Star> stars;
    private int maxCapacity;
    private int[][] tabulationTable;
    private List<Star>[][] chosenStars;
    private List<Star> resultStars;

    public Knapsack(DataTwo dataTwo, int maxCapacity) {
        this.stars = dataTwo.getStars();
        this.maxCapacity = maxCapacity;
        this.tabulationTable = new int[stars.size() + 1][maxCapacity + 1];
        this.resultStars = new ArrayList<>();
        this.chosenStars = new ArrayList[stars.size() + 1][maxCapacity + 1];
        for (int i = 0; i <= stars.size(); i++) {
            for (int j = 0; j <= maxCapacity; j++) {
                this.chosenStars[i][j] = new ArrayList<>();
            }
        }
    }

    public void solve() throws IOException {
        int totalStar = stars.size();

        // for (int star = 1; star <= totalStar ; star++) {
        // for (int capacity = 0; capacity <= maxCapacity; capacity++) {
        // if (stars.get(star - 1).getWeight() <= capacity) {
        // // If Star weight is lower than the current capacity
        // tabulationTable[star][capacity] = Math.max(tabulationTable[star -
        // 1][capacity], tabulationTable[star - 1][capacity - stars.get(star -
        // 1).getWeight()] + stars.get(star - 1).getProfit());
        // } else {
        // // If Star weight is bigger than current capacity
        // // Copy previous data to current index
        // tabulationTable[star][capacity] = tabulationTable[star - 1][capacity];
        // }
        // }
        // }

        for (int star = 0; star < totalStar; star++) {
            for (int capacity = 0; capacity <= maxCapacity; capacity++) {
                // If Star weight is lower or equal than the current capacity
                if (stars.get(star).getWeight() <= capacity) {
                    
                    int profitIfCurrentStarIncluded = tabulationTable[star][capacity - stars.get(star).getWeight()] + stars.get(star).getProfit();
                    int profitIfCurrentStarExcluded = tabulationTable[star][capacity];

                    if (profitIfCurrentStarIncluded > profitIfCurrentStarExcluded) {
                        // Update value in one star future
                        tabulationTable[star + 1][capacity] = profitIfCurrentStarIncluded;
                        chosenStars[star + 1][capacity].addAll(chosenStars[star][capacity - stars.get(star).getWeight()]);
                        chosenStars[star + 1][capacity].add(stars.get(star));
                    } else {
                        // Update value in one star future
                        tabulationTable[star + 1][capacity] = profitIfCurrentStarExcluded;
                        chosenStars[star + 1][capacity].addAll(chosenStars[star][capacity]);
                    }
                // If Star weight is bigger than current capacity
                // Copy previous data to current index
                } else {
                    tabulationTable[star + 1][capacity] = tabulationTable[star][capacity];
                    chosenStars[star + 1][capacity].addAll(chosenStars[star][capacity]);
                }
            }
        }

        // Get combination of Star resulting in highest profit at highest capacity
        int capacity = maxCapacity;
        for (int star = totalStar; star > 0 && capacity > 0; star--) {
            if (tabulationTable[star][capacity] != tabulationTable[star - 1][capacity]) {
                resultStars.add(stars.get(star - 1));
                capacity -= stars.get(star - 1).getWeight();
            }
        }

        printResult("DynamicProgramming/result.txt");
        printMatrix("DynamicProgramming/matrix.txt",1);
    }

    private void printResult(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Selected stars:\n");
            for (Star star : resultStars) {
                writer.write(String.format("Name: %s, Weight: %d, Profit: %d\n", star.getName(), star.getWeight(),
                        star.getProfit()));
            }
            writer.write(String.format("Total profit: %d\n", tabulationTable[stars.size()][maxCapacity]));
        }
    }

    // private void printMatrix(String filename) throws IOException {
    // try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
    // writer.write("DP Matrix:\n");
    // for (int i = 0; i <= stars.size(); i++) {
    // for (int weight = 0; weight <= maxCapacity; weight++) {
    // writer.write(tabulationTable[i][weight] + "\t");
    // }
    // writer.write("\n");
    // }
    // }
    // }

    // private void printMatrix(String filename) throws IOException {
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
    //         writer.write("DP Matrix:\n");
    //         for (int i = 0; i <= stars.size(); i++) {
    //             for (int weight = 0; weight <= maxCapacity; weight++) {
    //                 writer.write(tabulationTable[i][weight] + "\t");
    //             }
    //             writer.write("\n");
    //         }

    //         writer.write("\nSelected Stars Matrix:\n");
    //         for (int i = 0; i <= stars.size(); i++) {
    //             for (int weight = 0; weight <= maxCapacity; weight++) {
    //                 writer.write("[ ");
    //                 for (Star star : chosenStars[i][weight]) {
    //                     writer.write("Star " + (stars.indexOf(star) + 1) + " ");
    //                 }
    //                 writer.write("]\t");
    //             }
    //             writer.write("\n");
    //         }
    //     }
    // }

    private void printMatrix(String filename, int interval) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("DP Matrix:\n");
    
            // Find the maximum value in the tabulation table for dynamic spacing
            int maxValue = 0;
            for (int i = 0; i <= stars.size(); i++) {
                for (int weight = 0; weight <= maxCapacity; weight += interval) {
                    if (tabulationTable[i][weight] > maxValue) {
                        maxValue = tabulationTable[i][weight];
                    }
                }
            }
            // Determine the length of the largest number to format the output
            int maxDigits = Integer.toString(maxValue).length();
    
            // Print column headers
            writer.write(String.format("%-" + maxDigits + "s", "") + "\t|");
            for (int weight = 0; weight <= maxCapacity; weight += interval) {
                writer.write(String.format("%" + maxDigits + "d", weight) + "\t|");
            }
            writer.write("\n");
    
            // Print table with row headers
            for (int i = 0; i <= stars.size(); i++) {
                if (i == 0) {
                    writer.write(String.format("%-" + maxDigits + "s", "-") + "\t|");
                } else {
                    writer.write(String.format("%-" + maxDigits + "s", stars.get(i - 1).getName()) + "\t|");
                }
                for (int weight = 0; weight <= maxCapacity; weight += interval) {
                    writer.write(String.format("%" + maxDigits + "d", tabulationTable[i][weight]) + "\t|");
                }
                writer.write("\n");
            }
    
            writer.write("\nSelected Stars Matrix:\n");
            for (int i = 0; i <= stars.size(); i++) {
                if (i == 0) {
                    writer.write(String.format("%-" + maxDigits + "s", "-") + "\t|");
                } else {
                    writer.write(String.format("%-" + maxDigits + "s", stars.get(i - 1).getName()) + "\t|");
                }
                for (int weight = 0; weight <= maxCapacity; weight += interval) {
                    writer.write("[");
                    for (Star star : chosenStars[i][weight]) {
                        writer.write("S" + (stars.indexOf(star) + 1) + " ");
                    }
                    writer.write("]\t|");
                }
                writer.write("\n");
            }
        }
    }
    
    

    
}