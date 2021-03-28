import java.awt.Font;

public class CreateTestState extends State {


	private int wordIndex;
	private int NOA; // numberOfAttributes
	private int attributeIndex;
	private static final int maxSetSize = 128;
	private int WL = 0; //wordLength
	private int WSAP; //Word start at pixel
	
	private String[][] wordLists;
	private String[] attributeList;

	private UIText[][] UIAttributeList;

	private boolean creatingAttributes;

	
	public CreateTestState(StateID id, Handler handler, StateHandler stateHandler, Font font) {
		super(id, handler, stateHandler, font);

	}

	public void createNewSet() {

		UI.state = StateID.createTestState;
		handler.removeAllUIText();

		wordIndex = 0;
		attributeIndex = 0;
		WSAP = 0;
		
		attributeList = new String[maxSetSize];
		wordLists = new String[maxSetSize][maxSetSize];

		UIAttributeList = new UIText[maxSetSize][maxSetSize];

	
		creatingAttributes = true;
		

		handler.uiElementList.add(new UIText(36, 72, ID.InstructionText, font, "Create attribute, to type in words type /w"));

	}

	public void input(String input) {
		if (input.charAt(0) == '/') {

			if (input.equals("/d")) {
				if (attributeIndex == 0) {
					for(int i = 0; i < NOA; i ++) {
						
					}
					
				}else {
					handler.addObject(new UIMessage(800, 34, ID.UIMessage, 1000, handler, "Not full list of words"));
					
				}
				
				
				// saveWordData();
			} else if (input.equals("/w") && attributeIndex != 0 && creatingAttributes) {

				creatingAttributes = false;
				NOA = attributeIndex;
				attributeIndex = 0;
				
				handler.removeAllUIText();
				
				((UIText) handler.getObjectById(ID.InstructionText)).setText("Write word " + (wordIndex + 1) + " in attribute " + attributeList[attributeIndex] + ":" );

				for(int i = 0; i < NOA; i++) {
					UIAttributeList[0][i] = new UIText(72, UI.HEIGHT - 36 * (NOA - i)  - 48, ID.UIText, font, attributeList[i]);
					handler.uiElementList.add(UIAttributeList[0][i]);
					if (WSAP > TextIO.getStringWidth(attributeList[i])){
						WSAP = TextIO.getStringWidth(attributeList[i]);						
					}
				}
				
			WSAP += 72 + 36;
			}

		}

		else if (creatingAttributes) {
			attributeList[attributeIndex] = input;
			UIAttributeList[0][attributeIndex] = new UIText(72, 108 + 36 * attributeIndex, ID.UIText, font,
					"Attribute " + (attributeIndex + 1) + ": " + attributeList[attributeIndex]);
			handler.uiElementList.add(UIAttributeList[0][attributeIndex]);

			attributeIndex++;

		} else {
			
			wordLists[attributeIndex][wordIndex] = input;
			
			
			
			IntAndString tempObject = TextIO.nameInProgress(input, 41);
			
			if (WL < tempObject.number) {
			WL = tempObject.number;
			}
			
			
			
			
			
			UIAttributeList[wordIndex][attributeIndex] = new UIText(WSAP,  UI.HEIGHT - 36 * (NOA - attributeIndex)  - 48, ID.UIText, font, tempObject.text);
			handler.uiElementList.add(UIAttributeList[wordIndex][attributeIndex]);
			
			attributeIndex++;
			
			if(attributeIndex == NOA) {
				WSAP += WL + 12;
				WL = 0;
				attributeIndex = 0;
				wordIndex++;
				
			}
	
		}

	}

}
