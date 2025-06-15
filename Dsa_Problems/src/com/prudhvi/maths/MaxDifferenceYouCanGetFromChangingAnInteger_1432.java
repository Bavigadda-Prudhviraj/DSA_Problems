package com.prudhvi.maths;

/**
 * This class calculates the maximum difference between two numbers obtained by
 * changing one digit in the input number. The maximum number is created by replacing
 * the first non-9 digit with 9, and the minimum number is created by replacing the
 * first digit with 1 or a non-0, non-1 digit in other positions with 0.
 */
public class MaxDifferenceYouCanGetFromChangingAnInteger_1432 {

    /**
     * Main method to test the maxDiff functionality with sample inputs.
     * 
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println(maxDiff(9288)); // Output: 8700
        System.out.println(maxDiff(111));  // Output: 888
        System.out.println(maxDiff(9999)); // Output: 0
    }

    /**
     * Calculates the maximum difference by subtracting the minimum possible number
     * from the maximum possible number after changing one digit.
     * 
     * Logic:
     * - Compute the maximum number by calling getMax, which replaces the first non-9
     *   digit with 9.
     * - Compute the minimum number by calling getMin, which replaces the first digit
     *   with 1 or a non-0, non-1 digit with 0.
     * - Return the difference between max and min.
     * 
     * Time Complexity: O(n), where n is the number of digits, due to calls to getMax
     * and getMin, each of which processes the digits once.
     * Space Complexity: O(n) for the character arrays created in getMax and getMin.
     * 
     * @param num Input number
     * @return Difference between max and min numbers
     */
    public static int maxDiff(int num) {
        return getMax(num) - getMin(num);
    }

    /**
     * Computes the maximum number by replacing the first non-9 digit with 9.
     * 
     * Logic:
     * - Convert the number to a character array for digit manipulation.
     * - Find the first digit that is not '9'.
     * - Replace all occurrences of that digit with '9' to maximize the number.
     * - Convert the modified array back to an integer.
     * 
     * Example: For 9288, replace 9 (first non-9 is 2) → 9988.
     * 
     * Time Complexity: O(n), where n is the number of digits, due to single pass to
     * find non-9 digit and another to replace digits.
     * Space Complexity: O(n) for the character array.
     * 
     * @param num Input number
     * @return Maximum number after remapping
     */
    private static int getMax(int num) {
        // Convert number to character array for manipulation
        char[] digits = String.valueOf(num).toCharArray();
        // Find the first digit that is not 9 to replace with 9
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != '9') {
                char digitToReplace = digits[i];
                // Replace all occurrences of digitToReplace with 9
                for (int j = i; j < digits.length; j++) {
                    if (digits[j] == digitToReplace) {
                        digits[j] = '9';
                    }
                }
                break; // Stop after replacing the first non-9 digit
            }
        }
        // Convert modified array back to integer
        return Integer.parseInt(new String(digits));
    }

    /**
     * Computes the minimum number by replacing the first digit with 1 or a non-0,
     * non-1 digit in other positions with 0.
     * 
     * Logic:
     * - Convert the number to a character array.
     * - If the first digit is not '1', replace all its occurrences with '1' to minimize
     *   the number while avoiding leading zeros.
     * - If the first digit is '1', find the first non-0, non-1 digit in other positions
     *   and replace its occurrences with '0'.
     * - Convert the modified array back to an integer.
     * 
     * Example: For 9288, replace 9 (first digit) with 1 → 1288.
     *          For 111, first digit is 1, no non-0, non-1 digits → 111.
     * 
     * Time Complexity: O(n), where n is the number of digits, due to single pass to
     * check first digit and another to replace digits.
     * Space Complexity: O(n) for the character array.
     * 
     * @param num Input number
     * @return Minimum number after remapping
     */
    private static int getMin(int num) {
        // Convert number to character array for manipulation
        char[] digits = String.valueOf(num).toCharArray();
        // Handle the first digit separately to avoid leading zero
        if (digits[0] != '1') {
            char digitToReplace = digits[0];
            // Replace all occurrences of digitToReplace with 1
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == digitToReplace) {
                    digits[j] = '1';
                }
            }
        } else {
            // If first digit is 1, find the first non-0, non-1 digit to replace with 0
            for (int i = 1; i < digits.length; i++) {
                if (digits[i] != '0' && digits[i] != '1') {
                    char digitToReplace = digits[i];
                    // Replace all occurrences of digitToReplace with 0
                    for (int j = i; j < digits.length; j++) {
                        if (digits[j] == digitToReplace) {
                            digits[j] = '0';
                        }
                    }
                    break; // Stop after replacing the first valid digit
                }
            }
        }
        // Convert modified array back to integer
        return Integer.parseInt(new String(digits));
    }
}