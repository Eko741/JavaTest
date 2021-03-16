import java.awt.Font;

public class CreateTestState extends State {

	private WordSet wordSet;
	private UIText textDisplay1;
	private UIText textDisplay2;

	private int wordIndex;
	private static final int maxSetSize = 128;

	private String[] CWLSE; // currentWListSE
	private String[] CWLDE; // currentWListDE
	private String[][] wordLists;

	private boolean creatingSet;

	public CreateTestState(StateID id, Handler handler, StateHandler stateHandler, Font font) {
		super(id, handler, stateHandler, font);

	}

	public void createNewSet() {

		UI.state = StateID.createTestState;
		handler.removeAllUIText();

		int maxSetSize = 128;
		wordIndex = 0;

		CWLSE = new String[maxSetSize];
		CWLDE = new String[maxSetSize];
		creatingSet = true;

		textDisplay1 = new UIText(30, 60, ID.UIText, font, "");
		textDisplay2 = new UIText(30, 108, ID.UIText, font, "");
		
		handler.uiElementList.add(textDisplay1);
		handler.uiElementList.add(textDisplay2);

		textDisplay1.setText("Swedish word " + (wordIndex + 1) + ":");
		textDisplay2.setText("German word " + (wordIndex + 1) + ":");

	}

	public void input(String input) {
		if (input.equals("!d")) {
			//saveWordData();
		}else {
			CWLSE[wordIndex] = input;
			textDisplay1.setText("Swedish Word" + (wordIndex + 1) +  input);
			
			
			
		}

	}

}
