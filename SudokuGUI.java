package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GameFrame{
	static void displayFrame(){
		//Setting up the Frame
		JFrame mainFrame = new JFrame("Sudoku");
		//Setting up main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500,300);
		mainFrame.setLocationRelativeTo(null);
		//Add panels to mainPanel
		mainPanel.add(GamePanel.createGamePanel());
		mainPanel.add(OptionsPanel.createOptionsPanel());
		//Add mainPanel to mainFrame
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}
}

class MyJText extends JText{
	private int posX, posY;
	public void setPos(int x, int y){
		posX=x;
		posY=y;
	}
	public int getX(){
		return posX;
	}
	public int getY(){
		return posY;
	}
}

class GamePanel{
	static JPanel createGamePanel(){
		//Setting up game panel
		//The 9x9 grid is contained here
		JTextField temp;
		JPanel gamePanel = new JPanel(new GridLayout(9,9));
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				temp = new JTextField();
				temp.setPos(j,i);
				temp.setColumns(3);
				temp.addKeyListener(new TextEntryListener());
				gamePanel.add(temp);
			}
		}
		return gamePanel;
	}
}

class OptionsPanel{
	static JPanel createOptionsPanel(){
		//Setting up the options panel
		//Buttons newGame, quit, help contained here
		JPanel optionsPanel = new JPanel(new FlowLayout());
		//Setting up the "New Game" button
		JButton newGame = new JButton("New Game");
		newGame.setSize(100,50);
		newGame.setVisible(true);
		newGame.setActionCommand("new");
		newGame.addActionListener(new ButtonClickListener());
		optionsPanel.add(newGame);
		//Setting up the "Quit Game" button
		JButton quit = new JButton("Quit Game");
		quit.setSize(100,50);
		quit.setVisible(true);
		quit.setActionCommand("quit");
		quit.addActionListener(new ButtonClickListener());
		optionsPanel.add(quit);
		//Setting up the "Help" button
		JButton help = new JButton("Help");
		help.setSize(100,50);
		help.setVisible(true);
		help.setActionCommand("help");
		help.addActionListener(new ButtonClickListener());
		optionsPanel.add(help);
		return optionsPanel;
	}
}

/*class HelpDialog extends JDialog{
	public HelpDialog(JFrame parent){
		super(parent, "Help", true);
		if (parent != null) {
		  Dimension parentSize = parent.getSize(); 
		  Point p = parent.getLocation(); 
		  setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
		}
		JPanel helPanel = new JPanel();
		JLabel rules = new JLabel("")
	}
}*/
