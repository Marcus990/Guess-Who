//Assignment Title: Guess Who
//Programmers: Kevin and Marcus
//Date: January 12, 2023
//Programming Language: Java
//Purpose: The purpose of this program is to create a game called Guess Who where a player and an AI competes to see who could guess each other's character first by process of elimination. This project was created as part of Mr. A's final Guess Who Project for the ICS4U1 class. Multiple features were added as required by the rubric in order to create this final project. 

//Import lines
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

//Class header for GuessWho class
public class GuessWho{
	
	//Initiate GUI variables
	static JFrame window = new JFrame("Window");
	
	static JPanel gamePanel = new JPanel(); //The character grid for the user to observe
	static JPanel options = new JPanel();  //Options for which game mode is selected
	static JPanel winLoseScreen = new JPanel();  //Panel that pops up when player wins/lose
	static JPanel settingsMenu = new JPanel(); //Panel for the settings
	static JPanel whoGoFirst = new JPanel(); //Panel for the who go first screen
	
	static JButton charButton[][] = new JButton[4][6]; //2D array of JButtons used to store the buttons the player will click to select a character
	static JButton playerComp = new JButton("     Player vs Computer (Online)     "); // button for player vs computer online
	static JButton realWorld = new JButton("Player vs Computer  (In-Person)"); // button for player vs computer in person
	static JButton confirm = new JButton("Confirm"); //Button for confirm character
	static JButton confirmQuest = new JButton("Confirm"); //Button for confirm question that user will ask
	static JButton confirmChanges = new JButton("Confirm Changes"); //Button to confirm the changes that user made
	static JButton confirmAnswer = new JButton("Confirm"); //Button to confirm the user input answer
	static JButton yes = new JButton("Yes"); //Button for the user to say yes to question asked
	static JButton no = new JButton("No"); //Button for the user to say no to questions
	static JButton restart = new JButton("Restart"); //Button to return to restart the game
	static JButton returnMenu = new JButton("Return to Main Menu"); //Button to return to menu
	static JButton player1First = new JButton("Player (Yourself)"); //Button for you to go first
	static JButton player2First = new JButton("Computer (AI)"); //Button for Ai to go first
	static JButton howToPlay = new JButton("                          How To Play                          "); //Button for how to play gui
	static JButton confirmChar = new JButton("Confirm"); //Button to confirm the selected character
	static JButton on = new JButton("ON"); //Button to toggle the music on
	static JButton off = new JButton("OFF"); //Button to toggle the music off
	static JButton returnToMainMenu = new JButton("Return to Main Menu"); //button to return to main menu
	static JButton quitTheGame = new JButton("Quit the Game"); //Button to terminate the program
	static JButton settings = new JButton(); //Button to open the settings menu
	static JButton exitButton = new JButton(); //Button to edit the settings menu

	static JLabel selectDifficulty = new JLabel("Change Difficulty"); //Panel to contain difficulty mode buttons
	static JButton hardMode = new JButton("Hard"); //Button to select hard mode 
	static JButton normalMode = new JButton("Normal"); //Button the select normal mode
	static JButton easyMode = new JButton("Easy"); //Button to select easy mode
	
	static JLabel timer = new JLabel("Time: 00:00"); //Label to initiate and show the timer
	static JLabel timerBackground = new JLabel(); //Label to hold the timer background image icon that will go behind the timer label
	static JLabel endScreenTime = new JLabel(); //Label holding the text that will display at the end screen showing what the user's final time was
	static JLabel chooseText = new JLabel("Choose Who You Want to Go First!"); //Text to choose who go first
	static JLabel winLose = new JLabel(); //Label that shows when player wins
	static JLabel loseLabel = new JLabel(); //Label that shows when player loses
	static JLabel title = new JLabel("Choose the Game Mode"); //Label to select the game modes
	static JLabel credits = new JLabel("By Marcus Ng and Kevin Wang"); //Label for our group members names
	static JLabel selection = new JLabel("Choose your Character"); //Label to ask user to choose their character
	static JLabel character = new JLabel("N/A"); //Label to show who the player chose
	static JLabel computerText = new JLabel("Your opponent is waiting for your question..."); //Label to show the AI’s output response 
	static JLabel compCards = new JLabel("Your opponent has flipped 0 cards..."); //Label to show the # of cards Ai flipped
	static JLabel guessAICharacterLabel = new JLabel("Have a final answer? Guess it here! Only one try!"); //Label to prompt user to enter their answer 
	static JLabel yourCharacter = new JLabel("Your character is..."); //Label to remind user of who their character is 
	static JLabel playerGUI = new JLabel(); //Label of showing your character as an image 
	static JLabel chooseChar = new JLabel("Please Choose a Character from your Deck of Guess Who Cards!"); //Label to prompt user to choose a card from their deck
	static JLabel confirmWhenChosenChar = new JLabel("Press Confirm when you have Chosen a Character!"); //Label to prompt user to press confirm after selecting character
	static JLabel guessWhoLogo = new JLabel(); //Label that shows the guess who logo
	static JLabel guessWhoLogoInitial = new JLabel(); 
	static JLabel rightPersonPortrait = new JLabel();
	static JLabel leftPersonPortrait = new JLabel();
	static JLabel enterCorrectChar = new JLabel("Was the AI wrong? Enter your correct character below!"); //Prompts user to enter their user if Ai guesses wrong
	static JLabel settingsTitle = new JLabel("Settings"); //Label to show the settings
	static JLabel toggleMusicPrompt = new JLabel("Toggle Music"); //Prompt to show where to toggle music

