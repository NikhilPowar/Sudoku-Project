package sudoku;

import java.util.*;
import java.awt.event.*;

public class Sudoku{
	final static int GRID_LENGTH = 9;
	static int board[][] = new int[GRID_LENGTH][GRID_LENGTH];
	
	/*main funciton. Driver for the whole program.*/
	public static void main(String[] args){
		GameFrame.displayFrame();
		
	}
	
	/*Method to update game board after a move.*/
	public static void updateBoard(int x, int y, char c){
		//Update board matrix
		if(c == KeyEvent.VK_BACK_SPACE){
			board[y][x] = 0;
			return;
		}
		else
			board[y][x] = (int)(c-'0');
		
		//If a number is entered, check legality of the move.
		checkMove(x, y);
	}
	
	/*Method to check legality of move.*/
	/*Decomposes to calls to check row,*/
	/*column and group conditions independently.*/
	private static void checkMove(int x, int y){
		checkColumn(x);
		checkRow(y);
		checkGroup(x/3, y/3);
	}
	
	/*Method to check whether latest entry is a repeat in the column.*/
	/*If yes, then the whole column gets highlighted.*/
	private static void checkColumn(int x){
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(int i = 0; i<GRID_LENGTH; i++){
			if(board[i][x] != ' '){
				if(!coveredElements.add(board[i][x])){
					//Insert method to highlight incorrect column
					return;
				}
			}
		}
		//Insert method to remove highlight of column
	}
	
	/*Method to check whether latest entry is a repeat in the row.*/
	/*If yes, then the whole row gets highlighted.*/
	private static void checkRow(int y){
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(int i = 0; i < GRID_LENGTH; i++){
			if(board[y][i] != 0){
				if(!coveredElements.add(board[y][i])){
					//Insert method to highlight incorrect row
					return;
				}
			}
		}
		//Insert method to remove highlight of row
	}
	
	/*Method to check whether latest entry is a repeat in the 3*3 group.*/
	/*If yes, then the whole group gets highlighted.*/
	private static void checkGroup(int x, int y){
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(int i = 3*x+0; i < (3*x+3); i++){
			for(int j = 3*y+0; j < (3*y+3); j++){
				if(board[j][i] != 0){
					if(!coveredElements.add(board[x][i])){
						//Insert method to highlight incorrect group
						return;
					}
				}
			}
		}
		//Insert method to remove group highlight
	}
}
