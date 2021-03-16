import java.awt.Graphics;

public class UIMessage extends UIElement {
	private long timeOnScreen;
	private Handler handler;
	private long startTime;
	String text;

	public UIMessage(int x, int y, ID id, int timeOnScreen, Handler handler, String text) {
		super(x, y, id);
		this.timeOnScreen = timeOnScreen;
		this.handler = handler;
		this.text = text;
		
		startTime = System.currentTimeMillis();
		
	}

	public void tick() {
		long now = System.currentTimeMillis();
		if (now - startTime >= timeOnScreen) {
		handler.uiElementList.remove(this);
		}
	}

	public void render(Graphics g2d) {
		g2d.drawString(text, x, y);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	

}
