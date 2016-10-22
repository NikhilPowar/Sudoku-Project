package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/*Class for custom text field. Contains methods*/
/*to set and return textfield positions.*/
class MyJTextField extends JTextField{
	private int posX, posY;
	public MyJTextField(){
		Font font = this.getFont(); 
		this.setFont(font.deriveFont(Font.BOLD));
		setHorizontalAlignment(JTextField.CENTER);
	}
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

/*Class to create main frame for the game to run.*/
/*Creates and adds to itself the game and options panels.*/
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

/*Class to create the main game panel. Creates a 9*9 grid.*/
class GamePanel{
	public final static List<MyJTextField> tfList = new ArrayList<MyJTextField>();
	static JPanel createGamePanel(){
		//Setting up game panel
		//The 9x9 grid is contained here
		MyJTextField temp;
		JPanel gamePanel = new JPanel(new GridLayout(9,9));
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				temp = new MyJTextField();
				temp.setPos(j, i);
				tfList.add(temp);
				temp.addKeyListener(new TextEntryListener());
				gamePanel.add(temp);
			}
		}
		return gamePanel;
	}
	
	/*Method returns the custom textfield object at (x,y)*/
	public static MyJTextField getTextField(int x, int y){
		return tfList.get(y*9 + x);
	}
}

/*Class to create the main options panel of the game*/
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
		
		//Setting up the "Credits" button
		JButton credits = new JButton("Credits");
		credits.setSize(100,50);
		credits.setVisible(true);
		credits.setActionCommand("credits");
		credits.addActionListener(new ButtonClickListener());
		optionsPanel.add(credits);
		
		return optionsPanel;
	}
}

/*Class to create a help dialog with close button and rules.*/
class MyJDialog extends JDialog implements ActionListener{
	public MyJDialog(){
		super((Frame)null, "Help", true);
		
		//Create label
		JLabel helpbox = new JLabel("<html>Rules:<br>1.Enter numbers from 1 to 9 only.<br>2.Every 3*3 grid must have every digit from 1 to 9<br>"
					+"3.Every Row and Column must have 1 to 9 digits</html>");
		helpbox.setFont(new Font("Serif",Font.BOLD,26));
		
		//Create panel
		JPanel helpPanel = new JPanel();
		helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));
	 	helpPanel.add(helpbox);
		
		//Create and add button
		JButton close = new JButton("Close");
		close.setSize(100,50);
		close.setVisible(true);
		close.addActionListener(this);
		helpPanel.add(close);
		
		//Set panel to visible and add to dialog
		helpPanel.setVisible(true);
		add(helpPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/*Method to dispose of the help dialog on close*/
	public void actionPerformed(ActionEvent e){
		this.dispose();
	}
}

class CreditDialog{
	public CreditDialog(){
		super((Frame)null, "Credits", true);
		
		//Create panel
		JPanel creditPanel = new JPanel();
		creditPanel.setLayout(new BoxLayout(creditPanel, BoxLayout.Y_AXIS));
	 	creditPanel.add(creditbox);
		
		//Create and add button
		JButton close = new JButton("Close");
		close.setSize(100,50);
		close.setVisible(true);
		close.addActionListener(this);
		creditPanel.add(close);
	}
	
	/*Method to dispose of the credit dialog on close*/
	public void actionPerformed(ActionEvent e){
		this.dispose();
	}
}

/*Class containing several coloring methods for textfields of a grid.*/
class Highlight{
	/*Method to color all cells in the column red.*/
	public static void paintColumnRed(int x){
		for(int i=0; i<Sudoku.GRID_LENGTH; i++){
			GamePanel.getTextField(x, i).setBackground(Color.RED);
		}
	}
	
	/*Method to color all cells in the row red.*/
	public static void paintRowRed(int y){
		for(int i=0; i<Sudoku.GRID_LENGTH; i++){
			GamePanel.getTextField(i, y).setBackground(Color.RED);
		}
	}
	
	/*Method to color all cells in the group red.*/
	public static void paintGroupRed(int x,int y){
		for(int i=x*3; i<(x*3+3); i++){
			for(int j=y*3; j<(y*3+3); j++){
				GamePanel.getTextField(i, j).setBackground(Color.RED);
			}
		}
	}
	
	/*Method to reset color of entire board to white.*/
	public static void paintBoardWhite(){
		for(int i = 0; i<Sudoku.GRID_LENGTH; i++){
			for(int j = 0; j<Sudoku.GRID_LENGTH; j++){
				GamePanel.getTextField(i,j).setBackground(Color.WHITE);
			}
		}
	}
}
