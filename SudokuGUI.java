package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyJTextField extends JTextField{
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

class GamePanel{
	static JPanel createGamePanel(){
		//Setting up game panel
		//The 9x9 grid is contained here
		MyJTextField temp;
		JPanel gamePanel = new JPanel(new GridLayout(9,9));
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				temp = new MyJTextField();
				temp.setPos(j, i);
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
		//Buttons reset, quit, help contained here
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
class MyJDialog extends JDialog implements ActionListener{
	public MyJDialog(){
		super((Frame)null, "Help", true);
		JLabel helpbox = new JLabel("<html>Rules:<br>1.Enter numbers from 1 to 9 only.<br>2.Every 3*3 grid must have every digit from 1 to 9<br>"
					+"3.Every Row and Column must have 1 to 9 digits</html>");
		helpbox.setFont(new Font("Serif",Font.BOLD,26));
		JPanel helpPanel = new JPanel();
		helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));
	 	helpPanel.add(helpbox);
		JButton close = new JButton("Close");
		close.setSize(100,50);
		close.setVisible(true);
		close.addActionListener(this);
		helpPanel.add(close);
		helpPanel.setVisible(true);
		add(helpPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		this.dispose();
	}
}
