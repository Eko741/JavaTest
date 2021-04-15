import java.awt.Font;

public class CreateTestState extends State {

	private int wordIndex;
	private int NOA; // numberOfAttributes
	private int attributeIndex;
	private static final int maxSetSize = 128;
	private int WL = 0; // wordLength
	private int WSAP; // Word start at pixel
	private final int Margin = 72;

	private String[][] wordLists;
	private String[] attributeList;

	private UIText[][] UIAttributeList;

	private boolean creatingAttributes;

	public CreateTestState(StateID id, Handler handler, StateHandler stateHandler, Font font) {
		super(id, handler, stateHandler, font);

	}

	public void createNewSet() {

		UI.state = StateID.createTestState;

		wordIndex = 0;
		attributeIndex = 0;

		WSAP = 72;
		NOA = 0;

		attributeList = new String[maxSetSize];
		wordLists = new String[maxSetSize][maxSetSize];

		UIAttributeList = new UIText[maxSetSize][maxSetSize];

		creatingAttributes = true;

		handler.uiElementList
				.add(new UIText(36, 72, ID.InstructionText, font, "Create attribute, to type in words type /w"));

	}

	public void input(String input) {
		if (input.charAt(0) == '/') {
			commandInput(input);
			return;
		}

		if (creatingAttributes) {
			createAttribute(input);
			return;
		}

		addWord(input);

	}

	private void commandInput(String commmand) {

		if (commmand.equals("/d")) {
			if (attributeIndex == 0) {
				WordSet wordSet = new WordSet(new String[wordIndex + 1][NOA]);
				
				for (int i = 0; i < NOA; i++) {
					for (int j = 0; j < wordIndex + 1; j++) {
						wordSet.wordSet[j][i] = wordLists[j][i];
					}
				}
				
				FileIO.saveWordData("tests/test.txt", wordSet);
				
				handler.addTopRightMessage("Save succsesful");
				
				stateHandler.changeState(StateID.menuState);
				
				handler.removeObject(handler.getObjectById(ID.InstructionText));
				
				
				
			} else {
				handler.addTopRightMessage("Not full list of words");

			}

			return;
		}
		if (commmand.equals("/w") && attributeIndex != 0 && creatingAttributes) {

			creatingAttributes = false;
			NOA = attributeIndex;
			attributeIndex = 0;

			handler.removeAllUIText();

			((UIText) handler.getObjectById(ID.InstructionText))
					.setText("Write word " + (wordIndex + 1) + " in attribute " + attributeList[attributeIndex] + ":");

			for (int i = 0; i < NOA; i++) {
				
				UIAttributeList[0][i] = new UIText(Margin, UI.HEIGHT - 36 * (NOA - i) - 48, ID.UIText, font,
						attributeList[i]);
				
				handler.uiElementList.add(UIAttributeList[0][i]);
				
				if (WSAP < TextIO.getStringWidth(attributeList[i] + Margin )) {
					WSAP = TextIO.getStringWidth(attributeList[i] + Margin);
					
				}
			}

			WSAP += 72 + 36;
		}

	}

	private void createAttribute(String attribute) {
		wordLists[0][attributeIndex] = attribute;
		attributeList[attributeIndex] = attribute;

		UIAttributeList[0][attributeIndex] = new UIText(Margin, 108 + 36 * attributeIndex, ID.UIText, font,
				"Attribute " + (attributeIndex + 1) + ": " + attributeList[attributeIndex]);

		handler.uiElementList.add(UIAttributeList[0][attributeIndex]);
		
		
		
		

		attributeIndex++;

	}

	private void addWord(String word) {
		wordLists[wordIndex + 1][attributeIndex] = word;

		IntAndString tempObject = TextIO.firstLettersOfWord(word, 41);

		if (WL < tempObject.number) {
			WL = tempObject.number;
		}

		UIAttributeList[wordIndex][attributeIndex] = new UIText(WSAP, UI.HEIGHT - 36 * (NOA - attributeIndex) - 48,
				ID.UIText, font, tempObject.text);
		handler.uiElementList.add(UIAttributeList[wordIndex][attributeIndex]);

		attributeIndex++;

		if (attributeIndex == NOA) {
			WSAP += WL + 12;
			WL = 0;
			attributeIndex = 0;
			wordIndex++;

		}

	}


	public void startState() {
		handler.removeAllUIText();
	}
}
