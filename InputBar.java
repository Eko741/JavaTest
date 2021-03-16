import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class InputBar extends UIElement {

	private boolean writeable = true;

	private String text = "";

	private Font font;
	
	private int textLength;
	
	private Handler handler;
	
	public static FontMetrics metrics;

	public InputBar(int x, int y, ID id, Font font, Handler handler) {
		super(x, y, id);
		this.font = font;
		this.handler = handler;
	}

	public void tick() {

	}

	public void render(Graphics g2d) {

		g2d.setColor(new Color(255, 255, 255));

		g2d.setFont(font);

		g2d.drawString(text + "|", x, y);

		g2d.drawLine(x, y, UI.WIDTH, y);
	}
	
	public void smoothText(char letter) {
		
		text += letter;
		
		int charWidth = metrics.charWidth(letter);
		
		textLength += charWidth;
		
	
		
		handler.uiElementList.add( new UIBox (textLength + charWidth / 2, y, ID.UIBox, charWidth / 2, 24, handler));
		
		
	
		
	}
	
	public void smoothTextBack() {
		if(text.length() > 0) {
			StringBuffer tempString = new StringBuffer(text);
			char letter = tempString.charAt(text.length()- 1);
			text = tempString.deleteCharAt(text.length() - 1).toString();
			textLength -= metrics.charWidth(letter);

		}
	}
	public void setWriteable(boolean writeable) {
		this.writeable = writeable;
	}

	public boolean getWriteable() {
		return writeable;

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (writeable) {
			this.text = text;
		}

	}
	
	public int getTextLength() {
		return textLength;
	}


}
