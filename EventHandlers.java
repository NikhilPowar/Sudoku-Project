package sudoku;

import javax.swing.*;
import java.awt.event.*;

class ButtonClickListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand() == "quit"){
			System.exit(0);
		}
		else if(e.getActionCommand() == "help"){
			//Insert code for message box stating rules of the game
		}
		else if(e.getActionCommand() == "new"){
			//Insert code for starting a new game
		}
	}
}

class TextEntryListener implements KeyListener{
	public void keyPressed(KeyEvent e){};
	public void keyReleased(KeyEvent e){};
	public void keyTyped(KeyEvent e){
		char c = e.getKeyChar();
		if((c<'0'||c>'9') && c!=KeyEvent.VK_BACK_SPACE)
			e.consume();
		if(((JTextField)e.getSource()).getText().length()>=1){
			e.consume();
		}
	}
}
