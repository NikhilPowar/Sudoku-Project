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
      JLabel helpbox = new JLabel("<html>1.Enter numbers from 1 to 9 only.<br>2.Every 3*3 grid must have every digit from1 to 9<br>"
					+"3.Every Row and Column must have 1 to 9 digits<html>");
			helpbox.setFont(new Font("Serif",Font.BOLD,26));
	 		JFrame helpFrame=new JFrame();
	 		helpFrame.add(helpbox);
	 		helpFrame.setVisible(true);
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