	static JComboBox questions; //A combo box to store and show all the questions
	//A text area to show what the instructions are
	static JTextArea information = new JTextArea("1) Once you open up the game, take a moment to enjoy the music before \nclicking the game mode you would like to play! \n2) When you're ready, click the difficulty you would like to play against for \nthe AI! \n3) Then, if you are playing the in-person version, select a character from a \nphysical deck of cards before pressing confirm. \n4) Now, it's time to play Guess Who! Select which player you would like to go first before asking your first question. \n5) When your opponent answers, click on characters on the grid at the left\nhand side of your screen to eliminate them. \n6) Then, when your opponent asks you a question, make sure to answer\ntruthfully! \n7) Going back and forth, one player will eventually end up with one\ncharacter by process of elimination. Put your final answer in the answer box and confirm! You only have one try, if you're wrong, you lose! \n8) Hopefully you've enjoyed playing our version of Guess Who! \n9) Have fun!", 5, 100);
	static JTextArea displayWrongQuest = new JTextArea("N/A"); //text area to show what questions were answered wrong
	static JTextArea answer = new JTextArea("Insert your answer here"); //text area to enter who they think the aid character is
	static JTextArea corrChar = new JTextArea("Enter your character here"); //text area to allow users to enter their character for the wrong questions

	static JScrollPane informationScrollBar = new JScrollPane(information); //scroll pane to show the how to play instructions
	static JScrollPane wrongQuestScrollBar = new JScrollPane(displayWrongQuest); //scroll pane to display the questions answered wrong
	
	static Characters[][] chars = new Characters[4][6]; //A character variable to store all 24 characters
	static Characters compChar; //A variable to store the computers character
	static Characters playerChar; //A variable to store the player character
	
	//Initiate standard variables 
	static boolean[][] aiChars = new boolean[4][6]; //a boolean variable to store ai’s flipped/not flipped characters
	static boolean[] plrProperty = new boolean[25]; //a boolean variable to store all properties of user input character
	static boolean bannedQuest[] = new boolean[25]; //a Boolean variable to store the banned 
	static boolean gameStarted = false; //a boolean variable to determine whether or not the game has started
	static boolean won = false; //a boolean variable that activates when the player has won
	static boolean lying = false; //a boolean variable that activates when the player has lost
	static boolean realW = false; //a boolean variable that activates when the player chooses the official game mode
	static boolean easyS = true; //a boolean variable that activates when the player chooses easy mode
	static boolean normalS = false; //a boolean variable that activates when the player chooses normal mode
	static boolean hardS = false; //a boolean variable that activates when the player choose hard mode
	
	static String[] questionList = new String[25]; //a string array that stores all 25 questions
	//a string array that stores all the names of the characters
	static String charNamesArray[] = {"Olivia","Nick","David","Leo","Emma","Ben","Eric","Rachel","Amy","Mike","Gabe","Jordan","Carmen", "Joe","Mia","Sam","Sofia","Lily","Daniel","Al","Laura","Liz","Katie","Farah"};
	static String selectedQuestion; //a string variable that stores the player’s selected question
	static String aiSelectedQuestion; //a string variable that stores the ai’s selected question
	
	static int aiCards = 0; //an integer variable that stores the # of cards that the ai has flipped
	static int min = 0; //An integer variable that stores the minutes for the timer; 
	
	static ArrayList<ImageIcon> images = new ArrayList<ImageIcon>(); //An arraylist that stores all the images of the characters
	static ArrayList<Integer> savedIndex = new ArrayList<Integer>(); //an arraylist that stores the saved index of the question that the ai asked
	static ArrayList<Boolean> savedAns = new ArrayList<Boolean>(); //an arraylist that stores the saved answers of the question that the ai asked
	static ArrayList<String> savedQuest = new ArrayList<String>(); //an arraylist that stores the questions that the player asked

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
	static ImageIcon PlaidBackground = new ImageIcon("OfficialPlaidBackground.png");
	static ImageIcon GuessWhoLogo = new ImageIcon("GuessWhoLogo.png");
	static ImageIcon RightPersonPortrait = new ImageIcon("RightPersonPortrait.png");
	static ImageIcon LeftPersonPortrait = new ImageIcon("LeftPersonPortrait.png");
	static ImageIcon settingsImage = new ImageIcon("Settings.png");
	static ImageIcon ExitButton = new ImageIcon("Exit.png");
	static ImageIcon TimerBackground = new ImageIcon("TimerBackground.png");
	
	static Font font; // a Font variable that stores the general font used in the program
	static Font timerFont; // another Font variable that stores the general font used in the program
	
	static File backgroundMusicPath = new File("GuessWhoMusic.wav"); //The location to the wav background music file 
	private static Clip backgroundMusic; //The clip that stores the actual music
	
	static long getStartTime = System.currentTimeMillis(); 
	
	//Main method
	public static void main(String[] args) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException{
		
		//Initiate background music 
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(backgroundMusicPath);
		backgroundMusic = AudioSystem.getClip(); 
		backgroundMusic.open(audioInput);
		
		FloatControl gainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-10.0f);
		
		loopMusic(backgroundMusic);
		
		//Initiate custom fonts
		font = Font.createFont(Font.TRUETYPE_FONT, new File("GuessWhoFont.otf")).deriveFont(20f);
		timerFont = Font.createFont(Font.TRUETYPE_FONT, new File("GuessWhoFont.otf")).deriveFont(40f);
		
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

