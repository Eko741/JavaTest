import java.awt.Graphics;

public abstract class UIElement {

	protected int x, y;
	protected ID id;

	public UIElement(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();

	public abstract void render(Graphics g2d);

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;

	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;

	}

	public void setId(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;

	}
}
