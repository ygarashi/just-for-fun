package easy.ex001_two_number_sum;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class SumOfDigits {
    private static final int LIST_SIZE = Integer.MAX_VALUE;
    private static final int SET_OF_TESTS = 500;
    private static final short SUM_MULTIPLIER_FACTOR = 2;
    private static Instant start;

    public static void main(String[] args) {

        for (int tests = 0; tests <= SET_OF_TESTS; tests++) {
            startTimeCounter();

            int currentNumber = (int) (Math.random() * LIST_SIZE);
            int numberFound = generateCandidate(currentNumber);

            generateConsoleInfo(currentNumber, sumOfDigits(currentNumber), numberFound, getTimeElapsed());
        }
    }

    private static long getTimeElapsed() {
        return Duration.between(start, Instant.now()).toMillis();
    }

    
    private static void startTimeCounter() {
        start = Instant.now();
    }


    private static void generateConsoleInfo(int currentNumber, int sumOfDigits, int numberFound, long timeElapsed) {
        if (numberFound > 0)
            System.out.println("Current number: " + currentNumber + " Sum of its digits: "
                    + sumOfDigits + " | Number found: " + numberFound + " | Elapsed time: " + timeElapsed + " milliseconds");
        else
            System.out.println("Current number: " + currentNumber + " Sum of its digits: "
                    + sumOfDigits + " | No number found in the interval | Elapsed time: " + timeElapsed + " milliseconds");
    }


    static int generateCandidate(int currentNumber) {
        int sumOfDigitsDoubled = sumOfDigits(currentNumber) * SUM_MULTIPLIER_FACTOR;

        // searched sum exceeds the upper bound of Integers
        if (sumOfDigitsDoubled > 82) return -1;

        return generateClosestCandidate(currentNumber, sumOfDigitsDoubled);
    }

    // Generates the closest candidate possible for searched sum
    private static int generateClosestCandidate(int currentNumber, int sumOfDigitsSearched) {
        int nextCandidate = new Integer(generateLowestCandidate(sumOfDigitsSearched));

        int difference = 0;
        if (currentNumber >= nextCandidate) {
            difference = (currentNumber - nextCandidate) / 9;
            nextCandidate += (difference * 9);
        }

        while (sumOfDigits(nextCandidate) != sumOfDigitsSearched || (currentNumber >= nextCandidate))
            nextCandidate += 9;

        return nextCandidate;
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