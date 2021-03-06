package sudoku;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Sudoku{
	final static int GRID_LENGTH = 9;
	static int board[][] = new int[GRID_LENGTH][GRID_LENGTH];
	
	/*main funciton. Driver for the whole program.*/
	public static void main(String[] args){
		//Create new runnable object for GUI
		Runnable r = new Runnable(){
			public void run(){
				//Create and diplay the game frame
				GameFrame.displayFrame();
				
				//Initially disable textfields
				for(int i = 0; i<GRID_LENGTH; i++){
					for(int j=0; j<GRID_LENGTH; j++){
						GamePanel.getTextField(i, j).setEnabled(false);
					}
				}
				
				//Create the welcome dialog
				new WelcomeDialog();
			}
		};
		
		//Invoke the GUI thread
		SwingUtilities.invokeLater(r);
	}
	
	/*Method to start a new game*/
	public static void startNewGame(){
		//Set initial values of board matrix
		board = SudokuPuzzle.getPuzzle();
		
		//Prepare the text fields
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(board[i][j]!=0){
					//set text field at (i, j) as board[i][j]
					GamePanel.getTextField(j, i).setText(Integer.toString(board[i][j]));
					
					//Locking the text field
					GamePanel.getTextField(j, i).setEnabled(false);
					
					//Changing Font color for locked cell
					GamePanel.getTextField(j, i).setDisabledTextColor(Color.GRAY);
				}
			}
		}
	}
	
	public static void clearBoard(){
		for(int i=0; i<GRID_LENGTH; i++){
			for(int j=0; j<GRID_LENGTH; j++){
				board[i][j]=0;
				GamePanel.getTextField(j, i).setText("");
				GamePanel.getTextField(j, i).setEnabled(true);
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
		
		//Check if victory conditions are satisfied
		if(checkVictory()){
			System.out.println("You have won");
			(new VictoryDialog()).setVisible(true);
			
		}
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
	
	/*Function to check if previous move resulted in a victory*/
	private static boolean checkVictory(){
		for(int i=0; i<GRID_LENGTH; i++){
			for(int j=0; j<GRID_LENGTH; j++){
				if(board[i][j] == 0)
					return false;
			}
		}
		
		//Check column and row conditions
		for(int i=0; i<GRID_LENGTH; i++){
			if(!SudokuPuzzle.checkColumn(board, i) && !SudokuPuzzle.checkRow(board, i))
				return false;
		}
		
		//Check group conditions
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(!SudokuPuzzle.checkGroup(board, i, j))
					return false;
			}
		}
		
		//Return true if all columns, rows and groups are satisfied
		return true;
	}
}
