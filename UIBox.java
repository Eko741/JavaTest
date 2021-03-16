import java.awt.Color;
import java.awt.Graphics;

public class UIBox extends UIElement {
	private int x;
	private int y;
	private int width;
	private int heigth;
	private long startTime;
	private Handler handler;

	public UIBox(int x, int y, ID id, int width, int heigth, Handler handler) {
		
		super(x, y, id);
		this.width = width;
		this.heigth = heigth;
		this.handler = handler;
		this.x = x;
		this.y = y;

		startTime = System.currentTimeMillis();

	}

	public void tick() {
		long now = System.currentTimeMillis();
		if (now - startTime >= 50) {
			handler.uiElementList.remove(this);
		}

	}

	public void render(Graphics g2d) {
		g2d.setColor(Color.black);
		
		g2d.fillRect(x, y - 24, width, heigth);
	}

}