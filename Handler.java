import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<UIElement> uiElementList = new LinkedList<UIElement>(); 
	int numberOfMessages;
	
	public void tick() {
		for (int i = 0; i < uiElementList.size(); i++) {
			UIElement tempObject = uiElementList.get(i);

			tempObject.tick();
		}
	}

	public void render(Graphics g2d) {
		for (int i = 0; i < uiElementList.size(); i++) {
			UIElement tempObject = uiElementList.get(i);

			tempObject.render(g2d);

		}
	}

	public void addObject(UIElement object) {
		this.uiElementList.add(object);
	}

	public void removeObject(UIElement object) {

		this.uiElementList.remove(object);

	}

	public void removeAllUIText() {
		int listSize = uiElementList.size();

		for (int i = 0, j = 0; i < listSize; i++) {

			UIElement tempObject = uiElementList.get(i - j);

			if (tempObject.getId() == ID.UIText) {
				removeObject(tempObject);
				j++;

			}

		}
	}
	
	public UIElement getObjectById(ID id) {
		
		for (int i = 0; i < uiElementList.size(); i++) {
			UIElement tempObject = uiElementList.get(i);
			if (tempObject.id == id) {
				return tempObject;
			}
		}
		System.out.println("Object Not Found");
		return null;
		
	}
	
	public void removeTopRighteMessage(UIElement object) {
		numberOfMessages--;
		removeObject(object);
		for(int i = 0; uiElementList.size() > i; i++) {
			UIElement tempObject = uiElementList.get(i);
			if (tempObject instanceof TopRightMessage) {
				tempObject = ((TopRightMessage)tempObject);
				tempObject.setY(tempObject.getY() - 36);
			}
		}
	}
	
	public void addTopRightMessage(String text, int time) {
		
		numberOfMessages++;
		this.uiElementList.add((new TopRightMessage(UI.WIDTH - TextIO.getStringWidth(text) - 24, 36 * numberOfMessages, ID.UIMessage, time, this, text)));
		
	}
	public void addTopRightMessage(String text) {
		numberOfMessages++;
		this.uiElementList.add((new TopRightMessage(UI.WIDTH - TextIO.getStringWidth(text) - 24, 36 * numberOfMessages, ID.UIMessage, 1000, this, text)));
	}
	
}
