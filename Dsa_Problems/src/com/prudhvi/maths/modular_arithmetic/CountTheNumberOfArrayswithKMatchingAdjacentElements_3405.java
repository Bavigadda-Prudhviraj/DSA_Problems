package com.prudhvi.maths.modular_arithmetic;

/**
 * Problem:
 * Given:
 * - An array of size `n` (1-indexed),
 * - Each element can take a value in the range [1, m],
 * - Exactly `k` adjacent pairs (i.e., `arr[i] == arr[i-1]`) must be equal.
 * 
 * Objective:
 * - Count the number of such valid arrays modulo 10^9 + 7.
 * 
 * Approach:
 * - Out of (n - 1) adjacent pairs, choose exactly `k` positions where adjacent elements are equal.
 *   => C(n-1, k) ways to pick these positions.
 * - For the first element, we have `m` choices.
 * - For each of the remaining (n - 1 - k) positions (where adjacent elements differ), we can choose from (m - 1) values.
 *   (because it must differ from the previous one)
 * 
 * Total valid arrays:
 *     result = C(n - 1, k) * m * (m - 1)^(n - k - 1)
 * 
 * Tools used:
 * - Modular arithmetic (to avoid overflow and handle large numbers).
 * - Modular inverse using Fermat’s Little Theorem.
 * - Binary exponentiation for fast power computation.
 * 
 * Time Complexity: 
 * - O(n) for precomputing factorials and inverse factorials.
 * - O(log(n - k - 1)) for binary exponentiation.
 * - O(1) for computing nCr using precomputed arrays.
 * 
 * Space Complexity: 
 * - O(n) for factorial and inverseFactorial arrays.
 */
public class CountTheNumberOfArrayswithKMatchingAdjacentElements_3405 {

    private static final int MOD = 1_000_000_007;
    private static long[] factorial;
    private static long[] inverseFactorial;

    public static void main(String[] args) {
        int n = 5581;     // Length of array
        int m = 58624;    // Values in range [1, m]
        int k = 4766;     // Exactly k adjacent pairs should match

        // Output the total number of valid arrays for given n, m, k
        System.out.println("Number of good arrays: " + countGoodArrays(n, m, k)); // Expected: 846088010
    }

    /**
     * Returns the number of arrays of size `n` using values [1..m] with exactly `k` adjacent equal pairs.
     *
     * @param n Length of array
     * @param m Maximum value an element can take
     * @param k Required number of adjacent equal pairs
     * @return Count of such arrays modulo MOD
     */
    public static int countGoodArrays(int n, int m, int k) {
        // Step 1: Precompute factorials and their modular inverses
        precomputeFactorials(n);

        // Step 2: Choose k positions from (n - 1) adjacent places for equal elements
        long chooseKPositions = nCr(n - 1, k);

        // Step 3: Choose value for the first element (m options)
        long result = chooseKPositions * m % MOD;

        // Step 4: For the (n - k - 1) non-matching adjacent positions, choose (m - 1) options for each
        result = result * fastPower(m - 1, n - k - 1) % MOD;

        return (int) result;
    }

    /**
     * Precomputes:
     * - factorial[i]   = i! % MOD
     * - inverseFactorial[i] = (i!)^(-1) % MOD using Fermat's Little Theorem
     *
     * Fermat's Little Theorem:
     * For a prime MOD, a^(MOD-2) ≡ a^(-1) (mod MOD)
     *
     * @param n Max value up to which to compute factorials
     */
    private static void precomputeFactorials(int n) {
        factorial = new long[n + 1];
        inverseFactorial = new long[n + 1];

        factorial[0] = inverseFactorial[0] = 1;

        // Compute factorial[i] = (i!) % MOD
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        // Compute inverseFactorial[n] = (n!)^(MOD-2) % MOD
        inverseFactorial[n] = fastPower(factorial[n], MOD - 2);

        // Compute inverseFactorial[i] using: inv[i] = inv[i+1] * (i+1) % MOD
        for (int i = n - 1; i >= 1; i--) {
            inverseFactorial[i] = inverseFactorial[i + 1] * (i + 1) % MOD;
        }
    }

    /**
     * Efficient computation of nCr % MOD using:
     * C(n, r) = n! / (r! * (n - r)!) = factorial[n] * inverseFactorial[r] * inverseFactorial[n - r]
     *
     * @param n Total number of elements
     * @param r Number of selections
     * @return C(n, r) modulo MOD
     */
    private static long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return factorial[n] * inverseFactorial[r] % MOD * inverseFactorial[n - r] % MOD;
    }

    /**
     * Binary exponentiation (base^exp % MOD):
     * - Reduces time from O(exp) to O(log exp).
     * - Operates by squaring and reducing the exponent at each step.
     * - Handles even and odd exponents correctly by checking LSB of exponent.
     *
     * Example:
     * base = 3, exp = 5 (binary 101)
     * result = 1
     * Step 1: exp & 1 == 1 -> result = result * base % MOD
     * Step 2: base = base * base % MOD
     * Step 3: shift exp right >> 1
     * Repeat until exp == 0
     *
     * @param base Base value
     * @param exp Exponent (non-negative)
     * @return (base^exp) % MOD
     */
    private static long fastPower(long base, long exp) {
        long result = 1;
        base %= MOD;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }

        return result;
    }
}
