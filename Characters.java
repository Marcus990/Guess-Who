
public class Characters {
	
	private String name; 
	private String eyeColor;
	private String hairLength;
	private boolean wearingHat;
	private boolean visibleTeeth;
	private boolean glasses;
	private boolean facialHair; 
	private boolean gender; 
	private String skinTone;
	private String hairColor; 
	
	public void Character(String fname, String feyeColor, String fhairLength, boolean fwearingHat, boolean fvisibleTeeth, boolean fglasses, boolean ffacialHair, String fskinTone, String fhairColor, Boolean fgender) {
		
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
