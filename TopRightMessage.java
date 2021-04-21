import java.awt.Graphics;

public class TopRightMessage extends UIElement {
	private int timeOnScreen;
	private Handler handler;
	private String text;
	private long startTime;

	public TopRightMessage(int x, int y, ID id, int timeOnScreen, Handler handler, String text) {
		super(x, y, id);
		this.timeOnScreen = timeOnScreen;
		this.handler = handler;
		this.text = text;

		startTime = System.currentTimeMillis();

	}

	public void tick() {
		long now = System.currentTimeMillis();
		if (now - startTime >= timeOnScreen) {
			handler.removeTopRighteMessage(this);
		}
	}

	public void render(Graphics g2d) {
		g2d.drawString(text, x, y);
	}

}
