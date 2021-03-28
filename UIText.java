import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UIText extends UIElement {
	private String text;
	private Font font;

	public UIText(int x, int y, ID id, Font font, String text) {
		super(x, y, id);
		this.text = text;
		this.font = font;

	}

	public void tick() {

	}

	public void render(Graphics g2d) {

		g2d.setColor(new Color(255, 255, 255));

		g2d.drawString(text, x, y);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	public void addText(String text) {
		this.text = this.text + text;
	}
	public void keepXletters(int lettersToKeep) {
		text = text.substring(0, lettersToKeep);
	}

}
