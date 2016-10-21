package nikhilgui;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;




class GameFrame extends JFrame{
	 GameFrame(){
		//Setting up the Frame
		JFrame mainFrame = new JFrame();
		//Setting up main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500,300);
		mainFrame.setLocationRelativeTo(null);
		//Add panels to mainPanel
		mainPanel.add(GamePanel.createGamePanel());
		mainPanel.add(OptionsPanel.createOptionsPanel());mainPanel.setVisible(true);
		//Add mainPanel to mainFrame
		mainFrame.add(mainPanel);
		mainFrame.setTitle("sudoku");
		mainFrame.setVisible(true);
	}
}
class GamePanel{
	
	static JPanel createGamePanel(){
		//Setting up game panel
		//The 9x9 grid is contained here
		JPanel gamePanel = new JPanel(new GridLayout(9,9));
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				gamePanel.add(new JTextField());gamePanel.setVisible(true);
			}
		}
		return gamePanel;
	} 
}
class OptionsPanel{
	static JPanel createOptionsPanel(){
		//Setting up the options panel
		//Buttons reset, quit, help contained here
		JPanel optionsPanel = new JPanel(new FlowLayout());
		//Setting up the "New Game" button
		JButton newGame = new JButton("New Game");
		newGame.setSize(100,50);
		newGame.setVisible(true);
		optionsPanel.add(newGame);
		//Setting up the "Quit Game" button
		JButton quit = new JButton("Quit Game");
		quit.setSize(100,50);
		quit.setVisible(true);
		optionsPanel.add(quit);
		//Setting up the "Help" button
		JButton help = new JButton("Help");
		help.setSize(100,50);
		help.setVisible(true);
		optionsPanel.add(help);optionsPanel.setVisible(true);
		return optionsPanel;
	}
}
public class nikhilgui {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					new GameFrame();
				}
			});
		

	
	}
}
