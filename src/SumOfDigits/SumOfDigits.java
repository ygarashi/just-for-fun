import java.time.Duration;
import java.time.Instant;

import util.Util;

public class SumOfDigits {
    private static final int LIST_SIZE = Integer.MAX_VALUE; 
    private static final int SET_OF_TESTS = 500;
    private static final short SUM_MULTIPLIER_FACTOR = 2;

    public static void main(String[] args) {

        for (int currentTest = 1; currentTest <= SET_OF_TESTS; currentTest++) {
            Util util = new Util(); 
            util.startTimeCounter();

            int currentNumber = (int) (Math.random() * LIST_SIZE);
            int numberFound = generateCandidate(currentNumber);

            generateConsoleInfo(currentTest, currentNumber, sumOfDigits(currentNumber), numberFound, util.getTimeElapsed());
        }
    }


    
    private static void generateConsoleInfo(int currentTest, int currentNumber, int sumOfDigits, int numberFound, String timeElapsed) {
        
        if (validateRange(numberFound))
            System.out.println(currentTest + ". Current number: " + currentNumber + " Sum of its digits: "
                    + sumOfDigits + " | Number found: " + numberFound + " | " + timeElapsed );
        else
            System.out.println(currentTest + ". Current number: " + currentNumber + " Sum of its digits: "
                    + sumOfDigits + " | No number found in the interval | " + timeElapsed);
    }

    static int generateCandidate(int currentNumber) {
        int sumOfDigits = sumOfDigits(currentNumber) * SUM_MULTIPLIER_FACTOR;

        // searched sum exceeds the upper bound of Integers
        if (sumOfDigits > 82) return -1;

        return generateClosestCandidate(currentNumber, sumOfDigits);
    }

    // Generates the closest candidate possible for searched sum
    private static int generateClosestCandidate(int currentNumber, int sumOfDigitsSearched) {
        int nextCandidate = new Integer(generateLowestCandidate(sumOfDigitsSearched));

        int difference = 0;
        if (currentNumber >= nextCandidate) {
            difference = (currentNumber - nextCandidate) / 9;
            nextCandidate += (difference * 9);
        }

        while ((sumOfDigits(nextCandidate) != sumOfDigitsSearched 
                || (currentNumber >= nextCandidate)) 
                && (validateRange(nextCandidate))) {
            nextCandidate += 9;
        }      

        return nextCandidate;
    }

    private static boolean validateRange(int currentNumber) {
        return (currentNumber > 0 && currentNumber <= Integer.MAX_VALUE);
    }

    // Generates the lowest number possible for searched sum
    private static String generateLowestCandidate(int sumOfDigitsSearched) {

        String digits = "";
        int remainder = sumOfDigitsSearched;

        while (remainder != 0) {
            int currentDigit = 0;
            if (remainder >= 9)
                currentDigit = 9;
            else
                currentDigit = remainder;

            digits = currentDigit + digits;
            remainder -= currentDigit;
        }
        return digits;
    }


    /*
    // possible solution for numbers that exceed upper bound of integers

    private static int candidateAboveUpperBound (int sumOfDigitsDoubled, String digits) {
        System.out.println("candidateAboveUpperBound");
        int candidateInsideInterval = Integer.MAX_VALUE;
        while (sumOfDigits(candidateInsideInterval) != sumOfDigitsDoubled) {
            candidateInsideInterval--;
        }
        return candidateInsideInterval;
    }
    */

    static int sumOfDigits(int numToProcess) {
        int sumOfDigits = 0;

        while (numToProcess != 0) {
            sumOfDigits += numToProcess % 10;
            numToProcess /= 10;
        }

        return sumOfDigits;
    }

}