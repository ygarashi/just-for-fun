package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Util {

    public int[] generateDistinctRandomNumberArray(int arraySize) {
        if (arraySize == 0)
            return new int[]{};
            
        Set<Integer> distinctNumbers = new LinkedHashSet<>();
        Byte signal = 1;
        while (distinctNumbers.size() < arraySize) {
            distinctNumbers.add((int) Math.floor(Math.random() * (Integer.MAX_VALUE  - 1)) * signal);
            signal = (byte) (signal * (-1));
        }
        
        return distinctNumbers.stream().mapToInt(Integer::intValue).toArray();
    }


    public String getTimeElapsed(Instant start) {
        return "Elapsed time: " + Duration.between(start, Instant.now()).toMillis() + " milliseconds";
    }

    public Instant startTimeCounter() {
        return Instant.now();
    }

    public static void generateFileFromIntArray(String fileName, int[] inputArray) {
        String path = System.getProperty("user.dir")+"/just-for-fun/files/";

        try {
            FileWriter writer = new FileWriter(path + fileName.trim()); 
            
            for(int item: inputArray) 
                writer.write(item + System.lineSeparator());
            
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
