//Assignment Title: Guess Who
//Programmers: Marcus Ng and Kevin Wang
//Programming Language: Java
//Date: January 12, 2023
//Purpose: The purpose of this program is to store the Characters class, which is used in conjunction with the GuessWho class in the other program for the overall Guess Who Game. This game is part of the final project for Mr. A's ICS4U1 class.
//The main purpose of this class is to store the Constructor method for Characters and also to store the getter methods for all the attributes of the 24 characters in the Guess Who game. 

//Class header for the Characters class
public class Characters {
	
	private String name; //class/global variable named name and of type String
	private String eyeColor; //class/global variable named eyeColor and of type String
	private String hairLength; //class/global variable named hairLength and of type String
	private boolean wearingHat; //class/global variable named wearingHat and of type boolean
	private boolean visibleTeeth; //class/global variable named visibleTeeth and of type boolean
	private boolean glasses; //class/global variable named glasses and of type boolean
	private boolean facialHair; //class/global variable named facialHair and of type boolean
	private boolean gender; //class/global variable named name gender of type boolean
	private boolean piercings; //class/global variable named piercings and of type boolean
	private String skinTone; //class/global variable named skinTone and of type String
	private String hairColor; //class/global variable named hairColor and of type String

	/* Description: A constructor method that takes in several attributes and sets the attributes as the attributes of the character that is being constructed with this constructor method.
	@param These are the parameters that will be passed into the constructor method to be set to the character's attributes
	String fname, String feyeColor, boolean fgender, String fskinTone, String fhairColor, boolean ffacialHair, boolean fglasses, boolean fvisibleTeeth, boolean fwearingHat, String fhairLength, boolean fpiercings
	@return
	Void no return value
	*/
	public Characters(String fname, String feyeColor, boolean fgender, String fskinTone, String fhairColor, boolean ffacialHair, boolean fglasses, boolean fvisibleTeeth, boolean fwearingHat, String fhairLength, boolean fpiercings) {

		//Setting the attributes of the characters to the paramters passed into the constructor
		name = fname; 
		eyeColor = feyeColor;
		hairLength = fhairLength;
		wearingHat = fwearingHat;
		visibleTeeth = fvisibleTeeth;
		glasses = fglasses;
		facialHair = ffacialHair; 
		gender = fgender; 
		skinTone = fskinTone; 
		hairColor = fhairColor;
		piercings = fpiercings; 
	}
	/* Description: A getter method that retrieves the name of the character
	@param
	none
	@return
	String name
	*/
	public String getName() {
		return name; 
	}
	/* Description: A getter method that retrieves the gender of the character
	@param
	none
	@return
	boolean gender
	*/
	public boolean getGender() {
		
		return gender; 
	}
	/* Description: A getter method that retrieves the hair color of the character
	@param
	none
	@return
	String hairColor
	*/
	public String getHairColor() {
		
		return hairColor; 
	}
	/* Description: A getter method that retrieves the skin tone of the character
	@param
	none
	@return
	String skinTone
	*/
	public String getSkinTone() {
	
		return skinTone; 
	}
	/* Description: A getter method that retrieves the facial hair of the character
	@param
	none
	@return
	boolean facialHair
	*/
	public boolean getFacialHair() {
	
		return facialHair; 
	}
	/* Description: A getter method that retrieves the glasses of the character
	@param
	none
	@return
	boolean glasses
	*/
	public boolean getGlasses() {
	
		return glasses;
	}
	/* Description: A getter method that retrieves the visible teeth of the character
	@param
	none
	@return
	boolean visibleTeeth
	*/
	public boolean getVisibleTeeth() {
	
		return visibleTeeth; 
	}
	/* Description: A getter method that retrieves the wearing hat of the character
	@param
	none
	@return
	boolean wearingHat
	*/
	public boolean getWearHat() {
	
		return wearingHat; 
	}
	/* Description: A getter method that retrieves the hair length of the character
	@param
	none
	@return
	String hairLength
	*/
	public String getHairLength() {
	
		return hairLength; 
	}
	/* Description: A getter method that retrieves the eyeColor of the character
	@param
	none
	@return
	String eyeColor
	*/
	public String getEyeColor() {
	
		return eyeColor;
	}
	/* Description: A getter method that retrieves the piercings of the character
	@param
	none
	@return
	boolean piercings
	*/
	public boolean getPiercings(){
		return piercings;
	}

}
