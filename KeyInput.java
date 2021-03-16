import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private StateHandler stateHandler;
	private int keyPressedDown;

	public KeyInput(Handler handler, StateHandler stateHandler) {
		this.handler = handler;
		this.stateHandler = stateHandler;

	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if (key != 16) {
			if (keyPressedDown == 135 && key == 85) {
				if (e.isShiftDown()) {
					e.setKeyChar('�');
				} else {
					e.setKeyChar('�');
				}

			} else if (e.isAltDown() && key == 83) {
				e.setKeyChar('�');
			} else if (key == 10) {
				sendText();
			}
		}

		keyPressedDown = key;

		if (111 > key && key > 44 || key == 0 || key == 32 || key == 8) {

			if (key == 8) {

				((InputBar) handler.getObjectById(ID.InputBar)).smoothTextBack();

			} else {

				((InputBar) handler.getObjectById(ID.InputBar)).smoothText(e.getKeyChar());

			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (keyPressedDown == key) {
			keyPressedDown = 1;
		}
	}

	public void sendText() {

		UIElement tempObject = handler.getObjectById(ID.InputBar);

		if (((InputBar) tempObject).getText().equals("!createTest")) {
			((CreateTestState) stateHandler.getObjectByID(StateID.createTestState)).createNewSet();
		}

		else if (((InputBar) tempObject).getText().equals("!b")) {

			if (UI.state == StateID.menuState) {
				((MenuState) stateHandler.getObjectByID(StateID.menuState)).displayDirs();

			} else if (UI.state == StateID.testState) {
				UI.state = StateID.menuState;

				((MenuState) stateHandler.getObjectByID(StateID.menuState)).displaySets();

			} else if (UI.state == StateID.createTestState) {

			}

		}

		else {

			stateHandler.getObjectByID(UI.state).input(((InputBar) tempObject).getText());

		}

		((InputBar) tempObject).setText("");

	}

}
