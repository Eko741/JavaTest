import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

public class UI extends Canvas implements Runnable {

	private static final long serialVersionUID = 8989069420389345117L;

	public static final int WIDTH = 1080, HEIGHT = WIDTH / 16 * 9;

	private Font standardFont;

	private Thread thread;
	private boolean running = false;

	private Handler handler;
	private StateHandler stateHandler;

	private UIText fpsCounter;

	

	public static StateID state = StateID.menuState;

	public UI() {

		handler = new Handler();
		stateHandler = new StateHandler();
		new Window(WIDTH, HEIGHT, "UI", this);

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void run() {

		// Creating custom font
		try {
			standardFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/rainyhearts.ttf")).deriveFont(24f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			ge.registerFont(standardFont);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		// Creating Objects

		fpsCounter = new UIText(0, HEIGHT - 40, ID.FPSCounter, standardFont, "0");

		handler.uiElementList.add(fpsCounter);

		stateHandler.stateList.add(new MenuState(StateID.menuState, handler, stateHandler, standardFont)); 	
		stateHandler.stateList.add(new TrainTestState(StateID.testState, handler, stateHandler, standardFont));
		stateHandler.stateList.add(new CreateTestState(StateID.createTestState, handler, stateHandler, standardFont)); 	
		
		((MenuState)stateHandler.getObjectByID(StateID.menuState)).displayDirs();

		handler.addObject(new InputBar(10, 36, ID.InputBar, standardFont, handler));

		this.addKeyListener(new KeyInput(handler, stateHandler));

		// Setup for UI loop

		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;

				fpsCounter.setText(Integer.toString(frames));

				frames = 0;
			}
		}
		stop();
	}

	private void tick() {

		handler.tick();

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);

			return;
		}

		Graphics g = bs.getDrawGraphics();

		Graphics2D g2d = (Graphics2D) g;

		g2d.setFont(standardFont);
	
		InputBar.metrics = g2d.getFontMetrics(standardFont);
		TextIO.metrics = g2d.getFontMetrics(standardFont);

		g2d.setColor(Color.black);

		g2d.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g2d);

		g2d.dispose();
		bs.show();

	}

	public static void main(String args[]) {
		new UI();
	}

}
