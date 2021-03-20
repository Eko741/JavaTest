import java.awt.FontMetrics;

public class TextIO {
	
public static FontMetrics metrics;

public static boolean tooShort; 

	public static IntAndString nameInProgress(String text, int lengthInPixels) {
		
		IntAndString lengthAndText = new IntAndString(0, "");
		
		int originalLength = 0;
		int charIndex = 0;

		
		
		
		while (true) {
			
			
		
			
			
			if(charIndex >= text.length()) {
				tooShort = true; 
				break;
			}
			
			originalLength += metrics.charWidth(text.charAt(charIndex));
			
			
			if(originalLength >= lengthInPixels) {
				tooShort = false;
				break;
			}
			charIndex++;
		}
		
		
		String substring = text.substring(0, charIndex);		
		
		
		
		
		if(!tooShort) {
		substring += "...";
		originalLength += 9;
		}
		
		lengthAndText.number = originalLength;
		lengthAndText.text = substring;	
		
	
		return lengthAndText;
		
	}
	public static int getStringWidth(String text) {
		return metrics.stringWidth(text);
	}
}
