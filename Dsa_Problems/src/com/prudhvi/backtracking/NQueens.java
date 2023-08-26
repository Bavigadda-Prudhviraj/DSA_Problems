package com.prudhvi.backtracking;

import java.util.ArrayList;

public class NQueens {
	/*
	Problem Description
		The n-queens puzzle is the problem of placing n queens on an n×n chess board such that no two queens attack each other.
		Given an integer A, return all distinct solutions to the n-queens puzzle.
		Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
		The final list should be generated in such a way that the indices of the queens in each list should be in reverse lexicographical order.
	Problem Constraints
		1 <= A <= 10		
	 */
	public static void main(String[] args) {
		int chessBoardSize=4;
		ArrayList<ArrayList<String>> queenPlacement=placeQueens(chessBoardSize);
		System.out.println(queenPlacement);
	}
	/*
	Time Complexity:
			The backtracking approach tries out all possible positions for the queens, while considering constraints to ensure no two queens threaten each other.
			The time complexity depends on the number of valid solutions that can be found for the N-Queens problem.
		    The exact time complexity can vary depending on the size of the chess board and the number of valid solutions.
		 Therefore, the overAll time complexity is N^N.
	Space Complexity:
			The space complexity is determined by the recursion stack and additional data structures.
			The recursion stack can go as deep as the number of rows (N).
			The chessBoard 2D array requires O(N^2) space.
			The arrays colCheck, leftDig, and rightDig each require O(2N-1) space (constant space).
		Therefore, the overall space complexity is O(N^2) for the chess board and additional data structures.
	 */
	private static ArrayList<ArrayList<String>> placeQueens(int chessBoardSize) {
		ArrayList<ArrayList<String>> queenPlacement=new ArrayList<>();
		int row=chessBoardSize,col=chessBoardSize;
		int[][] chessBoard=new int[row][col];
		//The colCheck array is a boolean array of size col, where col represents the number of columns on the chess board. It tracks which columns are currently occupied by queens.
		boolean[] colCheck=new boolean[row];
		//The leftDig and rightDig arrays are boolean arrays used to track diagonals on the chess board.
		boolean[] leftDig=new boolean[row+col-1];//2N-1
		boolean[] rightDig=new boolean[row+col-1];
		queenPlacement(chessBoard,0,colCheck,leftDig,rightDig,col,queenPlacement);
		return queenPlacement;
	}

