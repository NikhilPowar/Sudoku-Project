package sudoku;

import java.util.*;
import java.awt.event.*;
import java.awt.*;

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
	private static boolean checkColumn(int[][] puzzle,int x){
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(int i = 0; i<9; i++){
			if(puzzle[i][x] != 0){
				if(!coveredElements.add(puzzle[i][x]))
					return false;
			}
		}
		return true;
	}
	private static boolean checkRow(int[][] puzzle,int y){
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(int i = 0; i < 9; i++){
			if(puzzle[y][i] != 0){
				if(!coveredElements.add(puzzle[y][i]))
					return false;
			}
		}
		return true;
	}
	private static boolean checkGroup(int[][] puzzle,int x, int y){
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

public class Sudoku{
	final static int GRID_LENGTH = 9;
	static int board[][] = new int[GRID_LENGTH][GRID_LENGTH];
	
	/*main funciton. Driver for the whole program.*/
	public static void main(String[] args){
		GameFrame.displayFrame();
		//Prepare the text fields
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(board[i][j]!=0){
					//set text field at (i, j) as board[i][j]
					getTextField(i, j).setText(board[i][j]);
					//Locking the text field
					getTextField(i, j).setEnabled(false);
				}
			}
		}
	}
	
	/*Method to update game board after a move.*/
	public static void updateBoard(int x, int y, char c){
		//Update board matrix
		if(c == KeyEvent.VK_BACK_SPACE){
			board[y][x] = 0;
		}
		else
			board[y][x] = (int)(c-'0');
		
		//If any rules are violated, warn player by coloring the fault.
		checkMove();
	}
	
	
	/*Method to check legality of move.*/
	/*Decomposes to calls to check row,*/
	/*column and group conditions independently.*/
	/*Then paints whatever is/are wrong.*/
	private static void checkMove(){
		//Erase all highlights.
		Highlight.paintBoardWhite();
		
		//Check and highlight columns.
		checkColumns();
		
		//Check and highlight rows.
		checkRows();
		
		//Check and highight groups.
		checkGroups();
	}
	
	/*Method to check whether there is a repeat in any of the columns.*/
	/*If yes, then the whole column gets highlighted in red.*/
	private static void checkColumns(){
		int i, j;
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(i = 0; i<GRID_LENGTH; i++){
			coveredElements.clear();
			for(j=0; j<GRID_LENGTH; j++){
				if(board[j][i] != 0){
					if(!coveredElements.add(board[j][i])){
						Highlight.paintColumnRed(i);
						break;
					}
				}
			}
		}
	}
	
	/*Method to check whether there is a repeat in any of the rows.*/
	/*If yes, then the whole row gets highlighted in red.*/
	private static void checkRows(){
		int i, j;
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(i = 0; i<GRID_LENGTH; i++){
			coveredElements.clear();
			for(j=0; j<GRID_LENGTH; j++){
				if(board[i][j] != 0){
					if(!coveredElements.add(board[i][j])){
						Highlight.paintRowRed(i);
						break;
					}
				}
			}
		}
	}
	
	/*Method to check whether there is a repeat in any of the 3*3 groups.*/
	/*If yes, then the whole group gets highlighted in red.*/
	private static void checkGroups(){
		int i, j, k, l;
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(i = 0; i<3; i++){
			for(j = 0; j<3; j++){
				coveredElements.clear();
				for(k = i*3; k<i*3+3; k++){
					for(l = j*3; l<j*3+3; l++){
						if(board[k][l] != 0){
							if(!coveredElements.add(board[k][l])){
								Highlight.paintGroupRed(j, i);
								break;
							}
						}
					}
				}
			}
		}
	}
}
