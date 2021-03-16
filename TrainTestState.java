import java.awt.Font;

public class TrainTestState extends State {
	private int setIndex;
	private WordSet wordSet;
	private UIText wordDisplay;
	
	public TrainTestState(StateID id, Handler handler, StateHandler stateHandler, Font font) {
		super(id, handler, stateHandler, font);
	}
	
	public void startTraining(WordSet wordSet) {
		this.wordSet = wordSet;

		setIndex = 0;
		wordDisplay = new UIText(20, 58, ID.UIText, font, "Vad är " + wordSet.wordsSE[setIndex] + " på tyska?");
		handler.uiElementList.add(wordDisplay);

	}

	public void input(String input) {
		
		if (input.equals(wordSet.wordsDE[setIndex])) {
			handler.uiElementList.add(new UIMessage(1000, 34, ID.UIMessage, 2000, handler, "Rätt"));
			setIndex++;
			
			if (setIndex == wordSet.setSize) {
				handler.uiElementList.add(new UIMessage(1000, 34, ID.UIMessage, 2000, handler, "Klar"));
				UI.state = StateID.menuState;
				handler.uiElementList.remove(wordDisplay);
				((MenuState)stateHandler.getObjectByID(StateID.menuState)).displayDirs();
				
			} else {
				wordDisplay.setText("Vad är " + wordSet.wordsSE[setIndex] + " på tyska?");
			}
			
		} else {
			handler.uiElementList.add(new UIMessage(1000, 34, ID.UIMessage, 2000, handler, "Fel"));
		}

	}
	
}
