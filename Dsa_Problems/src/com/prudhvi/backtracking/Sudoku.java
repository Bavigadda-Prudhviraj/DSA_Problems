package com.prudhvi.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

	public static void main(String[] args) {
		char[][] sudoku= {{'5','3','.','.','7','.','.','.','.'},
						  {'6','.','.','1','9','5','.','.','.'},
						  {'.','9','8','.','.','.','.','6','.'},
						  {'8','.','.','.','6','.','.','.','3'},
						  {'4','.','.','8','.','3','.','.','1'},
						  {'7','.','.','.','2','.','.','.','6'},
						  {'.','6','.','.','.','.','2','8','.'},
						  {'.','.','.','4','1','9','.','.','5'},
						  {'.','.','.','.','8','.','.','7','9'},
						  
		};
		solveSudoku(sudoku);
		ArrayList<ArrayList<Character>> sudokuList = new ArrayList<>();
		sudokuList.add(new ArrayList<>(List.of('5', '3', '.', '.', '7', '.', '.', '.', '.')));
		sudokuList.add(new ArrayList<>(List.of('6', '.', '.', '1', '9', '5', '.', '.', '.')));
		sudokuList.add(new ArrayList<>(List.of('.', '9', '8', '.', '.', '.', '.', '6', '.')));
		sudokuList.add(new ArrayList<>(List.of('8', '.', '.', '.', '6', '.', '.', '.', '3')));
		sudokuList.add(new ArrayList<>(List.of('4', '.', '.', '8', '.', '3', '.', '.', '1')));
		sudokuList.add(new ArrayList<>(List.of('7', '.', '.', '.', '2', '.', '.', '.', '6')));
		sudokuList.add(new ArrayList<>(List.of('.', '6', '.', '.', '.', '.', '2', '8', '.')));
		sudokuList.add(new ArrayList<>(List.of('.', '.', '.', '4', '1', '9', '.', '.', '5')));
		sudokuList.add(new ArrayList<>(List.of('.', '.', '.', '.', '8', '.', '.', '7', '9')));

		fillSudoko(sudokuList,0);
	}
	/*
	The  code is an implementation of a Sudoku solver using a backtracking algorithm. 
	The goal is to fill in the empty cells of a Sudoku puzzle with numbers 1 through 9 in such a way that each row, each column, and each of the nine 3x3 sub-grids contains all of the numbers from 1 to 9 without repetition
	
	Time Complexity:
				1.The time complexity of the sudoku solver is determined by the number of possibilities explored during backtracking.
				2.In the worst case, the algorithm tries out all possible configurations for each empty cell, 
				  resulting in a time complexity that is exponential in the number of empty cells.
				3.The time complexity is influenced by the difficulty of the sudoku puzzle and the choices made during the backtracking process.
			Therefore, the overall  time complexity for this algorithm is O(9^k), where k is the count of unfilled cells.
	Space Complexity:
				1.The space complexity is determined by the space required for the recursion stack and the input Sudoku grid.
				2.The recursion stack depth can go up to the number of empty cells in the grid.
				3.The Sudoku grid itself requires constant space proportional to the size of the grid (81 cells).
	
	 */
	private static void solveSudoku(char[][] suduko) {
		int startIndex=0;
		fillSudoku(suduko,startIndex);
	}

	private static boolean fillSudoku(char[][] sudoku, int startIndex) {
		// If the index reaches 81, it means the entire puzzle is filled, and a valid solution has been found. The function prints the sudoku grid
		if(startIndex==81) {
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					System.out.print(sudoku[i][j]+" ");
				}
				System.out.println();
			}
			return true;
			
		}
		//Calculate the row and column of the current cell based on the startIndex.
		int row=startIndex/9;
		int col=startIndex%9;
		//If the current cell is not empty ('.'), move to the next cell and recursively call fillSudoko.
		if(sudoku[row][col]!='.') {
			return fillSudoku(sudoku, startIndex+1);
		}
		else{
			//Otherwise, try placing digits '1' to '9' in the current cell
			for(int i=1;i<=9;i++) {
				//check if it's a valid placement using the check box function.
				if(checkbox(sudoku,row,col,i)) {
					//If it's valid, place the digit in the cell 
					sudoku[row][col]=(char)(i+48);
					// recursively call fillSudoko for the next cell.
					if(fillSudoku(sudoku, startIndex+1)) 
						return true;//If the recursive call returns true, the solution is found, and the function returns true.
						//If the recursive call returns false, 
					//backtrack by resetting the cell to '.' and continue with the next digit.
					sudoku[row][col]='.';
				}
			}
		}
		return false;
		
	}
	//This function checks whether placing the digit num in the specified cell (row, col) is valid according to Sudoku rules.
	private static boolean checkbox(char[][] sudoku, int row, int col,int num) {
		//It checks if num is already present in the current row or column.
		for(int i=0;i<9;i++) {
				if(sudoku[row][i]==(char)('0'+ num))  
					return false;
				if(sudoku[i][col]==(char)('0'+ num)) 
					return false;
		}
		//It calculates the starting indices of the 3x3 sub-grid containing the current cell 
		int squareI=row-(row%3);
		int squareJ=col-(col%3);
		//checks if num is present in that sub-grid.
		for(int i=squareI;i<squareI+3;i++) { 
			for(int j=squareJ;j<squareJ+3;j++)
				if(sudoku[i][j]==(char)('0'+ num))
					return false;
		}
		//If no conflicts are found, the placement is valid, and the function returns true. Otherwise, it returns false.
		return true;
	}
	//ArrayList as input
	private static boolean fillSudoko(ArrayList<ArrayList<Character>> suduko, int startIndex) {
        if(startIndex==81) {
        	System.out.println("Array List");
        	for(int i=0;i<9;i++) {
        		ArrayList<Character> row=suduko.get(i);
        		for(int j=0;j<9;j++) {
        			System.out.print(row.get(j)+" ");
        		}
        		System.out.println();
        	}
            return true;
            
        }
        int row=startIndex/9;
        int col=startIndex%9;
        if(suduko.get(row).get(col)!='.') {
            return fillSudoko(suduko, startIndex+1);
        }
        else{
            for(int i=1;i<=9;i++) {
                if(checkbox(suduko,row,col,i)) {
                    ArrayList<Character> list=suduko.get(row);
                    list.set(col,Character.forDigit(i,10));
                    if(fillSudoko(suduko, startIndex+1)) {
                        return true;
                    }
                    list.set(col,'.');
                }
            }
        }
        return false;
        
    }

    private static boolean checkbox(ArrayList<ArrayList<Character>> suduko, int row, int col,int num) {
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(suduko.get(row).get(j)==Character.forDigit(num,10)) 
                    return false;
                if(suduko.get(i).get(col)==Character.forDigit(num,10)) 
                    return false;
            }
        }
        int squareI=row-(row%3);
        int squareJ=col-(col%3);
        for(int i=squareI;i<squareI+3;i++) {
            for(int j=squareJ;j<squareJ+3;j++) {
                if(suduko.get(i).get(j)==Character.forDigit(num,10))
                    return false;
            }
        }
        
        return true;
    }

}



