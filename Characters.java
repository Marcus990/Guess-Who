
public class Characters {
	
	private String name; 
	private String eyeColor;
	private String hairLength;
	private boolean wearingHat;
	private boolean visibleTeeth;
	private boolean glasses;
	private boolean facialHair; 
	private boolean gender; 
	private boolean piercings; 
	private String skinTone;
	private String hairColor; 
	
	public void Character(String fname, String feyeColor, boolean fgender, String fskinTone, String fhairColor, boolean ffacialHair, boolean fglasses, boolean fvisibleTeeth, boolean fwearingHat, String fhairLength, boolean fpiercings) {
		
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
	public String getName() {
		return name; 
	}
	
	public boolean getGender() {
		
		return gender; 
	}
	public String getHairColor() {
		
		return hairColor; 
	}
	public String getSkinTone() {
	
		return skinTone; 
	}
	public boolean getFacialHair() {
	
		return facialHair; 
	}
	public boolean getGlasses() {
	
		return glasses;
	}
	public boolean getVisibleTeeth() {
	
		return visibleTeeth; 
	}
	public boolean getWearHat() {
	
		return wearingHat; 
	}
	public String getHairLength() {
	
		return hairLength; 
	}
	public String getEyeColor() {
	
		return eyeColor;
	}

}
