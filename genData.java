import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class genData {
    public static void main(String[] args) {
        Random rand = new Random(1211103426);

        int[] set1 = new int[100];
        int[] set2 = new int[1000];
        int[] set3 = new int[10000];
        int[] set4 = new int[100000];
        int[] set5 = new int[500000];
        int[] set6 = new int[1000000];

        for (int i = 0; i < 1000000; i++) {

            if (i < 100) {
                set1[i] = rand.nextInt(1000);
                
            }

            if (i < 1000) {
                set2[i] = rand.nextInt(1000);
            }

            if (i < 10000) {
                set3[i] = rand.nextInt(1000);
            }

            if (i < 100000) {
                set4[i] = rand.nextInt(1000);
            }

            if (i < 500000) {
                set5[i] = rand.nextInt(1000);
            }

            if (i < 1000000) {
                set6[i] = rand.nextInt(1000);
            }
        }

        try {
            File file = new File("Dataset1.txt");
            if (file.exists() && file.isFile() && file.canWrite()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(Arrays.toString(set1));
                fileWriter.write(Arrays.toString(set2));
                fileWriter.write(Arrays.toString(set3));
                fileWriter.write(Arrays.toString(set4));
                fileWriter.write(Arrays.toString(set5));
                fileWriter.write(Arrays.toString(set6));

                fileWriter.close();
            } else {
                System.out.println("cannot write");
            }
        } catch (IOException e) {
            System.out.println(e);

        }

       // System.out.println(Arrays.toString(set1));
       // System.out.println(Arrays.toString(set2));

    }
}