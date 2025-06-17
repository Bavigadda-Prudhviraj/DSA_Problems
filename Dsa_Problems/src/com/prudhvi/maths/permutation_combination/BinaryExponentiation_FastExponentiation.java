package com.prudhvi.maths.permutation_combination;

/**
 * This class implements binary exponentiation (fast exponentiation) to compute base^exponent efficiently.
 * The algorithm reduces the number of multiplications by using the binary representation of the exponent.
 */
public class BinaryExponentiation_FastExponentiation {
    // Constant for modulo to prevent overflow (commonly used in competitive programming)
    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) {
        int base = 2;
        int exponent = 10;
        // Print the result of base^exponent with modulo to handle large numbers
        System.out.println("Result of " + base + "^" + exponent + " = " + calculatePower(base, exponent));
    }

    /**
     * Calculates base^exponent using binary exponentiation.
     * Time Complexity: O(log exponent) - Each recursive call reduces the exponent by half.
     * Space Complexity: O(log exponent) - Due to the recursive call stack.
     * @param base The base number
     * @param exponent The exponent (non-negative integer)
     * @return The result of base^exponent (modulo MOD to prevent overflow)
     */
    public static long calculatePower(int base, int exponent) {
        // Base case: any number raised to power 0 is 1
        if (exponent == 0) {
            return 1;
        }

        // Recursively calculate the power for half the exponent
        long halfPower = calculatePower(base, exponent / 2);
        
        // Square the result of the half exponent to get (base^(exponent/2))^2
        long result = (halfPower * halfPower) % MOD;
        
        // If exponent is odd, multiply by base to account for the extra power
        if ((exponent & 1) == 1) {
            result = (result * base) % MOD;
        }
        
        return result;
    }
    /**
     * The algorithm reduces the number of multiplications by using the binary representation of the exponent.
     * 
     * Why and How Binary Exponentiation Works:
     * - Why Use Binary Exponentiation?
     *   - Naive exponentiation (multiplying base by itself exponent times) has a time complexity of O(n), where n is the exponent.
     *     This is inefficient for large exponents.
     *   - Binary exponentiation reduces the time complexity to O(log n) by leveraging the binary representation of the exponent,
     *     minimizing the number of multiplications.
     * - How It Works:
     *   - The algorithm uses the property that any number can be expressed in binary form. For example, to compute base^10,
     *     note that 10 in binary is 1010_2, which represents 2^3 + 2^1 = 8 + 2.
     *   - Thus, base^10 = base^(2^3) * base^(2^1).
     *   - The algorithm works by:
     *     1. Dividing the Exponent: For any exponent n, compute base^(n/2) recursively and square it to get (base^(n/2))^2 = base^n.
     *     2. Handling Odd Exponents: If the exponent is odd, multiply the result by base to account for the extra power.
     *     3. Bitwise Check: The condition (exponent & 1) == 1 checks if the exponent is odd by examining its least significant bit.
     *     4. Modulo Operation: To prevent overflow, each multiplication is taken modulo 10^9 + 7 (a standard prime in competitive programming).
     *   - This approach reduces the number of multiplications to approximately log_2(n), as the exponent is halved in each step.
     * - Example Walkthrough for 2^10:
     *   - 10 in binary is 1010_2.
     *   - Steps:
     *     - exponent = 10: Even, compute halfPower = 2^5, square it: (2^5)^2 = 2^10.
     *     - exponent = 5: Odd, compute halfPower = 2^2, square it: (2^2)^2 = 2^4, multiply by 2: 2^4 * 2 = 2^5.
     *     - exponent = 2: Even, compute halfPower = 2^1, square it: (2^1)^2 = 2^2.
     *     - exponent = 1: Odd, compute halfPower = 2^0 = 1, square it: 1^2 = 1, multiply by 2: 1 * 2 = 2^1.
     *     - exponent = 0: Return 1.
     *   - Final result: 2^10 = 1024, with modulo applied if necessary.
     */

}
