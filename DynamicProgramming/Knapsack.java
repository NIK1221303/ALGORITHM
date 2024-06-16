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

        long startTime = System.nanoTime();

        for (int star = 0; star < totalStar; star++) {
            for (int capacity = 0; capacity <= maxCapacity; capacity++) {
                // If Star weight is lower or equal than the current capacity
                if (stars.get(star).getWeight() <= capacity) {
                    
                    int profitIfCurrentStarIncluded = tabulationTable[star][capacity - stars.get(star).getWeight()] + stars.get(star).getProfit();
                    int profitIfCurrentStarExcluded = tabulationTable[star][capacity];

                    if (profitIfCurrentStarIncluded > profitIfCurrentStarExcluded) {
                        // Update value in one Star future
                        tabulationTable[star + 1][capacity] = profitIfCurrentStarIncluded;
                        chosenStars[star + 1][capacity].addAll(chosenStars[star][capacity - stars.get(star).getWeight()]);
                        // Update list of Stars in one Star future
                        chosenStars[star + 1][capacity].add(stars.get(star));
                    } else {
                        // Update value in one Star future
                        tabulationTable[star + 1][capacity] = profitIfCurrentStarExcluded;
                        // Update list of Stars in one Star future
                        chosenStars[star + 1][capacity].addAll(chosenStars[star][capacity]);
                    }
                // If Star weight is bigger than current capacity
                // Copy value into one Star future
                } else {
                    tabulationTable[star + 1][capacity] = tabulationTable[star][capacity];
                    chosenStars[star + 1][capacity].addAll(chosenStars[star][capacity]);
                }
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime); 
        System.out.println("Execution time in nanoseconds: " + (duration) + "ns\n");

        // Get combination of Star resulting in highest profit at highest capacity
        int capacity = maxCapacity;
        for (int star = totalStar; star > 0 && capacity > 0; star--) {
            if (tabulationTable[star][capacity] != tabulationTable[star - 1][capacity]) {
                resultStars.add(stars.get(star - 1));
                capacity -= stars.get(star - 1).getWeight();
            }
        }

        saveHighestProfitStars("DynamicProgramming/HighestProfitStars.txt");
        saveTabulationTable("DynamicProgramming/TabulationTable.txt",50);
    }

    private void saveHighestProfitStars(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("==== Highest Profit Stars ====\n");
            for (Star star : resultStars) {
                writer.write(String.format("Name: %s, Weight: %d, Profit: %d\n", star.getName(), star.getWeight(),
                        star.getProfit()));
            }
            writer.write(String.format("Total profit: %d\n", tabulationTable[stars.size()][maxCapacity]));
        }
    }

    private void saveTabulationTable(String filename, int interval) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("==== Tabulation Table ====\n");
    
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
    
            writer.write("\n==== Chosen Stars Table ====\n");
            for (int i = 0; i <= stars.size(); i++) {
                if (i == 0) {
                    writer.write(String.format("%-" + maxDigits + "s", "-") + "\t|");
                } else {
                    writer.write(String.format("%-" + maxDigits + "s", stars.get(i - 1).getName()) + "\t|");
                }
                for (int weight = 0; weight <= maxCapacity; weight += interval) {
                    writer.write("[");
                    for (Star star : chosenStars[i][weight]) {
                        writer.write((star.getName()) + " ");
                    }
                    writer.write("]\t|");
                }
                writer.write("\n");
            }
        }
    } 
}