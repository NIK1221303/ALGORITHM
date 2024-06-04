package DatasetOne;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataOnes {
    private static List<DataOne> sets;

    public DataOnes() {
        sets = new ArrayList<>();
        sets.add(new DataOne(100));
        sets.add(new DataOne(1000));
        sets.add(new DataOne(10000));
        sets.add(new DataOne(100000));
        sets.add(new DataOne(500000));
        sets.add(new DataOne(1000000));
    }

    public void retrieveData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineIndex = 0;

            while ((line = br.readLine()) != null) {
                if (lineIndex < sets.size()) {
                    sets.get(lineIndex).loadData(line);
                } else {
                    throw new IOException("Unexpected line in file: " + lineIndex);
                }
                lineIndex++;
            
            }
        
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public List<DataOne> getSets() {
        return sets;
    }

    public DataOne getSet(int index) {
        List<DataOne> setList = getSets();
        if (index >= 0 && index < setList.size()) {
            return setList.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid set index: " + index);
        }
    }
}

