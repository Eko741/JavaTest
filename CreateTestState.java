import java.awt.Font;

public class CreateTestState extends State {

	private WordSet wordSet;

	private int wordIndex;
	private int attributeIndex;
	private static final int maxSetSize = 128;

	private String[][] wordLists;
	private String[] attributeList;
	
	private UIText[] UIAttributeList;
	
	private boolean creatingAttributes;
	private boolean creatingSet;

	public CreateTestState(StateID id, Handler handler, StateHandler stateHandler, Font font) {
		super(id, handler, stateHandler, font);

	}

	public void createNewSet() {

		UI.state = StateID.createTestState;
		handler.removeAllUIText();

		int maxSetSize = 128;
		wordIndex = 0;
		
		attributeList = new String[maxSetSize];
		
		UIAttributeList = new UIText[maxSetSize]; 
		
		creatingSet = true;
		creatingAttributes = true;
		
		handler.uiElementList.add(new UIText(36, 72,  ID.UIText, font, "Create attribute, to type in words type !w"));

	}

	public void input(String input) {
		if(input.charAt(0) == '!') {
			
			if (input.equals("!d")) {
				//saveWordData();
			}
			else if (input.equals("!w") && attributeIndex != 0 && creatingAttributes) {
				
				creatingAttributes = false;
				wordLists = new String[attributeIndex][];
			}
			
		}
		
		
		else if (creatingAttributes) {
			attributeList[attributeIndex] = input;
			UIAttributeList[attributeIndex] = new UIText(72, 108 + 36 * attributeIndex , ID.UIText, font, "Attribute " + (attributeIndex + 1) + ": "+  attributeList[attributeIndex] );
			handler.uiElementList.add(UIAttributeList[attributeIndex]);
			
			attributeIndex++;
			
		}
		
		
		
		
		else {
			
			
			
		}

	}

}
