import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GuessWho{
	
	//Initiate GUI variables
	static JFrame window = new JFrame("Window");
	static JPanel gamePanel = new JPanel(); 
	static JPanel options = new JPanel(); 
	static JPanel winLoseScreen = new JPanel(); 
	static JPanel whoGoFirst = new JPanel(); 
	static JButton charButton[][] = new JButton[4][6]; 
	static JButton playerComp = new JButton("     Player vs Computer (Online)     ");
	static JButton realWorld = new JButton("Player vs Computer  (In-Person)");
	static JButton confirm = new JButton("Confirm"); 
	static JButton confirmQuest = new JButton("Confirm"); 
	static JButton confirmChanges = new JButton("Confirm Changes"); 
	static JButton confirmAnswer = new JButton("Confirm"); 
	static JButton yes = new JButton("Yes");
	static JButton no = new JButton("No"); 
	static JButton restart = new JButton("Restart"); 
	static JButton returnMenu = new JButton("Return to Main Menu"); 
	static JButton player1First = new JButton("Player 1");  
	static JButton player2First = new JButton("Player 2"); 
	static JButton howToPlay = new JButton("                          How To Play                          "); 
	static JButton confirmChar = new JButton("Confirm"); 
	static JLabel chooseText = new JLabel("Choose the player that goes first"); 
	static JLabel winLose = new JLabel(); 
	static JLabel title = new JLabel("Choose the Game Mode"); 
	static JLabel credits = new JLabel("By Marcus Ng and Kevin Wang");
	static JLabel selection = new JLabel("Choose your Character");
	static JLabel character = new JLabel("N/A"); 
	static JLabel computerText = new JLabel("Your opponent is waiting for your question...");
	static JLabel compCards = new JLabel("Your opponent has flipped 0 cards...");
	static JLabel guessAICharacterLabel = new JLabel("Have a final answer? Guess it here! Only one try!");
	static JLabel yourCharacter = new JLabel("Your character is..."); 
	static JLabel playerGUI = new JLabel(); 
	static JLabel chooseChar = new JLabel("Please Choose a Character from your Deck of Guess Who Cards!"); 
	static JLabel confirmWhenChosenChar = new JLabel("Press Confirm when you have Chosen a Character!"); 
	static JLabel guessWhoLogo = new JLabel();
	static JTextArea information = new JTextArea("1) Once you open up the game, take a moment to enjoy the music before \nclicking the game mode you would like to play! \n2) When you're ready, click the difficulty you would like to play against for \nthe AI! \n3) Then, if you are playing the in-person version, select a character from a \nphysical deck of cards before pressing confirm. \n4) Now, it's time to play Guess Who! Select which player you would like to go first before asking your first question. \n5) When your opponent answers, click on characters on the grid at the left\nhand side of your screen to eliminate them. \n6) Then, when your opponent asks you a question, make sure to answer\ntruthfully! \n7) Going back and forth, one player will eventually end up with one\ncharacter by process of elimination. Put your final answer in the answer box and confirm! You only have one try, if you're wrong, you lose! \n8) Hopefully you've enjoyed playing our version of Guess Who! \n9) Have fun!", 5, 100);
	static JTextArea displayWrongQuest = new JTextArea("N/A");
	static JScrollPane informationScrollBar = new JScrollPane(information);
	static JScrollPane wrongQuestScrollBar = new JScrollPane(displayWrongQuest);
	static JLabel guessWhoLogoInitial = new JLabel();
	static JLabel rightPersonPortrait = new JLabel();
	static JLabel leftPersonPortrait = new JLabel();
	static JLabel enterCorrectChar = new JLabel("If the AI guessed wrong, please enter your character");
	static JButton settings = new JButton();
	static JPanel settingsMenu = new JPanel();
	static JLabel settingsTitle = new JLabel("Settings");
	static JButton exitButton = new JButton();
	static JLabel toggleMusicPrompt = new JLabel("Toggle Music");
	static JButton on = new JButton("ON");
	static JButton off = new JButton("OFF");
	static JButton returnToMainMenu = new JButton("Return to Main Menu");
	static JButton quitTheGame = new JButton("Quit the Game");
	static JComboBox questions;
	static JTextArea answer = new JTextArea("Insert your answer here"); 
	static JTextArea corrChar = new JTextArea("Enter your character here"); 

	static Characters[][] chars = new Characters[4][6];
	static Characters compChar; 
	static Characters playerChar; 
	
	//Initiate standard variables
	static boolean[][] aiChars = new boolean[4][6]; 
	static boolean[] plrProperty = new boolean[25]; 
	static boolean bannedQuest[] = new boolean[25]; 
	static boolean gameStarted = false;
	static boolean won = false; 
	static boolean lying = false; 
	static boolean realW = false; 
	
	static String[] questionList = new String[25]; 
	static String selectedQuestion; 
	static String aiSelectedQuestion; 
	
	static int aiCards = 0; 
	
	static ArrayList<ImageIcon> images = new ArrayList<ImageIcon>(); 
	static ArrayList<Integer> savedIndex = new ArrayList<Integer>(); 
	static ArrayList<Boolean> savedAns = new ArrayList<Boolean>(); 
	static ArrayList<String> savedQuest = new ArrayList<String>(); 

	//Initiate images
	static ImageIcon Olivia = new ImageIcon("IMG_3789.jpg");
	static ImageIcon Nick = new ImageIcon("IMG_3792.jpg");
	static ImageIcon David = new ImageIcon("IMG_3781.jpg");
	static ImageIcon Leo = new ImageIcon("IMG_3794.jpg");
	static ImageIcon Emma = new ImageIcon("IMG_3777.jpg");
	static ImageIcon Ben = new ImageIcon("IMG_3778.jpg");
	static ImageIcon Eric = new ImageIcon("IMG_3791.jpg");
	static ImageIcon Rachel = new ImageIcon("IMG_3784.jpg");
	static ImageIcon Amy = new ImageIcon("IMG_3771.jpg");
	static ImageIcon Mike = new ImageIcon("IMG_3779.jpg");
	static ImageIcon Gabe = new ImageIcon("IMG_3786.jpg");
	static ImageIcon Jordan = new ImageIcon("IMG_3785.jpg");
	static ImageIcon Carmen = new ImageIcon("IMG_3790.jpg");
	static ImageIcon Joe = new ImageIcon("IMG_3772.jpg");
	static ImageIcon Mia = new ImageIcon("IMG_3782.jpg");
	static ImageIcon Sam = new ImageIcon("IMG_3774.jpg");
	static ImageIcon Sofia = new ImageIcon("IMG_3783.jpg");
	static ImageIcon Lily = new ImageIcon("IMG_3788.jpg");
	static ImageIcon Daniel = new ImageIcon("IMG_3776.jpg");
	static ImageIcon Al = new ImageIcon("IMG_3787.jpg");
	static ImageIcon Laura = new ImageIcon("IMG_3793.jpg");
	static ImageIcon Liz = new ImageIcon("IMG_3773.jpg");
	static ImageIcon Katie = new ImageIcon("IMG_3775.jpg");
	static ImageIcon Farah = new ImageIcon("IMG_3780.jpg");
	static ImageIcon PlaidBackground = new ImageIcon("OfficialPlaidBackground (1).png");
	static ImageIcon GuessWhoLogo = new ImageIcon("GuessWhoLogo.png");
	static ImageIcon RightPersonPortrait = new ImageIcon("RightPersonPortrait.png");
	static ImageIcon LeftPersonPortrait = new ImageIcon("LeftPersonPortrait.png");
	static ImageIcon settingsImage = new ImageIcon("Settings.png");
	static ImageIcon ExitButton = new ImageIcon("Exit.png");
	
	static Font font; 
	
	static File backgroundMusicPath = new File("GuessWhoMusic.wav"); 
	private static Clip backgroundMusic; 
	
	//Main method
	public static void main(String[] args) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException{
		
		//Initiate background music 
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(backgroundMusicPath);
		backgroundMusic = AudioSystem.getClip(); 
		backgroundMusic.open(audioInput);
		
		FloatControl gainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);
		
		loopMusic(backgroundMusic);
		
		//Initiate custom font
		font = Font.createFont(Font.TRUETYPE_FONT, new File("GuessWhoFont.otf")).deriveFont(20f);
		
		//Set properties for the game window
		window.setSize(1000, 700);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLayout(null);
		window.setResizable(false);

		//Setting the background image of the window to OfficialPlaidBackground.png
		JLabel contentPane = new JLabel();
		contentPane.setIcon(PlaidBackground);
		window.setContentPane(contentPane);
		
		//Set properties for the Guess Who Logo JLabel on the game panel
		guessWhoLogo.setIcon(GuessWhoLogo);
		guessWhoLogo.setBounds(600, 25, 256, 118);
		
		//Set properties for the Guess Who Logo JLabel on the main menu
		guessWhoLogoInitial.setIcon(GuessWhoLogo);
		guessWhoLogoInitial.setBounds(375, 80, 256, 118);
		guessWhoLogoInitial.setAlignmentX(Component.CENTER_ALIGNMENT);
		window.add(guessWhoLogoInitial);
		
		//Set properties for the Right Person Portrait JLabel on the main menu
		rightPersonPortrait.setIcon(RightPersonPortrait);
		rightPersonPortrait.setBounds(650, 275, 296, 400);
		window.add(rightPersonPortrait);
		
		//Set properties for the Left Person Portrait JLabel on the main menu
		leftPersonPortrait.setIcon(LeftPersonPortrait);
		leftPersonPortrait.setBounds(0, 350, 450, 327);
		window.add(leftPersonPortrait);
		
		//Set properties for the Settings button
		settings.setIcon(settingsImage);
		settings.setBounds(850, 50, 100, 100);
		settings.setOpaque(false);
		settings.setContentAreaFilled(false);
		settings.setBorderPainted(false);
		settings.setBorder(null);
		settings.setFocusPainted(false);
		settings.addActionListener(new clickSettings());
		window.add(settings);
		
		//Set properties for the Settings Menu when Settings button is clicked
		settingsMenu.setBounds(100, 100, 800, 500);
		settingsMenu.setBackground(Color.WHITE);
		settingsMenu.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		settingsMenu.setLayout(null);
		
		//Set properties for the Settings Menu Title
		settingsTitle.setFont(font);
		settingsTitle.setBounds(350, 25, 200, 50);
		settingsMenu.add(settingsTitle);
		
		//Set proprties for the Settings Menu Exit Button
		exitButton.setIcon(ExitButton);
		exitButton.setBounds(25, 25, 50, 50);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setBorder(null);
		exitButton.setFocusPainted(false);
		exitButton.addActionListener(new clickExitButton());
		settingsMenu.add(exitButton);
		
		//Set properties for the Toggle Music Prompt Label
		toggleMusicPrompt.setFont(font);
		toggleMusicPrompt.setBounds(215, 90, 200, 50);
		settingsMenu.add(toggleMusicPrompt);
		
		//Set properties for Music ON and OFF buttons
		on.setFont(font);
		on.setBounds(450, 90, 100, 40);
		on.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		on.addActionListener(new clickOn());
		off.setFont(font);
		off.setBounds(565, 90, 100, 40);
		off.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		off.addActionListener(new clickOff());
		settingsMenu.add(on);
		settingsMenu.add(off);
		
		//Set properties for the Return to Main Menu JButton in Settings
		returnToMainMenu.setFont(font);
		returnToMainMenu.setBounds(275, 275, 250,75);
		returnToMainMenu.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		returnToMainMenu.addActionListener(new Restart());
		settingsMenu.add(returnToMainMenu);
		
		//Set properties for the Quit the Game JButton in Settings
		quitTheGame.setFont(font);
		quitTheGame.setBounds(275, 375, 250, 75);
		quitTheGame.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		quitTheGame.addActionListener(new quitButtonPressed());
		settingsMenu.add(quitTheGame);
		
		//Set properties for the credits JLabel on the main menu
		credits.setFont(font);
		credits.setBounds(350, 200, 500, 50);
		window.add(credits);
		
		//Set properties for the computer flipped cards (Your opponent has flipped 0 cards...)
		compCards.setBounds(525, 320, 400, 30);
		compCards.setFont(font);
		
		//Set properties for the win/lose screen
		winLoseScreen.setLayout(new BoxLayout(winLoseScreen, BoxLayout.Y_AXIS));
		winLoseScreen.setBounds(window.getWidth()/2-250, 200, 500, 180);
		winLoseScreen.add(winLose);
		winLoseScreen.add(restart); 
		winLoseScreen.add(enterCorrectChar); 
		winLoseScreen.add(corrChar);
		corrChar.setBounds(300, 400, 300, 80);
		corrChar.setFont(font); 
		enterCorrectChar.setFont(font);
		winLoseScreen.add(confirmChar);
		confirmChar.setFont(font);
		confirmChar.addActionListener(new EnterCorrectAns()); 
		winLose.setFont(font);
		winLose.setSize(500, 50);
		restart.setFont(font); 
		restart.addActionListener(new Restart());
		
		//Set properties for the game panel
		gamePanel.setLayout(new GridLayout(4, 6));
		gamePanel.setBounds(50, 50, 400, 500);
		gamePanel.setVisible(true);
		
		//Set properties for Confirm that you have chosen a character from a deck of cards in real life JLabel
		chooseChar.setFont(font);
		chooseChar.setBounds(160, 250, 700, 50);
		
		//Set properties for next line under confirm button to confirm you have chosen a character (Press Confirm when you have chosen a Character!)
		confirmWhenChosenChar.setFont(font);
		confirmWhenChosenChar.setBounds(250, 300, 600, 50);
		
		//Set properties for the selection screen 
		selection.setBounds(600, 0, 400, 75);
		selection.setFont(font);
		
		//Set properties for the confirm button when chosen char (JButton)
		confirm.setBounds(355, 400, 300, 100);
		confirm.addActionListener(new Confirm());
		confirm.setFont(font);
		
		//Set properties for character selection label
		character.setBounds(600, 0, 300, 100);
		character.setFont(font);
		
		//Set properties for which player goes first GUI
		//window.add(whoGoFirst); 
		whoGoFirst.setLayout(new BoxLayout(whoGoFirst, BoxLayout.Y_AXIS)); 
		whoGoFirst.add(chooseText);	
		whoGoFirst.setBounds(window.getWidth()/2-250, 200, 500, 500);
		
		chooseText.setFont(font); 
		chooseText.setAlignmentX(Component.CENTER_ALIGNMENT);

		whoGoFirst.add(player1First);
		player1First.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		whoGoFirst.add(player2First);
		player2First.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		//Set properties for game option menu
		window.add(options);
		options.setOpaque(false); //sets the background of the options jpanel to transparent
		options.setBounds(window.getWidth()/2-250, 300, 500, 500);
		options.add(title); 
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
		title.setFont(font);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		//Set properties for player vs computer button
		options.add(playerComp);
		playerComp.setFont(font);
		playerComp.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerComp.addActionListener(new StartPlayerComp()); 
		
		//Set properties for computer vs real world button
		options.add(realWorld);
		realWorld.setFont(font);
		realWorld.addActionListener(new StartRealGame()); 
		realWorld.setAlignmentX(Component.CENTER_ALIGNMENT); 
		
		//Set properties for instructions button
		howToPlay.setFont(font);
		howToPlay.addActionListener(new Instructions());
		howToPlay.setAlignmentX(Component.CENTER_ALIGNMENT); 
		options.add(howToPlay);
		
		//Set properties for Return to Menu Option on the How to Play Menu
		returnMenu.setBounds(350, 500, 300, 60);
		returnMenu.setFont(font);
		returnMenu.addActionListener(new Restart());
		
		//Set properties for information for instructions on How to Play Menu
		information.setLineWrap(true);
		informationScrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		informationScrollBar.setBounds(100, 225, 800, 235);
		information.setFont(font);
		
		//Set properties for display of wrong questions
		displayWrongQuest.setLineWrap(true);
		wrongQuestScrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		wrongQuestScrollBar.setBounds(100, 225, 800, 235);
		displayWrongQuest.setFont(font);
		
		//Set properties for confirm question button (the confirm button on the game panel screen for confirming your question to ask the AI)
		confirmQuest.setBounds(650, 250, 150, 50); 
		confirmQuest.setFont(font); 
		confirmQuest.addActionListener(new AskQuestion()); 
		
		//Set properties for computer output text (Your opponent is waiting for your question...)
		computerText.setBounds(525, 150, 500, 50); 
		computerText.setFont(font); 
		
		//Set properties for the guessAICharacterLabel (Have an answer? Guess it here! You have one try, or you lose!)
		guessAICharacterLabel.setBounds(450, 360, 550, 50); 
		guessAICharacterLabel.setFont(font);
		
		//Set properties for confirm changes button
		confirmChanges.setBounds(575, 200, 300, 50);
		confirmChanges.setFont(font);
		confirmChanges.addActionListener(new ConfirmChanges()); 
		
		//Set properties for yes button
		yes.setBounds(625, 200, 100, 50); 
		yes.setFont(font);
		yes.addActionListener(new YesButton());
		
		//Set properties for no button
		no.setBounds(725, 200, 100, 50);
		no.setFont(font);
		no.addActionListener(new NoButton());
		
		//Set properties for answer text (Confirm your guess of the AI's character text box)
		answer.setBounds(500, 420, 300, 50);
		answer.setFont(font);
		
		//Set properties for confirm answer button (Confirm button for guessing AI's character)
		confirmAnswer.setBounds(800, 420, 150, 50); 
		confirmAnswer.setFont(font); 
		confirmAnswer.addActionListener(new ConfirmAnswer()); 
		
		//Set properties for player's selected character gui
		playerGUI.setBounds(500, 500, 80, 120);
		
		//Set properties for text that shows player's character
		yourCharacter.setBounds(590, 500, 300, 50); 
		yourCharacter.setFont(font); 
		
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
		
	    //initiate player character placeholder
	    playerChar = chars[0][0]; 
	    
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
		questions.setBounds(525, 200, 400, 50); 
		questions.setFont(font);
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				
				aiChars[i][j] = true; 
				
			}
		}
		
		window.setVisible(true);
		window.repaint();
	}
	
	public static void playMusic(Clip clip) {
		
		clip.start(); 
	
	}
	
	public static void loopMusic(Clip clip) {
		
		clip.loop(clip.LOOP_CONTINUOUSLY);

	}
	
	public static void stopMusic(Clip clip) {
		
		clip.stop();
		
	}
	public static void aiSelectsQuestion() {
		
		int propertyCount[] = new int[25];
		
		for (int i = 0; i < 25; i++) {
			propertyCount[i] = 0; 
		}
		
		int index;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				
				if (chars[i][j].getEyeColor() == "Brown" && aiChars[i][j] == true) {
					propertyCount[0]++; 
				}
				if (chars[i][j].getEyeColor() == "Green" && aiChars[i][j] == true) {
					propertyCount[1]++; 
				}
				if (chars[i][j].getEyeColor() == "Blue" && aiChars[i][j] == true) {
					propertyCount[2]++; 
				}
				if (chars[i][j].getGender() == true && aiChars[i][j] == true) {
					propertyCount[3]++; 
				}
				if (chars[i][j].getGender() == false && aiChars[i][j] == true) {
					propertyCount[4]++; 
				}
				if (chars[i][j].getSkinTone() == "Light Skin" && aiChars[i][j] == true) {
					propertyCount[5]++; 
				}
				if (chars[i][j].getSkinTone() == "Dark Skin" && aiChars[i][j] == true) {
					propertyCount[6]++; 
				}
				if (chars[i][j].getHairColor() == "Black" && aiChars[i][j] == true) {
					propertyCount[7]++; 
				}
				if (chars[i][j].getHairColor() == "Brown" && aiChars[i][j] == true) {
					propertyCount[8]++; 
				}
				if (chars[i][j].getHairColor() == "Ginger" && aiChars[i][j] == true) {
					propertyCount[9]++; 
				}
				if (chars[i][j].getHairColor() == "Blonde" && aiChars[i][j] == true) {
					propertyCount[10]++; 
				}
				if (chars[i][j].getHairColor() == "White" && aiChars[i][j] == true) {
					propertyCount[11]++; 
				}
				if (chars[i][j].getFacialHair() == true && aiChars[i][j] == true) {
					propertyCount[12]++; 
				}
				if (chars[i][j].getFacialHair() == false && aiChars[i][j] == true) {
					propertyCount[13]++; 
				}
				if (chars[i][j].getGlasses() == true && aiChars[i][j] == true) {
					propertyCount[14]++; 
				}
				if (chars[i][j].getGlasses() == false && aiChars[i][j] == true) {
					propertyCount[15]++; 
				}
				if (chars[i][j].getVisibleTeeth() == true && aiChars[i][j] == true) {
					propertyCount[16]++; 
				}
				if (chars[i][j].getVisibleTeeth() == false && aiChars[i][j] == true) {
					propertyCount[17]++; 
				}
				if (chars[i][j].getWearHat() == true && aiChars[i][j] == true) {
					propertyCount[18]++; 
				}
				if (chars[i][j].getWearHat() == false && aiChars[i][j] == true) {
					propertyCount[19]++; 
				}
				if (chars[i][j].getHairLength() == "Short" && aiChars[i][j] == true) {
					propertyCount[20]++; 
				}
				if (chars[i][j].getHairLength() == "Tied" && aiChars[i][j] == true) {
					propertyCount[21]++; 
				}
				if (chars[i][j].getHairLength() == "Long" && aiChars[i][j] == true) {
					propertyCount[22]++; 
				}
				if (chars[i][j].getHairLength() == "Bald" && aiChars[i][j] == true) {
					propertyCount[23]++; 
				}
				if (chars[i][j].getPiercings() == true && aiChars[i][j] == true) {
					propertyCount[24]++; 
				}
		
			}
		}
		
		int mostNum = 0;
		index = 0; 
		
		for (int i = 0 ; i < 24; i++) {
			
			if (propertyCount[i] >= mostNum) {
				
				if (bannedQuest[i] == false && propertyCount[i] != 24-aiCards) {
					index = i;
					mostNum = propertyCount[i]; 
				}
				
			}
		}
		
		System.out.println(24-aiCards + " " + propertyCount[index]); 
				
		aiSelectedQuestion = questionList[index]; 
		savedQuest.add(aiSelectedQuestion); 
		bannedQuest[index] = true; 
		
		if (index == 3) {
			bannedQuest[4] = true;
		}
		else if (index == 4) {
			bannedQuest[3] = true;
		}
		else if (index == 5) {
			bannedQuest[6] = true;
		}
		else if (index == 6) {
			bannedQuest[5] = true;
		}
		else if (index == 12) {
			bannedQuest[13] = true;
		}
		else if (index == 13) {
			bannedQuest[12] = true;
		}
		else if (index == 14) {
			bannedQuest[15] = true;
		}
		else if (index == 15) {
			bannedQuest[14] = true;
		}
		else if (index == 16) {
			bannedQuest[17] = true; 
		}
		else if (index == 17) {
			bannedQuest[16] = true;
		}
		else if (index == 18) {
			bannedQuest[19] = true;
		}
		else if (index == 19) {
			bannedQuest[18] = true; 
		}
	}
	
	public static void compCharacter() {
				
		int iValue = (int)(Math.random()*4); 
		int jValue = (int)(Math.random()*6);
		compChar = chars[iValue][jValue];
	
		System.out.println(compChar.getName()); 

				
	}

	static class EnterCorrectAns implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			window.add(wrongQuestScrollBar); 
			
			Characters plrChar = null; 

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					System.out.println(chars[i][j].getName() + " " + corrChar.getText());
					if (chars[i][j].getName().equals(corrChar.getText())) {
						
						System.out.println("Bruh"); 
						plrChar = chars[i][j]; 
						
					}
					
				}
			}
			
			for (int i = 0; i < 24; i++) {
				plrProperty[i] = false; 
			}
			
			if (plrChar != null) {
				
				if (plrChar.getEyeColor() == "Brown") {
					plrProperty[0] = true; 
				}
				if (plrChar.getEyeColor() == "Green") {
					plrProperty[1] = true; 
				}
				if (plrChar.getEyeColor() == "Blue") {
					plrProperty[2] = true; 
				}
				if (plrChar.getGender() == true) {
					plrProperty[3] = true; 
				}
				if (plrChar.getGender() == false) {
					plrProperty[4] = true; 
				}
				if (plrChar.getSkinTone() == "Light Skin") {
					plrProperty[5] = true; 
				}
				if (plrChar.getSkinTone() == "Dark Skin") {
					plrProperty[6] = true; 
				}
				if (plrChar.getHairColor() == "Black") {
					plrProperty[7] = true; 
				}
				if (plrChar.getHairColor() == "Brown") {
					plrProperty[8] = true; 
				}
				if (plrChar.getHairColor() == "Ginger") {
					plrProperty[9] = true; 
				}
				if (plrChar.getHairColor() == "Blonde") {
					plrProperty[10] = true; 
				}
				if (plrChar.getHairColor() == "White") {
					plrProperty[11] = true; 
				}
				if (plrChar.getFacialHair() == true) {
					plrProperty[12] = true; 
				}
				if (plrChar.getFacialHair() == false) {
					plrProperty[13] = true; 
				}
				if (plrChar.getGlasses() == true) {
					plrProperty[14] = true; 
				}
				if (plrChar.getGlasses() == false) {
					plrProperty[15] = true; 
				}
				if (plrChar.getVisibleTeeth() == true) {
					plrProperty[16] = true; 
				}
				if (plrChar.getVisibleTeeth() == false) {
					plrProperty[17] = true; 
				}
				if (plrChar.getWearHat() == true) {
					plrProperty[18] = true; 
				}
				if (plrChar.getWearHat() == false) {
					plrProperty[19] = true; 
				}
				if (plrChar.getHairLength() == "Short") {
					plrProperty[20] = true; 
				}
				if (plrChar.getHairLength() == "Tied") {
					plrProperty[21] = true; 
				}
				if (plrChar.getHairLength() == "Long") {
					plrProperty[22] = true; 
				}
				if (plrChar.getHairLength() == "Bald") {
					plrProperty[23] = true; 
				}
				if (plrChar.getPiercings() == true) {
					plrProperty[24] = true; 
				}
			}
			else {
				enterCorrectChar.setText("The character you entered does not exist!");
			}
			
			String temp = ""; 
				
			for (int i = 0; i < savedQuest.size(); i++) {
				for (int j = 0; j < 24; j++) {
					
					if (savedQuest.get(i) == questionList[j]) {
						
						savedIndex.add(j); 
						System.out.println(questionList[j]); 
						
					}
					
				}
			}
			
			for (int i = 0; i < savedIndex.size(); i++) {
				
				System.out.println(savedIndex.get(i)); 
				
				for (int j = 0; j < 24; j++) {
					
					if (savedIndex.get(i) == j && plrProperty[j] == true && savedAns.get(i) == false) {
						
						temp += questionList[j];
						temp += " \n ";
						
					}
					else if (savedIndex.get(i) == j && plrProperty[j] == false && savedAns.get(i) == true) {
						
						temp += questionList[j];
						temp += " \n ";
						
					}
					
				}	
			}
			
			displayWrongQuest.setText(temp);
			
		}	
	}
	
	static class Instructions implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			window.remove(options);
			window.remove(credits);
			window.remove(leftPersonPortrait);
			window.remove(rightPersonPortrait);
			window.add(informationScrollBar); 
			window.add(returnMenu); 
			window.setVisible(true);
			window.repaint();
			
		}	
	}
 	
	static class Restart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			savedAns.clear();
			
			window.remove(winLoseScreen);
			window.remove(compCards); 
			window.remove(playerGUI);
			window.remove(yourCharacter);
			window.remove(information);
			window.remove(returnMenu);
			window.remove(settingsMenu);
			window.remove(chooseChar);
			window.remove(confirmWhenChosenChar);
			window.remove(confirm);
			window.remove(gamePanel);
			window.remove(guessWhoLogo);
			window.remove(selection);
			window.remove(character);
			window.remove(informationScrollBar);
			window.remove(corrChar); 
			window.remove(displayWrongQuest); 
			
			compCards.setText("Your opponent has flipped 0 cards...");
			computerText.setText("Your opponent is waiting for your question...");
			window.add(options); 
			window.add(guessWhoLogoInitial); 
			window.add(credits);
			window.add(leftPersonPortrait);
			rightPersonPortrait.setBounds(650, 275, 296, 400);
			window.add(rightPersonPortrait);
			settings.setBounds(850, 50, 100, 100);
			window.add(settings);
			window.repaint();
			
			restart.setText("Restart");
			
			gameStarted = false; 
			realW = false; 
			aiCards = 0; 
			
			savedIndex.clear();
			
			for (int i = 0; i < 25; i++) {
				bannedQuest[i] = false; 
			}
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
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
						
			lying = false; 
			savedAns.add(true);
			
			if (aiSelectedQuestion == questions.getItemAt(0)) {
				
				if (playerChar.getEyeColor() != "Brown" && realW == false) {
					
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
				
				if (playerChar.getEyeColor() != "Green" && realW == false) {
					
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
				
				if (playerChar.getEyeColor() != "Green" && realW == false) {
					
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
				
				if (playerChar.getGender() != true && realW == false) {
					
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
				
				if (playerChar.getGender() != false && realW == false) {
					
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
				
				if (playerChar.getSkinTone() != "Light Skin" && realW == false) {
					
					 
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
				if (playerChar.getSkinTone() != "Dark Skin" && realW == false) {
					
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
				if (playerChar.getHairColor() != "Black" && realW == false) {
					
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
				
				if (playerChar.getHairColor() != "Brown" && realW == false) {
					
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
				
				if (playerChar.getHairColor() != "Ginger" && realW == false) {
					
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
				if (playerChar.getHairColor() != "Blonde" && realW == false) {
					
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
				
				if (playerChar.getHairColor() != "White" && realW == false) {
					
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
				
				if (playerChar.getFacialHair() != true && realW == false) {
					
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
				
				if (playerChar.getFacialHair() != false && realW == false) {
					
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
				
				if (playerChar.getGlasses() != true && realW == false) {
					
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
				
				if (playerChar.getGlasses() != false && realW == false) {
					
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
			
				if (playerChar.getVisibleTeeth() != true && realW == false) {
					
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
				
				if (playerChar.getVisibleTeeth() != false && realW == false) {
					
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
				
				if (playerChar.getWearHat() != true && realW == false) {
					
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
				
				if (playerChar.getWearHat() != false && realW == false) {
					
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
				if (playerChar.getHairLength() != "Short" && realW == false) {
					
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
				
				if (playerChar.getHairLength() != "Tied" && realW == false) {
					
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
				
				if (playerChar.getHairLength() != "Long" && realW == false) {
					
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
				
				if (playerChar.getHairLength() != "Bald" && realW == false) {
					
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
				
				if (playerChar.getPiercings() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getPiercings() != true) {
								
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
			
			aiCards = 0; 
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					if (aiChars[i][j] == false) {
						aiCards++; 
					}
				}
			}
			
			compCards.setText("Your opponent has flipped " + aiCards + " cards...");
			
			if (aiCards >= 23) {
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
			savedAns.add(false); 
			
			if (aiSelectedQuestion == questions.getItemAt(0)) {
				
				if (playerChar.getEyeColor() == "Brown" && realW == false) {
					
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
				
				if (playerChar.getEyeColor() == "Green" && realW == false) {
					
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
				
				if (playerChar.getEyeColor() == "Green" && realW == false) {
					
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
				
				if (playerChar.getGender() == true && realW == false) {
					
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
				
				if (playerChar.getGender() == false && realW == false) {
					
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
				
				if (playerChar.getSkinTone() == "Light Skin" && realW == false) {
					
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
				if (playerChar.getSkinTone() == "Dark Skin" && realW == false) {
					
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
				if (playerChar.getHairColor() == "Black" && realW == false) {
					
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
				
				if (playerChar.getHairColor() == "Brown" && realW == false) {
					
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
				
				if (playerChar.getHairColor() == "Ginger" && realW == false) {
					
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
				if (playerChar.getHairColor() == "Blonde" && realW == false) {
					
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
				
				if (playerChar.getHairColor() == "White" && realW == false) {
					
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
				
				if (playerChar.getFacialHair() == true && realW == false) {
					
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
				
				if (playerChar.getFacialHair() == false && realW == false) {
					
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
				
				if (playerChar.getGlasses() == true && realW == false) {
					
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
				
				if (playerChar.getGlasses() == false && realW == false) {
					
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
			
				if (playerChar.getVisibleTeeth() == true && realW == false) {
					
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
				
				if (playerChar.getVisibleTeeth() == false && realW == false) {
					
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
				
				if (playerChar.getWearHat() == true && realW == false) {
					
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
				
				if (playerChar.getWearHat() == false && realW == false) {
					
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
				if (playerChar.getHairLength() == "Short" && realW == false) {
					
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
				
				if (playerChar.getHairLength() == "Tied" && realW == false) {
					
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
				
				if (playerChar.getHairLength() == "Long" && realW == false) {
					
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
				
				if (playerChar.getHairLength() == "Bald" && realW == false) {
					
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
				
				if (playerChar.getPiercings() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				else {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							
							if (chars[i][j].getPiercings() == true) {
								
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
			
			aiCards = 0; 
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					if (aiChars[i][j] == false) {
						aiCards++; 
					}
				}
			}
			
			compCards.setText("Your opponent has flipped " + aiCards + " cards...");

			if (aiCards >= 23) {
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
				
				if (guessChar != null) {
					winLose.setText("You lost! The Ai guessed that you card was " + guessChar.getName());			
				}
				else
					winLose.setText("None of the characters match your description");
				
			}
			
		}
	}
	
	//Implement action for confirm changes button
	static class ConfirmChanges implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			aiSelectsQuestion(); 
			
			window.remove(confirmChanges);
			window.repaint();
			computerText.setText("Your opponent is coming up with a question...");
			computerText.setText(aiSelectedQuestion);
			window.add(no);
			window.add(yes);
					
		}
	}
	
	//Implement action for character selection button
	static class CharSelection implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			JButton button = (JButton)e.getSource(); 
			
			if (gameStarted == false) {
				character.setText(button.getText());
				if (button.getText() == "Olivia") {
					playerGUI.setIcon(Olivia);
					yourCharacter.setText("Your character is Olivia");
				}
				else if (button.getText() == "Nick") {
					playerGUI.setIcon(Nick);
					yourCharacter.setText("Your character is Nick");
				}
				else if (button.getText() == "Leo") {
					playerGUI.setIcon(Leo);
					yourCharacter.setText("Your character is Leo");

				}
				else if (button.getText() == "Emma") {
					playerGUI.setIcon(Emma);
					yourCharacter.setText("Your character is Emma");

				}
				else if (button.getText() == "Ben") {
					playerGUI.setIcon(Ben);
					yourCharacter.setText("Your character is Ben");

				}
				else if (button.getText() == "Eric") {
					playerGUI.setIcon(Eric);
					yourCharacter.setText("Your character is Eric");

				}
				else if (button.getText() == "Rachel") {
					playerGUI.setIcon(Rachel);
					yourCharacter.setText("Your character is Rachel");

				}
				else if (button.getText() == "Amy") {
					playerGUI.setIcon(Amy);
					yourCharacter.setText("Your character is Amy");

				}
				else if (button.getText() == "Mike") {
					playerGUI.setIcon(Mike);
					yourCharacter.setText("Your character is Mike");

				}
				else if (button.getText() == "Gabe") {
					playerGUI.setIcon(Gabe);
					yourCharacter.setText("Your character is Gabe");

				}
				else if (button.getText() == "Jordan") {
					playerGUI.setIcon(Jordan);
					yourCharacter.setText("Your character is Jordan");

				}
				else if (button.getText() == "Carmen") {
					playerGUI.setIcon(Carmen);
					yourCharacter.setText("Your character is Carmen");

				}
				else if (button.getText() == "Joe") {
					playerGUI.setIcon(Joe);
					yourCharacter.setText("Your character is Joe");

				}
				else if (button.getText() == "Mia") {
					playerGUI.setIcon(Mia);
					yourCharacter.setText("Your character is Mia");

				}
				else if (button.getText() == "Sam") {
					playerGUI.setIcon(Sam);
					yourCharacter.setText("Your character is Sam");

				}
				else if (button.getText() == "Sofia") {
					playerGUI.setIcon(Sofia);
					yourCharacter.setText("Your character is Sofia");

				}
				else if (button.getText() == "Lily") {
					playerGUI.setIcon(Lily);
					yourCharacter.setText("Your character is Lily");

				}
				else if (button.getText() == "Daniel") {
					playerGUI.setIcon(Daniel);
					yourCharacter.setText("Your character is Daniel");

				}
				else if (button.getText() == "Al") {
					playerGUI.setIcon(Al);
					yourCharacter.setText("Your character is Al");

				}
				else if (button.getText() == "Laura") {
					playerGUI.setIcon(Laura);
					yourCharacter.setText("Your character is Laura");

				}
				else if (button.getText() == "Liz") {
					playerGUI.setIcon(Liz);
					yourCharacter.setText("Your character is Liz");

				}
				else if (button.getText() == "Katie") {
					playerGUI.setIcon(Katie);
					yourCharacter.setText("Your character is Katie");

				}
				else if (button.getText() == "Farah") {
					playerGUI.setIcon(Farah);
					yourCharacter.setText("Your character is Farah");

				}
				else if (button.getText() == "David") {
					playerGUI.setIcon(David);
					yourCharacter.setText("Your character is David");

				}
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
						
			window.remove(questions);
			window.remove(confirmQuest);
			window.setSize(999, 700);
			window.setSize(1000, 700);
						
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
			
		}
	}
	
	//Implement action for confirm button
	static class Confirm implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (character.getText() != "N/A" || realW == true) {
			
				gameStarted = true; 
								
				window.remove(selection);
				window.remove(confirm);
				window.remove(character);
				window.remove(chooseChar);
				window.remove(confirmWhenChosenChar);
				window.remove(guessWhoLogoInitial);
				window.remove(rightPersonPortrait);
				window.remove(leftPersonPortrait);
				
				//window.remove(gamePanel); 
				
				//window.add(whoGoFirst); 
								
				if (realW == false) {
					window.add(playerGUI); 
					window.add(yourCharacter); 
				}
				
				settings.setBounds(850,550, 100, 100);
				window.add(questions);
				window.add(confirmQuest); 
				window.add(computerText); 
				window.add(answer);
				window.add(confirmAnswer); 
				window.add(compCards);
				window.add(gamePanel); 
				window.add(guessWhoLogo);
				window.add(guessAICharacterLabel);
				
				gamePanel.setBounds(20, window.getHeight()/2-300, 400, 500);
	
				window.repaint();
				window.setVisible(true);
								
				selectedQuestion = String.valueOf(questions.getSelectedItem());
		
			}
		}
	}
	
	//Implement action for start player vs computer button
	static class StartPlayerComp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
			compCharacter(); 

			window.remove(options);
			window.remove(guessWhoLogoInitial);
			window.remove(credits);
			window.remove(leftPersonPortrait);
			window.remove(rightPersonPortrait);
			window.add(gamePanel); 
			window.add(selection); 
			selection.setBounds(600, 200, 400, 75);
			window.add(confirm); 
			confirm.setBounds(560, 400, 300, 100);
			window.add(character); 
			character.setBounds(690, 250, 300, 100);
			window.add(guessWhoLogo);
			settings.setBounds(850,550, 100, 100);
			window.add(settings);
			window.setSize(999, 700);
			window.setSize(1000, 700);
			window.repaint();
		}
	}
	
	//Implement action for start real life plays button
	static class StartRealGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
			gameStarted = true; 
			realW = true; 

			compCharacter(); 

			window.remove(options);
			window.remove(credits);
			
			window.add(settings);
			window.add(chooseChar); 
			window.add(confirmWhenChosenChar);
			window.add(confirm); 
			confirm.setBounds(355, 400, 300, 100);
			
			rightPersonPortrait.setBounds(650, 350, 296, 400);

			window.setVisible(true);
			window.repaint();

			selectedQuestion = String.valueOf(questions.getSelectedItem());
		}
	}
	static class clickSettings implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			window.add(settingsMenu, 0);
			window.setVisible(true);
			window.repaint();
		}
	}
	static class clickExitButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			window.remove(settingsMenu);
			window.setVisible(true);
			window.repaint();
		}
	}
	static class clickOn implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			playMusic(backgroundMusic);
		}
	}
	static class clickOff implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stopMusic(backgroundMusic);
		}
	}
	static class quitButtonPressed implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
