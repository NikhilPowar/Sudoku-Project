package sudoku;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class ButtonClickListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand() == "quit"){
			System.exit(0);
		}
		else if(e.getActionCommand() == "help"){
			MyJDialog helpDialog = new MyJDialog();
		}
		else if(e.getActionCommand() == "new"){
			Sudoku.clearBoard();
			Sudoku.startNewGame();
		}
		else if(e.getActionCommand() == "credits"){
			CreditDialog creditDialog = new CreditDialog();
		}
	}
}

class TextEntryListener implements KeyListener{
	public void keyPressed(KeyEvent e){};
	public void keyReleased(KeyEvent e){};
	public void keyTyped(KeyEvent e){
		//Check whether entry was a valid digit
		char c = e.getKeyChar();
		if((c<'1'||c>'9') && c!=KeyEvent.VK_BACK_SPACE){
			e.consume();
			return;
		}
		else if(((JTextField)e.getSource()).getText().length()>=1){
			e.consume();
			return;
		}
		
		//find co-ordinates of source cell and update the board
		Sudoku.updateBoard(((MyJTextField)e.getSource()).getX(), ((MyJTextField)e.getSource()).getY(), c);
	}
}

class GameFocusListener implements WindowFocusListener{
	public void windowLostFocus(WindowEvent e){};
	public void windowGainedFocus(WindowEvent e){
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(Sudoku.board[j][i] == 0){
					GamePanel.getTextField(i, j).setText(" ");
					GamePanel.getTextField(i, j).setText("");
				}
				else{
					GamePanel.getTextField(i, j).setText(""+Sudoku.board[j][i]);
				}
			}
		}
	}
}
