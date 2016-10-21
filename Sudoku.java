import java.util.*;

public class Sudoku{
	final static int GRID_LENGTH = 9;
	static int board[][] = new int[GRID_LENGTH][GRID_LENGTH];
	
	public static void main(String[] args){
		
	}
	
	/*Method to update game board after a move.*/
	public void updateBoard(){
		//Insert method to obtain co-ordinates of latest entry
		checkMove(int x, int y);
	}
	
	/*Method to check legality of move.*/
	/*Decomposes to calls to check row,*/
	/*column and group conditions independently.*/
	public void checkMove(int x, int y){
		checkColumn(x);
		checkRow(y);
		checkGroup(x/3, y/3);
	}
	
	/*Method to check whether latest entry is a repeat in the column.*/
	/*If yes, then the whole column gets highlighted.*/
	public void checkColumn(int x){
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
	public void checkRow(int y){
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(int i = 0; i < GRID_LENGTH; i++){
			if(board[y][i] != ' '){
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
	public void checkGroup(int x, int y){
		Set<Integer> coveredElements = new HashSet<Integer>();
		for(int i = 3*x+0; i < (3*x+3); i++){
			for(int j = 3*y+0; j < (3*y+3); j++){
				if(board[j][i] != ' '){
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
