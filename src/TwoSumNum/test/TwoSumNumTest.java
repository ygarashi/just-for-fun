package TwoSumNum.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import TwoSumNum.TwoSumNum;
import util.Util;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

public class TwoSumNumTest {

    @Test
    public void inputArrayNotNull() {
        TwoSumNum twoSumNum = buildTwoSumNum(0);
        assertNotNull(twoSumNum.inputArray);
    }
    
    @Test
    public void inputArrayNotEmpty() {
        TwoSumNum twoSumNum = buildTwoSumNum(1);
        assertTrue(twoSumNum.inputArray.length > 0);
    }

    @Test
    public void inputArrayNotSingleItem() {
        TwoSumNum twoSumNum = buildTwoSumNum(2);
        assertTrue(twoSumNum.inputArray.length > 1);
    }

    @Test
    public void inputArrayNonDistinctItems() {
        TwoSumNum twoSumNum = buildTwoSumNum(5);

        Set<Integer> distinctItems = new LinkedHashSet<>();
        for (int item : twoSumNum.inputArray) 
            distinctItems.add(item);
        
        assertArrayEquals(twoSumNum.inputArray, distinctItems.stream().mapToInt(Integer::intValue).toArray());
    }

    @Test
    public void testSearchTwoNumSumNaive(){
        int[] inputArray = new int[]{3, 5, -4, 8, 11, 1, -1, 6};
        int target = 10;
        int[] expectedResult = new int[]{11, -1};
        
        TwoSumNum twoSumNum = new TwoSumNum(inputArray, target);

        assertArrayEquals(expectedResult, twoSumNum.searchTwoNumSumNaive());
    }

    /*@Test
    public void testSearchTwoNumSum(){
        int[] inputArray = new int[]{3, 5, -4, 8, 11, 1, -1, 6};
        int target = 10;
        int[] expectedResult = new int[]{11, -1};
        int[] expectedResultInversed = new int[]{-1, 11};
        
        TwoSumNum twoSumNum = new TwoSumNum(inputArray, target);

        int[] actual = twoSumNum.searchTwoNumSum();
        assert(actual == expectedResult 
                    || actual == expectedResultInversed);
    }*/


    private TwoSumNum buildTwoSumNum(int arraySize) {       
        Util util = new Util();
        return new TwoSumNum(util.generateDistinctRandomNumberArray(arraySize));
    }

}


/*
#### Write a function that takes in a non-empty array of distinct integers and an integer representing a target sum.

#### If any two numbers in the input array sum up to the target sum, the function should return them in an array, in any order. If no two numbers sum up to the target sum, the function should return an empty array.

#### Note that the target sum has to be obtained by summing two different integers in the array; you can't add a single integer to itself in order to obtain the target sum.

#### You can assume that there will be at most one pair of numbers summing up to the target sum.

## Sample Input
    array = [3, 5, -4, 8, 11, 1, -1, 6]

    targetSum = 10

## Sample Output
    [-1, 11] // the numbers could be in reverse order

 */