		//Set properties for the Change Difficulty Option in Settings
		selectDifficulty.setFont(font);
		selectDifficulty.setBounds(190, 180, 200, 50);
		settingsMenu.add(selectDifficulty);
		
		easyMode.setFont(font);
		easyMode.setBounds(400, 180, 100, 50);
		easyMode.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		easyMode.setOpaque(false);
		easyMode.addActionListener(new easyButton());
		settingsMenu.add(easyMode);
		
		normalMode.setFont(font);
		normalMode.setBounds(515, 180, 100, 50);
		normalMode.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		normalMode.addActionListener(new normalButton());
		settingsMenu.add(normalMode);
		
		hardMode.setFont(font);
		hardMode.setBounds(630, 180, 100, 50);
		hardMode.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		hardMode.addActionListener(new hardButton());
		settingsMenu.add(hardMode);
		
		//Set properties for the Return to Main Menu JButton in Settings
		returnToMainMenu.setFont(font);
		returnToMainMenu.setBounds(275, 275, 250,75);
		returnToMainMenu.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		returnToMainMenu.addActionListener(new Restart());
		settingsMenu.add(returnToMainMenu);
		
		//Sets properties for the timer label and background
		timer.setFont(timerFont); //add
		timer.setBounds(25, 580, 400, 100); //add
		timerBackground.setIcon(TimerBackground); //add
		timerBackground.setBounds(0, 550, 400, 170); //add
		
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
		winLoseScreen.setLayout(null);
		winLoseScreen.setOpaque(false);
		winLoseScreen.setBounds(0, 0, 1000, 700);
		winLoseScreen.add(winLose);
		winLoseScreen.add(restart); 
		winLoseScreen.add(enterCorrectChar); 
		winLoseScreen.add(corrChar);
		winLoseScreen.add(loseLabel);
		winLoseScreen.add(confirmChar);
		winLoseScreen.add(endScreenTime);
		winLose.setFont(font);
		winLose.setBounds(450, 200, 400, 50);
		loseLabel.setFont(font);
		loseLabel.setBounds(275, 267, 700, 50);
		corrChar.setBounds(200, 400, 400, 50);
		corrChar.setFont(font); 
		enterCorrectChar.setBounds(200, 300, 900, 100);
		enterCorrectChar.setFont(font);
		winLoseScreen.add(confirmChar);
		confirmChar.setFont(font);
		confirmChar.setBounds(600, 400, 150, 50);
		confirmChar.addActionListener(new EnterCorrectAns()); 
		endScreenTime.setFont(font);
		endScreenTime.setBounds(390, 500, 400, 50);
		restart.setFont(font); 
		restart.setBounds(400, 550, 200, 50);
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
		whoGoFirst.setOpaque(false);
		whoGoFirst.setBounds(0, 0, 1000, 700);
		whoGoFirst.setLayout(null);
		
		chooseText.setBounds(320, 200, 500, 100);
		chooseText.setFont(font); 
		whoGoFirst.add(chooseText);	

		player1First.setBounds(275, 300, 200, 70);
		player1First.setFont(font);
		player1First.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		player1First.addActionListener(new Player1First());
		player1First.setOpaque(true);	
		whoGoFirst.add(player1First);
		
		player2First.setBounds(515, 300, 200, 70);
		player2First.setFont(font);
		player2First.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		player2First.addActionListener(new Player2First()); 
		player2First.setOpaque(true);
		whoGoFirst.add(player2First);
				
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
		
