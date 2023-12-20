import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	static JComboBox questions;
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
	
	//Initiate variables used for the logic of the program
	static boolean gameStarted = false;
	static boolean won = false; 
	
	static String[] questionList = new String[25]; //are there not only 24 questions? i will check again but im sure there are only 24...
	static String selectedQuestion; 
	
	static int aiCards = 24; 
	
	static ArrayList<ImageIcon> images = new ArrayList<ImageIcon>(); 
	
	//Initiate images
	//for these images we're going to need to change it from path name to putting the images in the same file as the program and referring
	//to it from there. this is because mr. a's computer will not have the same path name as us
	static ImageIcon Olivia = new ImageIcon("C:/Files/IMG_3789.jpg");
	static ImageIcon Nick = new ImageIcon("C:/Files/IMG_3792.jpg");
	static ImageIcon David = new ImageIcon("C:/Files/IMG_3781.jpg");
	static ImageIcon Leo = new ImageIcon("C:/Files/IMG_3794.jpg");
	static ImageIcon Emma = new ImageIcon("C:/Files/IMG_3777.jpg");
	static ImageIcon Ben = new ImageIcon("C:/Files/IMG_3778.jpg");
	static ImageIcon Eric = new ImageIcon("C:/Files/IMG_3791.jpg");
	static ImageIcon Rachel = new ImageIcon("C:/Files/IMG_3784.jpg");
	static ImageIcon Amy = new ImageIcon("C:/Files/IMG_3771.jpg");
	static ImageIcon Mike = new ImageIcon("C:/Files/IMG_3779.jpg");
	static ImageIcon Gabe = new ImageIcon("C:/Files/IMG_3786.jpg");
	static ImageIcon Jordan = new ImageIcon("C:/Files/IMG_3785.jpg");
	static ImageIcon Carmen = new ImageIcon("C:/Files/IMG_3790.jpg");
	static ImageIcon Joe = new ImageIcon("C:/Files/IMG_3772.jpg");
	static ImageIcon Mia = new ImageIcon("C:/Files/IMG_3782.jpg");
	static ImageIcon Sam = new ImageIcon("C:/Files/IMG_3774.jpg");
	static ImageIcon Sofia = new ImageIcon("C:/Files/IMG_3783.jpg");
	static ImageIcon Lily = new ImageIcon("C:/Files/IMG_3788.jpg");
	static ImageIcon Daniel = new ImageIcon("C:/Files/IMG_3776.jpg");
	static ImageIcon Al = new ImageIcon("C:/Files/IMG_3787.jpg");
	static ImageIcon Laura = new ImageIcon("C:/Files/IMG_3793.jpg");
	static ImageIcon Liz = new ImageIcon("C:/Files/IMG_3773.jpg");
	static ImageIcon Katie = new ImageIcon("C:/Files/IMG_3775.jpg");
	static ImageIcon Farah = new ImageIcon("C:/Files/IMG_3780.jpg");
	
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
		gamePanel.setBounds(50, 50, 400, 500);
		gamePanel.setVisible(true);
		
		//Set properties for the selection screen
		selection.setBounds(600, 20, 500, 100);
		selection.setFont(font);
		
		//Set properties for the confirm button
		confirm.setBounds(600, 250, 300, 100);
		confirm.addActionListener(new Confirm());
		confirm.setFont(font);
		
		//Set properties for character selection label
		character.setBounds(600, 150, 300, 100);
		character.setFont(font2);
		
		//Set properties for game option menu
		window.add(options);
		options.setBounds(window.getWidth()/2-250, 200, 500, 500);
		options.add(title); 
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
		title.setFont(font);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Set properties for player vs computer button
		options.add(playerComp);
		playerComp.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerComp.addActionListener(new StartPlayerComp()); 
		
		//Set properties for computer vs computer button
		options.add(CompComp);
		CompComp.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Set properties for player vs player button
		options.add(playerPlayer);
		playerPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);		
		
		//Set properties for confirm question button
		confirmQuest.setBounds(500, 200, 150, 50); 
		confirmQuest.setFont(font); 
		confirmQuest.addActionListener(new AskQuestion()); 
		
		//Set properties for computer output text
		computerText.setBounds(500, 50, 500, 50); 
		computerText.setFont(font); 
		
		//Set properties for confirm changes button
		confirmChanges.setBounds(500, 200, 300, 50);
		confirmChanges.setFont(font);
		confirmChanges.addActionListener(new ConfirmChanges()); 
		
		//Set properties for yes button
		yes.setBounds(480, 200, 100, 50); 
		yes.setFont(font);
		yes.addActionListener(new YesButton());
		
		//Set properties for no button
		no.setBounds(580, 200, 100, 50);
		no.setFont(font);
		no.addActionListener(new NoButton());
		
		//Set properties for answer text
		answer.setBounds(500, 400, 300, 50);
		answer.setFont(font);
		
		//Set properties for confirm answer button
		confirmAnswer.setBounds(800, 400, 100, 50); 
		confirmAnswer.setFont(font); 
		
		//Set grid for button
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				
				charButton[i][j] = new JButton(); 
				charButton[i][j].addActionListener(new CharSelection()); 
				gamePanel.add(charButton[i][j]); 
				
			}
		}	
		
		//Set icon image for button
		charButton[0][0].setIcon(Olivia);
		charButton[0][1].setIcon(Nick);
		charButton[0][2].setIcon(David);
		charButton[0][3].setIcon(Leo);
		charButton[1][0].setIcon(Emma);
		charButton[1][1].setIcon(Ben);
		charButton[1][2].setIcon(Eric);
		charButton[1][3].setIcon(Rachel);
		charButton[2][0].setIcon(Amy);
		charButton[2][1].setIcon(Mike);
		charButton[2][2].setIcon(Gabe);
		charButton[2][3].setIcon(Jordan);
		charButton[3][0].setIcon(Carmen);
		charButton[3][1].setIcon(Joe);
		charButton[3][2].setIcon(Mia);
		charButton[3][3].setIcon(Sam);
		charButton[4][0].setIcon(Sofia);
		charButton[4][1].setIcon(Lily);
		charButton[4][2].setIcon(Daniel);
		charButton[4][3].setIcon(Al);
		charButton[5][0].setIcon(Laura);
		charButton[5][1].setIcon(Liz);
		charButton[5][2].setIcon(Katie);
		charButton[5][3].setIcon(Farah);
		
		//Set name for each button
		charButton[0][0].setText("Olivia"); 
		charButton[0][1].setText("Nick"); 
		charButton[0][2].setText("David"); 
		charButton[0][3].setText("Leo"); 
		charButton[1][0].setText("Emma"); 
		charButton[1][1].setText("Ben"); 
		charButton[1][2].setText("Eric"); 
		charButton[1][3].setText("Rachel"); 
		charButton[2][0].setText("Amy"); 
		charButton[2][1].setText("Mike"); 
		charButton[2][2].setText("Gabe"); 
		charButton[2][3].setText("Jordan"); 
		charButton[3][0].setText("Carmen"); 
		charButton[3][1].setText("Joe"); 
		charButton[3][2].setText("Mia"); 
		charButton[3][3].setText("Sam"); 
		charButton[4][0].setText("Sofia"); 
		charButton[4][1].setText("Lily"); 
		charButton[4][2].setText("Daniel"); 
		charButton[4][3].setText("Al"); 
		charButton[5][0].setText("Laura"); 
		charButton[5][1].setText("Liz"); 
		charButton[5][2].setText("Katie"); 
		charButton[5][3].setText("Farah"); 
		
		//Add questions to question bank
		questionList[0] = "Is the eye colour brown?"; 
		questionList[1] = "Is the eye colour green?"; 
		questionList[2] = "Is the eye colour blue?"; 
		questionList[3] = "Is the person a male?"; 
		questionList[4] = "Is the person a female?"; 
		questionList[5] = "Does the person have a light skin tone?"; 
		questionList[6] = "Does this person have a dark skin tone?"; 
		questionList[7] = "Is the hair colour black?"; 
		questionList[8] = "Is the hair colour brown?"; 
		questionList[9] = "Is the hair colour ginger?"; 
		questionList[10] = "Is the hair colour blonde?"; 
		questionList[11] = "Is the hair colour white?"; 
		questionList[12] = "Does the person have facial hair?"; 
		questionList[13] = "Does the pesron have no facial hair?"; 
		questionList[14] = "Is the person wearing glasses?"; 
		questionList[15] = "Is the person not wearing glasses?"; 
		questionList[16] = "Does the person have visible teeth?"; 
		questionList[17] = "Is the person not showing teeth?"; 
		questionList[18] = "Is the person wearing a hat?"; 
		questionList[19] = "Is the person not wearing a hat?"; 
		questionList[20] = "Does the person have short hair?"; 
		questionList[21] = "Does the person have their hair tied up?"; 
		questionList[22] = "Does the person have long hair?"; 
		questionList[23] = "Is the person bald?"; 
		questionList[24] = "Does the person have an ear piercing?"; 

		//Set properties for questions text
		questions = new JComboBox(questionList); 
		questions.setBounds(500, 120, 400, 50); 
		questions.setFont(font);
		
	}
	
	//Implement action for yes button
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
	
	//Implement action for no button
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
	
	//Implement action for confirm changes button
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
	
	//Implement action for character selection button
	static class CharSelection implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			JButton button = (JButton)e.getSource(); 
			
			if (gameStarted == false) {
				character.setText(button.getText());
			}
			else {
				button.setIcon(null);
				button.setBackground(Color.black);
				images.add((ImageIcon) button.getIcon()); 
			}
			
		}
	}
	
	//Implement action for ask questions button
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
	
	//Implement action for confirm button
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
			
			gamePanel.setBounds(20, window.getHeight()/2-300, 400, 500);
			
			selectedQuestion = String.valueOf(questions.getSelectedItem());
			
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					
					charButton[i][j].setEnabled(false); 
					
				}
			}
			
		}
	}
	
	//Implement action for start player vs computer button
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
