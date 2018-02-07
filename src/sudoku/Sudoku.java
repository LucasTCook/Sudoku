package sudoku;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Random;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class Sudoku extends JFrame {

	private static final long serialVersionUID = -2381667588833375032L;

	
	private JPanel contentPane;
	private JFormattedTextField[][] fields;						// Text fields to hold Sudoku values
	private int[][] puzzle;										// Array Representation of the Sudoku problem
	private boolean[][] show = new boolean[9][9];				// Array Representation of shown and to be filled , if false then need to fill
	
	NumberFormat format = NumberFormat.getInstance();			// format and formatter are used to restrict		
	NumberFormatter formatter = new NumberFormatter(format);	// user input to valid numbers range

	
	/*
	 * Constructor
	 */
	public Sudoku() {
		
		setTitle("Sudoku");

		/*
		 * Setting formatter to accept only numbers from 1 to 9.
		 */
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1);
		formatter.setMaximum(9);
		formatter.setAllowsInvalid(false);

		resetShow();		// This method called to generate the blanks

		generateBoard();	// This method is called to generate Sudoku array.

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(36, 35, 120, 120);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 3, 0, 0));

		
		/*
		 * Button - new game
		 * it invokes new game method.
		 */
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showNewGame();
			}
		});		
		btnNewButton.setBounds(36, 428, 99, 23);
		contentPane.add(btnNewButton);

		/*
		 * Button - solve
		 * It will populate all values from Sudoku representation array (puzzle)
		 * to text fields and mark all fields in blue color
		 * which represents , it has done using program not manually.
		 */
		JButton btnNewButton_1 = new JButton("Solve");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int x = 0; x < 9; x++) {
					for (int y = 0; y < 9; y++) {
						fields[x][y].setText(puzzle[x][y] + "");
						fields[x][y].disable();
						fields[x][y].setBackground(Color.BLUE);
					}
				}
			}
		});
		btnNewButton_1.setBounds(315, 428, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(160, 36, 120, 120);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(284, 36, 120, 120);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(36, 160, 120, 120);
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(36, 285, 120, 120);
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(160, 160, 120, 120);
		contentPane.add(panel_5);
		panel_5.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(160, 285, 120, 120);
		contentPane.add(panel_6);
		panel_6.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(284, 160, 120, 120);
		contentPane.add(panel_7);
		panel_7.setLayout(new GridLayout(3, 3, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(284, 285, 120, 120);
		contentPane.add(panel_8);
		panel_8.setLayout(new GridLayout(3, 3, 0, 0));
		
		
		//Initialized the text fields
		fields = new JFormattedTextField[9][9];

		/*
		 * Button - solve
		 * It will populate all values from Sudoku representation array (puzzle)
		 * to text fields and mark all fields in blue color
		 * which represents , it has done using program not manually.
		 */
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				fields[x][y] = new JFormattedTextField(formatter) {

					protected void invalidEdit() {
						setValue(null);
					}
				};
				fields[x][y].setHorizontalAlignment(JTextField.CENTER);
				fields[x][y].setForeground(new Color(0, 0, 0));
				fields[x][y].setFont(new Font("Tahoma", Font.BOLD, 14));
				
				if(y<3){
					if(x < 3)
						panel.add(fields[x][y]);
					else if(x<6)
						panel_3.add(fields[x][y]);
					else
						panel_4.add(fields[x][y]);
				}else if(y<6){
					if(x < 3)
						panel_1.add(fields[x][y]);
					else if(x<6)
						panel_5.add(fields[x][y]);
					else
						panel_6.add(fields[x][y]);
				}else{
					if(x < 3)
						panel_2.add(fields[x][y]);
					else if(x<6)
						panel_7.add(fields[x][y]);
					else
						panel_8.add(fields[x][y]);
				}
				
				fields[x][y].setColumns(5);
				if (show[x][y]) {
					fields[x][y].setText(puzzle[x][y] + "");
					fields[x][y].enable(false);
				}

				/*
				 * Add listener to track user inputs.
				 * If typed value is correct it will highlight in green otherwise read. (except in case of advance mode)
				 * It also check whether the all blanks are filled correctly
				 * If so inform the user that he finished, and ask for new game.
				 */
				fields[x][y].addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {

						JFormattedTextField source = (JFormattedTextField) e.getSource();

						for (int x = 0; x < 9; ++x) {
							for (int y = 0; y < 9; ++y) {
								if (fields[x][y] == source) {
									if (fields[x][y].getText().equals(puzzle[x][y] + "")) {
										fields[x][y].setBackground(Color.GREEN);
									} else if (fields[x][y].getText() == null || fields[x][y].getText().length() == 0) {
										fields[x][y].setBackground(Color.WHITE);
									} else {
										fields[x][y].setBackground(Color.RED);
									}
									break;
								}
							}
						}
						if (finish()) {

							final JOptionPane optionPane = new JOptionPane("You Won!", JOptionPane.QUESTION_MESSAGE,
									JOptionPane.YES_NO_OPTION);

							int x = optionPane.showConfirmDialog(null, "Would you like to start a new game?");

							if (x == JOptionPane.YES_OPTION) {
								showNewGame();
							} else {
								System.exit(0);
							}

						}
					}
				});

			}
		}
	}

	
	/*
	 * A helper method to decide whether all blanks have filled correctly.
	 */
	public boolean finish() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (!fields[x][y].getText().equals(puzzle[x][y] + "")) {
					return false;
				}
				;
			}
		}
		return true;
	}

	/*
	 * Initiate a new game
	 * Prompt user to select level
	 */
	public void showNewGame() {
		String[] level = { "Beginner", "Intermidiate","Advanced" };

		String lvl = (String) JOptionPane.showInputDialog(this, "Please Chose level ", "Level",
				JOptionPane.QUESTION_MESSAGE, null, level, level[0]);
		newGame(lvl);
	}

	
	/*
	 * A helper method, resetting the blank metrics
	 */
	public void resetShow(){
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				show[x][y] = true;
			}
		}
	}
	
	/*
	 * This method is called when user request a new game, 
	 * It will reset required components.
	 * It follows the same steps followed in constructor.
	 */
	public void newGame(String lvl) {

		resetShow();
				
		generateBoard();
		makeFillings(lvl);
		
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				fields[x][y].setText("");
				fields[x][y].setBackground(Color.WHITE);
				if (show[x][y]) {
					fields[x][y].setText(puzzle[x][y] + "");
					fields[x][y].enable(false);
				} else {
					fields[x][y].enable();
				}

				if (lvl.equals("Beginner") ||lvl.equals("Intermidiate") ) {
					fields[x][y].addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent e) {

							JFormattedTextField source = (JFormattedTextField) e.getSource();

							for (int x = 0; x < 9; ++x) {

								for (int y = 0; y < 9; ++y) {

									if (fields[x][y] == source) {
										if (fields[x][y].getText().equals(puzzle[x][y] + "")) {
											fields[x][y].setBackground(Color.GREEN);
										} else if (fields[x][y].getText() == null
												|| fields[x][y].getText().length() == 0) {
											fields[x][y].setBackground(Color.WHITE);
										} else {
											fields[x][y].setBackground(Color.RED);
										}
										break;
									}
								}
							}
						}
					});
				} else {
					fields[x][y].addKeyListener(new KeyAdapter() {
						@Override
						public void keyTyped(KeyEvent e) {
							JFormattedTextField source = (JFormattedTextField) e.getSource();

							for (int x = 0; x < 9; ++x) {

								for (int y = 0; y < 9; ++y) {

									if (fields[x][y] == source) {
										fields[x][y].setBackground(Color.WHITE);
									}
								}
							}
						}
					});
				}

			}
		}
	}

	
	/*
	 * When program starts this method call to populate first Sudoku puzzle and it is in beginner level.
	 */
	public void generateBoard() {
		puzzle = new int[9][9];
		tryValue(0, 0);
		makeFillings("Beginner"); 
	}

	
	/*
	 * This method select avail value and pass to validate check 
	 */
	public boolean tryValue(int x, int y) {
		int nextX = x;
		int nextY = y;
		int[] valid = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random r = new Random();
		int tmp = 0;
		int current = 0;
		int top = valid.length;

		for (int i = top - 1; i > 0; i--) {
			current = r.nextInt(i);
			tmp = valid[current];
			valid[current] = valid[i];
			valid[i] = tmp;
		}

		for (int i = 0; i < valid.length; i++) {
			if (valid(x, y, valid[i])) {
				puzzle[x][y] = valid[i];
				if (x == 8) {
					if (y == 8)
						return true;
					else {
						nextX = 0;
						nextY = y + 1;
					}
				} else {
					nextX = x + 1;
				}
				if (tryValue(nextX, nextY))
					return true;
			}
		}
		puzzle[x][y] = 0;
		return false;
	}

	
	
	/*
	 * An important method, which validates the random value picked match the requirement.
	 * for example if we pass 3, it will check associated row and column;
	 */
	private boolean valid(int x, int y, int current) {
		
		
		for (int i = 0; i < 9; i++) {
			if (current == puzzle[x][i])
				return false;
		}
		
		
		for (int i = 0; i < 9; i++) {
			if (current == puzzle[i][y])
				return false;
		}
		
		
		int cX = 0;
		int cY = 0;
		if (x > 2)
			if (x > 5)
				cX = 6;
			else
				cX = 3;
		if (y > 2)
			if (y > 5)
				cY = 6;
			else
				cY = 3;
		
		for (int i = cX; i < 10 && i < cX + 3; i++)
			for (int j = cY; j < 10 && j < cY + 3; j++)
				if (current == puzzle[i][j])
					return false;
		return true;
	}
	
	/*
	 * Once the sudoku array is build 
	 * this method is use to determine the blanks.
	 */
	public void makeFillings(String level) {
		
		double toFill = 0;	
		
		switch(level){
			case "Beginner"		: {toFill = Math.random() * 15 + 5;}break;
			case "Intermidiate"	: {toFill = Math.random() * 15 + 15;}break;
			case "Advanced"		: {toFill = Math.random() * 20 + 30;}break;
			default 			: {toFill = Math.random() * 15 + 5;}break;
		}
		
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				if (Math.random() <= (toFill / 81)) {
					show[i][j] = false;
				}
			}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudoku frame = new Sudoku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
