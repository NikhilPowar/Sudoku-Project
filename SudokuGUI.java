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
		this.setFont(font.deriveFont(Font.BOLD, 20));
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
		mainFrame.setSize(new Dimension(410,450));
		
		//Add panels to mainPanel
		mainPanel.add(GamePanel.createGamePanel());
		mainPanel.add(OptionsPanel.createOptionsPanel());
		
		//Add mainPanel to mainFrame
		mainFrame.add(mainPanel);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		
		//Add a window action listener
		mainFrame.addWindowFocusListener(new GameFocusListener());
	}
}

/*Class to create the main game panel. Creates a 9*9 grid.*/
class GamePanel{
	public final static List<MyJTextField> tfList = new ArrayList<MyJTextField>();
	static JPanel createGamePanel(){
		//Setting up game panel
		//The 9 3x3 grids are contained here
		MyJTextField temp;
		JPanel gamePanel = new JPanel(new GridLayout(3, 3, 5, 5));
		gamePanel.setPreferredSize(new Dimension(400,400));
		//Create 9 3x3 grids for groups
		//Creating grids
		JPanel[][] grids = new JPanel[3][3];
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				grids[i][j] = new JPanel(new GridLayout(3, 3));
				grids[i][j].setPreferredSize(new Dimension(130,130));
				
				//Set background color to black
				grids[i][j].setBackground(Color.BLACK);
			}
		}
		//Assigning textboxes to respective grids
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				temp = new MyJTextField();
				temp.setPos(j, i);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				tfList.add(temp);
				temp.addKeyListener(new TextEntryListener());
				grids[i/3][j/3].add(temp);
			}
		}
		//Assigning grids to main gamePanel
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				gamePanel.add(grids[i][j]);
			}
		}
		
		//Set background color to black
		gamePanel.setBackground(Color.BLACK);
		
		//Set Border to black
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		//return Game Panel
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
		newGame.setVisible(true);
		newGame.setActionCommand("new");
		newGame.addActionListener(new ButtonClickListener());
		optionsPanel.add(newGame);
		
		//Setting up the "Quit Game" button
		JButton quit = new JButton("Quit Game");
		quit.setVisible(true);
		quit.setActionCommand("quit");
		quit.addActionListener(new ButtonClickListener());
		optionsPanel.add(quit);
		
		//Setting up the "Help" button
		JButton help = new JButton("Help");
		help.setVisible(true);
		help.setActionCommand("help");
		help.addActionListener(new ButtonClickListener());
		optionsPanel.add(help);
		
		//Setting up the "Credits" button
		JButton credits = new JButton("Credits");
		credits.setVisible(true);
		credits.setActionCommand("credits");
		credits.addActionListener(new ButtonClickListener());
		optionsPanel.add(credits);
		
		return optionsPanel;
	}
}

/*Class to create a Welcome dialog with newGame button*/
class WelcomeDialog extends JDialog implements ActionListener{
	public WelcomeDialog(){
		super((Frame)null, "Welcome!", true);
		setSize(200,100);
		
		//Create label
		JLabel welcome = new JLabel("Welcome to sudoku!!!");
		welcome.setFont(new Font("Calibri", Font.BOLD, 16));
		
		//Create panel
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(new BorderLayout());
	 	welcomePanel.add(welcome, BorderLayout.CENTER);
		
		//Create and add button
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(new ButtonClickListener());
		newGame.setActionCommand("new");
		newGame.setVisible(true);
		newGame.addActionListener(this);
		welcomePanel.add(newGame, BorderLayout.PAGE_END);
		
		//Set panel to visible and add to dialog
		welcomePanel.setVisible(true);
		add(welcomePanel);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/*Method to dispose of the help dialog on close*/
	public void actionPerformed(ActionEvent e){
		this.dispose();
	}
}

/*Class to create a help dialog with close button and rules.*/
class MyJDialog extends JDialog implements ActionListener{
	public MyJDialog(){
		super((Frame)null, "Help", true);
		setSize(250,200);
		
		//Create label
		JLabel helpbox = new JLabel("<html><div style='text-align: center;'>Rules:<br>1.Enter numbers from 1 to 9 only.<br>2.Every 3*3 grid must have every digit from 1 to 9<br>"
					+"3.Every Row and Column must have 1 to 9 digits</html>");
		helpbox.setFont(new Font("Calibri",Font.BOLD,16));
		
		//Create panel
		JPanel helpPanel = new JPanel();
		helpPanel.setLayout(new BorderLayout());
	 	helpPanel.add(helpbox, BorderLayout.CENTER);
		
		//Create and add button
		JButton close = new JButton("Close");
		close.setAlignmentX(CENTER_ALIGNMENT);
		close.setVisible(true);
		close.addActionListener(this);
		helpPanel.add(close, BorderLayout.PAGE_END);
		
		//Set panel to visible and add to dialog
		helpPanel.setVisible(true);
		add(helpPanel);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/*Method to dispose of the help dialog on close*/
	public void actionPerformed(ActionEvent e){
		this.dispose();
	}
}

class CreditDialog extends JDialog implements ActionListener{
	public CreditDialog(){
		super((Frame)null, "Credits", true);
		setSize(150,200);
		
		//Create Label
		JLabel creditbox = new JLabel("<html><div style='text-align: center;'>Created by:-<br>Akhil Powar<br>Nikhil Powar<br>Chethan Kille<br>Harsh Kansara</html>");
		creditbox.setFont(new Font("Calibri",Font.BOLD,20));
		
		//Create panel
		JPanel creditPanel = new JPanel();
		creditPanel.setLayout(new BorderLayout());
	 	creditPanel.add(creditbox, BorderLayout.CENTER);
		
		//Create and add button
		JButton close = new JButton("Close");
		close.setAlignmentX(CENTER_ALIGNMENT);
		close.setVisible(true);
		close.addActionListener(this);
		creditPanel.add(close, BorderLayout.PAGE_END);
		
		//Set credit panel to visible and add to dialog
		creditPanel.setVisible(true);
		add(creditPanel);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/*Method to dispose of the credit dialog on close*/
	public void actionPerformed(ActionEvent e){
		this.dispose();
	}
}

class VictoryDialog extends JDialog implements ActionListener{
	public VictoryDialog(){
		super((Frame)null, "Victory!", true);
		setLayout(new BorderLayout());
		
		//Create quit button
		JButton quit = new JButton("Quit");
		quit.setSize(100, 50);
		quit.addActionListener(new ButtonClickListener());
		quit.setActionCommand("quit");
		quit.setVisible(true);
		
		//Create new button
		JButton newGame = new JButton("New Game");
		newGame.setSize(100, 50);
		newGame.addActionListener(new ButtonClickListener());
		newGame.setActionCommand("new");
		newGame.addActionListener(this);
		newGame.setVisible(true);
		
		//Create and add new jlabel with victory message
		JLabel victoryLabel = new JLabel("Congratulations! You win!");
		victoryLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		victoryLabel.setVisible(true);
		add(victoryLabel, BorderLayout.CENTER);
		
		//Create and add new options panel
		JPanel victoryOptionsPanel = new JPanel();
		victoryOptionsPanel.setLayout(new FlowLayout());
		victoryOptionsPanel.setVisible(true);
		victoryOptionsPanel.add(quit);
		victoryOptionsPanel.add(newGame);
		add(victoryOptionsPanel, BorderLayout.PAGE_END);
		
		pack();
		setLocationRelativeTo(null);
	}
	
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