		//Initiates the timer
		while(true) {
			getTimeInSeconds(); 
			window.repaint();
		}
	}
	
	/* Description: A method that starts the timer for each second
	@param
	void no parameters
	@return
	Void no return value
	*/
	public static void getTimeInSeconds() {
		long getEndTime = System.currentTimeMillis(); //Get the current time from the system 
		int secs = (int)((getEndTime - getStartTime)/1000); //Get the number of seconds passed
		String sec = String.valueOf(secs); //Convert int to string to display on label
		//Restarts the second timer once 60 seconds have passed and adds one to the minute timer
		if (secs >= 60) {
			min++; 
			getStartTime = System.currentTimeMillis();
		}
		//Adds a 0 in front of the minutes and seconds label 
		else if (secs < 10 && min < 10) {
				
			timer.setText("Time: 0" + min + ":" + "0" + sec);

		}
		//Adds a 0 in front of the minutes label only
		else if (secs >= 10 && min < 10){
				
			timer.setText("Time: 0" + min + ":" + sec);

		}
		//Adds a 0 in front of the seconds label only
		else if (secs < 10 && min >= 10) {
			
			timer.setText("Time: "+min + ":" + "0" + sec);
			
		}
		//No 0’s required to be added
		else if (secs >= 10 && min >= 10) {
			
			timer.setText("Time: "+min + ":" + sec); 
			
		}
	}
	/* Description: A method that starts playing the input music
	@param
	Clip clip: stores a music clip; 
	@return
	Void no return value
	*/
	public static void playMusic(Clip clip) {
		
		clip.start(); 
	
	}
	/* Description: A method that starts looping the input music
	@param
	Clip clip: stores a music clip; 
	@return
	Void no return value
	*/
	public static void loopMusic(Clip clip) {
		
		clip.loop(clip.LOOP_CONTINUOUSLY);

	}
	/* Description: A method that stops the input music
	@param
	Clip clip: stores a music clip; 
	@return
	Void no return value
	*/
	public static void stopMusic(Clip clip) {
		
		clip.stop();
		
	}
	/* Description: A method that chooses random questions from the question list for the ai to ask
	@param
	Void no parameters
	@return
	Void no return value
	*/
	public static void aiSelectsEasyQuestion() {
		
		//Gets random index
		int ranNum = (int)(Math.random()*(25));
		
		//Ai selects question based on index
		aiSelectedQuestion = questionList[ranNum]; 
		
		//Saves the question from ai
		savedQuest.add(aiSelectedQuestion);
		
	}
	/* Description: A method that chooses random questions for the ai to ask but each question can only be asked once
	@param
	Clip clip: stores a music clip; 
	@return
	Void no return value
	*/
	public static void aiSelectsNormalQuestion() {
		
		//Selects question based on a random index and bans the question from appearing again
		for (int i = 0; i < bannedQuest.length; i++) {
			int ranNum = (int)(Math.random()*(25));
			if (bannedQuest[ranNum] == false) {
				
				aiSelectedQuestion = questionList[ranNum]; 
				savedQuest.add(aiSelectedQuestion);
				bannedQuest[ranNum] = true;
				break;

			}
		}
	}
	/* Description: A method that chooses the question that corresponds to the most common property from all the characters so that it would eliminate most characters. The Ai also bans the questions that were already answered. 
	@param
	Clip clip: stores a music clip; 
	@return
	Void no return value
	*/
	public static void aiSelectsHardQuestion() {
		
		//Initiate property variable for characters
		int propertyCount[] = new int[25];
		
		//Sets the property variables to 0
		for (int i = 0; i < 25; i++) {
			propertyCount[i] = 0; 
		}
		
		//Initiate index variable
		int index;
		
		
		//Adds to the propertyCount based on the # of characters with the same property
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				
				//Property count for eye colors
				if (chars[i][j].getEyeColor() == "Brown" && aiChars[i][j] == true) {
					propertyCount[0]++; 
				}
				if (chars[i][j].getEyeColor() == "Green" && aiChars[i][j] == true) {
					propertyCount[1]++; 
				}
				if (chars[i][j].getEyeColor() == "Blue" && aiChars[i][j] == true) {
					propertyCount[2]++; 
				}
				//Property count for the gender
				if (chars[i][j].getGender() == true && aiChars[i][j] == true) {
					propertyCount[3]++; 
				}
				if (chars[i][j].getGender() == false && aiChars[i][j] == true) {
					propertyCount[4]++; 
				}
				//Property count for the skin tone
				if (chars[i][j].getSkinTone() == "Light Skin" && aiChars[i][j] == true) {
					propertyCount[5]++; 
				}
				if (chars[i][j].getSkinTone() == "Dark Skin" && aiChars[i][j] == true) {
					propertyCount[6]++; 
				}
				//Property count for the hair colors
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
				//Property count for the facial hair
				if (chars[i][j].getFacialHair() == true && aiChars[i][j] == true) {
					propertyCount[12]++; 
				}
				if (chars[i][j].getFacialHair() == false && aiChars[i][j] == true) {
					propertyCount[13]++; 
				}
				//Property count for glasses
				if (chars[i][j].getGlasses() == true && aiChars[i][j] == true) {
					propertyCount[14]++; 
				}
				if (chars[i][j].getGlasses() == false && aiChars[i][j] == true) {
					propertyCount[15]++; 
				}
				//Property count for visible teeth
				if (chars[i][j].getVisibleTeeth() == true && aiChars[i][j] == true) {
					propertyCount[16]++; 
				}
				if (chars[i][j].getVisibleTeeth() == false && aiChars[i][j] == true) {
					propertyCount[17]++; 
				}
				//Property count for wearing hat
				if (chars[i][j].getWearHat() == true && aiChars[i][j] == true) {
					propertyCount[18]++; 
				}
				if (chars[i][j].getWearHat() == false && aiChars[i][j] == true) {
					propertyCount[19]++; 
				}
				//Property count for hair length
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
				//Property count for piercings
				if (chars[i][j].getPiercings() == true && aiChars[i][j] == true) {
					propertyCount[24]++; 
				}
		
			}
		}
		
		//Initiate the variable that sets most number
		int mostNum = 0;
		index = 0; 
		
		//Gets the property that appeared most from the remainder characters
		for (int i = 0 ; i < 24; i++) {
			
			if (propertyCount[i] >= mostNum) {
				
				//Sets the index of the question that would be asked and checks whether or not it is banned
				if (bannedQuest[i] == false && propertyCount[i] != 24-aiCards) {
					index = i;
					mostNum = propertyCount[i]; 
				}
				
			}
		}
		
		System.out.println(24-aiCards + " " + propertyCount[index]); 
				
		//Sets the ai selected question from the index of the property that appeared the most
		aiSelectedQuestion = questionList[index]; 
		
		//Saves the ai questions
		savedQuest.add(aiSelectedQuestion); 
		
		//Bans the question that have already appeared
		bannedQuest[index] = true; 
		
		//Bans the questions that correspond with the already banned characters 
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
	/* Description: A method that generates a random character for the Ai 
	@param
	Clip clip: stores a music clip; 
	@return
	Void no return value
	*/
	public static void compCharacter() {
				
		int iValue = (int)(Math.random()*4); 
		int jValue = (int)(Math.random()*6);
		compChar = chars[iValue][jValue];
		System.out.println(compChar.getName()); 		
	}
	//An action listener class that gets the user input character and outputs the questions that they answered incorrectly
	static class EnterCorrectAns implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Sets the plrChar variable as a placeholder
			Characters plrChar = null; 

			//Gives the plrChar variable the value of the player entered input if it exists
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					System.out.println(chars[i][j].getName() + " " + corrChar.getText());
					if (chars[i][j].getName().equals(corrChar.getText())) {
						plrChar = chars[i][j]; 
						
					}
					
				}
			}
			
			//Initiates all player property as false
			for (int i = 0; i < 24; i++) {
				plrProperty[i] = false; 
			}
			
			//Sets the input players property 
			if (plrChar != null) {
				
				//Sets the properties for eye color
				if (plrChar.getEyeColor() == "Brown") {
					plrProperty[0] = true; 
				}
				if (plrChar.getEyeColor() == "Green") {
					plrProperty[1] = true; 
				}
				if (plrChar.getEyeColor() == "Blue") {
					plrProperty[2] = true; 
				}
				//Sets the properties for gender
				if (plrChar.getGender() == true) {
					plrProperty[3] = true; 
				}
				if (plrChar.getGender() == false) {
					plrProperty[4] = true; 
				}
				//Sets the properties for skin tone
				if (plrChar.getSkinTone() == "Light Skin") {
					plrProperty[5] = true; 
				}
				if (plrChar.getSkinTone() == "Dark Skin") {
					plrProperty[6] = true; 
				}
				//Sets the properties for hair color
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
				//Sets the properties for facial hair
				if (plrChar.getFacialHair() == true) {
					plrProperty[12] = true; 
				}
				if (plrChar.getFacialHair() == false) {
					plrProperty[13] = true; 
				}
				//Sets the properties for glasses
				if (plrChar.getGlasses() == true) {
					plrProperty[14] = true; 
				}
				if (plrChar.getGlasses() == false) {
					plrProperty[15] = true; 
				}
				//Sets the properties for visible teeth
				if (plrChar.getVisibleTeeth() == true) {
					plrProperty[16] = true; 
				}
				if (plrChar.getVisibleTeeth() == false) {
					plrProperty[17] = true; 
				}
				//Sets the properties for wearing a hat
				if (plrChar.getWearHat() == true) {
					plrProperty[18] = true; 
				}
				if (plrChar.getWearHat() == false) {
					plrProperty[19] = true; 
				}
				//Sets the properties for the hair length
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
				//Sets the properties for piercings
				if (plrChar.getPiercings() == true) {
					plrProperty[24] = true; 
				}
			}
			//Output that the character does not exist if plrChar is null
			else {
				enterCorrectChar.setText("The character you entered does not exist!");
			}
			
			//Initiate a place holder for the questions answered wrong
			String temp = ""; 

			//Clears the saved index so it does not overlap on itself
			savedIndex.clear();	
			
			//Adds the index of the saved questions
			for (int i = 0; i < savedQuest.size(); i++) {
				for (int j = 0; j < 24; j++) {
					
					if (savedQuest.get(i) == questionList[j]) {
						
						savedIndex.add(j); 
						System.out.println(questionList[j]); 
						
					}
					
				}
			}
			
			//Adds the questions that were answered wrong to the temp variable
			for (int i = 0; i < savedIndex.size(); i++) {
				
				System.out.println(savedIndex.get(i)); 
				
				for (int j = 0; j < 24; j++) {
					
					//If player answered no but the answer was yes
					if (savedIndex.get(i) == j && plrProperty[j] == true && savedAns.get(i) == false) {
						
						temp += questionList[j];
						temp += " \n ";
						temp += "You entered (false), the actual answer (true)"; 
						temp += " \n ";
						temp += " \n ";
						
					}
					//If player answered yes but the answer was no
					else if (savedIndex.get(i) == j && plrProperty[j] == false && savedAns.get(i) == true) {
						
						temp += questionList[j];
						temp += " \n ";
						temp += "You entered (true), the actual answer (false)";
						temp += " \n ";
						temp += " \n ";
						
					}
				}	
			}
			
			//Initiate a boolean that activates when plrChar is null
			boolean charNameExists = false;

			//Adds the question label to the scroll bar if plrChar is not null
			for(int i = 0; i < charNamesArray.length; i++) {
				if(corrChar.getText().equals(charNamesArray[i])) {
					window.add(wrongQuestScrollBar, 0); 
					displayWrongQuest.setText(temp);
					charNameExists = true;
				}
			}
			
			//Remove the question scroll bar if plrChar is null
			if(!charNameExists) {
				window.remove(wrongQuestScrollBar);
			}
		}	
	}
	//An action listener class called Instructions that displays the How to Play Menu GUI if the player clicks on that button in the main menu.
	static class Instructions implements ActionListener {
		//When the action is performed and the button is clicked, the window will remove the main menu and replace it with the how to play menu GUI
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
	//An action listener class that is called when the player clicks the "Player" would like to go first button. The program will then update the GUI so that the player can ask the first question
	static class Player1First implements ActionListener {
		public void actionPerformed(ActionEvent e) {
							
			getStartTime = System.currentTimeMillis();
			gameStarted = true; 
			min = 0; 
			
			if (easyS == true) {
				aiSelectsEasyQuestion();
			}
			if (normalS == true) {
				aiSelectsNormalQuestion();
			}
			if (hardS == true) {
				aiSelectsHardQuestion(); 
			}
			
			settings.setBounds(850,550, 100, 100);
			window.add(timer); 
			window.add(timerBackground);
			window.add(questions);
			window.add(confirmQuest); 
			window.add(computerText); 
			window.add(answer);
			window.add(confirmAnswer); 
			window.add(compCards);
			window.add(gamePanel); 
			window.remove(guessWhoLogoInitial);
			window.add(guessWhoLogo);
			settings.setBounds(850,550, 100, 100);
			window.add(settings);
			window.add(guessAICharacterLabel);
			window.remove(whoGoFirst);
			
			if (realW == false) {
				window.add(playerGUI); 
				window.add(yourCharacter); 
			}
			
			gamePanel.setBounds(20, window.getHeight()/2-300, 400, 500);

			window.repaint();
			window.setVisible(true);
							
			selectedQuestion = String.valueOf(questions.getSelectedItem());
	
		}
	}
	//An action listener class that is called when the player clicks the "Computer" would like to go first button. The program will then update the GUI so that the computer can ask the first question
	static class Player2First implements ActionListener {
		public void actionPerformed(ActionEvent e) {
							
			getStartTime = System.currentTimeMillis();
			gameStarted = true; 
			min = 0;
			
			if (easyS == true) {
				aiSelectsEasyQuestion();
			}
			if (normalS == true) {
				aiSelectsNormalQuestion();
			}
			if (hardS == true) {
				aiSelectsHardQuestion(); 
			}
			
			window.remove(confirmChanges);
			computerText.setText("Your opponent is coming up with a question...");
			computerText.setText(aiSelectedQuestion);
			window.add(no);
			window.add(yes);
			window.add(timer); 
			window.add(timerBackground);
			window.add(computerText); 
			window.add(answer);
			window.add(confirmAnswer); 
			window.add(compCards);
			window.add(gamePanel); 
			window.remove(guessWhoLogoInitial);
			window.add(guessWhoLogo);
			settings.setBounds(850,550, 100, 100);
			window.add(settings);
			window.add(guessAICharacterLabel);
			window.remove(whoGoFirst);
			
			gamePanel.setBounds(20, window.getHeight()/2-300, 400, 500);
			
			if (realW == false) {
				window.add(playerGUI); 
				window.add(yourCharacter); 
			}
			
			window.repaint();
			window.setVisible(true);
							
			selectedQuestion = String.valueOf(questions.getSelectedItem());
	
			
		}
	}
	//An action listener class that is called when the player clicks the "Easy" difficulty option in the Settings menu. The program will then change all questions asked by the AI to be easy and random questions.
	static class easyButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			easyMode.setBackground(Color.green);
			normalMode.setBackground(null);
			hardMode.setBackground(null);
			
			easyS = true;
			normalS = false;
			hardS = false;
			
		}
	}
	//An action listener class that is called when the player clicks the "Normal" difficulty option in the Settings menu. The program will then change all questions asked by the AI to be normal questions that are not too easy, but are also not too hard as well.
	static class normalButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			normalMode.setBackground(Color.green);
			easyMode.setBackground(null);
			hardMode.setBackground(null);
			
			normalS = true;
			easyS = false;
			hardS = false;
			
		}
	}
	//An action listener class that is called when the player clicks the "Hard" difficulty option in the Settings menu. The program will then change all questions asked by the AI to be hard, and all questions will follow an algorithm that asks questions that eliminate the most characters possible to guess the character in the shortest amount of rounds and tries.
	static class hardButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			hardMode.setBackground(Color.green);
			normalMode.setBackground(null);
			easyMode.setBackground(null);
			
			hardS = true;
			normalS = false;
			easyS = false; 
			
		}
	}
	//An action listener class called "Restart" that is called whenever the player clicks the "Restart" button or the "Return to Main Menu" button. This class will update all the GUI to the main menu options and will get rid of everything that was on the window before. Everything will essentially be reset so the player can restart and play the game again.
	static class Restart implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			savedAns.clear();
			savedQuest.clear();
			savedIndex.clear();

			options.setVisible(true);
			gamePanel.setVisible(true);
			confirmChar.setVisible(true);
			confirm.setVisible(true);
			confirmQuest.setVisible(true);
			confirmAnswer.setVisible(true);
			questions.setVisible(true);
			confirmChanges.setVisible(true);
			yes.setVisible(true); 
			no.setVisible(true);
			selectDifficulty.setVisible(true);
			easyMode.setVisible(true);
			normalMode.setVisible(true);
			hardMode.setVisible(true);
			
			window.getContentPane().removeAll();
			
			compCards.setText("Your opponent has flipped 0 cards...");
			computerText.setText("Your opponent is waiting for your question...");
			enterCorrectChar.setText("Was the AI wrong? Enter your correct character below!");
			
			winLoseScreen.add(corrChar);
			winLoseScreen.add(confirmChar);
			winLoseScreen.add(enterCorrectChar);
			
			winLose.setText("");
			loseLabel.setText("");
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
	//An action listener class called ConfirmAnswer that is called when the player enters their final answer for the computer's character, and will display if the player guessed right or wrong. The class will update the GUI and show the win/lose screen depending on if the player guessed the AI's character right or wrong.
	static class ConfirmAnswer implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
			
			if (answer.getText().equals(compChar.getName())) {
				window.repaint();
				winLose.setText("You won!");
				loseLabel.setText("You correctly guessed the AI's character as: "+compChar.getName());				
				winLose.remove(corrChar);
			}
			else {
				winLose.setText("You lost!");
				loseLabel.setText("The AI's character was: " + compChar.getName());
			}
			window.getContentPane().removeAll();
			window.add(winLoseScreen); 
			window.add(guessWhoLogoInitial);
			winLoseScreen.remove(corrChar);
			winLoseScreen.remove(confirmChar);
			winLoseScreen.remove(enterCorrectChar);
			endScreenTime.setText("Your Final "+timer.getText());
			window.setVisible(true);
			window.repaint();
		}
	}
	//An action listener class called "YesButton", that is called when the player is answering the AI's question and picks "Yes". If the player is lying in the online mode, the program will tell the player that they are lying. Otherwise, in the in-person mode, the program will not know if the player is lying or not until the end when the player enters their correct character.
	static class YesButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
						
			//Initiates the variable that gets if the question was answered wrong
			lying = false; 

			//Saves the answer of the player
			savedAns.add(true);
			
			//Flips the cards from the ai based on the answer of the player
			if (aiSelectedQuestion == questions.getItemAt(0)) {
				
				//Set the computer text to prompt the user to try again if the player chooses wrong answer
				if (playerChar.getEyeColor() != "Brown" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions
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
								//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getEyeColor() != "Green" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
								//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getEyeColor() != "Green" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
								//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getGender() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
								//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getGender() != false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
								//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getSkinTone() != "Light Skin" && realW == false) {
					
					 
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getSkinTone() != "Dark Skin" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairColor() != "Black" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
								//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairColor() != "Brown" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
								//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairColor() != "Ginger" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairColor() != "Blonde" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
								//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairColor() != "White" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getFacialHair() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getFacialHair() != false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getGlasses() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getGlasses() != false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getVisibleTeeth() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getVisibleTeeth() != false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getWearHat() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getWearHat() != false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairLength() != "Short" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairLength() != "Tied" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairLength() != "Long" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairLength() != "Bald" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getPiercings() != true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			
			//Conitnues the round once player has answered correctly
			if (lying == false) {
				window.remove(yes);
				window.remove(no);
				window.add(questions);
				window.add(confirmQuest); 
				window.repaint();
				computerText.setText("Your opponent is waiting for your question...");
			}
			
			//Resets the int ai flipped cards to 0 so it does not overlap
			aiCards = 0; 
			
			//Adds to the ai flipped cards 
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					if (aiChars[i][j] == false) {
						aiCards++; 
					}
				}
			}

			//Set the text label of the ai cards 
			compCards.setText("Your opponent has flipped " + aiCards + " cards...");

			
			if (aiCards >= 23) {
				window.getContentPane().removeAll();
				
				Characters Placeholder = new Characters("Placeholder", "Blank", false, "Blank", "Blank", false, false, false, false, "Blank", false); 
				Characters guessChar = Placeholder; 
				
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 6; j++) {
						
						if (aiChars[i][j] == true) {
							guessChar = chars[i][j]; 
						}
						
					}
				}
				
				if(guessChar!=Placeholder) {
					winLose.setText("You lost!");
					loseLabel.setText("The AI guessed your card was: " + guessChar.getName());
					window.add(guessWhoLogoInitial);
					window.add(winLoseScreen); 
					window.repaint();
					window.setVisible(true);
				}
				else 
					loseLabel.setText("None of the characters matched your description!");
				endScreenTime.setText("Your Final "+timer.getText());
			}
		}
	}
	//An action listener class called "NoButton", that is called when the player is answering the AI's question and picks "No". If the player is lying in the online mode, the program will tell the player that they are lying. Otherwise, in the in-person mode, the program will not know if the player is lying or not until the end when the player enters their correct character.
	static class NoButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			lying = false; 
			savedAns.add(false); 
			
			if (aiSelectedQuestion == questions.getItemAt(0)) {
				//Set the computer text to prompt the user to try again if the player chooses wrong answer
				if (playerChar.getEyeColor() == "Brown" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getEyeColor() == "Green" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getEyeColor() == "Green" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getGender() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getGender() == false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getSkinTone() == "Light Skin" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getSkinTone() == "Dark Skin" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairColor() == "Black" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairColor() == "Brown" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairColor() == "Ginger" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairColor() == "Blonde" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairColor() == "White" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getFacialHair() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getFacialHair() == false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getGlasses() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getGlasses() == false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer


				if (playerChar.getVisibleTeeth() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getVisibleTeeth() == false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getWearHat() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getWearHat() == false && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairLength() == "Short" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairLength() == "Tied" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairLength() == "Long" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getHairLength() == "Bald" && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
				//Set the computer text to prompt the user to try again if the player chooses wrong answer

				if (playerChar.getPiercings() == true && realW == false) {
					
					computerText.setText("Stop lying!");
					lying = true; 
					
				} 
				//Flips the cards from the ai based on the questions

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
			
			//Adds the label for the next round if the user input the correct answer
			if (lying == false) {
				window.remove(yes);
				window.remove(no);
				window.add(questions);
				window.add(confirmQuest); 
				window.repaint();
				computerText.setText("Your opponent is waiting for your question...");
			}
			
			//Resets the ai cards to 0 so it doesn’t overlap
			aiCards = 0; 
			
			//Adds to the ai cards for each flipped character
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 6; j++) {
					
					if (aiChars[i][j] == false) {
						aiCards++; 
					}
				}
			}
			
			//Set the computer text to the flipped cards
			compCards.setText("Your opponent has flipped " + aiCards + " cards...");


			if (aiCards >= 23) {
				window.getContentPane().removeAll();
				window.add(winLoseScreen); 
				window.add(guessWhoLogoInitial);
				window.repaint();
				Characters Placeholder = new Characters("Placeholder", "Blank", false, "Blank", "Blank", false, false, false, false, "Blank", false); 
				Characters guessChar = Placeholder; 
				
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 6; j++) {
						
						if (aiChars[i][j] == true) {
							guessChar = chars[i][j]; 
						}
						
					}
				}
				
				if (guessChar != Placeholder) {
					winLose.setText("You lost!");		
					loseLabel.setText("The AI guessed your character was: " + guessChar.getName());
				}
				else
					loseLabel.setText("None of the characters matched your description!");
				endScreenTime.setText("Your Final "+timer.getText());
			}
			
		}
	}
	//An action listener class for the "Confirm Changes" button that is called when the confirm changes button is pressed by the player. This button is pressed after the AI answers the question and the player has to press on characters on the left to eliminate them. The player will press this button when they have finished eliminating players and can confirm their changes.
	static class ConfirmChanges implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (easyS == true) {
				aiSelectsEasyQuestion();
			}
			if (normalS == true) {
				aiSelectsNormalQuestion();
			}
			if (hardS == true) {
				aiSelectsHardQuestion(); 
			}
			
			window.remove(confirmChanges);
			window.repaint();
			computerText.setText("Your opponent is coming up with a question...");
			computerText.setText(aiSelectedQuestion);
			window.add(no);
			window.add(yes);
					
		}
	}
	
	//An action listener class for the CharSelection button that is pressed after the player selects their character. This only applies to the Online mode. 
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
			else if (button.getBackground() != Color.BLACK){
				button.setIcon(null);
				button.setBackground(Color.BLACK);
			}
			else if (button.getBackground() == Color.BLACK) {
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
	
	//An action listener class used for updating the GUI based on the AI's answer to the player's question. This class will set the text on the screen of the AI's answer, which will then allow the player to start eliminating characters.
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
	//An action listener class that is called when the player needs to press the "Confirm" button to confirm that they have chosen a character, either online or in-person with a physical deck of Guess Who character cards.
	static class Confirm implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (character.getText() != "N/A" || realW == true) {
				window.remove(selection);
				window.remove(confirm);
				window.remove(character);
				window.remove(chooseChar);
				window.remove(confirmWhenChosenChar);
				window.remove(guessWhoLogo);
				window.add(guessWhoLogoInitial);
				window.remove(rightPersonPortrait);
				window.remove(leftPersonPortrait);
				window.remove(gamePanel); 
				window.add(whoGoFirst); 
				window.setVisible(true);						
				window.repaint(); 
			}			
		}
	}
	//An action listener class that is called when the player selects they want to player the "Player vs Computer Online" game mode. 
	static class StartPlayerComp implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	
			compCharacter(); 

			window.remove(options);
			window.remove(guessWhoLogoInitial);
			window.remove(credits);
			window.remove(leftPersonPortrait);
			window.remove(rightPersonPortrait);
			window.add(guessWhoLogo);
			window.add(gamePanel); 
			window.add(selection); 
			selection.setBounds(600, 200, 400, 75);
			window.add(confirm); 
			confirm.setBounds(560, 400, 300, 100);
			window.add(character); 
			character.setBounds(690, 250, 300, 100);
			settings.setBounds(850,550, 100, 100);
			window.setSize(999, 700);
			window.setSize(1000, 700);
			window.repaint();
		}
	}
	//An action listener class that is called when the player selects they want to play the "Player vs Computer In-Person" Mode. 
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
	//An action listener class that is called when the player clicks the settings menu. This class will display the Settings Menu GUI when the settings icon is pressed.
	static class clickSettings implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			window.add(settingsMenu, 0);
			window.setVisible(true);
			options.setVisible(false);
			gamePanel.setVisible(false);
			confirmChar.setVisible(false);
			confirm.setVisible(false);
			confirmQuest.setVisible(false);
			confirmChanges.setVisible(false);
			yes.setVisible(false); 
			no.setVisible(false);
			confirmAnswer.setVisible(false);
			questions.setVisible(false);
			
			window.repaint();
		}
	}
	//An action listener class that is called when the player presses the "X" button in the Settings Menu that will then exit out of the Settings Menu when the X is pressed. 
	static class clickExitButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			window.remove(settingsMenu);
			window.setVisible(true);
			options.setVisible(true);
			gamePanel.setVisible(true);
			confirmChar.setVisible(true);
			confirm.setVisible(true);
			confirmQuest.setVisible(true);
			confirmAnswer.setVisible(true);
			questions.setVisible(true);
			confirmChanges.setVisible(true);
			no.setVisible(true);
			yes.setVisible(true);
			window.repaint();
		}
	}
	//An action listener class that is called when the player clicks they want the Music "ON" in the Settings Menu.
	static class clickOn implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			loopMusic(backgroundMusic);
		}
	}
	//An action listener class that is called when the player clicks they want the Music "OFF" in the Settings Menu.
	static class clickOff implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			stopMusic(backgroundMusic);
		}
	}
	//An action listener class that is called when the player clicks they want to Quit the game in the Settings Menu. This class will exit out of the program. 
	static class quitButtonPressed implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
