package sudoku;

import java.util.*;
import java.lang.*;
class SudokuPuzzle{
	public static int[][] getPuzzle(){
		int[][] puzzle = new int[9][9];
		Random r = new Random();
		int i, j, count=0;
		while(count<20){
			i=r.nextInt(9);
			j=r.nextInt(9);
			if(puzzle[i][j]==0){
				puzzle[i][j]=r.nextInt(9)+1;
				if(checkColumn(puzzle,j)&&checkRow(puzzle,i)&&checkGroup(puzzle,(int)j/3,(int)i/3)){
					count++;
					continue;
				}
				puzzle[i][j]=0;
			}
		}
		return puzzle;
	}
	public static boolean checkColumn(int[][] puzzle,int x){
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(int i = 0; i<9; i++){
			if(puzzle[i][x] != 0){
				if(!coveredElements.add(puzzle[i][x]))
					return false;
			}
		}
		return true;
	}
	public static boolean checkRow(int[][] puzzle,int y){
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(int i = 0; i < 9; i++){
			if(puzzle[y][i] != 0){
				if(!coveredElements.add(puzzle[y][i]))
					return false;
			}
		}
		return true;
	}
	public static boolean checkGroup(int[][] puzzle,int x, int y){
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(int i = 3*x; i < (3*x+3); i++){
			for(int j = 3*y; j < (3*y+3); j++){
				if(puzzle[j][i] != 0){
					if(!coveredElements.add(puzzle[j][i]))
						return false;
				}
			}
		}
		return true;
	}
}