	private static void queenPlacement(int[][] chessBoard, int row, boolean[] colCheck, boolean[] leftDig,
		boolean[] rightDig, int col,ArrayList<ArrayList<String>> queenPlacement) {
		//If the current row is equal to the chess board size, it means all queens have been placed, and a valid placement is found. 
		//The function constructs a list of strings representing the current placement and adds it to the queenPlacement list.
		if(row==col) {
			ArrayList<String> placedQueens=new ArrayList<>();
			for(int i=0;i<row;i++) {
				StringBuilder rowPlacement=new StringBuilder();
				for(int j=0;j<col;j++) {
					rowPlacement.append(chessBoard[i][j]==1?"Q":".");
				}
				placedQueens.add(rowPlacement.toString());
			}
			queenPlacement.add(placedQueens);
			return;
		}
		for(int i=0;i<col;i++) {
			//For each column in the current row, the function checks if the current cell can be a valid position for placing a queen. 
			//This is determined by checking if the column, left diagonal, and right diagonal are safe (not under threat from other queens).
			if(!colCheck[i] && !leftDig[row+i] && !rightDig[row-i+(col-1)]) {
				chessBoard[row][i]=1;// Place a queen at current position
				colCheck[i]=true;// Mark the column as occupied
				leftDig[row+i]=true;// Mark the left diagonal as occupied
				rightDig[row-i+(col-1)]=true; // Mark the right diagonal as occupied
				// Recursively solve for the next row
				queenPlacement(chessBoard, row+1, colCheck, leftDig, rightDig, col, queenPlacement);
				// Backtrack: Remove the queen and unmark occupied positions
				chessBoard[row][i]=0;
				colCheck[i]=false;
				leftDig[row+i]=false;
				rightDig[row-i+(col-1)]=false;
			}
			
		}
		
	}

}
/*
	Check Function:
		Certainly! In the N-Queens problem, the colCheck, leftDig, and rightDig arrays are used to keep track of the columns and diagonals that are threatened by queens placed on the chessboard. These arrays help in quickly identifying if it's safe to place a queen in a particular cell during the backtracking process.
				colCheck array:
							1.The colCheck array is a boolean array of size col, where col represents the number of columns on the chess board. 
							   It tracks which columns are currently occupied by queens.
							2.When a queen is placed in a specific column, the corresponding element in the colCheck array is marked as true, indicating that this column is occupied.
				leftDig and rightDig arrays:
						The leftDig and rightDig arrays are boolean arrays used to track diagonals on the chess board.
							1.The size of these arrays is calculated as row + col - 1 (or 2N - 1), where row represents the current row, and col represents the number of columns.
							2.The reason for this size is that there are row + col - 1 diagonals in total in a square chess board.
						The index of a diagonal can be calculated as (row + col - 1), which ranges from 0 to 2N - 2.
				How the checks work:
						When attempting to place a queen in a particular cell (row, col), the code checks three conditions to determine if it's safe:
							1.No queen exists in the same column: 
									Check colCheck[col] to see if that column is already occupied by a queen.
							2.No queen exists on the same left diagonal: 
									Check leftDig[row + col] to see if that diagonal is already threatened.
							3.No queen exists on the same right diagonal: 
									Check rightDig[row - col + (col - 1)] to see if that diagonal is already threatened.
							If all three conditions are satisfied (i.e., no conflicts), the queen can be placed in the current cell. After placement, the corresponding elements in colCheck, leftDig, and rightDig arrays are updated to reflect the new threats.
							
				During backtracking, when the queen is removed from the current cell, the updates to the arrays are reversed to their previous states, allowing the exploration of other possibilities.
				
		Overall, these arrays allow the code to quickly determine whether a particular cell is safe to place a queen or not, which helps avoid unnecessary recursive exploration in the backtracking process. This efficient checking mechanism contributes to the algorithm's ability to find solutions for larger board sizes.
				 
 
 
 
 
 
 		N queen application
		 			Cryptography: 
		 					The N-Queens problem can be used to generate cryptographic keys. The solutions to the N-Queens problem can be used as a basis for creating unique and random configurations that are difficult to predict.
					Optimization and Scheduling: 
							The problem can be seen as an optimization challenge where you need to find a configuration that satisfies certain constraints. This relates to real-world scheduling problems, such as arranging tasks or events on a timeline without conflicts.
					DNA Sequencing: 
							In bioinformatics, the N-Queens problem can be used as a metaphor for solving DNA sequencing problems, where researchers aim to place DNA fragments in such a way that they overlap correctly and form a complete sequence.
					VLSI Design: 
							Very Large Scale Integration (VLSI) design involves placing components on a chip while avoiding conflicts and ensuring efficient routing of connections. The N-Queens problem can help understand and solve some of the placement challenges in chip design.
					Robotics and Sensor Placement: 
							When designing sensor networks or placing cameras on robots, you might need to ensure maximum coverage without overlaps. The N-Queens problem can provide insights into how to optimally place sensors or cameras.
					Board Layouts and Architecture: 
							Architects and urban planners can use N-Queens-like problems to solve challenges related to arranging buildings, rooms, or other elements on a grid while adhering to various constraints.
					Nurse Scheduling: 
							In health care settings, assigning nurses to shifts while considering their preferences and avoiding conflicts in schedules can be formulated as an N-Queens problem.
					Crossword Puzzle Generation: 
							Designing crossword puzzles involves placing words on a grid such that they intersect correctly. The N-Queens problem has similarities with this challenge.
					Resource Allocation: 
							When allocating resources, such as allocating classrooms to courses in a school, you need to avoid conflicts and efficiently utilize available resources. The N-Queens problem can offer insights into solving such allocation challenges.
					Game AI and Path finding: 
							In game development, the N-Queens problem can be abstracted to solve path finding or movement problems for characters or objects on a grid-based map.
			While the N-Queens problem might not be directly applicable to these domains, it provides a valuable framework for thinking about and solving problems involving arrangement, constraint satisfaction, and optimization. The problem's complexity and versatility make it a fundamental concept in computer science and problem-solving.
					 
 */
