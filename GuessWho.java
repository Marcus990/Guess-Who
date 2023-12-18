import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GuessWho {
	
	//Initiate GUI variables
	static JFrame window = new JFrame("Window");
	static JPanel gamePanel = new JPanel(); 
	static JPanel options = new JPanel(); 
	static JButton charButton[][] = new JButton[6][4]; 
	static JButton playerComp = new JButton("Player vs Computer");
	static JButton playerPlayer = new JButton("Player vs Player");
	static JButton CompComp = new JButton("Computer vs Computer"); 
	static JButton confirm = new JButton("Confirm"); 
	static JButton confirmQuest = new JButton("Confirm"); 
	static JButton confirmChanges = new JButton("Confirm changes"); 
	static JButton confirmAnswer = new JButton("Confirm answer"); 
	static JButton yes = new JButton("Yes");
	static JButton no = new JButton("No"); 
	static JLabel title = new JLabel("Choose the game mode"); 
	static JLabel selection = new JLabel("Choose your character");
	static JLabel character = new JLabel("N/A"); 
	static JLabel computerText = new JLabel("Your opponent is waiting for your question...");
	static JTextArea questions = new JTextArea("Insert questions here"); 
	static JTextArea answer = new JTextArea("Insert your answer here"); 
	
	/*
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	static Character Olivia = new Character(); 
	*/
	
	//Initiate various fonts
	static Font font = new Font("Size", Font.BOLD , 20);
	static Font font2 = new Font("Character", Font.BOLD, 50); 
	
	//Initiate standard variables
	static String testing = new String("abcdfeghijklmnopqrstuvwxyz"); 
	static boolean gameStarted = false;
	static boolean won = false; 
	
	//Main method
	public static void main(String[] args) {
		
		//Set properties for the game window
		window.setSize(1000, 700);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLayout(null);
		window.setResizable(false);
		
		/*
		startButton.setBounds(window.getWidth()/2-50, window.getHeight()/2-10, 100, 20);
		startButton.setText("Start Game");
		startButton.addActionListener(new StartGame());
		window.add(startButton); 
		*/
		
		//Set properties for the game panel
		gamePanel.setLayout(new GridLayout(4, 6));
		gamePanel.setBounds(50, 50, 500, 350);
		gamePanel.setVisible(true);
		
		//Set properties for the selection screen
		selection.setBounds(600, 20, 500, 100);
		selection.setFont(font);
		
		//Set properties for the confirm button
		confirm.setBounds(600, 250, 300, 100);
		confirm.addActionListener(new Confirm());
		confirm.setFont(font);
		
		character.setBounds(600, 150, 300, 100);
		character.setFont(font2);
		
		window.add(options);
		options.setBounds(window.getWidth()/2-250, 200, 500, 500);
		options.add(title); 
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
		title.setFont(font);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		options.add(playerComp);
		playerComp.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerComp.addActionListener(new StartPlayerComp()); 
		
		options.add(CompComp);
		CompComp.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		options.add(playerPlayer);
		playerPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);		
		
		questions.setBounds(500, 120, 300, 50); 
		questions.setFont(font);
		
		confirmQuest.setBounds(500, 200, 150, 50); 
		confirmQuest.setFont(font); 
		confirmQuest.addActionListener(new AskQuestion()); 
		
		computerText.setBounds(500, 50, 500, 50); 
		computerText.setFont(font); 
		
		confirmChanges.setBounds(500, 200, 300, 50);
		confirmChanges.setFont(font);
		confirmChanges.addActionListener(new ConfirmChanges()); 
		
		yes.setBounds(480, 200, 100, 50); 
		yes.setFont(font);
		yes.addActionListener(new YesButton());
		
		no.setBounds(580, 200, 100, 50);
		no.setFont(font);
		no.addActionListener(new NoButton());
		
		answer.setBounds(20, 400, 300, 50);
		answer.setFont(font);
		
		confirmAnswer.setBounds(350, 400, 200, 50); 
		confirmAnswer.setFont(font); 
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				
				charButton[i][j] = new JButton(); 
				charButton[i][j].setText(testing.substring(i, i+1)); 
				charButton[i][j].addActionListener(new CharSelection()); 
				gamePanel.add(charButton[i][j]); 
				
			}
		}	
	}
	
	static class YesButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int ranNum = (int)(Math.random()*(10000)); 
			try {Thread.sleep(ranNum);} catch (InterruptedException e1) {e1.printStackTrace();}

			window.remove(yes);
			window.remove(no);
			window.add(questions);
			window.add(confirmQuest); 
			window.setSize(999, 700);
			window.setSize(1000, 700);
			computerText.setText("Your opponent is waiting for your question...");
			
		}
	}
	
	static class NoButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int ranNum = (int)(Math.random()*(10000)); 
			try {Thread.sleep(ranNum);} catch (InterruptedException e1) {e1.printStackTrace();}

			window.remove(yes);
			window.remove(no);
			window.add(questions);
			window.add(confirmQuest);
			window.setSize(999, 700);
			window.setSize(1000, 700);
			computerText.setText("Your opponent is waiting for your quesiton...");
			
		}
	}
	
	static class ConfirmChanges implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			

			window.remove(confirmChanges);
			window.setSize(999, 700);
			window.setSize(1000, 700);
			int ranNum = (int)(Math.random()*(10000)); 
			computerText.setText("Your opponent is coming up with a question...");
			try {Thread.sleep(ranNum);} catch (InterruptedException e1) {e1.printStackTrace();}
			computerText.setText("What is your mom?");
			window.add(no);
			window.add(yes);
			
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					
					charButton[i][j].setEnabled(false); 
					
				}
			}			
		}
	}
	
	static class CharSelection implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			JButton button = (JButton)e.getSource(); 
			
			if (gameStarted == false) {
				character.setText(button.getText());
			}
			else
				button.setBackground(Color.black);
			
		}
	}
	
	static class AskQuestion implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int ranNum = (int)(Math.random()*(10000)); 
			window.remove(questions);
			window.remove(confirmQuest);
			window.setSize(999, 700);
			window.setSize(1000, 700);
			
			try {Thread.sleep(ranNum);} catch (InterruptedException e1) {e1.printStackTrace();}
			
			computerText.setText("Your opponent has answered YES");
			window.add(confirmChanges); 
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					
					charButton[i][j].setEnabled(true); 
					
				}
			}
		}
	}
	
	static class Confirm implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			gameStarted = true; 
			
			window.remove(selection);
			window.remove(confirm);
			window.remove(character);
			window.add(questions);
			window.add(confirmQuest); 
			window.add(computerText); 
			window.add(answer);
			window.add(confirmAnswer); 
			window.setSize(999, 700);
			window.setSize(1000, 700);
			
			gamePanel.setBounds(20, window.getHeight()/2-300, 450, 300);
			
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					
					charButton[i][j].setEnabled(false); 
					
				}
			}
			
		}
	}
	
	static class StartPlayerComp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
			window.remove(options);
			window.add(gamePanel); 
			window.add(selection); 
			window.add(confirm); 
			window.add(character); 
			window.setSize(999, 700);
			window.setSize(1000, 700);
			
		}
	}
}
