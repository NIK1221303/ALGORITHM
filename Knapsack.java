import java.util.*;
import java.io.*;

public class Knapsack {

    private List<Star> stars;
    private int maxWeight;
    private int[][] dp;
    private List<Star> resultStars;

    public Knapsack(DataTwo dataTwo, int maxWeight) {
        this.stars = dataTwo.getStars();
        this.maxWeight = maxWeight;
        this.dp = new int[stars.size() + 1][maxWeight + 1];
        this.resultStars = new ArrayList<>();
    }

    public void solve() throws IOException {
        int n = stars.size();

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= maxWeight; w++) {
                if (stars.get(i - 1).getWeight() <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - stars.get(i - 1).getWeight()] + stars.get(i - 1).getProfit());
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        int w = maxWeight;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                resultStars.add(stars.get(i - 1));
                w -= stars.get(i - 1).getWeight();
            }
        }

        printResult("result.txt");
        printMatrix("matrix.txt");
    }

    private void printResult(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Selected stars:\n");
            for (Star star : resultStars) {
                writer.write(String.format("Name: %s, Weight: %d, Profit: %d\n", star.getName(), star.getWeight(), star.getProfit()));
            }
            writer.write(String.format("Total profit: %d\n", dp[stars.size()][maxWeight]));
        }
    }

    private void printMatrix(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("DP Matrix:\n");
            for (int i = 0; i <= stars.size(); i++) {
                for (int w = 0; w <= maxWeight; w++) {
                    writer.write(dp[i][w] + " ");
                }
                writer.write("\n");
            }
        }
    }
}