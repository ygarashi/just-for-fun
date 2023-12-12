package TwoSumNum;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import util.Util;

public class TwoSumNum {
    public int[] inputArray;
    public int targetSum;

    public TwoSumNum(int[] inputArray) {
        this.inputArray = inputArray; 
    }

    public TwoSumNum(int[] inputArray, int targetSum) {
        this.inputArray = inputArray; 
        this.targetSum = targetSum;
    }

    public int[] searchTwoNumSumNaive() {
        for (int outer = 0; outer < inputArray.length - 1; outer++)
            for (int inner = outer + 1; inner < inputArray.length; inner++)
                if (inputArray[outer] + inputArray[inner] == targetSum)
                    return new int[]{inputArray[outer], inputArray[inner]};
        
        return new int[]{};
    }

    public int[] searchTwoNumSum() {
        Set<Integer> inputNumbers = new HashSet<>(); 
        for (int item : inputArray) {
            if (inputNumbers.contains(targetSum-item))
                return new int[]{item, targetSum-item};
            
            inputNumbers.add(item);
        }
        
        return new int[]{};
    }


    public static void main(String[] args) {
        int arraySize = 50_000;

        int[] tempArray = (new Util()).generateDistinctRandomNumberArray(arraySize); 
        TwoSumNum sumNum = new TwoSumNum(tempArray);
        TwoSumNum sumNumNaive = new TwoSumNum(tempArray);

        int targetSumTemp = (tempArray[arraySize-2] + tempArray[arraySize-1]);
        sumNum.targetSum = targetSumTemp;
        sumNumNaive.targetSum = targetSumTemp;

        
        System.out.println("Starting searching...");

        Util util = new Util(); 
        Instant start = util.startTimeCounter();
        //Util.generateFileFromIntArray("temp", tempArray);
        //To Do: Implement file read method

        System.out.println( "Hash searching: " + Arrays.toString(sumNum.searchTwoNumSum())  + " " + util.getTimeElapsed(start));
        System.out.println("Brute force searching: " + Arrays.toString(sumNumNaive.searchTwoNumSumNaive())  + " " + util.getTimeElapsed(start));
    }

}