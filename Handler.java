import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<UIElement> uiElementList = new LinkedList<UIElement>();

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
}
