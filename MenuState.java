import java.awt.Font;

public class MenuState extends State {
	
	private int NOS = 0;
	
	private String currentFilePath;
	private String[] fileList;
	
	public MenuState(StateID id, Handler handler, StateHandler stateHandler, Font font) {
		super(id, handler, stateHandler, font);
		fileList = FileIO.getTextFileList("tests");
	}
	
	public void input(String input) {

		try {
			int i = Integer.parseInt(input);
			if (i < NOS + 1 && handler.uiElementList.size() > 1) {

				UI.state = StateID.testState;
				handler.removeAllUIText();
				((TrainTestState)stateHandler.getObjectByID(StateID.testState)).startTraining(FileIO.loadTest(i, currentFilePath));
			}

		} catch (NumberFormatException nfe) {
			if (FileIO.fileExists("tests\\" + input + ".txt")) {
				currentFilePath = "tests\\" + input + ".txt";
				displaySets();
			} else {

				handler.uiElementList.add(new UIMessage(100, 34, ID.UIMessage, 1000, handler, "File not found"));
			}

		}
	}

	public void displayDirs() {
		handler.removeAllUIText();

		for (int i = 0; i < fileList.length; i++) {
			handler.uiElementList.add (new UIText(20, 58 + i * 24, ID.UIText, font, fileList[i]));
		}
	}

	public void displaySets() {
		handler.removeAllUIText();

		String[] setList = FileIO.getSets(currentFilePath);
		NOS = setList.length;
		for (int i = 0; i < setList.length; i++) {
			handler.addObject(new UIText(20, 34 + (i + 1) * 24, ID.UIText, font, setList[i]));
		}

	}


	
}
