package com.prudhvi.recursion;

import java.util.ArrayList;
import java.util.Arrays;

public class TowerofHanoi {
	
	/*
	 In the classic problem of the Towers of Hanoi, you have 3 towers numbered from 1 to 3 (left to right) and A disks numbered from 1 to A (top to bottom) of different sizes which can slide onto any tower.
		The puzzle starts with disks sorted in ascending order of size from top to bottom (i.e., each disk sits on top of an even larger one).
				You have the following constraints:
						Only one disk can be moved at a time.
							A disk is slid off the top of one tower onto another tower.
							A disk cannot be placed on top of a smaller disk.
		You have to find the solution to the Tower of Hanoi problem.
		You have to return a 2D array of dimensions M x 3, where M is the minimum number of moves needed to solve the problem.
		In each row, there should be 3 integers (disk, start, end), where:
			disk - number of the disk being moved
			start - number of the tower from which the disk is being moved
			end - number of the tower to which the disk is being moved
		Input A=3
		Output= [1 1 3 ] [2 1 2 ] [1 3 2 ] [3 1 3 ] [1 2 1 ] [2 2 3 ] [1 1 3 ]
	 
	  
	  Algorithm: Here's an approach to solve the Tower of Hanoi problem:
			1.Define a recursive function, let's call it tower_of_hanoi, that takes the following parameters:
				i.n: The number of disks to be moved.
				ii.source: The peg from which the disks should be moved.
				iii.destination: The peg where the disks should be placed.
				iv.helper: It can be used as an helper to facilitate the moves.
			2.If n is 1, simply move the disk from the source peg to the destination peg and return.
			3.Otherwise, follow these steps recursively:
            4.Move n-1 disks from the source tower to the helper tower using the destination tower.
			5.Move the remaining disk from the source tower to the destination tower.
			6.Move the n-1 disks from the helper to the destination using the source tower.
			7.Implement the main function, let's call it towerOfHanoi, which calls the tower_of_hanoi function with the given number of disks and the appropriate pegs.
			8.create an empty list to store the moves made during the Tower of Hanoi solution.
			9.Within the tower_of_hanoi function, whenever a disk move is made, append the move to the list of moves.
			10.Return the list of moves (if desired).
			
			Time complexity:(Total number of recursive calls)*(Time Complexity of one function call except for the recursive part)
								2^n*1=O(2^n)
			Space Complexity: Max size of Stack at any point of execution
							    n=O(n)
											
	  
	*/

	public static void main(String[] args) {
		int sourceTower=1;
		int helperTower=2;
		int destinationTower=3;
		int intput=4;
		ArrayList<ArrayList<Integer>> aList=new ArrayList<>();
		towerOfHanoi(intput,sourceTower,helperTower,destinationTower,aList);
		int[][] ans=new int[aList.size()][3];
		for(int i=0;i<aList.size();i++) {
			for(int j=0;j<3;j++) {
				ans[i][j]=aList.get(i).get(j);	
			}
		}
		System.out.println(Arrays.deepToString(ans));
		

	}
	public static void towerOfHanoi(int A,int sourceTower,int helperTower,int destinationTower,ArrayList<ArrayList<Integer>> aList) {
		ArrayList<Integer> diskFromToArrayList=new ArrayList<>(); 
		if(A==0) 
			//when A==0,
        	 return;
        	 
		
         //here we are written that move A-1,source to helper using destination 
        /* example: if n==3
         			we have to move n-1(2) elements from source tower to helper tower using destination tower
         			first we are considering n-1 means, then in source 1 disk will be there that can be directly switch to destination tower, 
         			NOTE:-when all remaining disk are arranged in ascending order from top to bottom
         
         */
         towerOfHanoi(A-1,sourceTower,destinationTower,helperTower,aList);
         diskFromToArrayList.add(A);
         diskFromToArrayList.add(sourceTower);
         diskFromToArrayList.add(destinationTower);
         aList.add(diskFromToArrayList);
         System.out.println("disk number: "+A+"  From: "+sourceTower+" To: "+destinationTower);
         /*
          now we have A-1 elements in helper tower,we have move this disk which are there helper tower to destination using source tower(keep the tower in middle of the method call, which is helping to move the disk)
          example: if n==3
          	above method call will help to move n-1(2) elements from source to helper using destination
          	after keeping final disk from source to destination we have to again
          	move all the disk in helper tower to destination tower using source tower(keep the tower in middle of the method call, which is helping to move the disk)
      
          */
         towerOfHanoi(A-1,helperTower,sourceTower,destinationTower,aList);
    }
	

}
