package com.prudhvi.Arrays;

import java.util.ArrayList;
import java.util.List;

public class LexicographicalNumbers_386 {

    public static void main(String[] args) {
        System.out.println(generateLexicographicalNumbers(34));
        
    }

    /**
     * Generates numbers from 1 to n in lexicographical (dictionary) order.
     */
    public static List<Integer> generateLexicographicalNumbers(int n) {
        List<Integer> result = new ArrayList<>();

        for (int startingDigit = 1; startingDigit <= 9; startingDigit++) {
            dfs(startingDigit, n, result);
        }

        return result;
    }

    /**
     * Depth-first traversal to build lexicographical order numbers.
     */
    private static void dfs(int current, int limit, List<Integer> result) {
        if (current > limit) return;

        result.add(current);

        for (int digit = 0; digit <= 9; digit++) {
            int next = current * 10 + digit;
            if (next > limit) return;
            dfs(next, limit, result);
        }
    }
}
