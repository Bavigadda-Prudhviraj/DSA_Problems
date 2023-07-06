package com.prudhvi.hashing;

import java.util.HashMap;
import java.util.HashSet;


public class CountRightAngleTriangles {

	public static void main(String[] args) {
		int[] x= {1, 1, 2, 3, 3};
		int[] y= {1, 2, 1, 2, 1};
		int answer=bruteForce(x, y);
		System.out.println(answer);
		int optimimunMethodAnswer=countTrianglesOptimized(x, y);
		System.out.println(optimimunMethodAnswer);
	}
	/*
	The countTrianglesOptimized method takes two arrays, x and y, as inputs. 
	It calculates the number of right-angled triangles that can be formed using the points given in the x and y arrays. 
	This method uses an optimized approach by counting the frequencies of x-coordinates and y-coordinates to calculate the number of right-angled triangles.
	
	Time complexity:
			The time complexity of this code is O(n), where n is the length of the input arrays. 
			The code iterates over the arrays once to calculate the frequencies of x-coordinates and y-coordinates. 
			The subsequent loop also iterates over the points once to calculate the count of right-angled triangles. 
			HashMap operations, such as put and get, have an average time complexity of O(1).

	Space Complexity:
			The space complexity of the code is O(n), 
			as it utilizes two HashMaps to store the frequencies of x-coordinates and y-coordinates, 
			which can have up to n distinct elements in the worst case.
	
	 */
	public static int countTrianglesOptimized(int[] x,int[] y) {
		//This line initializes the counter for the number of right-angled triangles to zero.
		int rightAngleTrianglesCount=0;
		//which will store the frequency of each x-coordinate.
		HashMap<Integer, Integer> xEleFre=new HashMap<>();
		// loop iterates over the x-coordinates and updates the frequency count in the xEleFreHashMap
		for(int i=0;i<x.length;i++) {
			xEleFre.put(x[i],(xEleFre.getOrDefault(x[i],0)+1));
		}
		//which will store the frequency of each y-coordinate
		HashMap<Integer, Integer> yEleFre=new HashMap<>();
		//loop iterates over the y-coordinates and updates the frequency count in the yEleFreHashMap
		for(int i=0;i<y.length;i++) {
			yEleFre.put(y[i],(yEleFre.getOrDefault(y[i],0)+1));
			
		}
		//oop iterates over the points and calculates the number of right-angled triangles using the frequencies of x and y coordinates:
		for(int i=0;i<x.length;i++) {
			int x1=x[i];
			int y2=y[i];
			int perpendicularToXaxis=0;
			int perpendicularToYaxis=0;
			////The code calculates the frequencies of x-coordinates (perpendicularToXaxis) and y-coordinates (perpendicularToYaxis) for the current point. 
			if(xEleFre.containsKey(x1)) {
				perpendicularToXaxis=xEleFre.get(x1);
			}
			if(yEleFre.containsKey(y2)) {
				perpendicularToYaxis=yEleFre.get(y2);
			}
			//It subtracts 1 from each frequency because the current point itself should not be counted as part of the right-angled triangle.
			//The rightAngleTrianglesCount is updated by multiplying the frequencies of perpendicular x-coordinates and perpendicular y-coordinates for the current point.
			rightAngleTrianglesCount+=(perpendicularToXaxis-1)*(perpendicularToYaxis-1);
		}
		//Finally, the method returns the total count of right-angled triangles.
		return rightAngleTrianglesCount;
	}
	/*
	bruteForce that takes two arrays, x and y, as inputs. 
	This method calculates the number of right-angled triangles that can be formed using the given points in the arrays.
	
	Time Complexity:
			The time complexity of this code is O(n^2), where n is the length of the input arrays. 
			This is because there are two nested loops iterating over the points, resulting in n * n iterations. 
			Additionally, the HashSet operations (addition and lookup) have an average time complexity of O(1).

	Space Complexity:
			The space complexity of the code is O(n), as it utilizes a HashSet to store unique point combinations, 
			which can take up to n entries in the worst case
	 */
	public static int bruteForce(int[] x,int[] y) {
		int n=x.length;//This line calculates the length of the input array x and assigns it to the variable n, which represents the number of points.
		//which will be used to store the unique combinations of points (represented as strings).
		HashSet<String> hashSet=new HashSet<>();
		// loop iterates over each point in the arrays x and y and adds their combinations as strings to the hashSet:
		for(int i=0;i<n;i++) {
			int x1=x[i];
			int y1=y[i];
			hashSet.add(x1+"@"+y1);
		}
		int trianglesCount=0;//the counter for the number of right-angled triangles to zero.
		//nested loop iterates over each pair of points in the arrays x and y to check for right-angled triangles:
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i==j) {
					continue;
				}
				else {
					// the code checks if the current pair of points forms a right-angled triangle. 
					//It checks two cases based on the relative positions of the points:
					int x1=x[i];
					int y1=y[i];
					int x2=x[j];
					int y2=y[j];
					//Within each case, it calculates the coordinates of the remaining two vertices of the triangle (rightAngleVertex1 and rightAngleVertex2) and checks if they exist in the hashSet:
					if(x1<x2 && y1<y2) {
						//why @ see comments in bottom
						String rightAngleVertex1=x1+"@"+y2;
						String rightAngleVertex2=x2+"@"+y1;
						if(hashSet.contains(rightAngleVertex1)) {
							trianglesCount++;
						}
						if(hashSet.contains(rightAngleVertex2)) {
							trianglesCount++;
						}
					}
					else if(x1<x2 && y1>y2){
						String rightAngleVertex1=x1+"@"+y2;
						String rightAngleVertex2=x2+"@"+y1;
						if(hashSet.contains(rightAngleVertex1)) {
							trianglesCount++;
						}
						if(hashSet.contains(rightAngleVertex2)) {
							trianglesCount++;
						}
						
					}
				}
			}
		}
		return trianglesCount;
	}
	/*
	The "@" symbol is used as a delimiter or separator when creating the string representation of the point coordinates before adding them to the HashSet.
	The reason for using "@" in this code is to create a unique string representation for each point. 
	Since the HashSet stores elements based on their uniqueness, combining the x-coordinate and y-coordinate with "@" ensures that each point is represented by a distinct string in the HashSet.

	By using "@" as a delimiter, it guarantees that two different points (x1, y1) and (x2, y2) will result in different strings, even if the x-coordinates or y-coordinates are the same. 
	For example, 
		if we have (1, 22) and (12, 2) as points, using "@" ensures that their string representations will be "1@22" and "12@2", respectively, 
		and they will be treated as distinct elements in the HashSet.

	This approach allows the code to efficiently check for the existence of the remaining vertices of the right-angled triangle by forming the same string representation and checking if it is present in the HashSet.
	 */

}
