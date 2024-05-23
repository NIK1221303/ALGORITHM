import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class GenDataOne {

    public GenDataOne() {
        generateData();
    }

    public void generateData() {
        boolean flag = false;
        Random rand = new Random(1211103426);

        int[] set1 = new int[100];
        int[] set2 = new int[1000];
        int[] set3 = new int[10000];
        int[] set4 = new int[100000];
        int[] set5 = new int[500000];
        int[] set6 = new int[1000000];

        for (int i = 0; i < 1000000; i++) {
            if (i < 100) {
                do {
                    set1[i] = rand.nextInt(700);
                    flag = check(set1[i]);
                } while (!flag);
            }

            if (i < 1000) {
                do {
                    set2[i] = rand.nextInt(700);
                    flag = check(set2[i]);
                } while (!flag);
            }

            if (i < 10000) {
                do {
                    set3[i] = rand.nextInt(700);
                    flag = check(set3[i]);
                } while (!flag);
            }

            if (i < 100000) {
                do {
                    set4[i] = rand.nextInt(700);
                    flag = check(set4[i]);
                } while (!flag);
            }

            if (i < 500000) {
                do {
                    set5[i] = rand.nextInt(700);
                    flag = check(set5[i]);
                } while (!flag);
            }

            if (i < 1000000) {
                do {
                    set6[i] = rand.nextInt(700);
                    flag = check(set6[i]);
                } while (!flag);
            }
        }

        try {
            File file = new File("Dataset1.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists() && file.isFile() && file.canWrite()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(Arrays.toString(set1) + "\n");
                fileWriter.write(Arrays.toString(set2) + "\n");
                fileWriter.write(Arrays.toString(set3) + "\n");
                fileWriter.write(Arrays.toString(set4) + "\n");
                fileWriter.write(Arrays.toString(set5) + "\n");
                fileWriter.write(Arrays.toString(set6) + "\n");

                fileWriter.close();
            } else {
                System.out.println("Cannot write to the file");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static boolean check(int number) {

        String result = String.valueOf(number);
        char[] split = result.toCharArray();

        for (char num : split) {
            if (num == '5' || num == '7' || num == '8' || num == '9') {
                return false;

            }
        }
        return true;
    }
}