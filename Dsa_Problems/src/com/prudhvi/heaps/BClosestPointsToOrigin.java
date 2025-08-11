package com.prudhvi.heaps;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
Problem Description:
--------------------
You are developing a feature for Zomato that helps users find the nearest restaurants 
to their current location. The user's location is assumed to be at the origin (0, 0).

Given:
- A list of restaurant coordinates (x, y) stored in 2D array form (ArrayList of ArrayLists)
- An integer B representing the number of closest restaurants to find.

Task:
Find the B closest restaurants to the origin using Euclidean distance:
    distance = sqrt( (x - 0)^2 + (y - 0)^2 )

Example:
--------
Input:
A = [[1, 3], [-2, 2]]
B = 1

Output:
[[-2, 2]]
Because:
Distance for [1, 3]  = sqrt(10)
Distance for [-2, 2] = sqrt(8)  --> Smaller, hence chosen.
*/

public class BClosestPointsToOrigin {
	// Main method for testing
    public static void main(String[] args) {
        BClosestPointsToOrigin solution = new BClosestPointsToOrigin();

        // Test data
        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        points.add(new ArrayList<Integer>() {{ add(1); add(3); }});
        points.add(new ArrayList<Integer>() {{ add(-2); add(2); }});
        points.add(new ArrayList<Integer>() {{ add(4); add(6); }});
        points.add(new ArrayList<Integer>() {{ add(-1); add(-1); }});

        int B = 2;

        // Call the method
        ArrayList<ArrayList<Integer>> closestPoints = solution.solve(points, B);

        // Print results
        System.out.println("Closest " + B + " points to origin:");
        for (ArrayList<Integer> point : closestPoints) {
            System.out.println(point);
        }
    }

    
    public ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A, int B) {

        // Max-heap (priority queue) where the farthest point is at the top
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
            (a, b) -> Double.compare(b.distanceFromOrigin, a.distanceFromOrigin)
        );

        // Add points to heap and keep only the B closest
        for (ArrayList<Integer> point : A) {
            int x = point.get(0);
            int y = point.get(1);
            Pair p = new Pair(x, y);

            maxHeap.add(p);

            if (maxHeap.size() > B) {
                maxHeap.poll(); // remove farthest point
            }
        }

        // Extract results from heap
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.poll().coordinates);
        }

        return result;
    }

    // Inner class to store coordinates and distance
    static class Pair {
        ArrayList<Integer> coordinates;
        double distanceFromOrigin;

        public Pair(int x, int y) {
            coordinates = new ArrayList<>();
            coordinates.add(x);
            coordinates.add(y);
            distanceFromOrigin = Math.sqrt(x * x + y * y);
        }
    }

    
}
