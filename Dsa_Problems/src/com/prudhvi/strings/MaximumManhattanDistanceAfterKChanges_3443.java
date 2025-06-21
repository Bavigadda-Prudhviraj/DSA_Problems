package com.prudhvi.strings;

public class MaximumManhattanDistanceAfterKChanges_3443 {

	public static void main(String[] args) {
		String s = "NSWWEW";
		int k = 3;
		System.out.println(maxDistance(s, k));

	}
	public static int maxDistance(String directions, int k) {
	    int maxDistance = 0;

	    int northCount = 0;
	    int southCount = 0;
	    int eastCount = 0;
	    int westCount = 0;

	    for (int i = 0; i < directions.length(); i++) {
	        char dir = directions.charAt(i);

	        if (dir == 'N') northCount++;
	        else if (dir == 'S') southCount++;
	        else if (dir == 'E') eastCount++;
	        else if (dir == 'W') westCount++;

	        int manhattanDistance = Math.abs(northCount - southCount) + Math.abs(eastCount - westCount);
	        int stepsTaken = i + 1;
	        int wastedSteps = stepsTaken - manhattanDistance;

	        int bonusBoost = (wastedSteps > 0) ? Math.min(2 * k, wastedSteps) : 0;

	        maxDistance = Math.max(maxDistance, manhattanDistance + bonusBoost);
	    }

	    return maxDistance;
	}


}
