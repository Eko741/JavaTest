import java.awt.Font;

public class TrainTestState extends State {

	private int attributeIndex;
	private int wordIndex;

	private int shownAttribute;
	private String[] attributeList;
	private String[] shownWords;
	private String[] attributesToGuess;
	private String[][] wordsToGuess;
	private WordSet wordSet;

	public boolean chooseAttribute;

	private UIText instructionText;
	private UIText[] UIAttributeList;

	public TrainTestState(StateID id, Handler handler, StateHandler stateHandler, Font font) {
		super(id, handler, stateHandler, font);
	}

	public void startTraining(WordSet wordSet) {

		attributeIndex = 0;
		wordIndex = 0;
		this.wordSet = wordSet;

		shownWords = new String[wordSet.NOW - 1];
		wordsToGuess = new String[wordSet.NOW][wordSet.NOA - 1];
		attributesToGuess = new String[wordSet.NOA - 1];

		attributeList = wordSet.wordSet[0];

		chooseAttribute = true;

		UIAttributeList = new UIText[wordSet.NOA];
		instructionText = new UIText(36, 72 + 12, ID.InstructionText, font, "Select which type to show");
		handler.addObject(instructionText);

		for (int i = 0; i < wordSet.NOA; i++) {

			UIAttributeList[i] = new UIText(36, 36 * (i + 3), ID.UIText, font, attributeList[i]);
			handler.addObject(UIAttributeList[i]);

		}

	}

	private void restartTraining() {

		attributeIndex = 0;
		wordIndex = 0;

		chooseAttribute = true;

		instructionText.setText("Select which type to show");

		for (int i = 0; i < wordSet.NOA; i++) {
			UIAttributeList[i].setText(attributeList[i]);

		}
	}

	public void input(String input) {
		if (chooseAttribute) {
			attributeChoice(input);
			return;
		}
		if (wordsToGuess[wordIndex][attributeIndex].equals(input)) {
			attributeIndex++;

			handler.addTopRightMessage("Rätt", 2000);
			UIAttributeList[attributeIndex].addText(input);

			if (attributeIndex == wordSet.NOA - 1) {
				attributeIndex = 0;
				wordIndex++;

				if (wordIndex == wordSet.NOW - 1) {
					handler.addTopRightMessage("Done", 2000);
					restartTraining();
					return;
				}
				UIAttributeList[0].keepXletters((attributeList[shownAttribute] + ": ").length());            

				for (int i = 0; i < wordSet.NOA - 1; i++) {
					UIAttributeList[i + 1].keepXletters((attributesToGuess[i] + ": ").length());                
				}

				UIAttributeList[0].addText(shownWords[wordIndex]);
			}
			return;
		}
		handler.addTopRightMessage("Fel", 2000);
	}

	public void attributeChoice(String input) {
		boolean attributeFound = false;
		for (int i = 0; i < wordSet.NOA; i++) {

			if (input.equals(attributeList[i])) {
				chooseAttribute = false;
				attributeFound = true;
				shownAttribute = i;

				for (int j = 1; j < wordSet.NOW; j++) {
					shownWords[j - 1] = wordSet.wordSet[j][i];
				}

			}

		}
		if (!attributeFound) {
			handler.addTopRightMessage("No such attribute", 2000);
			return;
		}
		boolean shownAttributePassed = false;
		
		for (int i = 0; i < wordSet.NOA; i++) {
			
			if (i == shownAttribute) {
				shownAttributePassed = true;
				i++;
				if(shownAttribute == wordSet.NOA - 1) {
					break;
				}
			}
			attributesToGuess[i - Utility.boolToInt(shownAttributePassed)] = attributeList[i];
			
			for (int j = 1; j < wordSet.NOW; j++) {
				
				
				wordsToGuess[j - 1][i - Utility.boolToInt(shownAttributePassed)] = wordSet.wordSet[j][i];

			}

		}
		instructionText.setText("Type the reamaining words");

		UIAttributeList[0].setText(attributeList[shownAttribute] + ": " + shownWords[wordIndex]);

		for (int i = 0; i < wordSet.NOA - 1; i++) {
			UIAttributeList[i + 1].setText(attributesToGuess[i] + ": ");
		}

	}

}
