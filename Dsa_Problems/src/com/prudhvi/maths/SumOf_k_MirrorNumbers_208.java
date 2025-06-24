package com.prudhvi.maths;

/**
 * Problem: Find the sum of the first 'n' natural numbers that are palindromes in both
 * base-10 and base-k (where k is a given base).
 *
 * Approach:
 * - Generate palindromes in increasing length using half-length mirroring.
 * - Convert each palindrome to base-k.
 * - Check if the base-k representation is also a palindrome.
 * - If yes, add to the sum and continue until n palindromes are found.
 */
public class SumOf_k_MirrorNumbers_208 {

    public static void main(String[] args) {
        int base = 2;   // target base (e.g., binary)
        int count = 5;  // how many k-mirror numbers we want
        long result = kMirror(base, count);
        System.out.println("Sum of first " + count + " base-" + base + " mirror numbers: " + result);
    }

    /**
     * Returns the sum of the first 'n' numbers that are palindromic in both base-10 and base-k.
     *
     * @param k Target base (e.g., 2 for binary)
     * @param n Number of k-mirror numbers to sum
     * @return Sum of first 'n' k-mirror numbers
     */
    public static long kMirror(int k, int n) {
        int length = 1;           // Current digit length of the full palindrome
        long mirrorSum = 0;       // Running sum of valid k-mirror numbers

        // Keep generating palindromes until we find 'n' valid ones
        while (n > 0) {
            int halfLength = (length + 1) / 2;  // Number of digits in the first half
            long min = (long) Math.pow(10, halfLength - 1);   // Smallest number for first half
            long max = (long) Math.pow(10, halfLength) - 1;   // Largest number for first half

            // Iterate over all numbers of 'halfLength' digits
            for (long current = min; current <= max; current++) {
                // Create first half and its reverse
                StringBuilder firstHalf = new StringBuilder(String.valueOf(current));
                StringBuilder secondHalf = new StringBuilder(firstHalf).reverse();

                // Construct full palindrome
                StringBuilder palindrome = new StringBuilder(firstHalf);
                if (length % 2 == 0) {
                    palindrome.append(secondHalf);  // even-length palindrome
                } else {
                    palindrome.append(secondHalf.substring(1)); // skip middle digit for odd
                }

                // Convert palindrome to string
                String decimalPalindrome = palindrome.toString();

                // Convert it to base-k string representation
                String baseKRepresentation = toBaseK(decimalPalindrome, k);

                // Check if base-k version is also a palindrome
                if (isPalindrome(baseKRepresentation)) {
                    mirrorSum += Long.parseLong(decimalPalindrome);  // Add to sum
                    n--;  // one more valid number found

                    // Early exit if we’ve found enough
                    if (n == 0) {
                        return mirrorSum;
                    }
                }
            }

            length++; // Move to next length of palindromes
        }

        return mirrorSum;
    }

    /**
     * Converts a decimal number string into base-k string representation.
     *
     * @param numberStr The number in base-10 as a string
     * @param k         The target base (e.g., 2 for binary)
     * @return The number converted to base-k as a string
     */
    private static String toBaseK(String numberStr, int k) {
        long number = Long.parseLong(numberStr);
        StringBuilder baseK = new StringBuilder();

        // Repeated division method
        while (number > 0) {
            baseK.append(number % k); // Get remainder (current digit in base-k)
            number /= k;
        }

        return baseK.reverse().toString(); // Digits collected in reverse order
    }

    /**
     * Checks if the given string is a palindrome.
     *
     * @param str Input string
     * @return true if palindrome, false otherwise
     */
    private static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;

        // Two-pointer technique to compare characters from both ends
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }

        return true;
    }
}
/**
 * 🧠 Logic Explanation (How the Code Works)
		Palindrome Generation:
			For each length len, generate all half-length numbers.
			Reflect the half to create full palindromes (even/odd separately).
			
		Base-k Conversion:
			Convert the decimal palindrome to the specified base k using repeated division.
		
		Dual Palindrome Check:
			Ensure the number is a palindrome in both base-10 and base-k.
		
		Accumulate Sum:
			If both conditions are met, add to the running sum.
			Repeat until n such numbers are found.
		
		📈 Time & Space Complexity
		Time Complexity:
			Let L be the maximum length of digits until n k-mirror numbers are found.
			For each length len, we check up to 9 * 10^(len/2) numbers.
		
			Each number:
				Palindrome generation: O(log N)
				Base conversion: O(log N)
				Palindrome check in base-k: O(log N)
		
			Worst-case time:
				O(n * log N) where N is the size of the largest palindrome encountered.
		
		Space Complexity:
		O(log N) for string buffers during base conversion and palindrome checks.


 */
