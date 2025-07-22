package com.prudhvi.dynamic_programming.one_dimensional;

/**
 * Problem Description:
 * Given an array A of N integers and three integers B, C, and D,
 * find the maximum value of the expression:
 *      A[i]*B + A[j]*C + A[k]*D, where 0 <= i <= j <= k < N
 * 
 * Constraints:
 * 1 <= N <= 10^5
 * -10000 <= A[i], B, C, D <= 10000
 */
public class MaximumSumValue {

    public static void main(String[] args) {
        int[] array = {1, 5, -3, 4, -2};
        int B = 2;
        int C = 1;
        int D = -1;

        int result = findMaximumExpressionValue(array, B, C, D);
        int resultOptimized = findMaximumExpressionValueOptimized(array, B, C, D);
        System.out.println(result);  // Output should be 18
        System.out.println(resultOptimized);
    }

    /**
     * Finds the maximum value of A[i]*B + A[j]*C + A[k]*D
     * such that i <= j <= k
     */
    public static int findMaximumExpressionValue(int[] A, int B, int C, int D) {
        int n = A.length;

        // Step 1: maxPrefixB[i] = max value of A[x]*B for x in [0...i]
        ////A[i]*B
        int[] maxPrefixB = new int[n];
        maxPrefixB[0] = A[0] * B;
        for (int i = 1; i < n; i++) {
            maxPrefixB[i] = Math.max(maxPrefixB[i - 1], A[i] * B);
        }

        // Step 2: maxPrefixBC[i] = max value of A[i]*B + A[j]*C
        int[] maxPrefixBC = new int[n];
        maxPrefixBC[0] = maxPrefixB[0] + A[0] * C;
        for (int j = 1; j < n; j++) {
            maxPrefixBC[j] = Math.max(maxPrefixBC[j - 1], maxPrefixB[j] + A[j] * C);
        }

        // Step 3: maxExpression[i] = max value of A[i]*B + A[j]*C + A[k]*D for x <= y <= z <= i
        int[] maxExpression = new int[n];
        maxExpression[0] = maxPrefixBC[0] + A[0] * D;
        for (int k = 1; k < n; k++) {
            maxExpression[k] = Math.max(maxExpression[k - 1], maxPrefixBC[k] + A[k] * D);
        }

        // Final result is the max value in maxExpression[]
        return maxExpression[n - 1];
    }
   // A[i]*B + A[j]*C + A[k]*D
   //such that i <= j <= k
    public static int findMaximumExpressionValueOptimized(int[] arr, int B, int C, int D) {
        int n = arr.length;

        int maxB = arr[0] * B;
        int maxBC = maxB + arr[0] * C;
        int maxTotal = maxBC + arr[0] * D;

        for (int i = 1; i < n; i++) {
            int currentB = arr[i] * B;
            maxB = Math.max(maxB, currentB);

            int currentBC = maxB + arr[i] * C;
            maxBC = Math.max(maxBC, currentBC);

            int currentTotal = maxBC + arr[i] * D;
            maxTotal = Math.max(maxTotal, currentTotal);
        }

        return maxTotal;
    }
}
