package com.prudhvi.maths;

/**
 * This class contains methods to calculate the maximum difference between two numbers
 * obtained by remapping a single digit in the input number. The maximum number is created
 * by replacing the first non-9 digit with 9, and the minimum number is created by replacing
 * the first digit with 0.
 */
public class MaximumDifferenceByRemappingADigit_2566 {

    public static void main(String[] args) {
    	System.out.println(minMaxDifference(11891));
        System.out.println(minMaxDifference(11891)); // Test the method
    }

    /**
     * Calculates the maximum difference by remapping a digit in the input number.
     * Logic: 
     * - Convert the number to a StringBuilder for easy manipulation.
     * - For max number: Replace the first non-9 digit with 9 (all occurrences).
     * - For min number: Replace the first digit with 0 (all occurrences).
     * - Return the difference between max and min numbers.
     * 
     * Time Complexity: O(n), where n is the number of digits (due to string traversal).
     * Space Complexity: O(n) for the StringBuilder objects.
     * 
     * @param num Input number
     * @return Difference between max and min numbers
     */
    public static int minMaxDifference(int num) {
        // Convert the number to StringBuilder for manipulation
        StringBuilder maxBuilder = new StringBuilder(String.valueOf(num));

        // Step 1: Maximize the number by replacing the first non-'9' digit with '9'
        for (int i = 0; i < maxBuilder.length(); i++) {
            if (maxBuilder.charAt(i) != '9') {
                char target = maxBuilder.charAt(i);
                // Replace all occurrences of target with '9'
                for (int j = 0; j < maxBuilder.length(); j++) {
                    if (maxBuilder.charAt(j) == target) {
                        maxBuilder.setCharAt(j, '9');
                    }
                }
                break; // Done with max transformation
            }
        }

        // Step 2: Minimize the number by replacing all occurrences of the first digit with '0'
        StringBuilder minBuilder = new StringBuilder(String.valueOf(num));
        char target = minBuilder.charAt(0);
        // Replace all occurrences of the first digit with '0'
        for (int i = 0; i < minBuilder.length(); i++) {
            if (minBuilder.charAt(i) == target) {
                minBuilder.setCharAt(i, '0');
            }
        }

        // Parse the manipulated numbers and return the difference
        int max = Integer.parseInt(maxBuilder.toString());
        int min = Integer.parseInt(minBuilder.toString());

        return max - min;
    }

    /**
     * Optimized version to calculate the maximum difference by remapping a digit.
     * Logic:
     * - Uses integer array manipulation instead of strings for efficiency.
     * - Converts number to digit array.
     * - Computes max by replacing first non-9 digit with 9.
     * - Computes min by replacing first digit with 0.
     * - Returns max - min.
     * 
     * Time Complexity: O(n), where n is the number of digits (due to digit extraction and array traversal).
     * Space Complexity: O(n) for the digit array.
     * 
     * @param num Input number
     * @return Difference between max and min numbers
     */
    public static int minMaxDifferenceOptimized(int num) {
        return getMax(num) - getMin(num);
    }

    /**
     * Computes the maximum number by replacing the first non-9 digit with 9.
     * Logic:
     * - Convert number to digit array.
     * - Find the first non-9 digit.
     * - Replace all occurrences of that digit with 9.
     * - Rebuild the number from the modified digits.
     * 
     * Time Complexity: O(n), where n is the number of digits.
     * Space Complexity: O(n) for the digit array.
     * 
     * @param num Input number
     * @return Maximum number after remapping
     */
    private static int getMax(int num) {
        int digitToReplace = -1;

        // Convert number to digit array
        int[] digits = getDigits(num);
        // Find first digit that is not 9 from left to right
        for (int d : digits) {
            if (d != 9) {
                digitToReplace = d;
                break;
            }
        }

        // Replace all occurrences of digitToReplace with 9 and rebuild number
        int max = 0;
        for (int d : digits) {
            if (d == digitToReplace) {
                d = 9;
            }
            max = max * 10 + d;
        }
        return max;
    }

    /**
     * Computes the minimum number by replacing the first digit with 0.
     * Logic:
     * - Convert number to digit array.
     * - Replace all occurrences of the first digit with 0.
     * - Rebuild the number from the modified digits.
     * 
     * Time Complexity: O(n), where n is the number of digits.
     * Space Complexity: O(n) for the digit array.
     * 
     * @param num Input number
     * @return Minimum number after remapping
     */
    private static int getMin(int num) {
        // Convert number to digit array
        int[] digits = getDigits(num);
        int digitToReplace = digits[0]; // First digit (most significant)

        // Replace all occurrences of the first digit with 0 and rebuild number
        int min = 0;
        for (int d : digits) {
            if (d == digitToReplace) {
                d = 0;
            }
            min = min * 10 + d;
        }
        return min;
    }

    /**
     * Converts an integer to an array of its digits (most to least significant).
     * Logic:
     * - Calculate the number of digits using log10.
     * - Extract each digit by repeatedly taking modulo 10 and dividing by 10.
     * - Store digits in an array in reverse order for correct significance.
     * 
     * Time Complexity: O(n), where n is the number of digits.
     * Space Complexity: O(n) for the digit array.
     * 
     * @param num Input number
     * @return Array of digits
     */
    private static int[] getDigits(int num) {
        // Calculate number of digits
        int length = (int) Math.log10(num) + 1;
        int[] digits = new int[length];
        // Extract digits from least to most significant
        for (int i = length - 1; i >= 0; i--) {
            digits[i] = num % 10;
            num /= 10;
        }
        return digits;
    }
}