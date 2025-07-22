package com.prudhvi.dynamic_programming.one_dimensional;

public class MaxProductSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxProduct(final int[] A) {
		int n = A.length;
		if (n == 1)
			return A[0];

		int[] minProduct = new int[n]; // Minimum product subarray ending at i
		int[] maxProduct = new int[n]; // Maximum product subarray ending at i

		minProduct[0] = A[0];
		maxProduct[0] = A[0];

		int result = A[0];

		for (int i = 1; i < n; i++) {
			int current = A[i];

			minProduct[i] = Math.min(current, Math.min(current * minProduct[i - 1], current * maxProduct[i - 1]));
			maxProduct[i] = Math.max(current, Math.max(current * minProduct[i - 1], current * maxProduct[i - 1]));

			result = Math.max(result, maxProduct[i]);
		}

		return result;
	}

	public int maxProductOptimized(final int[] A) {
		int n = A.length;

		int minEndingHere = A[0]; // Minimum product ending at current index
		int maxEndingHere = A[0]; // Maximum product ending at current index
		int result = A[0]; // Final result

		for (int i = 1; i < n; i++) {
			int current = A[i];

			// If current is negative, min and max will swap roles
			if (current < 0) {
				int temp = maxEndingHere;
				maxEndingHere = minEndingHere;
				minEndingHere = temp;
			}

			// Update max and min products
			maxEndingHere = Math.max(current, current * maxEndingHere);
			minEndingHere = Math.min(current, current * minEndingHere);

			// Update result with the best product so far
			result = Math.max(result, maxEndingHere);
		}

		return result;
	}

}
