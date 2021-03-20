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
					e.setKeyChar('Ü');
				} else {
					e.setKeyChar('ü');
				}

			} else if (e.isAltDown() && key == 83) {
				e.setKeyChar('ß');
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

		InputBar tempObject = (InputBar) handler.getObjectById(ID.InputBar);
		if (tempObject.getText().equals("")) {
			return;
		}
		if (tempObject.getText().charAt(0) == '!') { // ! command

			if (tempObject.getText().equals("!createTest")) {
				((CreateTestState) stateHandler.getObjectByID(StateID.createTestState)).createNewSet();
			}

			else if (tempObject.getText().equals("!b")) {

				if (UI.state == StateID.menuState) {
					((MenuState) stateHandler.getObjectByID(StateID.menuState)).displayDirs();

				} else if (UI.state == StateID.testState) {
					UI.state = StateID.menuState;

					((MenuState) stateHandler.getObjectByID(StateID.menuState)).displaySets();

				} else if (UI.state == StateID.createTestState) {

				}

			}
			else {
				handler.uiElementList.add(new UIMessage(800, 34, ID.UIMessage, 1000, handler, "No such command"));
				
			}
		}

		
		else {
			stateHandler.getObjectByID(UI.state).input(tempObject.getText());
		}

		tempObject.setText("");

	}

}
