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
import javax.swing.Icon;
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
	static JPanel winLoseScreen = new JPanel(); 
	static JButton charButton[][] = new JButton[4][6]; 
	static JButton playerComp = new JButton("Player vs Computer");
	static JButton playerPlayer = new JButton("Player vs Player");
	static JButton CompComp = new JButton("Computer vs Computer"); 
	static JButton confirm = new JButton("Confirm"); 
	static JButton confirmQuest = new JButton("Confirm"); 
	static JButton confirmChanges = new JButton("Confirm changes"); 
	static JButton confirmAnswer = new JButton("Confirm answer"); 
	static JButton yes = new JButton("Yes");
	static JButton no = new JButton("No"); 
	static JButton restart = new JButton("Restart"); 
	static JLabel winLose = new JLabel(); 
	static JLabel title = new JLabel("Choose the game mode"); 
	static JLabel selection = new JLabel("Choose your character");
	static JLabel character = new JLabel("N/A"); 
	static JLabel computerText = new JLabel("Your opponent is waiting for your question...");
	static JLabel compCards = new JLabel("Your opponent has flipped 0 cards..."); 
	static JLabel playerGUI = new JLabel(); 
	static JComboBox questions;
	static JTextArea answer = new JTextArea("Insert your answer here"); 
	
	static Characters[][] chars = new Characters[4][6];
	static Characters compChar; 
	static Characters playerChar; 

	//Set background photo
	window.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("OfficialPlaidBackground.png")))));
	
	//Initiate various fonts
	static Font font = new Font("Size", Font.BOLD , 20);
	static Font font2 = new Font("Character", Font.BOLD, 50); 
	
	//Initiate standard variables
	static boolean[][] aiChars = new boolean[4][6]; 
	static boolean gameStarted = false;
	static boolean won = false; 
	static boolean lying = false; 
	
	static String[] questionList = new String[25]; 
	static String selectedQuestion; 
	static String aiSelectedQuestion; 
	
	static int aiCards = 24; 
	
	static ArrayList<ImageIcon> images = new ArrayList<ImageIcon>(); 
	
	//Initiate images
	static ImageIcon Olivia = new ImageIcon("C:/Files/IMG_3789.jpg");
	static ImageIcon Nick = new ImageIcon("C:/Files/IMG_3792.jpg");
	static ImageIcon David = new ImageIcon("C:/Files/IMG_3781.jpg");
	static ImageIcon Leo = new ImageIcon("IMG_3794.jpg");
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
		

		
		//Set properties for the computer flipped cards
		compCards.setBounds(500, 330, 400, 30);
		compCards.setFont(font);
		
		//Set properties for the win/lose screen
		winLoseScreen.setLayout(new BoxLayout(winLoseScreen, BoxLayout.Y_AXIS));
		winLoseScreen.setBounds(window.getWidth()/2-250, 200, 500, 500);
		winLoseScreen.add(winLose);
		winLoseScreen.add(restart); 
		winLose.setFont(font);
		winLose.setSize(500, 50);
		restart.setFont(font); 
		restart.addActionListener(new Restart());
		
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
		confirmAnswer.addActionListener(new ConfirmAnswer()); 
		
		//Set grid for button
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				
				charButton[i][j] = new JButton(); 
				charButton[i][j].addActionListener(new CharSelection()); 
				gamePanel.add(charButton[i][j]); 
				
			}
		}	
		
		//Initialize all characters and attributes; 
		chars[0][0] = new Characters("Olivia", "Brown", false, "Dark Skin", "Black", false, false, false, false, "Tied", false); 
	    chars[1][0] = new Characters("Nick", "Brown", true, "Light Skin", "Blonde", false, false, false, false, "Short", true); 
	    chars[2][0] = new Characters("David", "Brown", true, "Light Skin", "Blonde", true, false, true, true, "Short", false); 
	    chars[3][0] = new Characters("Leo", "Brown", true, "Dark Skin", "White", true, false, true, false, "Short", false); 
	    chars[0][1] = new Characters("Emma", "Brown", false, "Light Skin", "Ginger", false, false, false, false, "Tied", false); 
	    chars[1][1] = new Characters("Ben", "Brown", true, "Light Skin", "Brown", false, true, false, false, "Short", true); 
	    chars[2][1] = new Characters("Eric", "Blue", true, "Light Skin", "Black", false, false, false, false, "Short", false); 
	    chars[3][1] = new Characters("Rachel", "Blue", false, "Light Skin", "Brown", false, true, false, false, "Long", true); 
	    chars[0][2] = new Characters("Amy", "Brown", false, "Light Skin", "Black", false, true, false, false, "Short", false); 
	    chars[1][2] = new Characters("Mike", "Brown", true, "Light Skin", "Black", false, false, true, true, "Short", false); 
	    chars[2][2] = new Characters("Gabe", "Brown", true, "Dark Skin", "Black", false, false, false, false, "Short", false); 
	    chars[3][2] = new Characters("Jordan", "Brown", true, "Dark Skin", "Black", true, false, false, false, "Short", true); 
	    chars[0][3] = new Characters("Carmen", "Brown", false, "Dark Skin", "White", false, false, true, false, "Short", true); 
	    chars[1][3] = new Characters("Joe", "Brown", true, "Dark Skin", "White", true, true, true, false, "Bald", false); 
	    chars[2][3] = new Characters("Mia", "Brown", false, "Dark Skin", "Black", false, false, true, false, "Long", false); 
	    chars[3][3] = new Characters("Sam", "Green", true, "Dark Skin", "Black", false, false, false, true, "Short", false); 
	    chars[0][4] = new Characters("Sofia", "Green", false, "Dark Skin", "Brown", false, false, true, false, "Short", true); 
	    chars[1][4] = new Characters("Lily", "Green", false, "Dark Skin", "Brown", false, false, true, true, "Long", false); 
	    chars[2][4] = new Characters("Daniel", "Green", true, "Light Skin", "Ginger", true, false, false, false, "Tied", false); 
	    chars[3][4] = new Characters("Al", "Green", true, "Dark Skin", "White", true, true, false, false, "Bald", false); 
	    chars[0][5] = new Characters("Laura", "Green", false, "Dark Skin", "Black", false, false, true, false, "Long", true); 
	    chars[1][5] = new Characters("Liz", "Blue", false, "Light Skin", "White", false, true, true, false, "Long", false); 
	    chars[2][5] = new Characters("Katie", "Blue", false, "Light Skin", "Blonde", false, false, false, true, "Tied", false); 
	    chars[3][5] = new Characters("Farah", "Blue", false, "Dark Skin", "Black", false, false, false, false, "Tied", false); 
		
		//Set icon image for button
		charButton[0][0].setIcon(Olivia);
		charButton[1][0].setIcon(Nick);
		charButton[2][0].setIcon(David);
		charButton[3][0].setIcon(Leo);
		charButton[0][1].setIcon(Emma);
		charButton[1][1].setIcon(Ben);
		charButton[2][1].setIcon(Eric);
		charButton[3][1].setIcon(Rachel);
		charButton[0][2].setIcon(Amy);
		charButton[1][2].setIcon(Mike);
		charButton[2][2].setIcon(Gabe);
		charButton[3][2].setIcon(Jordan);
		charButton[0][3].setIcon(Carmen);
		charButton[1][3].setIcon(Joe);
		charButton[2][3].setIcon(Mia);
		charButton[3][3].setIcon(Sam);
		charButton[0][4].setIcon(Sofia);
		charButton[1][4].setIcon(Lily);
		charButton[2][4].setIcon(Daniel);
		charButton[3][4].setIcon(Al);
		charButton[0][5].setIcon(Laura);
		charButton[1][5].setIcon(Liz);
		charButton[2][5].setIcon(Katie);
		charButton[3][5].setIcon(Farah);
	
		//Set name for each button
		charButton[0][0].setText("Olivia"); 
		charButton[1][0].setText("Nick"); 
		charButton[2][0].setText("David"); 
		charButton[3][0].setText("Leo"); 
		charButton[0][1].setText("Emma"); 
		charButton[1][1].setText("Ben"); 
		charButton[2][1].setText("Eric"); 
		charButton[3][1].setText("Rachel"); 
		charButton[0][2].setText("Amy"); 
		charButton[1][2].setText("Mike"); 
		charButton[2][2].setText("Gabe"); 
		charButton[3][2].setText("Jordan"); 
		charButton[0][3].setText("Carmen"); 
		charButton[1][3].setText("Joe"); 
		charButton[2][3].setText("Mia"); 
		charButton[3][3].setText("Sam"); 
		charButton[0][4].setText("Sofia"); 
		charButton[1][4].setText("Lily"); 
		charButton[2][4].setText("Daniel"); 
		charButton[3][4].setText("Al"); 
		charButton[0][5].setText("Laura"); 
		charButton[1][5].setText("Liz"); 
		charButton[2][5].setText("Katie"); 
		charButton[3][5].setText("Farah"); 
	
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
		questionList[13] = "Does the person have no facial hair?"; 
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
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				
				aiChars[i][j] = true; 
				
			}
		}
		/*
		int num = 50; 
		while (num >= 0) {
			num--;
			compCharacter();
		}
		*/
	}
	
	public static void getRanQuestion() {
		
		int ranNum = (int)(Math.random()*25); 
		
		aiSelectedQuestion = questionList[ranNum]; 
		
	}
	
	public static void compCharacter() {
				
		int iValue = (int)(Math.random()*4); 
		int jValue = (int)(Math.random()*6);
		compChar = chars[iValue][jValue];
	
		System.out.println(compChar.getName()); 

				
	}
	
	static class Restart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			window.remove(winLoseScreen);
			window.remove(compCards); 
			compCards.setText("Your opponent has flipped 0 cards...");
			window.add(options); 
			window.repaint();
			
			gameStarted = false; 
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					charButton[i][j].setEnabled(true);
					charButton[i][j].setBackground(null);
					aiChars[i][j] = true; 
					charButton[0][0].setIcon(Olivia);
					charButton[1][0].setIcon(Nick);
					charButton[2][0].setIcon(David);
					charButton[3][0].setIcon(Leo);
					charButton[0][1].setIcon(Emma);
					charButton[1][1].setIcon(Ben);
					charButton[2][1].setIcon(Eric);
					charButton[3][1].setIcon(Rachel);
					charButton[0][2].setIcon(Amy);
					charButton[1][2].setIcon(Mike);
					charButton[2][2].setIcon(Gabe);
					charButton[3][2].setIcon(Jordan);
					charButton[0][3].setIcon(Carmen);
					charButton[1][3].setIcon(Joe);
					charButton[2][3].setIcon(Mia);
					charButton[3][3].setIcon(Sam);
					charButton[0][4].setIcon(Sofia);
					charButton[1][4].setIcon(Lily);
					charButton[2][4].setIcon(Daniel);
					charButton[3][4].setIcon(Al);
					charButton[0][5].setIcon(Laura);
					charButton[1][5].setIcon(Liz);
					charButton[2][5].setIcon(Katie);
					charButton[3][5].setIcon(Farah);
					
				}
			}

		}
	}
	
	static class ConfirmAnswer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (answer.getText().equals(compChar.getName())) {
				
				System.out.println("You won!"); 
				window.getContentPane().removeAll();
				window.add(winLoseScreen); 
				window.repaint();
				winLose.setText("You won!");
				
			}
			else {
				System.out.println("You lost!"); 
				window.getContentPane().removeAll();
				window.add(winLoseScreen); 
				window.repaint();
				winLose.setText("You Lost!");
				
			}
		}
	}
	
	//Implement action for yes button
	static class YesButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			System.out.println(aiCards); 
			
			lying = false; 
			int ranNum = (int)(Math.random()*(10000)); 
			try {Thread.sleep(ranNum);} catch (InterruptedException e1) {e1.printStackTrace();}
			
			if (aiSelectedQuestion == questions.getItemAt(0)) {
				
				if (playerChar.getEyeColor() != "Brown") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() != "Brown") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(1)) {
				
				if (playerChar.getEyeColor() != "Green") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() != "Green") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(2)) {
				
				if (playerChar.getEyeColor() != "Green") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() != "Blue") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(3)) {
				
				if (playerChar.getGender() != true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGender() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(4)) {
				
				if (playerChar.getGender() != false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGender() != false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(5)) {
				
				if (playerChar.getSkinTone() != "Light Skin") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getSkinTone() != "Light Skin") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(6)) {
				if (playerChar.getSkinTone() != "Dark Skin") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getSkinTone() != "Dark Skin") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(7)) {
				if (playerChar.getHairColor() != "Black") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() != "Black") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(8)) {
				
				if (playerChar.getHairColor() != "Brown") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() != "Brown") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(9)) {
				
				if (playerChar.getHairColor() != "Ginger") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() != "Ginger") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(10)) {
				if (playerChar.getHairColor() != "Blonde") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() != "Blonde") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(11)) {
				
				if (playerChar.getHairColor() != "White") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() != "White") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(12)) {
				
				if (playerChar.getFacialHair() != true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getFacialHair() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(13)) {
				
				if (playerChar.getFacialHair() != false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getFacialHair() != false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(14)) {
				
				if (playerChar.getGlasses() != true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGlasses() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(15)) {
				
				if (playerChar.getGlasses() != false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGlasses() != false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(16)) {
			
				if (playerChar.getVisibleTeeth() != true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getVisibleTeeth() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(17)) {
				
				if (playerChar.getVisibleTeeth() != false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getVisibleTeeth() != false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(18)) {
				
				if (playerChar.getWearHat() != true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getWearHat() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(19)) {
				
				if (playerChar.getWearHat() != false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getWearHat() != false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(20)) {
				if (playerChar.getHairLength() != "Short") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() != "Short") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(21)) {
				
				if (playerChar.getHairLength() != "Tied") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() != "Tied") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(22)) {
				
				if (playerChar.getHairLength() != "Long") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() != "Long") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(23)) {
				
				if (playerChar.getHairLength() != "Bald") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() != "Bald") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(24)) {
				
				if (playerChar.getPiercings() != true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (playerChar.getPiercings() != true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			
			if (lying == false) {
				window.remove(yes);
				window.remove(no);
				window.add(questions);
				window.add(confirmQuest); 
				window.repaint();
				computerText.setText("Your opponent is waiting for your question...");
			}
			
			int num = 0; 
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					if (aiChars[i][j] == false) {
						num++; 
					}
				}
			}
			
			compCards.setText("Your opponent has flipped " + num + " cards...");
			
			if (num >= 23) {
				window.getContentPane().removeAll();
				window.add(winLoseScreen); 
				window.repaint();
				
				Characters guessChar = null; 
				
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 6; j++) {
						
						if (aiChars[i][j] == true) {
							guessChar = chars[i][j]; 
						}
						
					}
				}
				
				winLose.setText("You lost! The Ai guessed that your card was " + guessChar.getName());
			}

		}
	}
	
	//Implement action for no button
	static class NoButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			lying = false; 
			int ranNum = (int)(Math.random()*(10000)); 
			try {Thread.sleep(ranNum);} catch (InterruptedException e1) {e1.printStackTrace();}
			
			if (aiSelectedQuestion == questions.getItemAt(0)) {
				
				if (playerChar.getEyeColor() == "Brown") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() == "Brown") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(1)) {
				
				if (playerChar.getEyeColor() == "Green") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() == "Green") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(2)) {
				
				if (playerChar.getEyeColor() == "Green") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getEyeColor() == "Blue") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(3)) {
				
				if (playerChar.getGender() == true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGender() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
				
			}
			if (aiSelectedQuestion == questions.getItemAt(4)) {
				
				if (playerChar.getGender() == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGender() == false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(5)) {
				
				if (playerChar.getSkinTone() == "Light Skin") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getSkinTone() == "Light Skin") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(6)) {
				if (playerChar.getSkinTone() == "Dark Skin") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getSkinTone() == "Dark Skin") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(7)) {
				if (playerChar.getHairColor() == "Black") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() == "Black") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(8)) {
				
				if (playerChar.getHairColor() == "Brown") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() == "Brown") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(9)) {
				
				if (playerChar.getHairColor() == "Ginger") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() == "Ginger") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(10)) {
				if (playerChar.getHairColor() == "Blonde") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() == "Blonde") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(11)) {
				
				if (playerChar.getHairColor() == "White") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairColor() == "White") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(12)) {
				
				if (playerChar.getFacialHair() == true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getFacialHair() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(13)) {
				
				if (playerChar.getFacialHair() == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getFacialHair() == false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(14)) {
				
				if (playerChar.getGlasses() == true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGlasses() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(15)) {
				
				if (playerChar.getGlasses() == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getGlasses() == false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(16)) {
			
				if (playerChar.getVisibleTeeth() == true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getVisibleTeeth() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(17)) {
				
				if (playerChar.getVisibleTeeth() == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getVisibleTeeth() == false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(18)) {
				
				if (playerChar.getWearHat() == true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getWearHat() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(19)) {
				
				if (playerChar.getWearHat() == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getWearHat() == false) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(20)) {
				if (playerChar.getHairLength() == "Short") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() == "Short") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(21)) {
				
				if (playerChar.getHairLength() == "Tied") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() == "Tied") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(22)) {
				
				if (playerChar.getHairLength() == "Long") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() == "Long") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(23)) {
				
				if (playerChar.getHairLength() == "Bald") {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getHairLength() == "Bald") {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			if (aiSelectedQuestion == questions.getItemAt(24)) {
				
				if (playerChar.getPiercings() == true) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (playerChar.getPiercings() == true) {
								
								aiChars[i][j] = false; 
								
							}
							
						}
					}
				}
			}
			
			if (lying == false) {
				window.remove(yes);
				window.remove(no);
				window.add(questions);
				window.add(confirmQuest); 
				window.repaint();
				computerText.setText("Your opponent is waiting for your question...");
			}
			
			int num = 0; 
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					if (aiChars[i][j] == false) {
						num++; 
					}
				}
			}
			
			compCards.setText("Your opponent has flipped " + num + " cards...");

			if (num >= 23) {
				window.getContentPane().removeAll();
				window.add(winLoseScreen); 
				window.repaint();
				Characters guessChar = null; 
				
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 6; j++) {
						
						if (aiChars[i][j] == true) {
							guessChar = chars[i][j]; 
						}
						
					}
				}
				
				winLose.setText("You lost! The Ai guessed that you card was " + guessChar.getName());			}
			
		}
	}
	
	//Implement action for confirm changes button
	static class ConfirmChanges implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			getRanQuestion(); 
			
			window.remove(confirmChanges);
			window.repaint();
			int ranNum = (int)(Math.random()*(10000)); 
			computerText.setText("Your opponent is coming up with a question...");
			try {Thread.sleep(ranNum);} catch (InterruptedException e1) {e1.printStackTrace();}
			computerText.setText(aiSelectedQuestion);
			window.add(no);
			window.add(yes);
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
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
			else if (button.getBackground() != Color.black){
				button.setIcon(null);
				button.setBackground(Color.black);
			}
			else if (button.getBackground() == Color.black) {
				button.setBackground(null);
				if (button.getText() == "Olivia") {
					button.setIcon(Olivia);
				}
				else if (button.getText() == "Nick") {
					button.setIcon(Nick);
				}
				else if (button.getText() == "Leo") {
					button.setIcon(Leo);
				}
				else if (button.getText() == "Emma") {
					button.setIcon(Emma);
				}
				else if (button.getText() == "Ben") {
					button.setIcon(Ben);
				}
				else if (button.getText() == "Eric") {
					button.setIcon(Eric);
				}
				else if (button.getText() == "Rachel") {
					button.setIcon(Rachel);
				}
				else if (button.getText() == "Amy") {
					button.setIcon(Amy);
				}
				else if (button.getText() == "Mike") {
					button.setIcon(Mike);
				}
				else if (button.getText() == "Gabe") {
					button.setIcon(Gabe);
				}
				else if (button.getText() == "Jordan") {
					button.setIcon(Jordan);
				}
				else if (button.getText() == "Carmen") {
					button.setIcon(Carmen);
				}
				else if (button.getText() == "Joe") {
					button.setIcon(Joe);
				}
				else if (button.getText() == "Mia") {
					button.setIcon(Mia);
				}
				else if (button.getText() == "Sam") {
					button.setIcon(Sam);
				}
				else if (button.getText() == "Sofia") {
					button.setIcon(Sofia);
				}
				else if (button.getText() == "Lily") {
					button.setIcon(Lily);
				}
				else if (button.getText() == "Daniel") {
					button.setIcon(Daniel);
				}
				else if (button.getText() == "Al") {
					button.setIcon(Al);
				}
				else if (button.getText() == "Laura") {
					button.setIcon(Laura);
				}
				else if (button.getText() == "Liz") {
					button.setIcon(Liz);
				}
				else if (button.getText() == "Katie") {
					button.setIcon(Katie);
				}
				else if (button.getText() == "Farah") {
					button.setIcon(Farah);
				}
				else if (button.getText() == "David") {
					button.setIcon(David);
				}
				
			}
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6;j++) {
					
					if (chars[i][j].getName() == character.getText()) {
						
						playerChar = chars[i][j]; 
						System.out.println(playerChar.getName()); 
						
					}
					
				}
			}
			
		}
	}
	
	//Implement action for ask questions button
	static class AskQuestion implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			int ranNum = (int)(Math.random()*(10000)); 
			String question; 
			
			window.remove(questions);
			window.remove(confirmQuest);
			window.setSize(999, 700);
			window.setSize(1000, 700);
			
			try {Thread.sleep(ranNum);} catch (InterruptedException e1) {e1.printStackTrace();}
			
			if (questions.getSelectedIndex() == 0) {
				if (compChar.getEyeColor() == "Brown") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 1) {
				if (compChar.getEyeColor() == "Green") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 2) {
				if (compChar.getEyeColor() == "Blue") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 3) {
				if (compChar.getGender() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 4) {
				if (compChar.getGender() == false) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 5) {
				if (compChar.getSkinTone() == "Light Skin") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 6) {
				if (compChar.getSkinTone() == "Dark Skin") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 7) {
				if (compChar.getHairColor() == "Black") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 8) {
				if (compChar.getHairColor() == "Brown") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 9) {
				if (compChar.getHairColor() == "Ginger") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 10) {
				if (compChar.getHairColor() == "Blonde") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 11) {
				if (compChar.getHairColor() == "White") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 12) {
				if (compChar.getFacialHair() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 13) {
				if (compChar.getFacialHair() == false) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 14) {
				if (compChar.getGlasses() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 15) {
				if (compChar.getGlasses() == false) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 16) {
				if (compChar.getVisibleTeeth() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 17) {
				if (compChar.getVisibleTeeth() == false) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 18) {
				if (compChar.getWearHat() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 19) {
				if (compChar.getWearHat() == false) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 20) {
				if (compChar.getHairLength() == "Short") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 21) {
				if (compChar.getHairLength() == "Tied") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 22) {
				if (compChar.getHairLength() == "Long") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 23) {
				if (compChar.getHairLength() == "Bald") {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			else if (questions.getSelectedIndex() == 24) {
				if (compChar.getPiercings() == true) {
					
					computerText.setText("Your opponent has answered YES");
					
				}
				else
					computerText.setText("Your opponent has answered NO");
			}
			
			window.add(confirmChanges); 
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					charButton[i][j].setEnabled(true); 
					
				}
			}
		}
	}
	
	//Implement action for confirm button
	static class Confirm implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (character.getText() != "N/A") {
			
				gameStarted = true; 
				
				window.remove(selection);
				window.remove(confirm);
				window.remove(character);
				window.add(questions);
				window.add(confirmQuest); 
				window.add(computerText); 
				window.add(answer);
				window.add(confirmAnswer); 
				window.add(compCards);
	
				window.repaint();
				
				gamePanel.setBounds(20, window.getHeight()/2-300, 400, 500);
				
				selectedQuestion = String.valueOf(questions.getSelectedItem());
				
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 6; j++) {
						
						charButton[i][j].setEnabled(false); 
						
					}
				}
			}
		}
	}
	
	//Implement action for start player vs computer button
	static class StartPlayerComp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
			compCharacter(); 

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

